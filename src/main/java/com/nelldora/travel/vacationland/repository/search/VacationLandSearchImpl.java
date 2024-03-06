package com.nelldora.travel.vacationland.repository.search;

import com.nelldora.travel.board.domain.QBoard;
import com.nelldora.travel.board.utill.common.PageRequestDTO;
import com.nelldora.travel.board.utill.common.PageResponseDTO;
import com.nelldora.travel.vacationland.domain.QVacationLand;
import com.nelldora.travel.vacationland.domain.QVacationLandImage;
import com.nelldora.travel.vacationland.domain.VacationLand;
import com.nelldora.travel.vacationland.domain.VacationLandImage;
import com.nelldora.travel.vacationland.dto.VacationLandDTO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class VacationLandSearchImpl extends QuerydslRepositorySupport implements VacationLandSearch {

    public VacationLandSearchImpl(){
        super(VacationLand.class);
    }

    @Override
    public Page<VacationLandDTO> searchList(PageRequestDTO pageRequestDTO) {

        QVacationLand vacationLand = QVacationLand.vacationLand;
        QVacationLandImage vacationLandImage = QVacationLandImage.vacationLandImage;

        JPQLQuery<VacationLand> query = from(vacationLand).leftJoin(vacationLand.imageList, vacationLandImage)
                .where(likeTitle(pageRequestDTO.getSearchTitle()),vacationLandImage.ord.eq(0));


        Pageable pageable = PageRequest.of(
                pageRequestDTO.getPage()-1,
                pageRequestDTO.getSize(),
                Sort.by("vno").descending());

        Objects.requireNonNull(getQuerydsl().applyPagination(pageable, query));

        List<VacationLandDTO> vacationLandDTOList = query.select(Projections.constructor(VacationLandDTO.class,vacationLand,vacationLandImage)).fetch();

        long total = query.fetchCount();



        return new PageImpl<>(vacationLandDTOList,pageable,total);

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
