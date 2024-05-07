package com.demo.entity;

import java.io.Serializable;
import java.util.List;

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
public class UserSoftwareItems implements Serializable {

    private static final long serialVersionUID = 1L;
    
    
    private String nickname;

    private List<Long> softwareId;



}
