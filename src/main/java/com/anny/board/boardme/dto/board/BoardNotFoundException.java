package com.anny.board.boardme.dto.board;

import com.anny.board.boardme.global.exception.EntityNotFoundException;

public class BoardNotFoundException extends EntityNotFoundException {
    public BoardNotFoundException(String target) {
        super(target + "is not found");
    }
}
