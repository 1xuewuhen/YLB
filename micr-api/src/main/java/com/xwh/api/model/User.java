package com.xwh.api.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class User implements Serializable {
    private Integer id;

    private String phone;

    private String loginPassword;
    private String email;

    private String name;

    private String idCard;

    private Date addTime;

    private Date lastLoginTime;

    private String headerImage;

}