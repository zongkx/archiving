package com.zong.sys.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Table(name = "t_dictionary_detail")
public class DictionaryDetail {

    private static final long serialVersionUID = 1L;

    /**
     * 主键(自增)
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * dictionary表主键
     */
    @Column(name = "t_did")
    private Long did;

    /**
     * 通用字典列
     */
    @Column(name = "col1")
    private String col1;
    /**
     * 通用字典列
     */
    @Column(name = "col2")
    private String col2;
    /**
     * 通用字典列
     */
    @Column(name = "col3")
    private String col3;
    /**
     * 通用字典列
     */
    @Column(name = "col4")
    private String col4;
    /**
     * 通用字典列
     */
    @Column(name = "col5")
    private String col5;

    /**
     * 创建时间
     */
    @Column(name = "t_create_time")
    private LocalDateTime createTime;

    /**
     * 审核标志(1:通过,0:不通过)
     */
    @Column(name = "t_state")
    private String state;
}
