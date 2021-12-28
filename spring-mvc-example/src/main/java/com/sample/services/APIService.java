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

//Integer a=CodeCount("kaanapak");




    }



    //Is there a github account with that username

    public String getToken(){
        String token="Bearer " + "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";
        return token;
    }

    public Boolean isGithub(String GitUsername) throws IOException, InterruptedException {
        String url = "https://api.github.com/users/" + GitUsername+"/?access_token=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";
       //HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(url)).build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create( "https://api.github.com/users/" + GitUsername))
                .GET()
                .header("Authorization",getToken())
                .header("Content-Type", "application/json")
                .build();
        HttpClient client = HttpClient.newBuilder().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        Map<String, Object> response_map = new ObjectMapper().readValue(response.body(), HashMap.class);
        if (response_map.containsKey("message") && response_map.get("message").equals("Not Found")) {
            return false;
        } else {
            return true;
        }
    }

    //  Repository object has attriutes name,ıd,date,language and is_starred.(Please look to model/Repository).
    // But you can create repository using new Repository(String name,String ıd,String date) by default is_starred is 0
    //This method finds all repositories of that user
    public  ArrayList<Repository> RepoList(String GitUsername) throws IOException, InterruptedException, JSONException {

        ArrayList<Repository>RepoList=new ArrayList<>();
        String url = "https://api.github.com/users/"+GitUsername+"/repos/?access_token=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\";";

        //HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(url)).build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.github.com/users/"+GitUsername+"/repos"))
                .GET()
                .header("Authorization",getToken())
                .header("Content-Type", "application/json")
                .build();
        HttpClient client = HttpClient.newBuilder().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        ArrayList<String> listdata = new ArrayList<String>();
        System.out.println(response.body());
        JSONArray jsonArr = new JSONArray(response.body());
        for (int i = 0; i < jsonArr.length(); i++)
        {
            String input= String.valueOf(jsonArr.getJSONObject(i));
            Map<String, Object> response_map = new ObjectMapper().readValue(input, HashMap.class);
            String RepoName= (String) response_map.get("name");
            String time= (String) response_map.get("updated_at");
            String date1=time.substring(0,10);
            String hour=time.substring(11,16);
            String date=date1+" "+hour;
            String ıd= String.valueOf(response_map.get("id"));

            String new_url= (String) response_map.get("languages_url");
            ArrayList<String> languageList=getLanguage(new_url);
            Repository repository=new Repository(date,languageList,ıd,RepoName);
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
        return  RepoList;
    }

    //number of followers at GitHub
    public Integer FollowerNumber(String GitUsername) throws IOException, InterruptedException {
        String url = "https://api.github.com/users/" + GitUsername+"/?access_token=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";;
      //  HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(url)).build();
       HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.github.com/users/" + GitUsername))
                .GET()
                .header("Authorization", getToken())
                .header("Content-Type", "application/json")
                .build();
        HttpClient client = HttpClient.newBuilder().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        Map<String, Object> response_map = new ObjectMapper().readValue(response.body(), HashMap.class);

        Integer followers= (Integer) response_map.get("followers");
        return followers;
    }

    public  Integer CodeCount(String GitUsername) throws IOException, InterruptedException {
        String url = "https://api.github.com/users/"+GitUsername+"/?access_token=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";;
        //HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(url)).build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.github.com/users/" + GitUsername))
                .GET()
                .header("Authorization", getToken())
                .header("Content-Type", "application/json")
                .build();
        HttpClient client = HttpClient.newBuilder().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        Map<String, Object> response_map = new ObjectMapper().readValue(response.body(), HashMap.class);
        Integer CodeCount= (Integer) response_map.get("public_repos");
        return CodeCount;
    }

    public Integer LanguageCount(String GitUsername) throws JSONException, IOException, InterruptedException {
        String url = "https://api.github.com/users/" + GitUsername + "/repos"+"/?access_token=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";
     // HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(url)).build();
      HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create( "https://api.github.com/users/" + GitUsername + "/repos"))
                .GET()
                .header("Authorization", getToken())
                .header("Content-Type", "application/json")
                .build();
        HttpClient client = HttpClient.newBuilder().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        JSONArray jsonArr = new JSONArray(response.body());
        int LanguageCount = 0;
        for (int i = 0; i < jsonArr.length(); i++) {
            String input = String.valueOf(jsonArr.getJSONObject(i));
            Map<String, Object> response_map = new ObjectMapper().readValue(input, HashMap.class);

            String new_url = (String) response_map.get("languages_url");
            ArrayList<String> languageList = getLanguage(new_url);

            LanguageCount = languageList.size();

        }
        return LanguageCount;
    }

    public Integer ScoreCalculator(String GitUsername) throws IOException, InterruptedException, JSONException {
        Integer Score=10*FollowerNumber(GitUsername)+CodeCount(GitUsername)*15+LanguageCount(GitUsername)*5;
        //Change coefficients!
        return Score;
    }

    //Gives the last repository of that user
    public Repository LastRepository(String GitUsername) throws InterruptedException, JSONException, IOException {

        System.out.println("lasteespo" +RepoList(GitUsername).get(0) );
        return RepoList(GitUsername).get(0);
    }

    //Find repository information from ID
    public   Repository getRepository(String RepositoryId) throws IOException, InterruptedException {
        String url = "https://api.github.com/repositories/"+RepositoryId+"/?access_token=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";
        //HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(url)).build();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.github.com/repositories/"+RepositoryId))
                .GET()
                .header("Authorization", getToken())
                .header("Content-Type", "application/json")
                .build();
        HttpClient client = HttpClient.newBuilder().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        Map<String, Object> response_map = new ObjectMapper().readValue(response.body(), HashMap.class);

        String RepoName= (String) response_map.get("name");
        String time= (String) response_map.get("updated_at");
        String date1=time.substring(0,10);
        String hour=time.substring(11,16);
        String date=date1+" "+hour;
        String ıd=RepositoryId;

        String new_url= (String) response_map.get("languages_url");
        ArrayList<String> language=getLanguage(new_url);

        Repository repository=new Repository(date,language,ıd,RepoName);
        return repository;
    }

    public ArrayList<String> getLanguage(String url) throws IOException, InterruptedException {

       // HttpRequest request2 = HttpRequest.newBuilder().GET().uri(URI.create(url)).build();
       HttpRequest request2 = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .header("Authorization", getToken())
                .header("Content-Type", "application/json")
                .build();
        HttpClient client2 = HttpClient.newBuilder().build();
        HttpResponse<String> response2 = client2.send(request2, HttpResponse.BodyHandlers.ofString());
        Map<String, Object> response_map2 = new ObjectMapper().readValue(response2.body(), HashMap.class);
        //List<String> keys = new ArrayList<>(response_map2.keySet());
        //return String.join(", ", keys);
        return new ArrayList<>(response_map2.keySet());
    }
}