package com.jackson0714.passjava.question.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.jackson0714.passjava.question.entity.ExamQuestionEntity;
import com.jackson0714.passjava.question.entity.ExamQuestionRelationEntity;
import com.jackson0714.passjava.question.entity.QuestionBriefEntity;
import com.jackson0714.passjava.question.service.ExamQuestionRelationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jackson0714.passjava.common.utils.PageUtils;
import com.jackson0714.passjava.common.utils.Query;

import com.jackson0714.passjava.question.dao.ExamDao;
import com.jackson0714.passjava.question.entity.ExamEntity;
import com.jackson0714.passjava.question.service.ExamService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


@Service("examService")
public class ExamServiceImpl extends ServiceImpl<ExamDao, ExamEntity> implements ExamService {

    @Resource
    private ExamQuestionRelationService examQuestionRelationService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = (String) params.get("key");
        IPage<ExamEntity> page = this.page(
                new Query<ExamEntity>().getPage(params),
                new QueryWrapper<ExamEntity>()
                        .like("title", key)
                        .or()
                        .like("id", key)
        );

        return new PageUtils(page);
    }

    /**
     * 插入一条试卷记录
     *
     * @param examEntity 实体对象
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean save(ExamEntity examEntity) {
        // 1.创建试卷
        super.save(examEntity);

        // 2.添加或更新题目
        addOrUpdateRelation(examEntity);

        return true;
    }

    /**
     * 根据 ID 选择修改
     *
     * @param examEntity 实体对象
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateDetail(ExamEntity examEntity) {
        // 1.逻辑删除试卷关联的所有题目
        deleteRelation(examEntity);

        // 2.添加或更新题目
        addOrUpdateRelation(examEntity);

        // 3.更新试卷
        return super.updateById(examEntity);
    }

    /**
     * 根据 ID 查询
     *
     * @param id 主键ID
     */
    @Override
    public List<QuestionBriefEntity> queryQuestionsByExamId(Integer id, Map<String, Object> params) {
        return baseMapper.queryQuestionsByExamId(id, params);
    }

    @Override
    public List<ExamQuestionEntity> queryDetailQuestionsByExamId(Integer id, Map<String, Object> params) {
        return baseMapper.queryDetailQuestionsByExamId(id, params);
    }

    private void deleteRelation(ExamEntity examEntity) {
        UpdateWrapper<ExamQuestionRelationEntity> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("del_flag", 1)
                .eq("exam_id", examEntity.getId());
        examQuestionRelationService.update(new ExamQuestionRelationEntity(), updateWrapper);
    }

    private void addOrUpdateRelation(ExamEntity examEntity) {
        UpdateWrapper<ExamQuestionRelationEntity> updateWrapper;
        for(int questionId : examEntity.getQuestionIdList()) {

            updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("exam_id", examEntity.getId())
                    .eq("question_id", questionId)
                    .set("del_flag", 0)
                    .set("exam_id", examEntity.getId())
                    .set("question_id", questionId);
            ExamQuestionRelationEntity entity = new ExamQuestionRelationEntity();
            entity.setExamId(examEntity.getId());
            entity.setQuestionId(questionId);
            examQuestionRelationService.saveOrUpdate(entity, updateWrapper);
        }
    }


}