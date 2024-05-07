package com.demo.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
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
 * @since 2019-01-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("user_role")
public class UserRole implements Serializable {

    private static final long serialVersionUID = 1L;
   
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private Integer userId;

    private Integer roleId;
    
	/*
	 * @TableField(exist=false) private List<User> users;
	 * 
	 * @TableField(exist=false) private List<Role> roles;
	 */

}
