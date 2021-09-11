package com.anny.board.boardme.repository;

import com.anny.board.boardme.domain.user.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTests {
    @Autowired
    UserRepository userRepository;

    @Test
    public void testSave(){
        User user=User.of()
                .email("annyousung@gmail.com")
                .password("dbtjd007")
                .name("bugdebugging")
                .build();

        User savedUser=userRepository.save(user);
        assertNotNull(savedUser);
    }
}
