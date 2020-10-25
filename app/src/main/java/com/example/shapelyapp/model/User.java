package com.example.shapelyapp.model;

import java.io.Serializable;

public class User implements Serializable {
    private String name;
    private String age;
    private double height;
    private double weight;
    private String email;
    private String userType;
    private String gender;

    public User() { }

    public User(String name, String age, double height, double weight, String email, String userType, String gender) {
        this.name = name;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.email = email;
        this.userType = userType;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String isGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", height=" + height +
                ", weight=" + weight +
                ", email='" + email + '\'' +
                ", userType='" + userType + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
