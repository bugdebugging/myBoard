package com.anny.board.boardme.service;

import com.anny.board.boardme.domain.board.Board;
import com.anny.board.boardme.domain.reply.Reply;
import com.anny.board.boardme.domain.user.User;
import com.anny.board.boardme.dto.reply.ReplyCreationRequestDto;
import com.anny.board.boardme.dto.reply.ReplyCreationResponseDto;
import com.anny.board.boardme.repository.BoardRepository;
import com.anny.board.boardme.repository.ReplyRepository;
import com.anny.board.boardme.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReplyService {
    private final ReplyRepository replyRepository;
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;

    @Transactional
    public Long registerReplyByUser(ReplyCreationRequestDto replyCreationRequestDto) {
        User user = UserServiceHelper.findUserById(userRepository, replyCreationRequestDto.getUserId());
        Board board = BoardServiceHelper.findBoardById(boardRepository, replyCreationRequestDto.getBoardId());

        Reply reply = Reply.of()
                .user(user)
                .board(board)
                .contents(replyCreationRequestDto.getContents())
                .build();

        Reply savedReply = replyRepository.save(reply);
        return savedReply.getId();
    }
}
