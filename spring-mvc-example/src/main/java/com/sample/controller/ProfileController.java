package com.sample.controller;

import com.sample.model.Repository;
import com.sample.model.UserSystem;
import com.sample.services.APIService;
import com.sample.services.ServerService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sample.model.User;
import com.sample.services.UserService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

@Controller
public class ProfileController {
    @Autowired
    ServerService serverService=new ServerService();
    APIService apıService=new APIService();

    @PostMapping("/profile")
    public String profile(Model model,String username) throws JSONException, IOException, InterruptedException {
        User user=new User();
        user.setUsername(username);
        user.setScore(apıService.ScoreCalculator(serverService.getGitUsername(username)));
        String gitUsername=serverService.getGitUsername(username);
        ArrayList<Repository> RepoList= apıService.RepoList(gitUsername);

        for(int i=0;i<RepoList.size();i++){
            if(serverService.isStarred(username,RepoList.get(i).getId())){
                RepoList.get(i).setStarred();
            }
        }
        user.setRepoList(RepoList);
        user.setGitHubUsername(serverService.getGitUsername(username));
        model.addAttribute("user",user);
        return "profile";
    }

    @PostMapping("/searchedUser")
    public String searchedUser(Model model,String username,String searchedUsername,Integer IsAddStar,Integer IsRemoveStar,String StarredId,Integer IsFollow,Integer IsUnFollow) throws JSONException, IOException, InterruptedException {
        if(!Objects.isNull(IsFollow)){
            serverService.addfollowing(username,searchedUsername);
        }else if(!Objects.isNull(IsUnFollow)){
            serverService.unfollow(username,searchedUsername);
        }else if(!Objects.isNull(IsAddStar)){
            serverService.addStarredRepo(username,StarredId);
        }else if(!Objects.isNull(IsRemoveStar)){
            serverService.removeStarredRepo(username,StarredId);
        }

        User user=new User();
        user.setUsername(username);
        model.addAttribute("user",user);
        User searchedUser=new User();
        Integer isFollowing=0;
        if(serverService.isFollowing(username,searchedUsername)){
            isFollowing=1;
        }
        searchedUser.setIsFollowing(isFollowing);
        searchedUser.setUsername(searchedUsername);

        String gitUsername=serverService.getGitUsername(searchedUsername);
        ArrayList<Repository> RepoList= apıService.RepoList(gitUsername);

        for(int i=0;i<RepoList.size();i++){
            if(serverService.isStarred(username,RepoList.get(i).getId())){
                RepoList.get(i).setStarred();
            }
        }
        searchedUser.setGitHubUsername(serverService.getGitUsername(searchedUsername));
        searchedUser.setRepoList(RepoList);
        model.addAttribute("searchedUser",user);

        return "searchedUser";
    }
}
