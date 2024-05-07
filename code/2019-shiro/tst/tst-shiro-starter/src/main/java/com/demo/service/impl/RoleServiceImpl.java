package com.demo.service.impl;

import com.demo.entity.Role;
import com.demo.mapper.RoleMapper;
import com.demo.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Raynor
 * @since 2019-01-10
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

}
