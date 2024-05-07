package com.demo.entity;

import java.time.LocalDateTime;
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
    private Long userId;

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


}
