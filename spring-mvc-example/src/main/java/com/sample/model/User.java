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
    private String StringFollowingRepos;
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

    public ArrayList<User> getFollowingUsers() {
        return FollowingUsers;
    }

    public void setRepoList(ArrayList<Repository> repoList) {
        this.RepoList = repoList;
    }
    public void setFollowings(ArrayList<String> followings) {
        this.Followings = followings;
    }
    public void setStarredRepos(ArrayList<Repository> starredRepos) {
        this.StarredRepos = starredRepos;
    }
    public void setFollowingsRepos(ArrayList<Repository> followingsRepos) {
       this.FollowingsRepos = followingsRepos;
    }
    public void setScore(double score) {
        this.score = score;
    }
    public Integer getIsFollowing() {
        return isFollowing;
    }
    public void setIsFollowing(Integer isFollowing) {
        this.isFollowing = isFollowing;
    }
    public void setFollowingUsers(ArrayList<User> followingUsers) {
        FollowingUsers = followingUsers;
    }
    public String toStringFollowingUsers(){
        String text_a ="";
        for(int i=0;i<FollowingUsers.size();i++){
          text_a+=FollowingUsers.get(i).toStringUserFollowing();
            if(i!=FollowingUsers.size()-1){
                text_a+="*";
            }
        }
        return String.valueOf(text_a);
    }
    public String toStringUserFollowing(){
        return username+"|"+GitHubUsername+"|"+score;
    }
    public String toStringStarredRepos(){
        String text_a = "";
        for(int i=0;i<StarredRepos.size();i++){
            text_a+=StarredRepos.get(i).toString();
            if(i!=StarredRepos.size()-1){
                text_a+="*";
            }
        }
        return String.valueOf(text_a);
    }
    public String toStringRepoList(){
        String text_a = "";
        for(int i=0;i<RepoList.size();i++){
            text_a+=RepoList.get(i).toString();
            if(i!=RepoList.size()-1){
                text_a+="*";
            }
        }
        return String.valueOf(text_a);
    }
    public String toStringFollowingRepos(){
        String text_a = "";
        for(int i=0;i<FollowingsRepos.size();i++){
            text_a+=FollowingsRepos.get(i).toString();
            if(i!=FollowingsRepos.size()-1){
                text_a+="*";
            }
        }
        System.out.println("kkkkk: "+String.valueOf(text_a));
        return String.valueOf(text_a);
    }

    public void setStringFollowingRepos(String stringFollowingRepos) {
        StringFollowingRepos = stringFollowingRepos;
    }

    public String getStringFollowingRepos() {
        return StringFollowingRepos;
    }

    public String getGitHubUsername() {
        return GitHubUsername;
    }

    public void setGitHubUsername(String gitHubUsername) {
        GitHubUsername = gitHubUsername;
    }
}
