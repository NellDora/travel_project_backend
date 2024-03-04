package com.nelldora.travel.board.repository;

import com.nelldora.travel.board.domain.BoardReply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BoardReplyRepository extends JpaRepository<BoardReply, Long> {

    @Query("select r from BoardReply r where r.board = :bno")
    List<BoardReply> getBoardReplyList(Long bno);
}
