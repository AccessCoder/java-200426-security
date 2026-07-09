package org.example.backend.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class HelloControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    void sayHello() throws Exception {
        //GIVEN
        //WHEN & THEN
        mockMvc.perform(MockMvcRequestBuilders.get("/api/hello"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Hello World"));
    }

    @Test
    void sayHello_shouldReturn401_whenUserIsNotAuthenticated() throws Exception {
        //GIVEN
        //WHEN & THEN
        mockMvc.perform(MockMvcRequestBuilders.get("/api/hello"))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }
}