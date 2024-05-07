package com.demo.entity;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author Raynor
 * @since 2019-04-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 评论用户id
     */
    private String fromUid;

    /**
     * 评论目标用户id
     */
    private String toUid;

    /**
     * 关联动态或者软件的ID
     */
    private Integer aid;

    /**
     * 留言时间
     */
    private LocalDateTime time;

    /**
     * 内容
     */
    private String content;

    /**
     * 主题: 1 软件 2.动态
     */
    private String type;
    
    /**
     * 主题: 0 未读  1 已读
     */
    private String flag;



}
