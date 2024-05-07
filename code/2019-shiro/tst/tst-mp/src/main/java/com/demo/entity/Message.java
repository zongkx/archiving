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
 * @since 2019-04-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Message implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 评论用户id
     */
    private Long fromUid;

    /**
     * 评论目标用户id
     */
    private Long toUid;

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


}
