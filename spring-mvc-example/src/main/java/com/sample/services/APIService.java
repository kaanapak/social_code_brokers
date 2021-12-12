package com.sample.services;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.sample.model.Repository;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class APIService {
public static void main (String [] args) throws IOException, InterruptedException {

    HttpResponse<String> response=APIReturn("kaanapak");
System.out.println(response.body());
    }

    public static HttpResponse<String>  APIReturn(String username) throws IOException, InterruptedException {

        String url = "https://api.github.com/users/"+username+"/repos";
        HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(url)).build();
        HttpClient client = HttpClient.newBuilder().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return response;
    }

    //Is there a github account with that username

    public Boolean isGithub(String GitUsername) {

        return true;
    }

    //  Repository object has attriutes name,ıd,date,language and is_starred.(Please look to model/Repository).
    // But you can create repository using new Repository(String name,String ıd,String date) by default is_starred is 0
    //This method finds all repositories of that user
    public ArrayList<Repository> RepoList(String GitUsername){
        ArrayList<Repository>RepoList=new ArrayList<>();


        return  RepoList;
    }

    //number of followers at GitHub
    public Integer FollowerNumber(String GitUsername){
        Integer Reponumber=0; //Change this!
        return Reponumber;
    }

    public Integer CodeCount(String GitUsername){
        Integer CodeCount=0;//Change this!
        return CodeCount;
    }

    public  Integer LanguageCount(String GitUsername){
        Integer LanguageCount=0;//Change this!
        return  LanguageCount;
    }

    public Integer ScoreCalculator(String GitUsername){
        Integer Score=10*FollowerNumber(GitUsername)+CodeCount(GitUsername)*15+LanguageCount(GitUsername)*5;
        //Change coefficients!
        return Score;
    }

//Gives the last repository of that user
    public Repository LastRepository(String GitUsername){
        String RepoName="";
        String date="";
        String ıd="";
        String language="";


        Repository LastRepository=new Repository(date,language,ıd,RepoName);
return LastRepository;
    }

    //Find repository information from ID
    public Repository getRepository(String RepositoryId){
        String RepoName="";
        String date="";
        String ıd="";
        String language="";

        Repository repository=new Repository(date,language,ıd,RepoName);
        return repository;
    }
}
