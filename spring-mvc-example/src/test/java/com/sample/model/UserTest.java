package com.sample.model;

import org.junit.jupiter.api.Test;


import java.util.ArrayList;


import static org.junit.jupiter.api.Assertions.*;


class UserTest {

    @Test
    void getUsername() {
        User user=new User();
        user.setUsername("username");
        assertEquals(user.getUsername(),"username");
    }


    @Test
    void getRepoList() {
        User user=new User();
        ArrayList<Repository> repolar=new ArrayList();
        user.setRepoList(repolar);
        assertEquals(user.getRepoList(),repolar);
    }


    @Test
    void getFollowings() {
        User user=new User();
        ArrayList<String> followingslist=new ArrayList();
        followingslist.add("ege");
user.setFollowings(followingslist);
assertEquals(user.getFollowings(),followingslist);
    }


    @Test

    void getStarredRepos() {
        User user=new User();
        ArrayList<Repository> starredlist=new ArrayList();
        user.setStarredRepos(starredlist);
        assertEquals(user.getStarredRepos(),starredlist);
    }


    @Test
    void getFollowingsRepos() {
        User user=new User();
        ArrayList<Repository> followingrepos=new ArrayList();
        user.setFollowingsRepos(followingrepos);
        assertEquals(user.getFollowingsRepos(),followingrepos);
    }


    @Test
    void getScore() {
        User user=new User();
        user.setScore(4);
        assertEquals(user.getScore(),4);
    }



    @Test
    void getIsFollowing() {
        User user=new User();
        user.getIsFollowing();
        assertEquals(user.getScore(),0);


    }

    @Test
    void setFollowingUsers(){
        User user=new User();
        ArrayList<User> followingslist=new ArrayList();

        user.setFollowingUsers(followingslist);
        assertEquals(user.getFollowingUsers(),followingslist);
    }



    @Test
    void toStringFollowingUsers() {
        User user=new User();
        user.setUsername("egegoztepe");


        ArrayList<User> followingslist=new ArrayList();
        User user2=new User();
        user2.setScore(1);
        user2.setUsername("kaanapak");
        user2.setIsFollowing(1);
        followingslist.add(user2);
        user.setFollowingUsers(followingslist);

        assertEquals(user.toStringFollowingUsers(),"egegoztepe|10|1");
    }

    @Test
    void toStringUserFollowing() {
        User user2=new User();
        user2.setScore(1);
        user2.setUsername("kaanapak");
        user2.setIsFollowing(1);
        assertEquals(user2.toStringUserFollowing(),"kaanapak|1.0|1");
    }


    @Test
    void toStringStarredRepos() {
        User user2=new User();
        ArrayList<String> Languages=new ArrayList();
        ArrayList<Repository> starreds=new ArrayList<>();
        starreds.add(new Repository("12/02/2021",Languages,"egoztepe","ege4e"));
        user2.setStarredRepos(starreds);
        assertEquals(user2.toStringStarredRepos(),"deneme");
    }


    @Test
    void toStringRepoList() {
        User user=new User();
        ArrayList<Repository> repolar=new ArrayList<>();
        ArrayList<String> Languages=new ArrayList();
        repolar.add(new Repository("12/02/2021",Languages,"egoztepe","ege4e"));
        user.setRepoList(repolar);
        assertEquals(user.toStringRepoList(),"deneme");


    }


    @Test

    void toStringFollowingRepos() {
        User user=new User();
        ArrayList<Repository> repofollow=new ArrayList<>();
        ArrayList<String> Languages=new ArrayList();
        repofollow.add(new Repository("12/02/2021",Languages,"egoztepe","ege4e"));
        user.setFollowingsRepos(repofollow);
        assertEquals(user.toStringFollowingRepos(),"deneme");

    }
}
