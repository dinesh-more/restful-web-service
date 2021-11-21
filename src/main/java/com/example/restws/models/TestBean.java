package com.example.restws.models;

import com.fasterxml.jackson.annotation.JsonFilter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@JsonFilter("testBeanFilter")
public class TestBean {

    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String password;
    private double mobile_number;
    private int age;

    public TestBean() {
    }

    public TestBean(String name, String password, double mobile_number, int age) {
        this.name = name;
        this.password = password;
        this.mobile_number = mobile_number;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getMobile_number() {
        return mobile_number;
    }

    public void setMobile_number(double mobile_number) {
        this.mobile_number = mobile_number;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "TestBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", mobile_number=" + mobile_number +
                ", age=" + age +
                '}';
    }
}
