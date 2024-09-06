package com.jackson0714.passjava.question.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jackson0714.passjava.common.utils.PageUtils;
import com.jackson0714.passjava.common.utils.Query;

import com.jackson0714.passjava.question.dao.ExamQuestionRelationDao;
import com.jackson0714.passjava.question.entity.ExamQuestionRelationEntity;
import com.jackson0714.passjava.question.service.ExamQuestionRelationService;


@Service("examQuestionRelationService")
public class ExamQuestionRelationServiceImpl extends ServiceImpl<ExamQuestionRelationDao, ExamQuestionRelationEntity> implements ExamQuestionRelationService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ExamQuestionRelationEntity> page = this.page(
                new Query<ExamQuestionRelationEntity>().getPage(params),
                new QueryWrapper<ExamQuestionRelationEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void batchInsertOrUpdateQuestion(List<ExamQuestionRelationEntity> examQuestionRelationList) {
        baseMapper.batchInsertOrUpdateQuestion(examQuestionRelationList);
    }

}