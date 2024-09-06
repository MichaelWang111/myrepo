pipeline {  
    agent any  
    parameters {  
        choice(name: 'ENVIRONMENT', choices: ['passjava研发环境'], description: '请选择环境')

        gitParameter(
            name: 'GIT_BRANCH',
            type: 'PT_BRANCH',
            branchFilter: 'origin/(.*)',
            defaultValue: 'develop',
            sortMode: 'ASCENDING_SMART',
            quickFilterEnabled: false,
            selectedValue: 'DEFAULT',
            tagFilter: '*',
            description: '请选择部署分支')

        extendedChoice( 
            defaultValue: 'All', 
            description: 'The services to deploy', 
            multiSelectDelimiter: ',', 
            name: 'SERVICE_NAME', 
            quoteValue: false, 
            saveJSONParameterToFile: false, 
            type: 'PT_CHECKBOX', 
            value:'All,passjava-auth,passjava-content,passjava-member,passjava-question,passjava-channel,passjava-study,passjava-search,passjava-thirdparty,passjava-gateway',
            visibleItemCount: 10
        )
    }
    // 定义 GitLab 仓库的 URL 和分支  
    environment {
        // Git 仓库地址
        GIT_URL = 'https://gitcode.net/jackson0714/passjava.git'
        // Jenkins 后台配置的凭证 ID  
        CREDENTIALS_ID = '2dcbf8b2-6314-4f43-8f52-79ec802f6d14'
        SSH_URL = getSSHUrl(params.ENVIRONMENT)
        // 指定运行 jar 包时激活哪个配置文件，比如 application-dev.yml  
        PROFILE_ENV = getProfileEnv(params.ENVIRONMENT)
        // 备份 JAR 包的时候会根据这个名字来匹配 apps 目录下的 JAR 包
        ALL_SERVICE = "passjava-auth,passjava-content,passjava-member,passjava-question,passjava-channel,passjava-study,passjava-search,passjava-thirdparty,passjava-gateway"
        // 清理一小时之前的目录，mtime 指定天数，mmin 指定小时。
        CLEAN_JAR_DAY = 1
        // JAR 包执行的目录
        UPLOAD_FILE_PATH = "/nfs-data/passjava/apps"
    }  
     stages {  
        stage('获取最新代码') {  
            steps {  
                git credentialsId: "${CREDENTIALS_ID}", branch: "${params.GIT_BRANCH}", url: "${GIT_URL}"
                script {  
                    // 使用 params 对象获取参数值  
                    def branchName = params.GIT_BRANCH  
                    echo "Building branch: ${branchName}" 

                    def serviceNameList = getServiceNameList(params.SERVICE_NAME)
                    echo "Building service array: ${serviceNameList}"

                    // 使用 git 插件检出仓库的特定分支  
                    checkout([  
                        $class: 'GitSCM',  
                        branches: [[name: "${branchName}"]],  
                        doGenerateSubmoduleConfigurations: false,  
                        extensions: [],  
                        submoduleCfg: [],  
                        userRemoteConfigs: [[  
                            credentialsId: "${CREDENTIALS_ID}", // 在 Jenkins 凭据中定义的 GitLab 凭据 ID  
                            url: "${GIT_URL}"  
                        ]]  
                    ])  
                }  
            }  
        }
        stage('编译代码') {  
            steps {  
                
                script {  
                    echo "--------------- 步骤：开始编译 --------------- "
                    sh 'mvn clean package -U'
                    echo "--------------- 步骤：编译完成 --------------- "
                }
            }  
        }
        stage('上传JAR包') {  
            steps {  
                script {
                    echo "--------------- 步骤：开始上传 JAR 包到临时目录 --------------- "
                    echo "开始上传 JAR 包到临时目录"
                    // 获取服务名称列表
                    def serviceNameList = getServiceNameList(params.SERVICE_NAME)

                    // 上传路径：jenkins 全局 ssh server 配置中的路径 + uploadFolder 路径
                    def uploadFolder = "apps/build/${env.BUILD_NUMBER}/"
                    echo "uploadFolder: ${uploadFolder}"
                    
                    // 获取 maven 打包后的 JAR 包相对路径和相对路径前缀
                    result = getJarPackagePath(serviceNameList)
                    def filesToCopy = result[0]
                    def removePrefixs = result[1]

                    echo "打印所有需要上传的 JAR 包"
                    filesToCopy.eachWithIndex { file, index ->
                        echo "第 ${index + 1} 个文件，${file}"
                    }
                    
                    sshPublisher(
                        failOnError: true,
                        publishers: [
                        sshPublisherDesc(
                            configName: "${SSH_URL}",
                            verbose: true,
                            transfers: [
                                sshTransfer(
                                    execCommand: "mkdir -p ${env.UPLOAD_FILE_PATH}/build/${env.BUILD_NUMBER}/",
                                    execTimeout: 120000
                                )
                            ]
                        )
                    ])
                        
                    // 使用 SSHPublisher 将文件复制到远程服务器的指定目录
                    echo "--------------- 开始上传 JAR 包 ---------------"
                    filesToCopy.eachWithIndex { file, index -> 
                        echo "开始上传第 ${index + 1} 个 JAR 包， ${file} ..."
                        sshPublisher(
                            failOnError: true,
                            publishers: [
                                sshPublisherDesc(
                                    configName: "${SSH_URL}",
                                    verbose: true,
                                    transfers: [
                                        sshTransfer(
                                            execCommand: '', 
                                            execTimeout: 120000, 
                                            flatten: false, 
                                            makeEmptyDirs: false, 
                                            noDefaultExcludes: false, 
                                            patternSeparator: '[, ]+', 
                                            remoteDirectory: "${uploadFolder}", 
                                            remoteDirectorySDF: false, 
                                            removePrefix: removePrefixs[index], 
                                            sourceFiles: file
                                        )
                                    ]
                                )
                            ]
                        )
                        echo "完成上传 JAR 包 ${file}"
                    }
                    
                    echo "--------------- 步骤：完成上传 JAR 包 --------------- "
                }
            }  
        }
        stage('备份JAR包') {
            steps {
                echo "--------------- 步骤：开始备份 JAR 文件 --------------- "
                script {
                    def timestamp = new Date().format("yyyyMMddHHmmss")
                    echo "本机当前时间为: ${timestamp}"
                    def bakFolder = "${env.BUILD_NUMBER}-${timestamp}"
                    echo "bakFolder: ${bakFolder}"
                    // 获取服务名称列表
                    def serviceNameList = getServiceNameList(params.SERVICE_NAME)
                    def commands = [
                        'passjava-auth': 'passjava-auth',
                        'passjava-content': 'passjava-content',
                        'passjava-member': 'passjava-member',
                        'passjava-question': 'passjava-question',
                        'passjava-channel': 'passjava-channel',
                        'passjava-study': 'passjava-study',
                        'passjava-search': 'passjava-search',
                        'passjava-thirdparty': 'passjava-thirdparty',
                        'passjava-gateway': 'passjava-gateway'
                    ]
                    
                    echo "----- 开始备份 JAR 包-----"
                    serviceNameList.eachWithIndex { service, index -> 
                        echo "开始备份第 ${index + 1} 个 JAR 包，${env.UPLOAD_FILE_PATH}/${commands[service]}.jar ..."
                        sshPublisher(
                            failOnError: true,
                            publishers: [
                                sshPublisherDesc(
                                    configName: "${SSH_URL}",
                                    verbose: true,
                                    transfers: [
                                        sshTransfer(
                                            execCommand: "mkdir -p ${env.UPLOAD_FILE_PATH}/bak/${bakFolder} && cd ${env.UPLOAD_FILE_PATH} && cp -f ${commands[service]}.jar ./bak/${bakFolder}/${commands[service]}.jar",
                                            execTimeout: 120000
                                        )
                                    ]
                                )
                            ]
                        )
                        
                        echo "----- 完成备份 JAR 包 -----"
                    }
                    
                }
                echo "----- 步骤：完成备份 JAR 包 -----"
            }
        }
        stage('更新 JAR 包') {  
            steps {  
                
                script {  
                    echo "--------------- 开始更新 JAR 包 --------------- "
                    def serviceName = params.SERVICE_NAME  
                    // 获取服务名称列表
                    def serviceNameList = getServiceNameList(params.SERVICE_NAME)

                    def commands = [
                        'passjava-auth': 'passjava-auth',
                        'passjava-content': 'passjava-content',
                        'passjava-member': 'passjava-member',
                        'passjava-question': 'passjava-question',
                        'passjava-channel': 'passjava-channel',
                        'passjava-study': 'passjava-study',
                        'passjava-search': 'passjava-search',
                        'passjava-thirdparty': 'passjava-thirdparty',
                        'passjava-gateway': 'passjava-gateway'
                    ]
                    
                    echo "----- 开始更新 JAR 包 -----"
                    serviceNameList.eachWithIndex { service, index -> 
                        echo "开始更新第 ${index + 1} 个 JAR 包，${env.UPLOAD_FILE_PATH}/build/${env.BUILD_NUMBER}/${commands[service]}.jar ..."
                        sshPublisher(
                            failOnError: true,
                            publishers: [
                                sshPublisherDesc(
                                    configName: "${SSH_URL}",
                                    verbose: true,
                                    transfers: [
                                        sshTransfer(
                                            execCommand: "cd ${env.UPLOAD_FILE_PATH} && cp -f ./build/${env.BUILD_NUMBER}/${commands[service]}.jar ${commands[service]}.jar",
                                            execTimeout: 120000
                                        )
                                    ]
                                )
                            ]
                        )
                        echo "----- 完成更新 JAR 包 -----"
                    }
                    
                    echo "--------------- 步骤：更新 JAR 包完成 --------------- "
                }
            }  
        }
        stage('重启服务') {  
            steps {  
                
                script {  
                    echo "--------------- 步骤：开始重启服务 --------------- "
                    def serviceName = params.SERVICE_NAME  
                    // 获取服务名称列表
                    def serviceNameList = getServiceNameList(params.SERVICE_NAME)
                    def commands = [
                        'passjava-auth': 'passjava-auth',
                        'passjava-content': 'passjava-content',
                        'passjava-member': 'passjava-member',
                        'passjava-question': 'passjava-question',
                        'passjava-channel': 'passjava-channel',
                        'passjava-study': 'passjava-study',
                        'passjava-search': 'passjava-search',
                        'passjava-thirdparty': 'passjava-thirdparty',
                        'passjava-gateway': 'passjava-gateway'
                    ]
                    
                    echo "----- 开始重启微服务 -----"
                    serviceNameList.eachWithIndex { service, index -> 
                        echo "开始重启第 ${index + 1} 个微服务，${service}，${commands[service]} ..."
                        echo "开始停止服务 ${service} ..."
                        try {
                            sshPublisher(
                                failOnError: false,
                                publishers: [
                                    sshPublisherDesc(
                                        configName: "${SSH_URL}",
                                        verbose: true,
                                        transfers: [
                                            sshTransfer(
                                                execCommand: "nohup sh /nfs-data/passjava/apps/shutdown_service.sh ${commands[service]} > ${env.UPLOAD_FILE_PATH}/shutdown_service.log 2>&1 &",
                                                execTimeout: 120000,
                                            )
                                        ]
                                    )
                                ]
                            )
                        } catch(Exception e) {
                            echo "catch exception"
                        }
                        echo "开始启动服务 ${service} ..."
                        sshPublisher(
                            failOnError: true,
                            publishers: [
                                sshPublisherDesc(
                                    configName: "${SSH_URL}",
                                    verbose: true,
                                    transfers: [
                                        sshTransfer(
                                            execCommand: "nohup java -jar -Xms128m -Xmx128m ${env.UPLOAD_FILE_PATH}/${commands[service]}.jar --spring.profiles.active=${PROFILE_ENV} > ${env.UPLOAD_FILE_PATH}/output.log 2>&1 &",
                                            execTimeout: 120000,
                                            execInPty: true
                                        )
                                    ]
                                )
                            ]
                        )
                        echo "----- 完成重启微服务 -----"
                    }
                    
                    echo "--------------- 步骤：重启微服务完成 --------------- "
                }
            }  
        }
        stage('清理代码') {  
            steps {  
                script {  
                    echo "--------------- 步骤：开始清理备份代码 --------------- "
                    echo "开始清理过期 ${CLEAN_JAR_DAY} 天的备份代码"
                    sshPublisher(
                        failOnError: true,
                        publishers: [
                        sshPublisherDesc(
                            configName: "${SSH_URL}",
                            verbose: true,
                            transfers: [
                                sshTransfer(
                                    execCommand: "find ${env.UPLOAD_FILE_PATH}/bak/ -mindepth 1 -maxdepth 1 -type d -mmin +${CLEAN_JAR_DAY} -print -exec rm -rf {} +",
                                    execTimeout: 120000
                                )
                            ]
                        )
                    ])

                    echo "开始清理过期 ${CLEAN_JAR_DAY} 天的打包代码"
                    sshPublisher(
                        failOnError: true,
                        publishers: [
                        sshPublisherDesc(
                            configName: "${SSH_URL}",
                            verbose: true,
                            transfers: [
                                sshTransfer(
                                    execCommand: "find ${env.UPLOAD_FILE_PATH}/build/ -mindepth 1 -maxdepth 1 -type d -mmin +${CLEAN_JAR_DAY} -print -exec rm -rf {} +",
                                    execTimeout: 120000
                                )
                            ]
                        )
                    ])
                    
                    echo "完成清理 JAR 包"
                    echo "--------------- 步骤：清理备份代码完成 --------------- "
                }
            }
        }
    }
//     post {
//         success {
//             script {
//                 echo "成功时通知触发构建者"
//                 emailext(
//                     subject: "'构建成功通知: ${env.JOB_NAME} - Build # ${env.BUILD_NUMBER}'",
//                     recipientProviders: [requestor()],
//                     body: '''${FILE,path="/home/jenkins/email-template/email.html"}''',
//                     to: 'jackson0585@163.com',
//                     mimeType: 'text/html'
//                 )
//             }
//         }
//         failure {
//             script {
//                 echo "失败时通知提交者"

//                 def result = generateChangesAndAuthors()
//                 def changes = result[0]
//                 def authors = result[1]
//                 echo "${changes}"
//                 echo "Authors: ${authors}"
                
//                 // 将收件人列表加入环境变量
//                 def toAddress = authors.join(',')
//                 echo "代码提交者邮箱：${toAddress}"

//                 // 如果没有找到提交者，则使用默认收件人
//                 if (toAddress == '') {
//                     toAddress = 'jackson0585@163.com'
//                 }

//                 // 发送失败通知邮件
//                 emailext(
//                     subject: "'构建失败通知: ${env.JOB_NAME} - Build # ${env.BUILD_NUMBER} - ${currentBuild.currentResult}'",
//                     recipientProviders: [requestor()],
//                     body: '''${FILE,path="/home/jenkins/email-template/email-failure.html"}''',
//                     to: "${toAddress}",
//                     mimeType: 'text/html'
//                 )
//             }
//         }
//     }
}

// 根据服务名列表获取所有 maven 打包完的路径
def getJarPackagePath(serviceNameList) {
    // 根据选择的参数构建文件路径列表
    def filesToCopy = []
    def removePrefixs = []
    serviceNameList.each { service -> 
        echo "需要部署的微服务： ${service}"
        switch(service) {
            case 'passjava-auth':
                filesToCopy.add("passjava-auth/target/passjava-auth.jar")
                removePrefixs.add("passjava-auth/target")

            case 'passjava-content':
                filesToCopy.add("passjava-content/target/passjava-content.jar")
                removePrefixs.add("passjava-content/target")
                break
            case 'passjava-member':
                filesToCopy.add("passjava-member/passjava-member-core/target/passjava-member.jar")
                removePrefixs.add("passjava-member/passjava-member-core/target")
                break
            case 'passjava-question':
                filesToCopy.add("passjava-question/target/passjava-question.jar")
                removePrefixs.add("passjava-question/target")
                break
            case 'passjava-channel':
                filesToCopy.add("passjava-channel/target/passjava-channel.jar")
                removePrefixs.add("passjava-channel/target")
                break
            case 'passjava-study':
                filesToCopy.add("passjava-study/target/passjava-study.jar")
                removePrefixs.add("passjava-study/target")
                break
            case 'passjava-search':
                filesToCopy.add("passjava-search/target/passjava-search.jar")
                removePrefixs.add("passjava-search/target")
                break
            case 'passjava-thirdparty':
                filesToCopy.add("passjava-thirdparty/target/passjava-thirdparty.jar")
                removePrefixs.add("passjava-thirdparty/target")
                break
            case 'passjava-gateway':
                filesToCopy.add("passjava-gateway/target/passjava-gateway.jar")
                removePrefixs.add("passjava-gateway/target")
                break
            case 'All':
                break

            default:
                echo "没有找到 ${service} 对应的 JAR 包"
                break
        }
    }
    
    return [filesToCopy, removePrefixs]
}


def generateChangesAndAuthors() {
    def changes = "Changes:\n"
    def authors = []
    build = currentBuild
    while(build != null && build.result != 'SUCCESS') {
        changes += "Changes for Build #${build.id}:\n"
        if(build.changeSets.isEmpty()) {
            changes += "  * No changes \n"
        }
        for (changeLog in build.changeSets) {
            for(entry in changeLog.items) {
                def timestamp = entry.timestamp
                def date = new Date(timestamp)
                def dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                def formattedDateTime = dateFormat.format(date)
                changes += "  * 提交时间: ${formattedDateTime}\n"
                def author = entry.author.fullName
                changes += "  * 提交者: [${author}]\n"
                def comment = entry.comment
                changes += "  * 提交注释: ${comment}"
                changes += "  * 提交文件列表: \n"
                for(file in entry.affectedFiles) {
                    changes += "   * ${file.path}\n"
                }
                def authorEmail = entry.authorEmail
                echo "authorEmail: ${authorEmail}"
                if (!authors.contains(authorEmail)) {
                    authors.add(authorEmail)
                }
            }
        }
        build = build.previousBuild
    }
    return [changes, authors]
}


def getSSHUrl(environment) {
    switch (environment) {
        case 'passjava研发环境':
            return 'wukong@192.168.145.129'
        case 'passjava测试环境':
            return ''
        case 'passjavas生产环境':
            return ''
        default:
            error "Unsupported environment: ${environment}"
    }
}

def getProfileEnv(environment) {
    switch (environment) {
        case 'passjava研发环境':
            return 'dev'
        case 'passjava测试环境':
            return 'uat'
        case 'passjavas生产环境':
            return 'prod'
        default:
            error "Unsupported environment: ${environment}"
    }
}
def getServiceNameList(serviceName) {
    echo "SERVICE_NAME parameter: ${serviceName}"  
    if(serviceName.contains('All')) {
        // 正则匹配移除字符串最开始 All 字符串
        serviceName = "${env.ALL_SERVICE}".replaceAll("All,", "")
    }
    echo "Building service: ${serviceName}"
    def serviceNameList = serviceName.split(',')

    return serviceNameList
}