package com.xwh.front.model;

import com.xwh.front.myAnnoation.Value;

import java.util.Date;

/**
 * 作者:陈方银
 * 时间:2023/7/24
 */
public class User {
    @Value(value = "张三")
    private String name;
    @Value(value = "1001")
    private Integer id;
    @Value(value = "32")
    private Integer age;
    @Value(value = "2023-01-03 12:23:12")
    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", age=" + age +
                ", date=" + date +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
