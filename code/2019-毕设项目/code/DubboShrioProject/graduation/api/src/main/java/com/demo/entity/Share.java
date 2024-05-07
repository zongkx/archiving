package com.demo.entity;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
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
 * @since 2019-04-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Share implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 时间
     */
    private LocalDateTime datetime;

    /**
     * 标题
     */
    private String title;

    /**
     * 关联到用户表ID
     */
    private String userId;

    /**
     * 正文
     */
    private String text;

    /**
     * 图片
     */
    private String pic;

    /**
     * 关联到软件表ID
     */
    private Long softwareId;

    /**
     * 点赞数
     */
    private Integer likenum;
    
    /**
     * 点赞者
     */
    private String likeId;
    
    
    /**
     * 关联用户信息
     */
    @TableField(exist = false)
    private User user;

}
