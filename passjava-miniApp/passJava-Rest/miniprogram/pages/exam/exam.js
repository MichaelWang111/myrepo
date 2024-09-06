// pages/answer/index.js
const app = getApp()
const timeUtil = require('../../utils/timeUtil')
const mockFifthSimulatedExam = require('../../mockFifthSimulatedExam');
const mockSixthSimulatedExam = require('../../mockSixthSimulatedExam');
const mockFirstSimulatedExam = require('../../mockFirstSimulatedExam');
const mockDailyExam = require('../../mockDailyExam');
const emptyChar = '-'
const displayExplainText = '解释'
const hiddenExplainText = '隐藏'

Page({
  data: {
    examQuestionsList: [], // 题目列表
    memberAnswers: [], //作答的详细信息
    choosedAnswers: [], //作答的简要信息，只有答案，如 A B B C D
    detailRightAnswers: [],
    rightAnswers: [],
    lastedAnswers: '',
    isDisplayExplain: false,
    explainBtnText: displayExplainText,
    isRightAnswer: false,
    belongs: '',
    isLoading: true,
    rightAnswerCount: 0,
    wrongAnswerCount: 0,
    notFinishedAnswerCount: 0,
    accuracyRate: 0,
    result: {}, //题目
    total: 0, //题目总总数
    number: 1, //第几题
    currentAnswer: '', //单选选中的答案
    isDisplayAnswerCard: false, //弹出层,
    clock: ["00:00:00"],
    currentTime: ''
  },

  onLoad(e) {
    let that = this
    let belongs = {
      type: e.type,
      value: e.value
    }
    let title = e.title
    wx.setNavigationBarTitle({
      title: title,
      success: function (res) { },
      fail: function (res) { },
      complete: function (res) { },
    })
    this.setData({
      belongs: belongs,
      currentTime: Date.now() / 1000
    });
    this.getExamQuestionsList()
    // 修改
    //this.getMyAnswers()
    this.setData({
        isLoading: false
    })
    
  },

  onReady(res) {
    var interval = setInterval(function () {
      //将时间传如 调用
      let currentTime = this.data.currentTime; //时间戳
      var clock = timeUtil.calculateUsedTime(currentTime);

      this.setData({//正常倒计时        
        clock: clock
      });
    }.bind(this), 1000);
  },

  getExamQuestionList: function (type) {
    restService.get(config.service.getExamQuestionList).then((res) => {
      if (res && res.statusCode == 200) {
        const data = JSON.parse(res.data)
        if (data.code == 401) {
            util.logAndTips("getExamQuestionList:", "getExamQuestionList:", data.msg)
            return;
        }
        const javaQuestions = JSON.parse(res.data).page.list
        this.setData({
          javaQuestions: javaQuestions,
          javaQuestionList: javaQuestions,
          isLoading: false,
        })
      } else {
        util.logAndTips("getExamQuestionList:", "getExamQuestionList:", res)
      }
    }).catch((res) => {
      util.logAndTips("getExamQuestionList:", "getExamQuestionList:", res)
    })
  },


  getExamQuestionsList: function () {

    let examQuestionsList = []
    let number = 1
    // 1.查询模拟考试题目
    if (this.data.belongs.type == 'simulated') {
      
      switch (this.data.belongs.value) {
        case '5': 
          examQuestionsList = mockFifthSimulatedExam.fifthSimulatedExam.items;
          break;
        case '6':
          examQuestionsList = mockSixthSimulatedExam.sixthSimulatedExam.items;
          break;
        case '1':
          examQuestionsList = mockFirstSimulatedExam.firstSimulatedExam.items;
          break;
        default :
          examQuestionsList = []
      }
    } else if (this.data.belongs.type == 'daily') {
      examQuestionsList = mockDailyExam.mockDailyExam.items;
    }
    if (examQuestionsList.length == 0 ) {
      this.setData({
        examQuestionsList: examQuestionsList,
      })
      return;
    }
    let detailRightAnswers = []
    let rightAnswers = []
    for (let question of examQuestionsList) {
      detailRightAnswers.push({
        rightAnswer: question.rightAnswer,
        staticNumber: question.staticNumber,
        questionId: question._id,
      })
      let rightAnswersStr = question.rightAnswer ? question.rightAnswer : emptyChar
      rightAnswers = rightAnswers + ' ' + rightAnswersStr
    }

    this.setData({
      examQuestionsList: examQuestionsList,
      detailRightAnswers: detailRightAnswers,
      rightAnswers: rightAnswers,
      total: examQuestionsList.length,
      number: number,
    })
    this.setCurrentQuestion(number)
    return

    
  },

  recoverAnswer() {
    let examQuestionsList = this.data.examQuestionsList
    let memberAnswers = this.data.memberAnswers
    for (let i = 0; i < memberAnswers.length; i++) {
      let answerList = []
      let choose = memberAnswers[i].choose
      this.data.examQuestionsList[i].choose = choose
      for (let answer of this.data.examQuestionsList[i].answerList) {
        answer.checked = false
        if (choose == answer.key) {
          answer.checked = true
        }
        answerList.push(answer)
      }
      this.data.examQuestionsList[i].answerList = answerList
    }
  },

  //清空缓存的答案
  clearCacheAnswerClick: function (e) {
    let that = this
    wx.showModal({
      title: '提示',
      content: '确认清空所有答案，并开始重新作答吗？',
      success(res) {
        if (res.confirm) {
          that.clearCacheAnswer()
        } else if (res.cancel) {
          console.log('用户点击取消')
        }
      }
    })
   
  },

  clearCacheAnswer() {
    let examQuestionsList = this.data.examQuestionsList
    for (let i = 0; i < examQuestionsList.length; i++) {
      let answerList = []
      this.data.examQuestionsList[i].choose = ''
      for (let answer of this.data.examQuestionsList[i].answerList) {
        answer.checked = false
        answerList.push(answer)
      }
      this.data.examQuestionsList[i].answerList = answerList
    }
    let questionInfo = this.data.examQuestionsList[this.data.number - 1]
    this.setData({
      questionInfo: questionInfo,
      memberAnswers: [],
      rightAnswerCount: 0,
      wrongAnswerCount: 0,
      notFinishedAnswerCount: this.data.examQuestionsList.length,
      accuracyRate: 0,
      clock: ["00:00:00"],
    })
  },

  getMyAnswers: function () {
    wx.cloud.callFunction({
      // 云函数名称
      name: 'exam',
      data: {
        action: 'getMyAnswers',
        belongs: this.data.belongs,
        memberId: app.globalData.memberId
      },
    }).then(res => {
      if (res.result == null) {
        this.setData({
          isLoading: false
        })
        return
      }
      
      let rightAnswerCount = res.result.rightAnswerCount ? res.result.rightAnswerCount : 0
      let wrongAnswerCount = res.result.wrongAnswerCount ? res.result.wrongAnswerCount : 0
      let notFinishedAnswerCount = res.result.notFinishedAnswerCount ? res.result.notFinishedAnswerCount : 0
      let accuracyRate = (rightAnswerCount + wrongAnswerCount) > 0 ? (rightAnswerCount / (rightAnswerCount + wrongAnswerCount) * 100).toFixed(2) : '0'
      this.setData({
        lastedAnswers: res.result.choosedAnswers,
        memberAnswers: res.result.memberAnswers,
        rightAnswerCount: rightAnswerCount,
        wrongAnswerCount: wrongAnswerCount,
        notFinishedAnswerCount: notFinishedAnswerCount,
        accuracyRate: accuracyRate,
      })

      this.recoverAnswer()
      this.setData({
        isLoading: false
      })
    }).catch(console.error)
  },

  submitAnswer: function (e) {
    let choosedAnswers = []
    let questionList = this.data.examQuestionsList
    for (let question of questionList) {
      let chooseStr = question.choose ? question.choose : emptyChar
      choosedAnswers = choosedAnswers + ' ' + chooseStr
    }
    this.setData({
      choosedAnswers: choosedAnswers,
    })
    wx.showLoading({
      title: "保存中，请稍等~",
      mask: true
    })
    let that = this
    setTimeout(function () {
      that.submit()
    }, 1000)
  },

  submit() {
  wx.cloud.callFunction({
      // 云函数名称
      name: 'exam',
      data: {
        action: 'submitAnswers',
        belongs: this.data.belongs,
        memberAnswers: this.data.memberAnswers,
        choosedAnswers: this.data.choosedAnswers,
        rightAnswerCount: this.data.rightAnswerCount,
        wrongAnswerCount: this.data.wrongAnswerCount,
        notFinishedAnswerCount: this.data.notFinishedAnswerCount,
        accuracyRate: (this.data.rightAnswerCount + this.data.wrongAnswerCount) > 0 ? this.data.rightAnswerCount / (this.data.rightAnswerCount + this.data.wrongAnswerCount) : 0,
        memberId: app.globalData.memberId,
        nickName: app.globalData.nickName
      },
    }).then(res => {
      wx.hideLoading()

      if (res.errMsg == "cloud.callFunction:ok" && res.result.errMsg == "collection.add:ok") {
        wx.showToast({
          title: "保存成功哦~",
          duration: 2000,
        })
      }

      else if (res.errMsg == "cloud.callFunction:ok" && res.result.stats.updated == 1) {
          wx.showToast({
          title: "保存成功",
          duration: 2000,
        })
      }

      else {
        wx.showToast({
          icon: 'none',
          title: "保存失败",
          duration: 2000,
          mask: true
        })
      }

    }).catch(console.error)
  },

  setCurrentQuestion(number) {
    let questionInfo = this.data.examQuestionsList[number-1]
    let currentQuestionBelongs = questionInfo.belongs
    this.setData({
      questionInfo: questionInfo,
      number: number,
      currentQuestionBelongs: currentQuestionBelongs,
      isDisplayExplain: false,
      explainBtnText : displayExplainText
    })
  },

  selectAnswer: function (e) {
    //将选项check状态改为true
    this.data.examQuestionsList[this.data.number-1].choose = e.detail.value
    let answerList = []
    for (let answer of this.data.examQuestionsList[this.data.number-1].answerList) {
      answer.checked = false
      if (e.detail.value == answer.key) {
        answer.checked = true
      }
      answerList.push(answer)
    }

    this.data.examQuestionsList[this.data.number-1].answerList = answerList

    let choosedAnswers = []
    let questionList = this.data.examQuestionsList
    let memberAnswers = []
    let answerStatus = ''
    let rightAnswerCount = 0
    let wrongAnswerCount = 0
    let notFinishedAnswerCount = 0
    for (let question of questionList) {
      if (question.choose != '' && question.choose != '-' && question.choose === question.rightAnswer) {
        rightAnswerCount += 1
        answerStatus = 'right'
      } else if (question.choose != '' 
                  && question.choose != '-' 
                  && question.choose != undefined 
                  && question.rightAnswer != '' 
                  && question.choose != question.rightAnswer) {
        wrongAnswerCount += 1
        answerStatus = 'wrong'
      } else if(question.choose == '' || question.choose == undefined) {
        notFinishedAnswerCount += 1
        answerStatus = 'notFinished'
      }

      memberAnswers.push({
        choose: question.choose,
        staticNumber: question.staticNumber,
        questionId: question._id,
        rightAnswer: question.rightAnswer,
        answerStatus: answerStatus
      })
      let chooseStr = question.choose ? question.choose : emptyChar
      choosedAnswers = choosedAnswers + ' ' + chooseStr
    }
    
    let accuracyRate = (rightAnswerCount + wrongAnswerCount) > 0 ? (rightAnswerCount / (rightAnswerCount + wrongAnswerCount) * 100).toFixed(2) : 0
    // 当前选择的答案
    this.setData({
      currentAnswer: e.detail.value,
      choosedAnswers: choosedAnswers,
      memberAnswers: memberAnswers,
      rightAnswerCount: rightAnswerCount,
      wrongAnswerCount: wrongAnswerCount,
      notFinishedAnswerCount: notFinishedAnswerCount,
      accuracyRate: accuracyRate,
    });
  },

  //展示解释
  displayExplain() {
    let isDisplayExplain = this.data.isDisplayExplain
    let explainBtnText = isDisplayExplain ? displayExplainText : hiddenExplainText
    let isRightAnswer = false
    if (this.data.currentAnswer == this.data.questionInfo.rightAnswer) {
      isRightAnswer = true
    }

    this.setData({
      isDisplayExplain: !isDisplayExplain,
      explainBtnText: explainBtnText,
      isRightAnswer: isRightAnswer
    })
  },

  //翻页
  handlePageChange({ detail }) {
    if (this.data.currentAnswer == "" && (this.data.examQuestionsList[this.data.number - 1].choose == undefined || this.data.examQuestionsList[this.data.number - 1].choose == "")) {
      /*
      wx.showToast({
        title: '请选择答案',
        duration: 1500,
        image: '../../images/answer/warning.png'
      })
      return;
      */
    }

    const action = detail.type;
    //上下一题
    let number = 1
    if (action === 'next') {
      number = this.data.number + 1
    } else if (action === 'prev') {
      number = this.data.number - 1
    }
    this.setData({
      number: number,
      currentAnswer: ""
    });
    this.setCurrentQuestion(this.data.number)
  },
   
  gotoSelectedQuestion: function (e) {
    let number = e.currentTarget.dataset.item.staticNumber
    this.setCurrentQuestion(number)
    this.setData({
      isDisplayAnswerCard: false
    })
  },
  
  hiddenMask: function (e) {
    this.setData({
      isDisplayAnswerCard: false
    })
  },

  //重头开始答题
  fromBegin(menu) {
    wx.u.getMenuById(menu).then(res1 => {
      var time = res1.result.time
      var questionNum = res1.result.questionNum
      //获取题目
      wx.u.getQuestions(menu, questionNum).then(res => {
        console.log(res.result);
        this.setData({
          loading: false,
          result: res.result,
          total: res.result.length,
        })

        this.Countdown(parseInt(time) * 60000)
        this.setThisData(0)
      })
    });
  },
 
  //弹出统计下拉层
  displayAnswerCard() {
    this.setData({
      isDisplayAnswerCard: !this.data.isDisplayAnswerCard
    })
  },
  //关闭统计下拉层
  actionCancel() {
    this.setData({
      isDisplayAnswerCard: false
    })
  },
  stop() {
    this.Countdown.stop()
  },
  start() {
    this.Countdown.start()
  },
  update(time) {
    this.Countdown.update(+(new Date) + parseInt(time))
  }
})
