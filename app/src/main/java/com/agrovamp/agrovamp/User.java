package com.agrovamp.agrovamp;

import java.util.List;

/**
 * Created by Nishat Sayyed on 27-01-2018.
 */

public class User {
    private String languageCode;
    private String firstName;
    private String lastName;
    private String phone;
    private String id;

    public User(String id, String firstName, String lastName,  String phone){
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.id = id;
    }

    public String getName(){
        return firstName + " " + lastName;
    }
    public void setName(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
    public String getId(){
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getPhone(){
        return phone;
    }
    public void setPhone(String phone){
        this.phone = phone;
    }
    public String getLanguageCode(){
        return languageCode;
    }
    public void setLanguageCode(String  languageCode){
        this.languageCode = languageCode;
    }
}
