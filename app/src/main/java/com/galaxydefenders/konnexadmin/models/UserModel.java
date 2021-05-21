package com.galaxydefenders.konnexadmin.models;

/**
 * Created by Rounak
 * For more info visit https://rounak.tech
 **/
public class UserModel {

    String username;
    String password;


    public UserModel(String username, String password) {
        this.username = username;
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

    public void setPassword(String password) {
        this.password = password;
    }
}
