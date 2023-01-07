package com.sapient.creditcard;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sapient.creditcard.controller.CreditCardController;
import com.sapient.creditcard.model.CreditCardModel;

import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.assertj.core.util.Lists;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ConfigurationUtils.class})

public class CreditCardControllerTests {
	
    @Autowired
    CreditCardController controller;

    MockMvc mockMvc;

    @Before
    public void init() {
        mockMvc = standaloneSetup(controller).build();
    }
    
    
    @Test
    public void testCreditCardOperations() throws Exception {
        mockMvc.perform(
            MockMvcRequestBuilders.get("/creditcarddetails/getall"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(MockMvcResultMatchers.jsonPath("$").isEmpty());
        
        mockMvc.perform( MockMvcRequestBuilders
            	.post("/creditcards")
                .content(new ObjectMapper().writeValueAsString(new CreditCardModel("Arun Kumar Sharma", "1234567891210126", 1111, 0)))
                .contentType(MediaType.APPLICATION_JSON)
    		    .accept(MediaType.APPLICATION_JSON))
    		    .andExpect(status().isOk());
        
        mockMvc.perform(
                MockMvcRequestBuilders.get("/creditcarddetails/getall"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(MockMvcResultMatchers.jsonPath("$[*].number").value(Lists.newArrayList("1234567891210126")));
    }
	
}
