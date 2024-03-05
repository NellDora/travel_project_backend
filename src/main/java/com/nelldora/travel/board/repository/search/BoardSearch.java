package com.nelldora.travel.board.repository.search;

import com.nelldora.travel.board.domain.Board;
import com.nelldora.travel.board.utill.common.PageRequestDTO;
import org.springframework.data.domain.Page;

public interface BoardSearch {

    Page<Board> searchList (PageRequestDTO pageRequestDTO);

}
