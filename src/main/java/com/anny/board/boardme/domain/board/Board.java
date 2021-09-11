package com.anny.board.boardme.domain.board;

import com.anny.board.boardme.domain.category.Category;
import com.anny.board.boardme.domain.reply.Reply;
import com.anny.board.boardme.domain.user.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tbl_board")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", length = 126, nullable = false)
    private String title;

    @Column(name = "contents", length = 512, nullable = false)
    private String contents;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime createdAt;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime modifiedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY ,cascade = CascadeType.REMOVE)
    private List<Reply> replies;

    @Builder(builderMethodName = "of")
    public Board(User user, Category category, String title, String contents) {
        this.title = title;
        this.contents = contents;
        setUser(user);
        setCategory(category);
        createdAt = LocalDateTime.now();
        modifiedAt = LocalDateTime.now();
    }

    private void setUser(User user) {
        if (user == null)
            throw new IllegalArgumentException("user is null");
        this.user = user;
    }

    private void setCategory(Category category) {
        if (category == null)
            throw new IllegalArgumentException("category is null");
        this.category = category;
    }
    private void setTitle(String title){
        if(title==null)
            throw new IllegalArgumentException("title is null");
        this.title=title;
    }
    private void setContents(String contents){
        if(contents==null)
            throw new IllegalArgumentException("title is null");
        this.contents=contents;
    }
    private void updateDetailContents(String title,String contents){
        setTitle(title);
        setContents(contents);
        this.modifiedAt=LocalDateTime.now();
    }
}
