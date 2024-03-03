package com.nelldora.travel.board.controller;

import com.nelldora.travel.board.domain.Board;
import com.nelldora.travel.board.dto.BoardDTO;
import com.nelldora.travel.board.dto.PageBoardRequestDTO;
import com.nelldora.travel.board.dto.PageBoardResponseDTO;
import com.nelldora.travel.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/boards")
@RequiredArgsConstructor
@Log4j2
public class BoardController {

    private final BoardService boardService;

    @PostMapping("/")
    public String register(@RequestBody BoardDTO boardDTO){
        Board board = Board.builder().title(boardDTO.getTitle()).content(boardDTO.getContent()).build();

        String result = boardService.register(board);
        return result;
    }

    @GetMapping("/")
    public PageBoardResponseDTO getList(PageBoardRequestDTO boardRequestDTO){
        
        log.info("BoardList 호출");
        return boardService.getBoardList(boardRequestDTO);
    }

    @GetMapping("/{bno}")
    public BoardDTO getBoard(@PathVariable("bno") Long bno){
        log.info(" board/{bno} 호출");
        return boardService.getBoard(bno);
    }
}
