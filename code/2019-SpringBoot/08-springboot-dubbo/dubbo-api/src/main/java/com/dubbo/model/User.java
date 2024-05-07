package com.dubbo.model;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable{
	private static final long serialVersionUID = 1L;

	private Integer uId;

    private String uAccount;

    private String uPassword;

    private Date uCreatetime;

    private Integer uState;

    public Integer getuId() {
        return uId;
    }

    public void setuId(Integer uId) {
        this.uId = uId;
    }

    public String getuAccount() {
        return uAccount;
    }

    public void setuAccount(String uAccount) {
        this.uAccount = uAccount;
    }

    public String getuPassword() {
        return uPassword;
    }

    public void setuPassword(String uPassword) {
        this.uPassword = uPassword == null ? null : uPassword.trim();
    }

    public Date getuCreatetime() {
        return uCreatetime;
    }

    public void setuCreatetime(Date uCreatetime) {
        this.uCreatetime = uCreatetime;
    }

    public Integer getuState() {
        return uState;
    }

    public void setuState(Integer uState) {
        this.uState = uState;
    }
}