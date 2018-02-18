package com.comp1601.waitassistantv2;

/**
 * Created by sahilkapal on 2018-02-17.
 */

public class Person {
    private String name;
    private String email;
    private String phoneNum;

    public Person(){};

    public Person(String name, String email, String phoneNum){
        this.name = name;
        this.email = email;
        this.phoneNum = phoneNum;
    }

    public String getName(){return this.name;}
    public String getEmail(){return this.email;}
    public String getPhoneNum(){return this.phoneNum;}

}
