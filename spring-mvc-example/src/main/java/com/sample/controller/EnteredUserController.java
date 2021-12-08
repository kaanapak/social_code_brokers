package com.sample.controller;

import com.sample.model.UserSystem;
import com.sample.services.APIService;
import com.sample.services.ServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sample.model.User;
import com.sample.services.UserService;

@Controller
public class EnteredUserController {

    @Autowired
    ServerService serverService=new ServerService();
    APIService apıService=new APIService();
    UserService userService=new UserService();

    @PostMapping("/signUp")
    public String signUp(Model model) {

        return "signUp";
    }

    @PostMapping("/mainPage")
    public String mainPage(Model model,@RequestParam(name="username", required=false, defaultValue="World") String username,@RequestParam(name="password", required=false, defaultValue="World") String password
            , @RequestParam(name="gitUserName", required=false, defaultValue="World") String GitUsername,@RequestParam(name="starredID", required=false, defaultValue="World") String StarredId
            , Integer M,@RequestParam(name="ısSıgnUp", required=false, defaultValue="0") Integer IsSıgnup
            , @RequestParam(name="ısAddStar", required=false, defaultValue="0") Integer IsAddStar,@RequestParam(name="ısRemoveStar", required=false, defaultValue="0") Integer IsRemoveStar){
boolean directMainPage=true;
String returnpage="";

        if(username.equals("Kaan")){
            System.out.println("Entered here");
            if(!serverService.PasswordCheck(username,password)){
                directMainPage=false;
                Error error=new Error("Wrong username/password");
                model.addAttribute(error);
                returnpage="index";
            }
        }else if(IsSıgnup==1){
            if(!serverService.ısFirst(username)){
                directMainPage=false;
                Error error=new Error("There is a user with same username");
                model.addAttribute(error);
              returnpage="signUp";
            } else if(!apıService.isGithub(GitUsername)){
                directMainPage=false;
                Error error=new Error("It isn't a valid github username");
                model.addAttribute(error);
                returnpage="signUp";
            }else{
                serverService.AddUser(username, GitUsername, password);
            }
        }else if(IsAddStar==1){
            serverService.addStarredRepo(username,StarredId);
        }else if(IsRemoveStar==1){
            serverService.removeStarredRepo(username,StarredId);
        }

        if(directMainPage){
            UserSystem system=new UserSystem(serverService.UserNameList());
            model.addAttribute(system);
            User user=new User();
            user.setFollowingsRepos(userService.FollowingRepos(username));
            model.addAttribute(user);
            returnpage="mainPage";

        }
        return returnpage;
    }
}
