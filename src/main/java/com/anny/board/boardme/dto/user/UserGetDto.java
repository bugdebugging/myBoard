package com.anny.board.boardme.dto.user;

import com.anny.board.boardme.domain.user.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserGetDto {
    private String email;
    private String name;

    public UserGetDto(User user) {
        this.email = user.getEmail();
        this.name = user.getName();
    }
}
