package com.kapturecx.supportticketsystem;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class ApiIntegrationTests {

    @Autowired
    private MockMvc mvc;

    @Test
    void createAgentAndTicketAndListWithPagination() throws Exception {
        // create agent
        String agentJson = "{\"name\":\"Test Agent\",\"email\":\"agent@example.com\"}";
        mvc.perform(post("/api/agents").contentType(MediaType.APPLICATION_JSON).content(agentJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.email").value("agent@example.com"));

        // create ticket
        String ticketJson = "{\"subject\":\"Test issue\",\"description\":\"Details\",\"requesterEmail\":\"cust@example.com\"}";
        mvc.perform(post("/api/tickets").contentType(MediaType.APPLICATION_JSON).content(ticketJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.status").value("OPEN"));

        // list tickets with pagination
        mvc.perform(get("/api/tickets?page=0&size=10")).andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray());
    }
}
