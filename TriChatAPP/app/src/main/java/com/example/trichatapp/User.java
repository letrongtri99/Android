package com.example.trichatapp;

public class User {
    private String password;
    private String ten;
    private String username;
    private String status;
    private String avatarURL;


    public User(String password, String ten, String username, String status, String avatarURL) {
        this.password = password;
        this.ten = ten;
        this.username = username;
        this.status = status;
        this.avatarURL = avatarURL;
    }

    public User() { }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAvatarURL() {
        return avatarURL;
    }

    public void setAvatarURL(String avatarURL) {
        this.avatarURL = avatarURL;
    }
}
