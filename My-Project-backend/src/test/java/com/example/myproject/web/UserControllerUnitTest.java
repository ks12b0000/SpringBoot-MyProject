package com.example.myproject.web;

import com.example.myproject.domain.user.User;
import com.example.myproject.service.UserService;
import com.example.myproject.web.dto.UserDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class UserControllerUnitTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

//    @Test
//    public void save_Test() throws Exception{
//        // given (테스트를 하기 위한 준비)
//        UserDto user = new UserDto("1","1", "스프링따라하기", "코스");
//
//        // stub (행동 지정)
//        when(userService.join(user)).thenReturn(new User("1","1", "1", "코스"));
//
//        // when (테스트 실행)
//        ResultActions resultActions = mvc.perform(post("/join")
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON)
//                .characterEncoding("UTF-8")
//                .content(
//                        "{"
//                                + "  \"username\" : \"1\", "
//                                + "  \"name\" : \"1\", "
//                                + "  \"email\": \"1\", "
//                                + "  \"password\": \"코스\", "
//                                + "}"));
//        // then (검증)
//        resultActions
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("name").value("1"))
//                .andDo(MockMvcResultHandlers.print());
//    }
}
