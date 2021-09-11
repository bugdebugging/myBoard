package com.anny.board.boardme.repository;

import com.anny.board.boardme.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    public Optional<User>findByEmail(String email);
}
