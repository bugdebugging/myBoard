package com.anny.board.boardme.domain.user;

import com.anny.board.boardme.domain.board.Board;
import com.anny.board.boardme.domain.reply.Reply;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tbl_user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter(AccessLevel.PRIVATE)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="email",length = 128, nullable = false, unique = true)
    private String email;

    @Column(name="name" ,length = 56 ,nullable = false)
    private String name;

    @Column(name="password" ,length = 64 ,nullable = false)
    private String password;

    @Column(name="created_at", columnDefinition = "TIMESTAMP")
    private LocalDateTime createdAt;

    @Column(name="modified_at", columnDefinition = "TIMESTAMP")
    private LocalDateTime modifiedAt;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Board>boards=new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Reply>replies=new ArrayList<>();

    @Builder(builderMethodName = "of")
    public User(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
        createdAt=LocalDateTime.now();
        modifiedAt=LocalDateTime.now();
    }
}
