package com.anny.board.boardme.domain.user;

import com.anny.board.boardme.global.exception.EntityNotFoundException;

public class UserNotFoundException extends EntityNotFoundException {
    public UserNotFoundException(String target) {
        super(target+" is not found");
    }
}
