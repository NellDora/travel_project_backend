package com.nelldora.travel.board.repository.search;

import com.nelldora.travel.board.domain.Board;
import com.nelldora.travel.board.domain.QBoard;
import com.nelldora.travel.utill.common.PageRequestDTO;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQuery;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.util.StringUtils;

import java.util.List;

@Log4j2
public class BoardSearchImpl extends QuerydslRepositorySupport implements BoardSearch {

    public BoardSearchImpl(){
        super(Board.class);
    }

    @Override
    public Page<Board> searchList(PageRequestDTO pageRequestDTO) {

        QBoard board = QBoard.board;

        JPQLQuery<Board> query = from(board).where(likeBoardTitle(pageRequestDTO.getSearchTitle()));


        Pageable pageable = PageRequest.of(
                pageRequestDTO.getPage()-1,
                pageRequestDTO.getSize(),
                Sort.by("bno").descending());

        this.getQuerydsl().applyPagination(pageable,query);

        List<Board> list = query.fetch();

        long total = query.fetchCount();

        return new PageImpl<>(list,pageable,total);
    }

    private BooleanExpression likeBoardTitle(String title){
        if(StringUtils.hasText(title)){
            return QBoard.board.title.like("%"+title+"%");
        }
        return null;
    }

    private BooleanExpression boardCategory(String category){
        if(StringUtils.hasText(category)){
            return QBoard.board.category.eq(category);
        }
        return null;
    }
}
