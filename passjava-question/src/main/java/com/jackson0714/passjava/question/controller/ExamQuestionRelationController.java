package com.jackson0714.passjava.question.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jackson0714.passjava.question.entity.ExamQuestionRelationEntity;
import com.jackson0714.passjava.question.service.ExamQuestionRelationService;
import com.jackson0714.passjava.common.utils.PageUtils;
import com.jackson0714.passjava.common.utils.R;



/**
 * 试卷题目关联表
 *
 * @author 悟空聊架构
 * @site www.passjava.cn
 * @date 2023-06-10 07:36:21
 */
@RestController
@RequestMapping("question/examquestionrelation")
public class ExamQuestionRelationController {
    @Autowired
    private ExamQuestionRelationService examQuestionRelationService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("question:examquestionrelation:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = examQuestionRelationService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("question:examquestionrelation:info")
    public R info(@PathVariable("id") Integer id){
		ExamQuestionRelationEntity examQuestionRelation = examQuestionRelationService.getById(id);

        return R.ok().put("examQuestionRelation", examQuestionRelation);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("question:examquestionrelation:save")
    public R save(@RequestBody ExamQuestionRelationEntity examQuestionRelation){
		examQuestionRelationService.save(examQuestionRelation);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("question:examquestionrelation:update")
    public R update(@RequestBody ExamQuestionRelationEntity examQuestionRelation){
		examQuestionRelationService.updateById(examQuestionRelation);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("question:examquestionrelation:delete")
    public R delete(@RequestBody Integer[] ids){
		examQuestionRelationService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
