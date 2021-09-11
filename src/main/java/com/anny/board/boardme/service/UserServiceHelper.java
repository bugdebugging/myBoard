package com.anny.board.boardme.service;

import com.anny.board.boardme.domain.user.DuplicateEmailException;
import com.anny.board.boardme.domain.user.User;
import com.anny.board.boardme.repository.UserRepository;
import com.anny.board.boardme.domain.user.UserNotFoundException;

public final class UserServiceHelper {
    public static User findUserById(UserRepository userRepo, Long userId) {
        return userRepo.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId.toString()));
    }

    public static void checkUserEmailNotDuplicate(UserRepository userRepo, String email) {
        if (userRepo.findByEmail(email).isPresent())
            throw new DuplicateEmailException(email + "is duplicated");
    }
}
