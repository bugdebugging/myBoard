package com.anny.board.boardme.controller;

import com.anny.board.boardme.dto.board.BoardCreationRequestDto;
import com.anny.board.boardme.dto.board.BoardCreationResponseDto;
import com.anny.board.boardme.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @PostMapping("/api/boards")
    public ResponseEntity<JsonWrapper> createBoardByUser(@Valid @RequestBody BoardCreationRequestDto boardCreationRequestDto) {
        System.out.println(boardCreationRequestDto);
        Long boardId = boardService.registerBoard(boardCreationRequestDto);
        BoardCreationResponseDto response = new BoardCreationResponseDto(boardId);

        return ResponseEntity.ok(JsonWrapper.of().data(response).build());
    }
}
