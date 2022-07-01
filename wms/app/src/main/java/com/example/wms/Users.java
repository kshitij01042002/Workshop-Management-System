package com.example.wms;

public class Users {
    public String name;
    public String email;
    public String phone;
    public String jobprofile;

    public Users(){

    }

    public Users(String name, String email, String phone,String jobprofile) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.jobprofile = jobprofile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getJobprofile() {
        return jobprofile;
    }

    public void setJobprofile(String jobprofile) {
        this.jobprofile = jobprofile;
    }
}