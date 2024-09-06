package com.jackson0714.passjava.question.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jackson0714.passjava.common.utils.PageUtils;
import com.jackson0714.passjava.question.entity.ExamQuestionEntity;
import com.jackson0714.passjava.question.entity.QuestionEntity;

import java.util.Map;

/**
 * 试卷题目表
 *
 * @author 悟空聊架构
 * @site www.passjava.cn
 * @date 2023-06-03 15:42:13
 */
public interface ExamQuestionService extends IService<ExamQuestionEntity> {

    PageUtils queryPage(Map<String, Object> params);

    IPage<ExamQuestionEntity> queryPage1(IPage<ExamQuestionEntity> page, Map<String, Object> params);

}

