package com.blackwell;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class AuthenticationTests {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    private static final String USERNAME = "user";
    private static final String PASSWORD = "test";
    private static final String EMAIL = "email@gmail.com";

    private static final String AUTH_RESPONSE_JSON =
            "{" +
            "    \"user\": {" +
            "        \"username\": \"" + USERNAME + "\"" +
            "    }," +
            "    \"tokenType\": \"Bearer\"" +
            "}";

    private final String REQUEST_LOGIN_JSON;
    private final String REQUEST_REGISTRATION_JSON;
    private final String REQUEST_VALIDATION_JSON;


    public AuthenticationTests() throws JsonProcessingException {
        Map<String, String> bodyMap = new HashMap<>();
        bodyMap.put("username", USERNAME);

        bodyMap.put("password", PASSWORD);
        bodyMap.put("email", EMAIL);
        REQUEST_REGISTRATION_JSON = getObjectAsJson(bodyMap);

        bodyMap.remove("email");
        REQUEST_LOGIN_JSON = getObjectAsJson(bodyMap);

        bodyMap.remove("password");
        bodyMap.put("email", EMAIL);
        REQUEST_VALIDATION_JSON = getObjectAsJson(bodyMap);
    }


    @PostConstruct
    public void afterPropertiesSet() {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(this.wac)
                .apply(springSecurity())
                .build();
    }

    @Test
    @Order(0)
    public void validateFailedTest() throws Exception {
        mockMvc.perform(get("/api/users/{username}", USERNAME))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @Order(1)
    public void registrationTest() throws Exception {
        mockMvc.perform(post("/api/auth/register")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(REQUEST_REGISTRATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(AUTH_RESPONSE_JSON));
    }

    @Test
    @Order(2)
    public void authenticationTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(post("/api/auth/login")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(REQUEST_LOGIN_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(AUTH_RESPONSE_JSON))
                .andReturn();
        String responseStr = mvcResult.getResponse().getContentAsString();
        assertNotNull(responseStr);

        String jwt = getToken(responseStr);
        mockMvc.perform(get("/api/users/{username}", USERNAME).header("Authorization" , jwt))
                .andExpect(status().isOk())
                .andExpect(content().json(REQUEST_VALIDATION_JSON));
    }

    private String getObjectAsJson(Object object) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(object);
    }

    private String getToken(String response) {
        return "Bearer " +
                StringUtils.substringBefore(
                    StringUtils.substringAfter(response, "accessToken\":\""),
                    "\",\""
                );
    }

}
