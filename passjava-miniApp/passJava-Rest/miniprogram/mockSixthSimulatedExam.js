const sixthSimulatedExam = {
  'items': [
    {
      "staticNumber": 1,
      "question": "1.项目经理收到一些相关方对项目利弊的矛盾看法。这会影响其他相关方对该项目如何使组织收益的支持和理解水平。项目经理应该如何调整相关方的意见并获得他们的积极参与？",
      "answerList": [{
        "key": "A",
        "value": "A.向相关方解释工作说明书（SOW）中概述的业务需求、产品范围描述和战略计划。"
      },
      {
        "key": "B",
        "value": "B.审查工作说明书和商业论证以识别关键相关方，然后立即召开会议以澄清商业价值。"
      },
      {
        "key": "C",
        "value": "C.立即与相关方开会，并敦促他们做决定。"
      },
      {
        "key": "D",
        "value": "D.将其登记在风险登记册中以记录从而减轻潜在风险。"
      }
      ],
      "rightAnswer": "B",
      "explain": "B 相关方问题，要管理相关方。相关方怀疑项目的价值，用项目的商业价值来说服他们",
      "belongs": {
        "type": "simulated",
        "value": "6"
      },
      "_id": "4435748e-758d-4c24-886e-3248200006001",
    },
    {
      "staticNumber": 2,
      "question": "2.在为相关方准备状态报告时，项目经理发现一个项目的成本偏差为负数，该项目10%的工作已完成，在与相关方开会前，项目经理应该怎么做？",
      "answerList": [{
        "key": "A",
        "value": "A.预测完工估算（EAC）"
      },
      {
        "key": "B",
        "value": "B.启动变更程序"
      },
      {
        "key": "C",
        "value": "C.搁置项目直到确定对应策略"
      },
      {
        "key": "D",
        "value": "D.使用应急储备"
      }
      ],
      "rightAnswer": "A",
      "explain": "A 向相关方发布的状态报告不光要有偏差，还要有预测，让相关方能判断情况",
      "belongs": {
        "type": "simulated",
        "value": "6"
      },
      "_id": "4435748e-758d-4c24-886e-3248200006002",
    },
    {
      "staticNumber": 3,
      "question": "3.项目经理识别到一个基于项目例外事项报告的项目基准主要偏差，项目经理下一步应该怎么做？",
      "answerList": [{
        "key": "A",
        "value": "A.赶工"
      },
      {
        "key": "B",
        "value": "B.与主要相关方联系"
      },
      {
        "key": "C",
        "value": "C.重新制定项目计划"
      },
      {
        "key": "D",
        "value": "D.与发起人谈判"
      }
      ],
      "rightAnswer": "B",
      "explain": "B 未知未知风险发生，且题干中强调该情况是应该汇报的，用排除法选B",
      "belongs": {
        "type": "simulated",
        "value": "6"
      },
      "_id": "4435748e-758d-4c24-886e-3248200006003",
    },
    {
      "staticNumber": 4,
      "question": "4.一家公司正在进行重组，虽然并不是所有新职位都是已知的，但是组织变更情况已经传达，项目经理应该怎么做？",
      "answerList": [{
        "key": "A",
        "value": "A.在风险管理计划中更新潜在的未来干系人变更。"
      },
      {
        "key": "B",
        "value": "B.在干系人登记册中更新已知的信息。"
      },
      {
        "key": "C",
        "value": "C.与关键干系人的期望保持一致。"
      },
      {
        "key": "D",
        "value": "D.通知所有干系人当前项目状态。"
      }
      ],
      "rightAnswer": "B",
      "explain": "B 项目的相关方发生改变，应该首先更新相关方登记册",
      "belongs": {
        "type": "simulated",
        "value": "6"
      },
      "_id": "4435748e-758d-4c24-886e-3248200006004",
    },
    {
      "staticNumber": 5,
      "question": "5.一个项目预算为6000万美元，预计需要24个月才能完成。12个月后，该项目完成了60%，并使用了3500万美元。那么预算和进度的状态如何？",
      "answerList": [{
        "key": "A",
        "value": "A.符合预算，并超前于进度"
      },
      {
        "key": "B",
        "value": "B.超出预算，并超前于进度"
      },
      {
        "key": "C",
        "value": "C.符合进度与预算"
      },
      {
        "key": "D",
        "value": "D.落后于进度，并超出预算"
      }
      ],
      "rightAnswer": "A",
      "explain": "A PV=3000万，EV=3600万，AC=3500万，进度提前，成本节约，没有正确选项，选一个最接近的",
      "belongs": {
        "type": "simulated",
        "value": "6"
      },
      "_id": "4435748e-758d-4c24-886e-3248200006005",
    },
    
  ]
}

const mock = {
  sixthSimulatedExam: sixthSimulatedExam
}

module.exports = mock