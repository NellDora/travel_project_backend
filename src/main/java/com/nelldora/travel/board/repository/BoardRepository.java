package com.nelldora.travel.board.repository;

import com.nelldora.travel.board.domain.Board;
import com.nelldora.travel.board.dto.PageBoardRequestDTO;
import com.nelldora.travel.board.repository.search.BoardSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board,Long>, BoardSearch {


}
