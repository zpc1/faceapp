package com.deeocare.deepcare.bean;

import java.io.Serializable;

public class HuiYuan implements Serializable {

    private long id;
    private String name;
    private int age;

    private String sex;
    private String phoneNumber;
    private String trainDate;
    private String modifyDateTime;
    public HuiYuan() {
        super();
    }
    public HuiYuan(long id, String name, int age, String sex,  String phoneNumber,
                String trainDate, String modifyDateTime) {
        super();
        this.id = id;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.phoneNumber = phoneNumber;
        this.trainDate = trainDate;
        this.modifyDateTime = modifyDateTime;
    }
    public HuiYuan(String name, int age, String sex, String phoneNumber,
                String trainDate, String modifyDateTime) {
        super();
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.phoneNumber = phoneNumber;
        this.trainDate = trainDate;
        this.modifyDateTime = modifyDateTime;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getSex() {
        return sex;     }
    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;     }
    public String getTrainDate() {
        return trainDate;
    }
    public void setTrainDate(String trainDate) {
        this.trainDate = trainDate;
    }
    public String getModifyDateTime() {
        return modifyDateTime;
    }
    public void setModifyDateTime(String modifyDateTime) {
        this.modifyDateTime = modifyDateTime;
    }
}
