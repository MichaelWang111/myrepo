package com.jackson0714.passjava.question.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * 试卷题目表
 * 
 * @author 悟空聊架构
 * @site www.passjava.cn
 * @date 2023-06-03 15:42:13
 */
@Data
@TableName("qms_exam_question")
public class ExamQuestionEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 题目主键 id
	 */
	@TableId
	private Integer id;
	/**
	 * 问题
	 */
	private String question;
	/**
	 * 答案选项 A
	 */
	private String chooseA;
	/**
	 * 答案选项 B
	 */
	private String chooseB;
	/**
	 * 答案选项 C
	 */
	private String chooseC;
	/**
	 * 答案选项 D
	 */
	private String chooseD;
	/**
	 * 正确选项(单选或多选，A/B/C/D)
	 */
	private String rightChoose;

	@TableField(exist = false)
	private List<String> rightChooseList;
	/**
	 * 题目类型，如 JVM
	 */
	private Integer type;
	/**
	 * 是否多选
	 */
	private Integer multiple;
	/**
	 * 创建时间
	 */
	@TableField(fill = FieldFill.INSERT)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date createTime;
	/**
	 * 更新时间
	 */
	@TableField(fill = FieldFill.INSERT_UPDATE)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date updateTime;
	/**
	 * 是否已删除
	 */
	private Integer delFlag;
	/**
	 * 创建人
	 */
	private String createUser;
	/**
	 * 更新人
	 */
	private String updateUser;
	/**
	 * 是否开启
	 */
	private Integer enable;
	/**
	 * 题目类型备注
	 */
	@TableField(exist = false)
	private String typeComments;

}
