package com.demo.entity;

import java.time.LocalTime;
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
 * @since 2019-05-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Behavior implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nickname;

    private Long softwareId;

    private Float score;

    private LocalTime time;


}
