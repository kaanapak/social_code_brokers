package com.sample.services;

import com.sample.model.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



import org.springframework.stereotype.Service;

import com.sample.model.Repository;


public class RepositoryService {
    public Repository getRepositoryFromString(String RepositoryString){
        List<String> RepoList = new ArrayList<String>(Arrays.asList(RepositoryString.split("|")));
Repository repository=new Repository(RepoList.get(0),RepoList.get(1),RepoList.get(2),RepoList.get(3));
return repository;
    }
}
