package com.sample.model;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class UserSystemTest {

    @Test
    public void setUserList() {
        UserSystem userSystem=new UserSystem();
        ArrayList <User> UserList=new ArrayList<>();
        ArrayList <Repository> RepoList=new ArrayList<>();
        ArrayList <Repository> StarredRepos=new ArrayList<>();
        ArrayList <Repository> FollowingRepos=new ArrayList<>();

        ArrayList<String> FollowingList=new ArrayList<>();
        User user=new User("kaanapak","kaanapak",RepoList,FollowingList,StarredRepos,FollowingRepos,10);
        UserList.add(user);
        userSystem.setUserList(UserList);
        assertEquals(userSystem.getUserList(), UserList);


        }

    @Test
    public void setUserNameList() {
        UserSystem userSystem=new UserSystem();
ArrayList<String>UserNameList=new ArrayList<>();
UserNameList.add("Kaan");
userSystem.setUserNameList(UserNameList);
        assertEquals(userSystem.toStringUsers(),"Kaan");
    }

    @Test
    public void getUserNameList() {
        UserSystem userSystem=new UserSystem();
        ArrayList<String>UserNameList=new ArrayList<>();
        UserNameList.add("Kaan");
        userSystem.setUserNameList(UserNameList);
        assertEquals(userSystem.getUserNameList(),UserNameList);
    }


}