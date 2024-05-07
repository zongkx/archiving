package com.demo.mapper;

import com.demo.model.Message;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (message)表数据库访问层
 *
 * @author makejava
 * @since 2020-02-19 12:28:13
 */
@Mapper
public interface MessageMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param xlh 主键
     * @return 实例对象
     */
    Message queryById(String xlh);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Message> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param tMessage 实例对象
     * @return 对象列表
     */
    List<Message> queryAll(Message message);

    /**
     * 新增数据
     *
     * @param message 实例对象
     * @return 影响行数
     */
    int insert(Message message);

    /**
     * 修改数据
     *
     * @param message 实例对象
     * @return 影响行数
     */
    int update(Message message);

    /**
     * 通过主键删除数据
     *
     * @param xlh 主键
     * @return 影响行数
     */
    int deleteById(String xlh);




}