package com.jackson0714.passjava.question.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * 试卷题目关联表
 * 
 * @author 悟空聊架构
 * @site www.passjava.cn
 * @date 2023-06-10 07:36:21
 */
@Data
@TableName("qms_exam_question_relation")
public class ExamQuestionRelationEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键 id
	 */
	@TableId
	private Integer id;
	/**
	 * 问题 id
	 */
	private Integer questionId;
	/**
	 * 试卷 id
	 */
	private Integer examId;
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
	@TableLogic
	@TableField("del_flag")
	private Integer delFlag;
	/**
	 * 创建人
	 */
	private String createUser;
	/**
	 * 更新人
	 */
	private String updateUser;

}
