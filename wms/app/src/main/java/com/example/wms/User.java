package com.example.wms;

    public class User {
        public String name, email, phone,jobprofile;

        public User(){

        }

        public User(String name, String email, String phone,String jpbprofile) {
            this.name = name;
            this.email = email;
            this.phone = phone;
            this.jobprofile = jpbprofile;
        }
    }
