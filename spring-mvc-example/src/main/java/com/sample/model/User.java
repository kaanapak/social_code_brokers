package com.sample.model;

import java.util.ArrayList;

public class User {
    private String username;
    private String GitHubUsername;
    private Integer isFollowing;
    private ArrayList<Repository> RepoList=new ArrayList<>();
    private ArrayList<String> Followings=new ArrayList<>();
    private ArrayList <User> FollowingUsers=new ArrayList<>();
    private ArrayList<Repository> StarredRepos=new ArrayList<>();
    private ArrayList<Repository> FollowingsRepos=new ArrayList<>();
    private  double score;


    public User(){}
    public User(String username,String GitHubUsername,ArrayList<Repository> RepoList,ArrayList<String> Followings,ArrayList<Repository> StarredRepos,ArrayList<Repository> FollowingsRepos,double score){
this.username=username;
this.GitHubUsername=GitHubUsername;
this.RepoList=RepoList;
this.Followings=Followings;
this.StarredRepos=StarredRepos;
this.FollowingsRepos=FollowingsRepos;
this.score=score;
    }

    public String getUsername() {
        return username;
    }
    public ArrayList<Repository> getRepoList() {
        return RepoList;
    }
    public ArrayList<String> getFollowings() {
        return Followings;
    }
    public ArrayList<Repository> getStarredRepos() {
        return StarredRepos;
    }
    public ArrayList<Repository> getFollowingsRepos() {
        return FollowingsRepos;
    }
    public double getScore() {
        return score;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setRepoList(ArrayList<Repository> repoList) {
        RepoList = repoList;
    }
    public void setFollowings(ArrayList<String> followings) {
        Followings = followings;
    }
    public void setStarredRepos(ArrayList<Repository> starredRepos) {
        StarredRepos = starredRepos;
    }
    public void setFollowingsRepos(ArrayList<Repository> followingsRepos) {
        FollowingsRepos = followingsRepos;
    }
    public void setScore(double score) {
        this.score = score;
    }
    public Integer getIsFollowing() {
        return isFollowing;
    }
    public void setIsFollowing(Integer isFollowing) {
        isFollowing = isFollowing;
    }
    public void setFollowingUsers(ArrayList<User> followingUsers) {
        FollowingUsers = followingUsers;
    }
    public String toStringFollowingUsers(){
        StringBuilder text_a = null;
        for(int i=0;i<FollowingUsers.size();i++){
          text_a.append(FollowingUsers.get(i).toStringUserFollowing());
            if(i!=FollowingUsers.size()-1){
                text_a.append("*");
            }
        }
        return String.valueOf(text_a);
    }
    public String toStringUserFollowing(){
        return username+"|"+score;
    }
    public String toStringStarredRepos(){
        StringBuilder text_a = null;
        for(int i=0;i<StarredRepos.size();i++){
            text_a.append(StarredRepos.get(i).toString());
            if(i!=StarredRepos.size()-1){
                text_a.append("*");
            }
        }
        return String.valueOf(text_a);
    }
    public String toStringRepoList(){
        StringBuilder text_a = null;
        for(int i=0;i<RepoList.size();i++){
            text_a.append(RepoList.get(i).toString());
            if(i!=RepoList.size()-1){
                text_a.append("*");
            }
        }
        return String.valueOf(text_a);
    }
    public String toStringFollowingRepos(){
        StringBuilder text_a = null;
        for(int i=0;i<FollowingsRepos.size();i++){
            text_a.append(FollowingsRepos.get(i).toString());
            if(i!=FollowingsRepos.size()-1){
                text_a.append("*");
            }
        }
        return String.valueOf(text_a);
    }

}
