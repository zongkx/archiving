package com.demo.entity;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UserWithRole extends User {
	private static final long serialVersionUID = -5680235862276163462L;
	
	private Integer roleId;
	
	private List<Long> roleIds;

}
