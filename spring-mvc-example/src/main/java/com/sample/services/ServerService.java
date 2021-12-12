package com.sample.services;
import com.sample.model.User;
import com.sample.model.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ServerService {

    public void AddUser(String username,String GitUsername,String password){
    }

    //There are 2 usernames,one is for our site,one is for the user's account name at GitHub
    public String getGitUsername(String username){
        return "";
    }

    public void DeleteUser(String username){

    }

    //Checks if there is a user with that username
    public Boolean ısFirst(String username){
        return true;
    }

    public Boolean PasswordCheck(String username,String password){
        return true;
    }

    public void ChangePassword(String username,String oldPassword,String newPassword){

    }

    //Return list of username list
    public ArrayList <String> UserNameList(){
        ArrayList<String> UserNameList=new ArrayList<>();
        return  UserNameList;
    }

    //Adds starred repo to that username. In database there will be a list of starred repositories for each user.
    //We will hold only ID of each starred repository
    public void addStarredRepo(String username,String repository_Id){

    }

    public void removeStarredRepo(String username,String repository_Id){

    }

    public ArrayList<String> getStarredRepoList(String username){
        ArrayList<String> RepoList=new ArrayList<>();
        return RepoList;
    }

    //Checks if id is in that user's starredrepo list
    public Boolean isStarred(String username,String Id){
        return false;
    }

    //We can hold username of each following user
    public void addfollowing(String username,String new_following_user){

    }

    public Boolean isFollowing (String username,String FollowingUsername){
        return true;
    }
    public void unfollow(String username,String removinguser){

    }

    public ArrayList<String> getFollowingList(String username){
        ArrayList<String> FollowingList=new ArrayList<>();
            return FollowingList;
        }

}
