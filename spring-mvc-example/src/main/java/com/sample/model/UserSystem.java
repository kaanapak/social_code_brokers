package com.sample.model;

import java.util.ArrayList;

public class UserSystem {
    private ArrayList<String> UserNameList=new ArrayList<>();
    private ArrayList<User> UserList=new ArrayList<>();
//Usernames of users
    public UserSystem(){
    }

    public void setUserList(ArrayList<User> userList) {
        UserList = userList;
    }

    public void setUserNameList(ArrayList<String> userNameList) {
        UserNameList = userNameList;
    }

    public ArrayList<User> getUserList() {
        return UserList;
    }

    public ArrayList<String> getUserNameList() {
        return UserNameList;
    }
    public String toStringUsers(){
       return String.join(", ", UserNameList);
    }
}
