package com.sample.services;

import com.sample.model.Repository;
import org.json.JSONException;
import org.testng.annotations.Test;


import javax.swing.text.html.HTML;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class APIServiceTest {


    @Test
    public void isGithub() throws IOException, InterruptedException {
        APIService try1 = new APIService();
        if (try1.isGithub("kaanapak") == true) {
            assertTrue(true);
        } else {
            assertTrue(false);
        }

    }

    @Test
    public void repoList() throws JSONException, IOException, InterruptedException {

        APIService try1 = new APIService();
        boolean istrue = true;
        if (!try1.RepoList("kaanapak").get(0).getId().equals("426197570")) {
            istrue = false;
        }
        assertTrue(istrue);
    }

    @Test
    public void followerNumber() throws IOException, InterruptedException {
        APIService try1 = new APIService();
        if (try1.FollowerNumber("kaanapak") == 2) {
            assertTrue(true);
        } else {
            assertTrue(false);
        }
    }

    @Test
    public void codeCount() throws IOException, InterruptedException {
        APIService try1 = new APIService();
        if (try1.CodeCount("kaanapak") == 2) {
            assertTrue(true);
        } else {
            assertTrue(false);
        }
    }

    @Test
    public void languageCount() throws JSONException, IOException, InterruptedException {
        APIService try1 = new APIService();
        if (try1.LanguageCount("kaanapak") == 4) {
            assertTrue(true);
        } else {
            assertTrue(false);
        }
    }

    @Test
    public void scoreCalculator() throws JSONException, IOException, InterruptedException {
        APIService try1 = new APIService();
        if (try1.ScoreCalculator("kaanapak") == 70) {
            assertTrue(true);
        } else {
            assertTrue(false);
        }
    }

    @Test
    public void lastRepository() throws JSONException, IOException, InterruptedException {
        APIService try1 = new APIService();
        if (try1.LastRepository("kaanapak").getId().equals("426197570")) {
            assertTrue(true);
        } else {
            assertTrue(false);
        }

    }

    @Test
    public void getRepository() throws IOException, InterruptedException {
        APIService try1 = new APIService();
        Repository repository = try1.getRepository("426197570");
        boolean istrue = true;
        if (!repository.getName().equals("social_code_brokers")) {
            istrue = false;
        }
        assertTrue(istrue);
    }

    @Test
    public void getLanguage() throws IOException, InterruptedException {


        ArrayList<String> languages = APIService.getLanguage("https://api.github.com/repos/kaanapak/social_code_brokers/languages");

        boolean istrue = true;
        if (!languages.get(1).equals("[Java, CSS, HTML]")){
            istrue = false;
        }
        assertTrue(istrue);

    }
}
