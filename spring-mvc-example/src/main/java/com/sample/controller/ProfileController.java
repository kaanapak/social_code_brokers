package com.sample.controller;

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
import java.util.Objects;

@Controller
public class ProfileController {
    @Autowired
    ServerService serverService=new ServerService();
    APIService apıService=new APIService();
    UserService userService=new UserService();

    @PostMapping("/profile")
    public String profile(Model model,String username) throws IOException, InterruptedException, JSONException {
        User user=new User();
        user.setUsername(username);
        user.setScore(userService.getScore(username));
        user.setRepoList(userService.getRepoList(username));
        model.addAttribute("user",user);
        return "profile";
    }

    @PostMapping("/searchedUser")
    public String searchedUser(Model model,String username,String searchedUsername,Integer isAddStar,Integer isRemoveStar,String StarredId,Integer isFollow,Integer isUnFollow) throws IOException, InterruptedException, JSONException {
        if(!Objects.isNull(isFollow)){
            serverService.addfollowing(username,searchedUsername);
        }else if(!Objects.isNull(isUnFollow)){
            serverService.unfollow(username,searchedUsername);
        }else if(!Objects.isNull(isAddStar)){
            serverService.addStarredRepo(username,StarredId);
        }else if(!Objects.isNull(isRemoveStar)){
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
