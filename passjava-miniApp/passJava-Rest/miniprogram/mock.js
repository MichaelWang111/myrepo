const banner = { 
  "item": [
    {
      'isShow': true,
      "bannerUrl": "https://7465-test-0jlva-1254012214.tcb.qcloud.la/banner/pmp2.png?sign=4498f01db8b7349915d34352d98f0c83&t=1556897293", 
      "navigationType": "pmp", 
      "needRegister": true, 
      "displayOrder": 1 
    }]
}

const dailyQuestions = {
  "items": [
    {
      'id': 123,
      "imageUrl": "https://7465-test-0jlva-1254012214.tcb.qcloud.la/banner/pmp2.png?sign=4498f01db8b7349915d34352d98f0c83&t=1556897293",
      "title": "2019-05-08 练习题",
      "subTitle": "每天进步一点点",
      "pagePath": '/dailyQuestions/daily0508.html'
    }, {
      'id': 234,
      "imageUrl": "https://7465-test-0jlva-1254012214.tcb.qcloud.la/banner/pmp2.png?sign=4498f01db8b7349915d34352d98f0c83&t=1556897293",
      "title": "2019-05-09 练习题",
      "subTitle": "每天进步一点点",
      "pagePath": '/dailyQuestions/daily0509.html'
    }]
}

const releaseTimeLine = {
  "name": "面试突击",
  "description": "这款小程序可以帮助大家利用零碎时间刷题和复习。纯个人开发，请多多支持！",
  "img": "https://7465-test-0jlva-1254012214.tcb.qcloud.la/profile/banner1.png",
  "website": "博客园：www.jackson0714.cnblogs.com",
  "github": "GitHub：www.github.com/Jackson0714",
  "wechatService": "公众号：悟空聊架构",
  "wechat": "微信号：PassJava",
  "aboutMe": "公众号：悟空聊架构，蓝桥签约作者，InfoQ 签约作者。官网：www.passjava.cn",
  "timeLinelist": [
    {
      "date": "2022年05月1日",
      "version": "V1.0.0",
      "description": "Rest 版 1.0",
    },
    
  ]
}

const mock = {
  banner: banner,
  dailyQuestions: dailyQuestions,
  releaseTimeLine: releaseTimeLine
}

module.exports = mock
