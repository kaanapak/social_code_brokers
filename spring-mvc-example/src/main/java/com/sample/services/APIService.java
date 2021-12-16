package com.sample.services;

import java.io.DataInput;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.stereotype.Service;

import com.sample.model.Repository;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class APIService {
public static void main (String [] args) throws IOException, InterruptedException, JSONException {
    ArrayList<Repository> repo=RepoList("kaanapak");
//Integer a=CodeCount("kaanapak");


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
    public static ArrayList<Repository> RepoList(String GitUsername) throws IOException, InterruptedException, JSONException {
        ArrayList<Repository>RepoList=new ArrayList<>();
        String url = "https://api.github.com/users/"+GitUsername+"/repos";
        HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(url)).build();
        HttpClient client = HttpClient.newBuilder().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        ArrayList<String> listdata = new ArrayList<String>();
        JSONArray jsonArr = new JSONArray(response.body());
        for (int i = 0; i < jsonArr.length(); i++)
        {
          String input= String.valueOf(jsonArr.getJSONObject(i));
            Map<String, Object> response_map = new ObjectMapper().readValue(input, HashMap.class);
            String RepoName= (String) response_map.get("name");
            String date= (String) response_map.get("updated_at");
           String ıd= String.valueOf(response_map.get("id"));

            String new_url= (String) response_map.get("languages_url");
            String language=getLanguage(new_url);
            Repository repository=new Repository(date,language,ıd,RepoName);
            RepoList.add(repository);

        }

     /*      for(int i=0;i<ResponseList.size();i++){

            Map<String, Object> response_map = new ObjectMapper().readValue(ResponseList.get(i), HashMap.class);
            String RepoName= (String) response_map.get("name");
            String date= (String) response_map.get("updated_at");
            String ıd=(String) response_map.get("id");
            String new_url= (String) response_map.get("languages_url");
            String language=getLanguage(new_url);
            Repository repository=new Repository(date,language,ıd,RepoName);
            RepoList.add(repository);
        }*/
        System.out.println(response.body());
        return  RepoList;
    }

    //number of followers at GitHub
    public static Integer FollowerNumber(String GitUsername){
        Integer Reponumber=0; //Change this!
        return Reponumber;
    }

    public  static Integer CodeCount(String GitUsername) throws IOException, InterruptedException {
        String url = "https://api.github.com/users/"+GitUsername;
        HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(url)).build();
        HttpClient client = HttpClient.newBuilder().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
        Map<String, Object> response_map = new ObjectMapper().readValue(response.body(), HashMap.class);
        Integer CodeCount= (Integer) response_map.get("public_repos");
        return CodeCount;
    }

    public  Integer LanguageCount(String GitUsername){
        Integer LanguageCount=0;//Change this!
        return  LanguageCount;
    }

    public Integer ScoreCalculator(String GitUsername) throws IOException, InterruptedException {
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
    public   Repository getRepository(String RepositoryId) throws IOException, InterruptedException {
        String url = "https://api.github.com/repositories/"+RepositoryId;
        HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(url)).build();
        HttpClient client = HttpClient.newBuilder().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        Map<String, Object> response_map = new ObjectMapper().readValue(response.body(), HashMap.class);
        System.out.println(response.body());

        String RepoName= (String) response_map.get("name");
        String date= (String) response_map.get("updated_at");
        String ıd=RepositoryId;

        String new_url= (String) response_map.get("languages_url");
       String language=getLanguage(new_url);

        Repository repository=new Repository(date,language,ıd,RepoName);
        return repository;
    }

    public static String getLanguage(String url) throws IOException, InterruptedException {

        HttpRequest request2 = HttpRequest.newBuilder().GET().uri(URI.create(url)).build();
        HttpClient client2 = HttpClient.newBuilder().build();
        HttpResponse<String> response2 = client2.send(request2, HttpResponse.BodyHandlers.ofString());
        Map<String, Object> response_map2 = new ObjectMapper().readValue(response2.body(), HashMap.class);
        System.out.println(response2.body());
        List<String> keys = new ArrayList<>(response_map2.keySet());
        return String.join(", ", keys);
    }
}
