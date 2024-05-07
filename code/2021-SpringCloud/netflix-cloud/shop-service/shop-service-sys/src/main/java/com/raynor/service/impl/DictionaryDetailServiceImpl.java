package com.raynor.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.raynor.mapper.DictionaryDetailMapper;
import com.raynor.service.IDictionaryDetailService;
import com.zong.sys.pojo.DictionaryDetail;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Raynor
 * @since 2020-04-18
 */
@Service
public class DictionaryDetailServiceImpl
        extends ServiceImpl<DictionaryDetailMapper, DictionaryDetail> implements IDictionaryDetailService {

}
