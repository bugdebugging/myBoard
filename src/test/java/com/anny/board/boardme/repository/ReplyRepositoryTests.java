package com.anny.board.boardme.repository;

import com.anny.board.boardme.domain.board.Board;
import com.anny.board.boardme.domain.category.Category;
import com.anny.board.boardme.domain.reply.Reply;
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
public class ReplyRepositoryTests {
    @Autowired
    ReplyRepository replyRepository;

    @Autowired
    TestEntityManager testEntityManager;

    @Test
    @DisplayName("댓글 저장")
    void testReplySave() {
        Category category = Category.of()
                .name("category1")
                .description("category desc1")
                .build();

        User user = User.of()
                .name("user1")
                .email("email@email.com")
                .password("password1")
                .build();
        Board board = Board.of()
                .user(user)
                .category(category)
                .contents("contents~~")
                .title("this is title")
                .build();
        testEntityManager.persist(category);
        testEntityManager.persist(user);
        testEntityManager.persist(board);

        //then
        Reply reply = Reply.of()
                .board(board)
                .user(user)
                .contents("reply 1")
                .build();
        Reply savedReply = replyRepository.save(reply);

        assertNotNull(savedReply);
        assertTrue(savedReply.getId() > 0);
    }
}
