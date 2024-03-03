package com.nelldora.travel.board.service;


import com.nelldora.travel.board.domain.Board;
import com.nelldora.travel.board.dto.BoardDTO;
import com.nelldora.travel.board.dto.PageBoardRequestDTO;
import com.nelldora.travel.board.dto.PageBoardResponseDTO;
import com.nelldora.travel.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class BoardServiceImpl implements BoardService{

    private final BoardRepository boardRepository;

    public String register(Board board){
        Board result = boardRepository.save(board);
        return result.getBno().toString();
    }

    @Override
    public BoardDTO getBoard(Long bno) {
        Optional<Board> result = boardRepository.findById(bno);
        Board board = result.orElseThrow();
        BoardDTO boardDTO = entityToDTO(board);
        return boardDTO;
    }

    public PageBoardResponseDTO<BoardDTO> getBoardList(PageBoardRequestDTO boardRequestDTO){

        Page<Board> result = boardRepository.searchList(boardRequestDTO);

        List<BoardDTO> dtoList = result.get().map(board -> entityToDTO(board)).collect(Collectors.toList());

        PageBoardResponseDTO<BoardDTO> responseDTO =
                PageBoardResponseDTO.<BoardDTO>withAll()
                        .dtoList(dtoList)
                        .pageBoardRequestDTO(new PageBoardRequestDTO())
                        .total(result.getTotalElements())
                        .build();
        return responseDTO;

    }
}
