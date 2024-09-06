package com.jackson0714.passjava.question.controller;

import com.jackson0714.passjava.common.utils.PageUtils;
import com.jackson0714.passjava.common.utils.R;
import com.jackson0714.passjava.question.entity.ExamEntity;
import com.jackson0714.passjava.question.entity.QuestionBriefEntity;
import com.jackson0714.passjava.question.service.ExamQuestionService;
import com.jackson0714.passjava.question.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


/**
 * 试卷表
 *
 * @author 悟空聊架构
 * @site www.passjava.cn
 * @date 2023-06-03 15:42:13
 */
@RestController
@RequestMapping("/question/app/v1/exam")
public class ExamAppController {
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
     * 管理端查询试卷的所有题目
     */
    @RequestMapping("/questions/{id}")
    //@RequiresPermissions("question:exam:info")
    public R questions(@PathVariable("id") Integer id, @RequestParam Map<String, Object> params){
        List<QuestionBriefEntity> questionBriefEntity = examService.queryQuestionsByExamId(id, params);

        return R.ok().put("records", questionBriefEntity);
    }



}
