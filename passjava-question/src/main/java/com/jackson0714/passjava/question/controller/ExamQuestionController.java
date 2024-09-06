package com.jackson0714.passjava.question.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jackson0714.passjava.question.entity.ExamQuestionEntityVO;
import com.jackson0714.passjava.question.entity.QuestionEntity;
import com.jackson0714.passjava.question.service.IQuestionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jackson0714.passjava.question.entity.ExamQuestionEntity;
import com.jackson0714.passjava.question.service.ExamQuestionService;
import com.jackson0714.passjava.common.utils.PageUtils;
import com.jackson0714.passjava.common.utils.R;



/**
 * 试卷题目表
 *
 * @author 悟空聊架构
 * @site www.passjava.cn
 * @date 2023-06-03 15:42:13
 */
@RestController
@RequestMapping("question/examquestion")
public class ExamQuestionController {
    @Autowired
    private ExamQuestionService examQuestionService;

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
     * 信息
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

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("question:examquestion:save")
    public R save(@RequestBody ExamQuestionEntity examQuestion){
        String rightChoose = String.join(",", examQuestion.getRightChoose());

        //examQuestion.setRightChoose(rightChoose);
		examQuestionService.save(examQuestion);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("question:examquestion:update")
    public R update(@RequestBody ExamQuestionEntity examQuestion){
		examQuestionService.updateById(examQuestion);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("question:examquestion:delete")
    public R delete(@RequestBody Integer[] ids){
		examQuestionService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
