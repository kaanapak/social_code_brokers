package com.sample.model;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class RepositoryTest {

    @Test
    public void getId() {
        Repository repository=new Repository();
        repository.setId("1");
        assertEquals(repository.getId(), "1");

    }

    @Test
    public void getDate() {
        Repository repository=new Repository();
        repository.setDate("1");
        assertEquals(repository.getDate(), "1");
    }

    @Test
    public void testToString() {
        Repository repository=new Repository();
        repository.setId("1");
        repository.setDate("12/03/2021");
        repository.setStarred();
        ArrayList<String> Language=new ArrayList();
        Language.add("Java");
        repository.setLanguageList(Language);
        assertEquals(repository.toString(), "12/03/2021|Java|1|jdsnjnf|1|null");
    }


    @Test
    public void getLanguage() {
        Repository repository=new Repository();
        ArrayList<String> Language=new ArrayList();
        Language.add("Java");
        repository.setLanguageList(Language);
        assertEquals(repository.getLanguage(), "Java");
    }

    @Test
    public void getLanguageList() {
        Repository repository=new Repository();
        ArrayList<String> Language=new ArrayList();
        Language.add("Java");
        repository.setLanguageList(Language);
        assertEquals(repository.getLanguageList(), Language);

    }

    @Test
    public void getName() {
        Repository repository=new Repository();
        assertEquals(repository.getName(), "jdsnjnf");
    }

    @Test
    public void toStringLanguageList() {
        Repository repository=new Repository();
        ArrayList<String> Language=new ArrayList();
        Language.add("Java");
        repository.setLanguageList(Language);
        assertEquals(repository.toStringLanguageList(), "Java");
    }


    @Test
    public void setOwner() {
        Repository repository=new Repository();
        repository.setOwner("Kaanapak");
        assertEquals(repository.getOwner(), "Kaanapak");

    }

    @Test
    public void RepoConstructor1() {
        Repository repository=new Repository("12:01:10","1","kaanapak");
        assertEquals(repository.getName(), "kaanapak");

    }

    @Test
    public void RepoConstructor2() {
        ArrayList<String> Language=new ArrayList();
        Language.add("Java");
        Repository repository=new Repository("12:01:10",Language,"12","kaanapak");
        assertEquals(repository.getName(), "kaanapak");
    }


}