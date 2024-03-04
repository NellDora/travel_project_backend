package com.nelldora.travel.board.service;

import com.nelldora.travel.board.domain.Board;
import com.nelldora.travel.board.domain.BoardReply;
import com.nelldora.travel.board.dto.BoardReplyDTO;
import com.nelldora.travel.board.repository.BoardReplyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class BoardReplyServiceImpl implements BoardReplyService {

    private final BoardReplyRepository boardReplyRepository;
    private final BoardService boardService;
    @Override
    public Long register(BoardReplyDTO boardReplyDTO) {
        BoardReply boardReply = dtoToEntity(boardReplyDTO);
        Board board = boardService.dtoTnEntity(boardService.getBoard(boardReplyDTO.getBoardNum()));
        log.info(board.getTitle());
        boardReply.addBoard(board);

        BoardReply result = boardReplyRepository.save(boardReply);
        return result.getBrno();
    }

    public BoardReplyDTO getReplyList(Long bno) {
         boardReplyRepository.getBoardReplyList(bno);
        return null;
    }

    private BoardReply dtoToEntity(BoardReplyDTO boardReplyDTO){

        BoardReply boardReply = BoardReply.builder()
                .brno(boardReplyDTO.getBrno())
                .board(boardReplyDTO.getBoard())
                .content(boardReplyDTO.getContent())
                .regDate(boardReplyDTO.getRegDate())
                .reportFlag(boardReplyDTO.isReportFlag()).build();

        return boardReply;
    }
}
