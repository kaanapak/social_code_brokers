package com.sample.controller;

import com.sample.model.UserSystem;
import com.sample.services.APIService;
import com.sample.services.ServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sample.model.User;
import com.sample.services.UserService;

import java.util.Objects;

@Controller
public class ProfileController {
    @Autowired
    ServerService serverService=new ServerService();
    APIService apıService=new APIService();
    UserService userService=new UserService();

    @PostMapping("/profile")
    public String profile(Model model,String username) {
        User user=new User();
        user.setUsername(username);
        user.setScore(userService.getScore(username));
        user.setRepoList(userService.getRepoList(username));
        model.addAttribute("user",user);
        return "profile";
    }

    @PostMapping("/searchedUser")
    public String searchedUser(Model model,String username,String searchedUsername,Integer IsAddStar,Integer IsRemoveStar,String StarredId,Integer IsFollow,Integer IsUnFollow) {
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
        searchedUser.setIsFollowing(userService.getIsFollowing(username,searchedUsername));
        searchedUser.setUsername(searchedUsername);
        searchedUser.setRepoList(userService.getRepoList(username));
        model.addAttribute("searchedUser",user);

        return "searchedUser";
    }
}
