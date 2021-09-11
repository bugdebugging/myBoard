package com.anny.board.boardme.controller;

import com.anny.board.boardme.dto.user.JoinRequest;
import com.anny.board.boardme.dto.user.JoinResponse;
import com.anny.board.boardme.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/auth/join")
    public ResponseEntity<JsonWrapper> join(@Valid @RequestBody JoinRequest joinRequest) {
        JoinResponse joinResponse = new JoinResponse(userService.joinUser(joinRequest));
        return ResponseEntity.status(HttpStatus.OK)
                .body(JsonWrapper.of()
                        .data(joinResponse)
                        .error(null)
                        .build());
    }

    @GetMapping("/api/users/{userId}")
    public ResponseEntity<JsonWrapper> retrieveUserDetail(@PathVariable Long userId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(JsonWrapper.of()
                        .data(userService.getUserInfoBy(userId))
                        .error(null)
                        .build());
    }
}
