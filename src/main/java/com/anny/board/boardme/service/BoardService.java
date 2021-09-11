package com.anny.board.boardme.service;

import com.anny.board.boardme.domain.board.Board;
import com.anny.board.boardme.domain.user.User;
import com.anny.board.boardme.dto.board.BoardCreationRequestDto;
import com.anny.board.boardme.repository.BoardRepository;
import com.anny.board.boardme.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    @Transactional
    public Long registerBoard(BoardCreationRequestDto boardCreationRequestDto) {
        User user = UserServiceHelper.findUserById(userRepository, boardCreationRequestDto.getUserId());
        Board board = makeBoardFrom(boardCreationRequestDto, user);
        Board savedBoard = boardRepository.save(board);
        return savedBoard.getId();
    }

    private Board makeBoardFrom(BoardCreationRequestDto boardCreationRequestDto, User user) {
        return Board.of()
                .title(boardCreationRequestDto.getTitle())
                .contents(boardCreationRequestDto.getContents())
                .user(user)
                .build();
    }
}
