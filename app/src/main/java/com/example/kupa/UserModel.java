package com.example.kupa;

public class UserModel {

    public String name, email, phone;

    public UserModel() {
        // Required for Firebase
    }

    public UserModel(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }
}