package com.jackson0714.passjava.question.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.jackson0714.passjava.question.entity.ExamQuestionEntity;
import com.jackson0714.passjava.question.entity.QuestionBriefEntity;
import com.jackson0714.passjava.question.service.ExamQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jackson0714.passjava.question.entity.ExamEntity;
import com.jackson0714.passjava.question.service.ExamService;
import com.jackson0714.passjava.common.utils.PageUtils;
import com.jackson0714.passjava.common.utils.R;



/**
 * 试卷表
 *
 * @author 悟空聊架构
 * @site www.passjava.cn
 * @date 2023-06-03 15:42:13
 */
@RestController
@RequestMapping("question/exam")
public class ExamController {
    @Autowired
    private ExamService examService;

    private ExamQuestionService examQuestionService;
    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("question:exam:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = examService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("question:exam:info")
    public R info(@PathVariable("id") Integer id){
		ExamEntity exam = examService.getById(id);

        return R.ok().put("exam", exam);
    }

    /**
     * 查询右边框的题目列表（题目只包含主要信息）
     */
    @RequestMapping("/questions/{id}")
    //@RequiresPermissions("question:exam:info")
    public R questions(@PathVariable("id") Integer id, @RequestParam Map<String, Object> params){
        List<QuestionBriefEntity> questionBriefEntity = examService.queryQuestionsByExamId(id, params);

        return R.ok().put("records", questionBriefEntity);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("question:exam:save")
    public R save(@RequestBody ExamEntity exam){
		examService.save(exam);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("question:examquestion:update")
    public R update(@RequestBody ExamEntity examQuestion){
        examService.updateById(examQuestion);

        return R.ok();
    }
    /**
     * 修改
     */
    @RequestMapping("/update-detail")
    @Transactional(rollbackFor = Exception.class)
    //@RequiresPermissions("question:exam:update")
    public R updateDetail(@RequestBody ExamEntity exam){
		examService.updateDetail(exam);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("question:exam:delete")
    public R delete(@RequestBody Integer[] ids){
		examService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
