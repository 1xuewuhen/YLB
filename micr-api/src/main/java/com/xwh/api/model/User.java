package com.xwh.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
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