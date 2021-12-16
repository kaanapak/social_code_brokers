package com.sample.controller;

import com.sample.model.User;
import com.sample.services.APIService;
import com.sample.services.ServerService;
import com.sample.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.util.Objects;

public class RankingController {
    @Autowired
    ServerService serverService=new ServerService();
    APIService apıService=new APIService();
    UserService userService=new UserService();

    @PostMapping("/starredProjects")
    public String starredProjects(Model model, String username,String StarredId,Integer isRemoveStar) throws IOException, InterruptedException {
        if(!Objects.isNull(isRemoveStar)){
            serverService.removeStarredRepo(username,StarredId);
        }
        User user=new User();
        user.setUsername(username);
        user.setStarredRepos(userService.starredRepos(username));
        model.addAttribute("user",user);
        return "starredProjects";
    }

    @PostMapping("/followings")
    public String followings(Model model, String username,String UnfollowUser,Integer isUnfollow) throws IOException, InterruptedException {
        if(!Objects.isNull(isUnfollow)){
            serverService.unfollow(username,UnfollowUser);
        }
        User user=new User();
        user.setUsername(username);
        user.setFollowingUsers(userService.setFollowingUserList(username));
        model.addAttribute("user",user);
        return "followings";
    }


}
