package com.demo.service;

import com.demo.model.Message;

import java.util.List;

/**
 * (TMessage)表服务接口
 *
 * @author makejava
 * @since 2020-02-19 12:28:15
 */
public interface MessageService {

    /**
     * 通过ID查询单条数据
     *
     * @param xlh 主键
     * @return 实例对象
     */
    Message queryById(String xlh);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Message> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param message 实例对象
     * @return 实例对象
     */
    Message insert(Message message);

    /**
     * 修改数据
     *
     * @param message 实例对象
     * @return 实例对象
     */
    Message update(Message message);

    /**
     * 通过主键删除数据
     *
     * @param xlh 主键
     * @return 是否成功
     */
    boolean deleteById(String xlh);

}