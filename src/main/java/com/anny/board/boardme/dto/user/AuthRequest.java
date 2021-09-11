package com.anny.board.boardme.dto.user;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class AuthRequest {
    @NotBlank
    private String email;
    @NotBlank
    private String password;
}
