package com.sample.model;

public class Repository {
    private String date;
    private String language;
    private String ıd;
    private String name;
    private Integer ıs_starred;

public Repository(String date,String language,String ıd,String name){
    this.date=date;
    this.language=language;
    this.ıd=ıd;
    this.name=name;
    ıs_starred=0;

}

    public String getId() {
        return ıd;
    }

    public String toString(){
    String RepoString=date+"|"+language+"|"+ıd+"|"+name+"|"+ıs_starred;
    return RepoString;
}
public void setStarred(){
    ıs_starred=1;
}

}
