package com.sample.model;

import java.util.ArrayList;

public class Repository {
    private String date;
    private String ıd;
    private String name;
    private Integer ıs_starred;
    private ArrayList<String> languageList;
    private String owner;

public Repository(String date,ArrayList<String> languageList,String ıd,String name){
    this.date=date;
    this.languageList=languageList;
    this.ıd=ıd;
    this.name=name;
    ıs_starred=0;

}
public Repository(){
    this.date="4343434";
    this.ıd="kekemn";
    this.name="jdsnjnf";
}

    public Repository(String date, String ıd, String name) {
        this.date = date;
        this.ıd = ıd;
        this.name = name;
    }

    public String getId() {
        return ıd;
    }

    public String getDate() {
        return date;
    }


    public String toString(){
    String RepoString=date+"|"+toStringLanguageList()+"|"+ıd+"|"+name+"|"+ıs_starred+"|"+owner;
    return RepoString;
}
public void setStarred(){
    ıs_starred=1;
}

    public String getLanguage() {
        return toStringLanguageList();
    }

    public ArrayList<String> getLanguageList() {
        return languageList;
    }

    public String getName() {
        return name;
    }

    public String toStringLanguageList(){
        return String.join(", ", languageList);
    }

    public void setLanguageList(ArrayList<String> languageList) {
        this.languageList = languageList;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setId(String ıd) {
        this.ıd = ıd;
    }
}
