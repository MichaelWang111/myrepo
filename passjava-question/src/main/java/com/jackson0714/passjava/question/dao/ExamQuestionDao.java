package com.jackson0714.passjava.question.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jackson0714.passjava.question.entity.ExamQuestionEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jackson0714.passjava.question.entity.QuestionEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * 试卷题目表
 * 
 * @author 悟空聊架构
 * @site www.passjava.cn
 * @date 2023-06-03 15:42:13
 */
@Mapper
public interface ExamQuestionDao extends BaseMapper<ExamQuestionEntity> {
    IPage<ExamQuestionEntity> selectPage1(IPage<ExamQuestionEntity> page, Map<String, Object> params);

}
