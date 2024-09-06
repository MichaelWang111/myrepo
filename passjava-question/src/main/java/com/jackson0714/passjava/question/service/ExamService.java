package com.jackson0714.passjava.question.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jackson0714.passjava.common.utils.PageUtils;
import com.jackson0714.passjava.question.entity.ExamEntity;
import com.jackson0714.passjava.question.entity.ExamQuestionEntity;
import com.jackson0714.passjava.question.entity.QuestionBriefEntity;

import java.util.List;
import java.util.Map;

/**
 * 试卷表
 *
 * @author 悟空聊架构
 * @site www.passjava.cn
 * @date 2023-06-03 15:42:13
 */
public interface ExamService extends IService<ExamEntity> {

    PageUtils queryPage(Map<String, Object> params);

    boolean updateDetail(ExamEntity examEntity);

    List<QuestionBriefEntity> queryQuestionsByExamId(Integer id, Map<String, Object> params);

    List<ExamQuestionEntity> queryDetailQuestionsByExamId(Integer id, Map<String, Object> params);
}

