package com.jackson0714.passjava.question.dao;

import com.jackson0714.passjava.question.entity.ExamQuestionRelationEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 试卷题目关联表
 * 
 * @author 悟空聊架构
 * @site www.passjava.cn
 * @date 2023-06-10 07:36:21
 */
@Mapper
public interface ExamQuestionRelationDao extends BaseMapper<ExamQuestionRelationEntity> {

    void batchInsertOrUpdateQuestion(List<ExamQuestionRelationEntity> examQuestionRelationList);
}
