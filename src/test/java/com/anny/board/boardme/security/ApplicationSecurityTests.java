package com.anny.board.boardme.security;

import com.anny.board.boardme.dto.user.AuthRequest;
import com.anny.board.boardme.dto.user.JoinRequest;
import com.anny.board.boardme.security.jwt.JwtUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class ApplicationSecurityTests {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    JwtUtils jwtUtils;

    final String email = "dhaorktm125@naver.com";
    final String password = "password";
    final Long userId = 4L;

    @Test
    @DisplayName("유저 회원가입")
    void when_request_join_response_userId() throws Exception {
        JoinRequest joinRequest = new JoinRequest("annyousung@naver.com", "yousung", "dhaork");

        mockMvc.perform(MockMvcRequestBuilders.post("/auth/join")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(joinRequest)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.user_id", is(notNullValue())));
    }

    @Test
    @DisplayName("토큰 획득")
    void when_requestToken_response_token() throws Exception {
        AuthRequest authRequest = new AuthRequest(email, password);

        mockMvc.perform(MockMvcRequestBuilders.post("/auth/token")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(authRequest)))
                .andDo(print())
                .andExpect(header().exists("Authorization"));
    }

    @Test
    @DisplayName("토큰과 함께 요청 시 성공")
    void when_requestApiWithToken_ok() throws Exception {
        String token = jwtUtils.createToken(email);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/users/" + userId)
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("토큰 없이 요청 시 실패")
    void when_requestApiWithoutToken_unAuthorized() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/users/" + userId))
                .andExpect(status().isForbidden());
    }
}
