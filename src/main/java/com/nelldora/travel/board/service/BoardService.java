package com.nelldora.travel.board.service;

import com.nelldora.travel.board.domain.Board;
import com.nelldora.travel.board.dto.BoardDTO;
import com.nelldora.travel.board.dto.PageBoardRequestDTO;
import com.nelldora.travel.board.dto.PageBoardResponseDTO;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Transactional
public interface BoardService {

    String register(Board board);

    PageBoardResponseDTO<BoardDTO> getBoardList(PageBoardRequestDTO boardRequestDTO);

    BoardDTO getBoard(Long bno);

    default BoardDTO entityToDTO(Board board){
        return BoardDTO.builder()
                .bno(board.getBno())
                .title(board.getTitle())
                .content(board.getContent())
                .delFlag(board.isDelFlag())
                .regDate(board.getRegDate())
                .category(board.getCategory())
                .reportFlag(board.isReportFlag()).build();
    }

    default  Board dtoTnEntity(BoardDTO boardDTO){
        return Board.builder()
                .bno(boardDTO.getBno())
                .title(boardDTO.getTitle())
                .content(boardDTO.getContent())
                .delFlag(boardDTO.isDelFlag())
                .regDate(boardDTO.getRegDate())
                .category(boardDTO.getCategory())
                .reportFlag(boardDTO.isReportFlag()).build();
    }
}
