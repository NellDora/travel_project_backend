package com.nelldora.travel.board.controller;

import com.nelldora.travel.board.dto.BoardDTO;
import com.nelldora.travel.board.dto.BoardReplyDTO;
import com.nelldora.travel.board.utill.common.PageRequestDTO;
import com.nelldora.travel.board.utill.common.PageResponseDTO;
import com.nelldora.travel.board.service.BoardReplyService;
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
    private final BoardReplyService boardReplyService;

    @PostMapping("/")
    public String register(@RequestBody BoardDTO boardDTO){
        String result = boardService.register(boardDTO);
        return result;
    }

    @GetMapping("/")
    public PageResponseDTO getList(PageRequestDTO boardRequestDTO){
        
        log.info("BoardList 호출");
        return boardService.getBoardList(boardRequestDTO);
    }

    @GetMapping("/{bno}")
    public BoardDTO getBoard(@PathVariable("bno") Long bno){
        log.info(" board/{bno} 호출");
        return boardService.getBoard(bno);
    }

    @PostMapping("/{bno}/replys")
    public Long register(@RequestBody BoardReplyDTO boardReplyDTO){
        Long result = boardReplyService.register(boardReplyDTO);
        return result;
    }
}
