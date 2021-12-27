package com.sample.services;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.junit.Assert.*;

public class ServerServiceTest {
    @Autowired
    private JdbcTemplate jdbctemplate;


    @Test
    public void addUser() {
        ServerService serverService=new ServerService();
        serverService.AddUser("kaanapak","kaanapak","12345");
    }

    @Test
    public void getGitUsername() {
    }

    @Test
    public void isGitFirst() {
    }

    @Test
    public void deleteUser() {
    }

    @Test
    public void ısFirst() {
    }

    @Test
    public void passwordCheck() {
    }

    @Test
    public void changePassword() {
    }

    @Test
    public void userNameList() {
    }

    @Test
    public void addStarredRepo() {
    }

    @Test
    public void removeStarredRepo() {
    }

    @Test
    public void getStarredRepoList() {
    }

    @Test
    public void isStarred() {
    }

    @Test
    public void addfollowing() {
    }

    @Test
    public void isFollowing() {
    }

    @Test
    public void unfollow() {
    }

    @Test
    public void getFollowingList() {
    }
}