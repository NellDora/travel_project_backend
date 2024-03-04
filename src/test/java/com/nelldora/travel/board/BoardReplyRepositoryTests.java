package com.nelldora.travel.board;


import com.nelldora.travel.board.repository.BoardReplyRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BoardReplyRepositoryTests {

    @Autowired
    private BoardReplyRepository boardReplyRepository;

    @Test
    public void testGetList(){
        boardReplyRepository.getBoardReplyList(1L);

    }
}
