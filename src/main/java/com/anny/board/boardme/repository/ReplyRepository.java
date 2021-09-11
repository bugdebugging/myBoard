package com.anny.board.boardme.repository;

import com.anny.board.boardme.domain.reply.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyRepository extends JpaRepository<Reply,Long> {
}
