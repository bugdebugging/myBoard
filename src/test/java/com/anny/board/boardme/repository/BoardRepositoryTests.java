package com.anny.board.boardme.repository;

import com.anny.board.boardme.domain.board.Board;
import com.anny.board.boardme.domain.category.Category;
import com.anny.board.boardme.domain.user.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BoardRepositoryTests {
    @Autowired
    BoardRepository boardRepository;
    @Autowired
    TestEntityManager testEntityManager;

    @Test
    @DisplayName("게시물 등록 with 유저")
    void testBoardSave() {
        User user = User.of()
                .email("annyousung@gmail.com")
                .password("password")
                .name("yousung")
                .build();
        Category category=Category.of()
                .name("type")
                .description("description1")
                .build();

        testEntityManager.persist(category);
        testEntityManager.persist(user);

        Board board = Board.of()
                .user(user)
                .contents("this is first article")
                .title("article title")
                .category(category)
                .build();

        Board savedBoard = boardRepository.save(board);

        assertNotNull(savedBoard);
        assertTrue(savedBoard.getId() > 0);
    }
}
