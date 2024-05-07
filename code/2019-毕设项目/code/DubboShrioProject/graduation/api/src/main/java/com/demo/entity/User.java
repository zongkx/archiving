package com.demo.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.elasticsearch.annotations.Field;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author Raynor
 * @since 2019-01-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("user")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;

	/**
	 * 用户昵称
	 */
	@Field
	private String nickname;

	/**
	 * 邮箱|登录帐号
	 */
	private String email;

	/**
	 * 密码
	 */
	private String pswd;

	/**
	 * 创建时间
	 */
	private LocalDateTime createTime;

	/**
	 * 最后登录时间
	 */
	private LocalDateTime lastLoginTime;

	/**
	 * 1:有效，0:禁止登录
	 */
	private Long status;
	
	/**
	 * 头像ID
	 */
	//private Long headId;
	
	/**
	 * 1:申请中, 0:未申请,2:已批准,3:已禁止
	 */
	private Long apply;
	

	@TableField(exist = false)
	private List<Role> userRoles;

	@TableField(exist = false)
	private List<Permission> userPerms;

}
