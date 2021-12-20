package com.sample.services;
import com.sample.configuration.Database;
import com.sample.model.User;
import com.sample.model.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
public class ServerService {
    @Autowired
    private  JdbcTemplate jdbctemplate;



    public void AddUser(String username, String GitUsername, String password){

        jdbctemplate.update(
                "insert into user_1 (username, github_username,password)"  +
                        "values ('"+username+"','"+GitUsername+"','"+password+"')"
        );

    }
    @Autowired
    JdbcTemplate conn;
    //There are 2 usernames,one is for our site,one is for the user's account name at GitHub
    public String getGitUsername(String username){
      /* List<Map<String, Object>> response = conn.queryForList( //query'de hata alıyoruz
                "SELECT github_username FROM user_1 where username = ?", new Object[]{username}
        );*/

       // String sql = "SELECT github_username FROM user_1 where username = ?";
       /* jdbctemplate.query(
                "SELECT github_username FROM user_1 where username = ?"
        ); */
        //String git = jdbctemplate.queryForObject(sql, String.class);
        //return git;
        /*return jdbctemplate.queryForObject(
                sql, new Object[]{username}, String.class);*/
       /* var sql = "SELECT COUNT(*) FROM user_1";

        int numOfCars = jdbctemplate.queryForObject(sql, Integer.class);
        System.out.println(numOfCars);
        return "kaanapak"; */
System.out.println("k "+username);
        List<Map<String, Object>> response = jdbctemplate.queryForList(
                "SELECT github_username FROM user_1 where username = ?", new Object[]{username}
        );
        System.out.println("Response "+response.get(0).get("github_username"));
        return String.valueOf(response.get(0).get("github_username"));


    }
    public Boolean isGitFirst(String GitUsername){
       /* jdbctemplate.update(
                "SELECT github_username FROM user_1 where github_username = ?"
        );
        if(jdbctemplate==null)
            return false;*/
        return true;
    }

    public void DeleteUser(String username){
        String deleteQuery = "delete from user_1 where username = ?";
        jdbctemplate.update(deleteQuery,new Object[]{username});
    }

    //Checks if there is a user with that username
    public Boolean ısFirst(String username){
        //if(conn == null)
           // return false;
        List<Map<String, Object>> response = conn.queryForList(
                "SELECT username FROM user_1 where username = ?", new Object[]{username}
        );

        if(response==null)
            return false;
        else
            return true;
    }

    public Boolean PasswordCheck(String username,String password){

        List<Map<String, Object>> response = conn.queryForList(
                "SELECT username,password FROM user_1 where username = ? and password = ?", new Object[]{username,password}
        );
        if(response.size()==0)
            return false;
        else
            return true;


    }

    public void ChangePassword(String username,String oldPassword,String newPassword){

    }

    //Return list of username list
    public ArrayList <String> UserNameList(){
        ArrayList<String> UserNameList=new ArrayList<>();
        List<Map<String, Object>> response = conn.queryForList(
                "SELECT username FROM user_1");
        for(int i = 0; i < response.size(); i++){
            UserNameList.add(String.valueOf(response.get(i).get("username")));
        }
        System.out.println("ResponseU "+response);

        return  UserNameList;
    }

    //Adds starred repo to that username. In database there will be a list of starred repositories for each user.
    //We will hold only ID of each starred repository
    public void addStarredRepo(String username,String repository_Id){
        jdbctemplate.update(
                "insert into repo (username, repo_id)"  +
                        "values ('"+username+"','"+repository_Id+"')"
        );
    }

    public void removeStarredRepo(String username,String repository_Id){
        String deleteQuery = "delete from repo where username = ? and repo_id = ?";
        jdbctemplate.update(deleteQuery,new Object[]{username,repository_Id});
    }

    public ArrayList<String> getStarredRepoList(String username){
        ArrayList<String> RepoList=new ArrayList<>();
        List<Map<String, Object>> response = conn.queryForList(
                "SELECT repo_id FROM repo where username = ?", new Object[]{username}
        );
        for(int i = 0; i < response.size(); i++){
            RepoList.add(String.valueOf(response.get(i).get("repo_id")));
        }
        System.out.println("ResponseU "+response);
        //return String.valueOf(response.get(0).get("github_username"));

        return RepoList;
    }

    //Checks if id is in that user's starredrepo list
    public Boolean isStarred(String username,String Id){
        List<Map<String, Object>> response = conn.queryForList(
                "SELECT username,repo_id FROM repo where username = ? and repo_id = ?", new Object[]{username,Id}
        );
        if(response.size()==0)
            return false;
        else
            return true;
    }

    //We can hold username of each following user
    public void addfollowing(String username,String new_following_user){
        jdbctemplate.update(
                "insert into follow (username, following)"  +
                        "values ('"+username+"','"+new_following_user+"')"
        );
    }

    public Boolean isFollowing (String username,String FollowingUsername){
        List<Map<String, Object>> response = conn.queryForList(
                "SELECT username,following FROM follow where username = ? and following = ?", new Object[]{username,FollowingUsername}
        );
        if(response.size()==0)
            return false;
        else
            return true;


    }
    public void unfollow(String username,String removinguser){
       /* jdbctemplate.update(
                "delete from follow (username, following)"  +
                        "values ('"+username+"','"+GitUsername+"','"+password+"')"
        ); */
       /* String sql = jdbctemplate.update(
                "delete FROM follow where username =" +  username + "and following =" + removinguser
        );
        jdbctemplate.execute(sql); */
        String deleteQuery = "delete from follow where username = ? and following = ?";
        jdbctemplate.update(deleteQuery,new Object[]{username,removinguser});
    }

    public ArrayList<String> getFollowingList(String username){
        ArrayList<String> FollowingList=new ArrayList<>();
        List<Map<String, Object>> response = conn.queryForList(
                "SELECT following FROM follow where username = ?", new Object[]{username}
        );
        for(int i = 0; i < response.size(); i++){
            FollowingList.add(String.valueOf(response.get(i).get("following")));
        }
        System.out.println("ResponseU "+response);
        //return String.valueOf(response.get(0).get("github_username"));
        return FollowingList;
    }

}

