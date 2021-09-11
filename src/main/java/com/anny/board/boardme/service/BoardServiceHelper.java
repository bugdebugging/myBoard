package com.anny.board.boardme.service;

import com.anny.board.boardme.domain.board.Board;
import com.anny.board.boardme.dto.board.BoardNotFoundException;
import com.anny.board.boardme.repository.BoardRepository;

public class BoardServiceHelper {
    public static Board findBoardById(BoardRepository boardRepo, Long boardId) {
        return boardRepo.findById(boardId)
                .orElseThrow(() -> new BoardNotFoundException(boardId.toString()));
    }
}
