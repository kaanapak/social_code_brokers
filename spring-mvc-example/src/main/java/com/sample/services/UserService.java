package com.sample.services;

import java.io.IOException;
import java.util.ArrayList;

import com.sample.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sample.model.Repository;

@Service
public class UserService {
    @Autowired
    ServerService serverService=new ServerService();
    APIService apıService=new APIService();


public String getGitHubUsername(String username){
    return serverService.getGitUsername(username);
}
public ArrayList <Repository> getRepoList (String username) throws IOException, InterruptedException {
    String gitUsername=serverService.getGitUsername(username);
    ArrayList <Repository> RepoList= apıService.RepoList(gitUsername);

    for(int i=0;i<RepoList.size();i++){
        if(serverService.isStarred(username,RepoList.get(i).getId())){
            RepoList.get(i).setStarred();
        }
    }
    return RepoList;
}

public ArrayList <String> getFollowings (String username){
    ArrayList <String> followings = serverService.getFollowingList(username);
    return followings;
}

public ArrayList<Repository> starredRepos (String username) throws IOException, InterruptedException {
    ArrayList <String> StarredId= serverService.getStarredRepoList(username);
    ArrayList <Repository> StarredRepos=new ArrayList<>();
for(int i=0;i<StarredId.size();i++){
    Repository repo= apıService.getRepository(StarredId.get(i));
    repo.setStarred();
    StarredRepos.add(repo);
}
    return StarredRepos;
}

public ArrayList <Repository> FollowingRepos(String username){
    String gitUsername=serverService.getGitUsername(username);
    ArrayList <String> followings=getFollowings(username);
    ArrayList <Repository> FollowingRepos=new ArrayList<>();
    for(int i=0;i<followings.size();i++){
        Repository repo= apıService.LastRepository(gitUsername);
        if(serverService.isStarred(username,repo.getId())){
            repo.setStarred();
        }
        FollowingRepos.add(repo);
    }
    return FollowingRepos;
}

public Integer getScore(String username) throws IOException, InterruptedException {
    String gitUsername=serverService.getGitUsername(username);
    return apıService.ScoreCalculator(gitUsername);
}

public Integer getIsFollowing(String username,String FollowingUsername){
    Integer ısFollowing=0;
    if(serverService.isFollowing(username,FollowingUsername)){
        ısFollowing=1;
    }
    return ısFollowing;
}

    public ArrayList<User> setFollowingUserList (String username) throws IOException, InterruptedException {
        ArrayList<User> UserList =new ArrayList<>();
        ArrayList <String> FollowingNames=getFollowings(username);
        for(int i=0;i<FollowingNames.size();i++){
            String currentUsername=FollowingNames.get(i);
            User user=new User();
            user.setUsername(currentUsername);
            user.setScore(getScore(currentUsername));
            UserList.add(user);
        }
        return UserList;

    }
}
