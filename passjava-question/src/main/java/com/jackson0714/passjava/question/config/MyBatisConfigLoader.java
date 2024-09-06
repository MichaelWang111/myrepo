package com.jackson0714.passjava.question.config;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author 悟空聊架构
 * description TODO
 * @date 2023/6/4 01:16
 * site www.passjava.cn
 * github https://github.com/Jackson0714
 */

@Configuration
public class MyBatisConfigLoader {

    public static SqlSessionFactory loadMyBatisConfig() {
        try {
            // 加载 mybatis-config.xml 配置文件
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);

            // 构建 SqlSessionFactory
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

            return sqlSessionFactory;
        } catch (IOException e) {
            // 处理异常
            e.printStackTrace();
            return null;
        }
    }

}
