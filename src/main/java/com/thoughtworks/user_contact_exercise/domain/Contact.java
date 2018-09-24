package com.thoughtworks.user_contact_exercise.domain;

public class Contact {
    private int id;
    private String name;
    private int age;
    private String gender;
    private String mobile;


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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Contact() {
    }
    public Contact(int id, String name, int age, String gender, String mobile) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.mobile = mobile;
    }
}
