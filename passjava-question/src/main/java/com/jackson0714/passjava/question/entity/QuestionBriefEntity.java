package com.jackson0714.passjava.question.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * question 简要信息
 * 
 * @author 悟空聊架构
 * @site www.passjava.cn
 * @date 2023-06-03 15:42:13
 */
@Data
public class QuestionBriefEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 题目 id
	 */
	private Integer id;
	/**
	 * 试卷 id
	 */
	private Integer examId;
	/**
	 * 关系主键 id
	 */
	private Integer relationId;
	/**
	 * 题目
	 */
	private String question;
	/**
	 * 类型
	 */
	private String typeComments;

}
