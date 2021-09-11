package com.anny.board.boardme.dto.board;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class BoardCreationRequestDto {
    @NotBlank
    private String title;
    @NotBlank
    private String contents;
    @NotNull
    private Long userId;
}
