package com.example.mohit.ticketbookingsystem;

public class UserDetailsPojo {
private String username;
    private String password;

    public  UserDetailsPojo()
    {

    }

    public void setPassword(String password) {

        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {

        return password;
    }
}
