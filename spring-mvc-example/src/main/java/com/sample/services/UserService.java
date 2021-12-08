package com.sample.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sample.model.Repository;

@Service
public class UserService {
    @Autowired
    ServerService serverService=new ServerService();
    APIService apıService=new APIService();


public void try_user(){


}
public String getGitHubUsername(String username){
    return serverService.getGitUsername(username);
}
public ArrayList <Repository> getRepoList (String username){
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

public ArrayList<Repository> starredRepos (String username){
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

public Integer getScore(String username){
    String gitUsername=serverService.getGitUsername(username);
    return apıService.ScoreCalculator(gitUsername);
}
}
