package com.example.ecoguard.Model;

public class UserModel{
    private final String email, userName, password, phoneNumber;

    public UserModel(String email, String userName, String password, String phoneNumber){
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.phoneNumber = password;
    }
    public String getEmail(){
        return email;
    }
    public String getUserName(){
        return userName;
    }
    public String getPassword(){
        return password;
    }
    public String getPhoneNumber(){
        return phoneNumber;
    }

}
