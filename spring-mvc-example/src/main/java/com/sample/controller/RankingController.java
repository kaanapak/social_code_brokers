package com.sample.controller;

import com.sample.model.Repository;
import com.sample.model.User;
import com.sample.services.APIService;
import com.sample.services.ServerService;
import com.sample.services.UserService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class RankingController {
    @Autowired
    ServerService serverService=new ServerService();
    APIService apıService=new APIService();

    @PostMapping("/starredProjects")
    public String starredProjects(Model model, String username,String StarredId,Integer IsRemoveStar) throws IOException, InterruptedException {
        if(!Objects.isNull(IsRemoveStar)){
            serverService.removeStarredRepo(username,StarredId);
        }
        User user=new User();
        user.setUsername(username);
        ArrayList<String> StarredList= serverService.getStarredRepoList(username);
        ArrayList <Repository> StarredRepos=new ArrayList<>();
        for(int i=0;i<StarredList.size();i++){
            Repository repo= apıService.getRepository(StarredList.get(i));
            repo.setStarred();
            StarredRepos.add(repo);
        }
        user.setStarredRepos(StarredRepos);
        model.addAttribute("user",user);
        return "starredProjects";
    }

    @PostMapping("/followings")
    public String followings(Model model, String username,String UnfollowUser,Integer IsUnfollow) throws JSONException, IOException, InterruptedException {
        if(!Objects.isNull(IsUnfollow)){
            serverService.unfollow(username,UnfollowUser);
        }
        User user=new User();
        user.setUsername(username);
        ArrayList<User> UserList =new ArrayList<>();
        ArrayList <String> FollowingNames=serverService.getFollowingList(username);

        for(int i=0;i<FollowingNames.size();i++){
            String currentUsername=FollowingNames.get(i);
            User userF=new User();
            userF.setUsername(currentUsername);
            userF.setScore(apıService.ScoreCalculator(serverService.getGitUsername(currentUsername)));
            userF.setGitHubUsername(serverService.getGitUsername(currentUsername));
            UserList.add(userF);
        }
        user.setFollowingUsers(UserList);
        model.addAttribute("user",user);
        return "followings";
    }


}
