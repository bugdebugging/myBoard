package com.anny.board.boardme.domain.reply;

import com.anny.board.boardme.domain.board.Board;
import com.anny.board.boardme.domain.user.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_reply")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "contents", nullable = false, length = 256)
    private String contents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP")
    private LocalDateTime creadtdAt;

    @Column(name = "modified_at", columnDefinition = "TIMESTAMP")
    private LocalDateTime modifiedAt;

    @Builder(builderMethodName = "of")
    public Reply(String contents, User user, Board board) {
        this.contents = contents;
        setUser(user);
        setBoard(board);
        creadtdAt = LocalDateTime.now();
        modifiedAt = LocalDateTime.now();
    }

    private void setUser(User user) {
        if (user == null)
            throw new IllegalArgumentException("user is null");
        this.user = user;
    }

    private void setBoard(Board board) {
        if (board == null)
            throw new IllegalArgumentException("board is null");
        this.board = board;
    }

    public void updateContents(String contents) {
        this.contents = contents;
        this.modifiedAt = LocalDateTime.now();
    }
}
