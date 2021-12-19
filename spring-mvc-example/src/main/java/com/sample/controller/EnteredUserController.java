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

import java.io.IOException;
import java.util.Objects;

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
    @RequestMapping("/mainPage")
    @PostMapping("/mainPage")
    public String mainPage(Model model,String username,String password,String GitUsername,String StarredId, Integer IsSıngIn,Integer IsSıgnup, Integer IsAddStar,Integer IsRemoveStar) throws IOException, InterruptedException {
boolean directMainPage=true;
String returnpage="";

        if(!Objects.isNull(IsSıngIn)){
            serverService.isGitFirst("kaanapak");
            System.out.println("Entered sign in");
            System.out.println(username);
            System.out.println(password);
            if(!serverService.PasswordCheck(username,password)){
                directMainPage=false;
                Error error=new Error("Wrong username/password");
                model.addAttribute(error);
                returnpage="index";
            }
        }else if(!Objects.isNull(IsSıgnup)){

            System.out.println("Entered sign up");
            System.out.println(username);
            System.out.println(password);
            System.out.println(GitUsername);
            if(!serverService.ısFirst(username)){
                System.out.println("There is a user with same username");
                directMainPage=false;
                Error error=new Error("There is a user with same username");
                model.addAttribute(error);
              returnpage="signUp";
            } else if(!apıService.isGithub(GitUsername)){
                System.out.println("It isn't a valid github username");
                directMainPage=false;
                Error error=new Error("It isn't a valid github username");
                model.addAttribute(error);
                returnpage="signUp";
            }else{
                serverService.AddUser(username, GitUsername, password);
            }
        }else if(!Objects.isNull(IsAddStar)){
            serverService.addStarredRepo(username,StarredId);
        }else if(!Objects.isNull(IsRemoveStar)){
            serverService.removeStarredRepo(username,StarredId);
        }

        if(directMainPage){
            UserSystem system=new UserSystem();
           // system.setUserNameList(serverService.UserNameList());
            model.addAttribute("system",system);
            User user=new User();
           // user.setFollowingsRepos(userService.FollowingRepos(username));
            model.addAttribute("user",user);
            returnpage="mainPage";

        }
        return returnpage;
    }
}
