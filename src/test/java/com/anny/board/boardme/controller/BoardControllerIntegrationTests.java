package com.anny.board.boardme.controller;

import com.anny.board.boardme.dto.board.BoardCreationRequestDto;
import com.anny.board.boardme.dto.user.AuthRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@SpringBootTest
@AutoConfigureMockMvc
public class BoardControllerIntegrationTests {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    String token = "Bearer ";

    final String email = "dhaorktm125@naver.com";
    final String password = "password";
    final Long userId = 11L;

    @BeforeEach
    public void getToken() throws Exception {

        String authRequest = objectMapper.writeValueAsString(new AuthRequest(email, password));

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/auth/token")
                .content(authRequest))
                .andReturn();
        token += result.getResponse().getHeader("Authorization");
    }

    @Test
    @DisplayName("게시물 등록")
//    @Transactional
    void when_CreatBoard_Return_BoardId() throws Exception {
        String creationRequest = objectMapper.writeValueAsString(new BoardCreationRequestDto("first title","first contents",userId));
        mockMvc.perform(MockMvcRequestBuilders.post("/api/boards")
                .header("Authorization",token)
                .content(creationRequest))
                .andExpect(jsonPath("$.data.board_id").exists());
    }
}
