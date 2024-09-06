package com.jackson0714.passjava.question.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jackson0714.passjava.common.utils.PageUtils;
import com.jackson0714.passjava.question.entity.ExamQuestionRelationEntity;

import java.util.List;
import java.util.Map;

/**
 * 试卷题目关联表
 *
 * @author 悟空聊架构
 * @site www.passjava.cn
 * @date 2023-06-10 07:36:21
 */
public interface ExamQuestionRelationService extends IService<ExamQuestionRelationEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void batchInsertOrUpdateQuestion(List<ExamQuestionRelationEntity> ExamQuestionRelationList);
}

