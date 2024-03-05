package com.nelldora.travel.vacationland.repository.search;

import com.nelldora.travel.board.domain.QBoard;
import com.nelldora.travel.board.utill.common.PageRequestDTO;
import com.nelldora.travel.vacationland.domain.QVacationLand;
import com.nelldora.travel.vacationland.domain.VacationLand;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.util.StringUtils;

import java.util.List;

public class VacationLandSearchImpl extends QuerydslRepositorySupport implements VacationLandSearch {

    public VacationLandSearchImpl(){
        super(VacationLand.class);
    }

    @Override
    public Page<VacationLand> searchList(PageRequestDTO pageRequestDTO) {

        QVacationLand vacationLand = QVacationLand.vacationLand;

        JPQLQuery<VacationLand> query = from(vacationLand).where(likeTitle(pageRequestDTO.getSearchTitle()));

        Pageable pageable = PageRequest.of(
                pageRequestDTO.getPage()-1,
                pageRequestDTO.getSize(),
                Sort.by("vno").descending());

        this.getQuerydsl().applyPagination(pageable, query);

        List<VacationLand> list = query.fetch();

        long total = query.fetchCount();

        return new PageImpl<>(list,pageable,total);

    }

    private BooleanExpression likeTitle(String title){
        if(StringUtils.hasText(title)){
            return QVacationLand.vacationLand.title.like("%"+title+"%");
        }
        return null;
    }

    private BooleanExpression category(String category){
        if(StringUtils.hasText(category)){
            return QVacationLand.vacationLand.category.eq(category);
        }
        return null;
    }
}
