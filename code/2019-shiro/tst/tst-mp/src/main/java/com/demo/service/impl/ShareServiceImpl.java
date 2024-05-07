package com.demo.service.impl;

import com.demo.entity.Share;
import com.demo.mapper.ShareMapper;
import com.demo.service.IShareService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Raynor
 * @since 2019-04-04
 */
@Service
public class ShareServiceImpl extends ServiceImpl<ShareMapper, Share> implements IShareService {

}
