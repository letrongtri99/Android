package com.example.bookfood;

public class User {
    String password;
    String uId;
    String uName;

    public User(String password, String uId, String uName) {
        this.password = password;
        this.uId = uId;
        this.uName = uName;
    }

    public User() {}

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }
}
