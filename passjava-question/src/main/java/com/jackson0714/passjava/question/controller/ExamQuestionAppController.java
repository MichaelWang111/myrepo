package com.jackson0714.passjava.question.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jackson0714.passjava.common.utils.PageUtils;
import com.jackson0714.passjava.common.utils.R;
import com.jackson0714.passjava.question.entity.ExamQuestionEntity;
import com.jackson0714.passjava.question.entity.QuestionBriefEntity;
import com.jackson0714.passjava.question.service.ExamQuestionService;
import com.jackson0714.passjava.question.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * 试卷题目表
 *
 * @author 悟空聊架构
 * @site www.passjava.cn
 * @date 2023-06-03 15:42:13
 */
@RestController
@RequestMapping("question/app/v1/examquestion")
public class ExamQuestionAppController {
    @Autowired
    private ExamQuestionService examQuestionService;

    @Autowired
    private ExamService examService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("question:examquestion:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = examQuestionService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 关联查询题目类型表
     * @param page
     * @param params
     * @return
     */
    @RequestMapping("/list2")
    public R list2(Page<ExamQuestionEntity> page, @RequestParam Map<String, Object> params){
        Page<ExamQuestionEntity> page1 = (Page<ExamQuestionEntity>) examQuestionService.queryPage1(page, params);
        return R.ok().put("page", page1);
    }

    /**
     * 根据试卷 id 查询试卷的所有题目
     */
    @RequestMapping("/all/{id}")
    //@RequiresPermissions("question:exam:info")
    public R questions(@PathVariable("id") Integer id, @RequestParam Map<String, Object> params){
        List<ExamQuestionEntity> questionBriefEntity = examService.queryDetailQuestionsByExamId(id, params);

        return R.ok().put("records", questionBriefEntity);
    }

    /**
     * 查询某个题目的详情
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("question:examquestion:info")
    public R info(@PathVariable("id") Integer id){
        ExamQuestionEntity examQuestion = examQuestionService.getById(id);
        List<String> rightChooseList = null;
        if (examQuestion.getRightChoose() != null) {
            rightChooseList = Arrays.stream(examQuestion.getRightChoose().split(",")).collect(Collectors.toList());
        }
        examQuestion.setRightChooseList(rightChooseList);

        return R.ok().put("examQuestion", examQuestion);
    }


}
