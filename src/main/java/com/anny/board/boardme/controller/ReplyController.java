package com.anny.board.boardme.controller;

import com.anny.board.boardme.dto.reply.ReplyCreationRequestDto;
import com.anny.board.boardme.dto.reply.ReplyCreationResponseDto;
import com.anny.board.boardme.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class ReplyController {
    private final ReplyService replyService;

    @PostMapping("/api/replies")
    public ResponseEntity<JsonWrapper> createReplyByUser(@Valid ReplyCreationRequestDto replyCreationRequestDto) {
        Long replyId = replyService.registerReplyByUser(replyCreationRequestDto);
        ReplyCreationResponseDto response = new ReplyCreationResponseDto(replyId);
        return ResponseEntity.ok(JsonWrapper.of().data(response).build());
    }
}
