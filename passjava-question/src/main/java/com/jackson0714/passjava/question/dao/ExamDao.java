package com.jackson0714.passjava.question.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jackson0714.passjava.question.entity.ExamEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jackson0714.passjava.question.entity.ExamQuestionEntity;
import com.jackson0714.passjava.question.entity.QuestionBriefEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 试卷表
 * 
 * @author 悟空聊架构
 * @site www.passjava.cn
 * @date 2023-06-03 15:42:13
 */
@Mapper
public interface ExamDao extends BaseMapper<ExamEntity> {
    List<QuestionBriefEntity> queryQuestionsByExamId(int id, Map<String, Object> params);

    List<ExamQuestionEntity> queryDetailQuestionsByExamId(Integer id, Map<String, Object> params);
}
