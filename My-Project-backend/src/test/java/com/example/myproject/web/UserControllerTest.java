package com.example.myproject.web;

import com.example.myproject.service.UserService;
import com.example.myproject.web.dto.request.JoinReqDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import javax.transaction.Transactional;

import static org.springframework.boot.test.context.SpringBootTest.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private UserService userService;


    @Test
    public void join_Test() throws Exception{
        // given (테스트를 하기 위한 준비)
        JoinReqDto join = new JoinReqDto("junit수정123","메타코딩수정6", "ks12b0000@gmail.com", "sadasdsad");
        String content = new ObjectMapper().writeValueAsString(join);

        // when (테스트 실행)
        ResultActions resultActions = mvc.perform(post("/join")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content)
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
        );

        // then (검증)
        resultActions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("name").value("메타코딩수정6"))
                .andDo(MockMvcResultHandlers.print());
    }
}
