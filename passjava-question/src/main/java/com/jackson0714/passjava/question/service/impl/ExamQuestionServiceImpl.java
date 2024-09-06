package com.jackson0714.passjava.question.service.impl;

import com.jackson0714.passjava.question.entity.QuestionEntity;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jackson0714.passjava.common.utils.PageUtils;
import com.jackson0714.passjava.common.utils.Query;

import com.jackson0714.passjava.question.dao.ExamQuestionDao;
import com.jackson0714.passjava.question.entity.ExamQuestionEntity;
import com.jackson0714.passjava.question.service.ExamQuestionService;


@Service("examQuestionService")
public class ExamQuestionServiceImpl extends ServiceImpl<ExamQuestionDao, ExamQuestionEntity> implements ExamQuestionService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = (String) params.get("key");
        IPage<ExamQuestionEntity> page = this.page(
                new Query<ExamQuestionEntity>().getPage(params),
                new QueryWrapper<ExamQuestionEntity>()
                        .like("question", key)
                        .or()
                        .like("id", key)
        );

        return new PageUtils(page);
    }

    @Override
    public IPage<ExamQuestionEntity> queryPage1(IPage<ExamQuestionEntity> page, Map<String, Object> params) {
        return baseMapper.selectPage1(page, params);
    }
}