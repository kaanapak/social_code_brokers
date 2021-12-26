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


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import com.sample.model.Error;

@Controller
public class EnteredUserController {

    @Autowired
    ServerService serverService = new ServerService();
    APIService apıService = new APIService();
    @PostMapping("/index")
    public String index(Model model) {

        return "index";
    }

    @PostMapping("/signUp")
    public String signUp(Model model) {

        Error error = new Error("K");
        model.addAttribute("error",error);

        return "signUp";
    }

  // @RequestMapping("/mainPage")
    @PostMapping("/mainPage")

    public String mainPage(Model model, String username, String password, String GitUsername, String StarredId, Integer IsSıngIn, Integer IsSıgnup, Integer IsAddStar, Integer IsRemoveStar) throws IOException, InterruptedException, JSONException {
        boolean directMainPage = true;
        String returnpage = "";

        if (!Objects.isNull(IsSıngIn)) {
           //serverService.addfollowing("emaden99github","oguzhangithub");
           //System.out.println("check: "+serverService.isFollowing("emaden99github","oguzhangithub"));

            if (!serverService.PasswordCheck(username, password)) {
                directMainPage = false;
                Error error = new Error("Wrong username/password");
                model.addAttribute("error",error);
                return "index";
            }else{
                UserSystem system = new UserSystem();
                system.setUserNameList(serverService.UserNameList());
                model.addAttribute("system", system);
                User user = new User();
                user.setUsername(username);
                ArrayList<Repository> FollowingRepos = new ArrayList<>();
                ArrayList<String> followings = serverService.getFollowingList(username);
                for (int i = 0; i < followings.size(); i++) {
                    String gitUsername = serverService.getGitUsername(followings.get(i));
                    System.out.println(i + " " + gitUsername);
                    Repository repo= apıService.LastRepository(gitUsername);
                    repo.setOwner(followings.get(i));
               if(serverService.isStarred(username,repo.getId())){
                    repo.setStarred();
                }
                    FollowingRepos.add(repo);
                }
                user.setFollowingsRepos(FollowingRepos);
                model.addAttribute("user", user);
                Error error = new Error("K");
                model.addAttribute("error",error);

                return "mainPage";
            }
        } else if (!Objects.isNull(IsSıgnup)) {
            if (!serverService.ısFirst(username)) {
                System.out.println("There is a user with same username");
                directMainPage = false;
                Error error = new Error("There is a user with same username");
                model.addAttribute("error",error);
                return "signUp";
            } else if (!apıService.isGithub(GitUsername)) {
                System.out.println("It isn't a valid github username");
                directMainPage = false;
                Error error = new Error("It isn't a valid github username");
                model.addAttribute("error",error);
                return "signUp";
            } else {
                serverService.AddUser(username, GitUsername, password);
                UserSystem system = new UserSystem();
                system.setUserNameList(serverService.UserNameList());
                model.addAttribute("system", system);
                User user = new User();
                user.setUsername(username);
                ArrayList<Repository> FollowingRepos = new ArrayList<>();
                ArrayList<String> followings = serverService.getFollowingList(username);
                for (int i = 0; i < followings.size(); i++) {
                    String gitUsername = serverService.getGitUsername(followings.get(i));
                    System.out.println(i + " " + gitUsername);
                    Repository repo= apıService.LastRepository(gitUsername);
                    repo.setOwner(followings.get(i));
                    if(serverService.isStarred(username,repo.getId())){
                        repo.setStarred();
                    }
                    FollowingRepos.add(repo);
                }
                user.setFollowingsRepos(FollowingRepos);
                model.addAttribute("user", user);
                Error error = new Error("K");
                model.addAttribute("error",error);

                return "mainPage";
            }


        } else {
            if (!Objects.isNull(IsAddStar)) {
                serverService.addStarredRepo(username, StarredId);
            } else if (!Objects.isNull(IsRemoveStar)) {
                serverService.removeStarredRepo(username, StarredId);
            }
            UserSystem system = new UserSystem();
            system.setUserNameList(serverService.UserNameList());
            model.addAttribute("system", system);
            User user = new User();
            user.setUsername(username);
            ArrayList<Repository> FollowingRepos = new ArrayList<>();
            ArrayList<String> followings = serverService.getFollowingList(username);
            for (int i = 0; i < followings.size(); i++) {
                String gitUsername = serverService.getGitUsername(followings.get(i));
                System.out.println(i + " " + gitUsername);
                Repository repo= apıService.LastRepository(gitUsername);
                repo.setOwner(followings.get(i));
                if(serverService.isStarred(username,repo.getId())){
                    repo.setStarred();
                }
                FollowingRepos.add(repo);
            }
            user.setFollowingsRepos(FollowingRepos);
            model.addAttribute("user", user);
            Error error = new Error("K");
            model.addAttribute("error",error);

            return "mainPage";
        }

    }








}
