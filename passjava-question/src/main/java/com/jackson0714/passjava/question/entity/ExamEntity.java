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
 * 试卷表
 * 
 * @author 悟空聊架构
 * @site www.passjava.cn
 * @date 2023-06-03 15:42:13
 */
@Data
@TableName("qms_exam")
public class ExamEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 题目主键 id
	 */
	@TableId
	private Integer id;
	/**
	 * 试卷标题
	 */
	private String title;
	/**
	 * 试卷限时
	 */
	private Integer limitTime;
	/**
	 * 试卷难度等级(1-简单，2-中等，3-最难）
	 */
	private Integer level;
	/**
	 * 是否开启
	 */
	private Integer enable;
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
	 * 题目 id list
	 */
	@TableField(exist = false)
	private List<Integer> questionIdList;

	/**
	 * 题目 list
	 */
	@TableField(exist = false)
	private List<QuestionBriefEntity> questionBriefList;

}
