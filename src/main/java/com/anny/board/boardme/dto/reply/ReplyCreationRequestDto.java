package com.anny.board.boardme.dto.reply;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class ReplyCreationRequestDto {
    @NotNull
    private Long userId;
    @NotNull
    private Long boardId;
    @NotBlank
    private String contents;
}
