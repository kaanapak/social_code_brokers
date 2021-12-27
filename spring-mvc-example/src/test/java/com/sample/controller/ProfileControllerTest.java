package com.sample.controller;

//import org.testng.annotations.Test;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sample.model.Input;
import com.sample.services.APIService;
import com.sample.services.ServerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.assertEquals;


import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(ProfileController.class)

public class ProfileControllerTest {
    @Autowired  MockMvc mockMvc;

    @Mock
    private ServerService serverService;
    private APIService apiService;



    @MockBean
    private ServerService service;


    @Test
    public void profile() throws Exception {
        String uri = "/profile";
        Input input=new Input();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        String inputJson = objectMapper.writeValueAsString(input);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri).param("username", "kaanapak")
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    public void searchedUser() throws Exception {
        String uri = "/searchedUser";
        Input input=new Input();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        String inputJson = objectMapper.writeValueAsString(input);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri).param("username", "kaanapak").param("searchedUsername", "emaden99")
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }
}