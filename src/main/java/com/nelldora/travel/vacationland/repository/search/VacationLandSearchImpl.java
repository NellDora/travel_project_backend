package com.nelldora.travel.vacationland.repository.search;

import com.nelldora.travel.utill.common.PageRequestDTO;
import com.nelldora.travel.vacationland.domain.QVacationLand;
import com.nelldora.travel.vacationland.domain.QVacationLandImage;
import com.nelldora.travel.vacationland.domain.VacationLand;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQuery;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.nelldora.travel.vacationland.domain.QVacationLand.vacationLand;

@Log4j2
public class VacationLandSearchImpl extends QuerydslRepositorySupport implements VacationLandSearch {

    public VacationLandSearchImpl(){
        super(VacationLand.class);
    }

//    @Override
//    public Page<VacationLand> searchList(PageRequestDTO pageRequestDTO) {
//
//        QVacationLand vacationLand = QVacationLand.vacationLand;
//        QVacationLandImage vacationLandImage = QVacationLandImage.vacationLandImage;
//
//        JPQLQuery<VacationLand> query  =from(vacationLand).leftJoin(vacationLand.imageList, vacationLandImage)
//                .where(likeTitle(pageRequestDTO.getSearchTitle()));
//
//
//        Pageable pageable = PageRequest.of(
//                pageRequestDTO.getPage()-1,
//                pageRequestDTO.getSize(),
//                Sort.by("vno").descending());
//
//        Objects.requireNonNull(getQuerydsl().applyPagination(pageable, query));
//
//
//        List<VacationLand> vacationLandList = query.fetch();
//
//        long total = query.fetchCount();
//
//
//        log.info(vacationLandList.get(0).getVno());
//        log.info(vacationLandList.get(0).getTitle());
//
//        return new PageImpl<>(vacationLandList,pageable,total);
//
//    }

//    public Page<VacationLand> searchList(PageRequestDTO pageRequestDTO) {
//
//        QVacationLand vacationLand = QVacationLand.vacationLand;
//        QVacationLandImage vacationLandImage = QVacationLandImage.vacationLandImage;
//
//        JPQLQuery<Tuple> query = from(vacationLand)
//                .leftJoin(vacationLand.imageList, vacationLandImage)
//                .where(likeTitle(pageRequestDTO.getSearchTitle())).where(vacationLandImage.ord.eq(0))
//                .select(vacationLand, vacationLandImage);
//
//        // 페이징 및 정렬 설정
//        Pageable pageable = PageRequest.of(
//                pageRequestDTO.getPage() - 1,
//                pageRequestDTO.getSize(),
//                Sort.by(Sort.Direction.DESC, "vno"));
//
//        // 페이징 및 정렬 적용
//        query = getQuerydsl().applyPagination(pageable, query);
//
//        // 결과 조회
//        List<Tuple> results = query.fetch();
//
//        // 튜플을 VacationLand 객체로 변환
//        List<VacationLand> vacationLandList = results.stream()
//                .map(tuple -> {
//                    VacationLand resultVacationLand = tuple.get(vacationLand);
//
//                    log.info(resultVacationLand.getImageList().get(0));
//                    log.info(resultVacationLand.getImageList().get(0).getFileName());
//
//                    return resultVacationLand;
//                        }).collect(Collectors.toList());
//
//
//        // 전체 레코드 수 조회
//        long total = query.fetchCount();
//
//        log.info("vno: {}", vacationLandList.get(0).getVno());
//        log.info("title: {}", vacationLandList.get(0).getTitle());
//
//        return new PageImpl<>(vacationLandList, pageable, total);
//    }

    public Page<VacationLand> searchList(PageRequestDTO pageRequestDTO) {

        QVacationLand vacationLand = QVacationLand.vacationLand;
        QVacationLandImage vacationLandImage = QVacationLandImage.vacationLandImage;

        JPQLQuery<VacationLand> query = from(vacationLand)
                .leftJoin(vacationLand.imageList, vacationLandImage)
                .where(likeTitle(pageRequestDTO.getSearchTitle()))
                .select(vacationLand);

        // 페이징 및 정렬 설정
        Pageable pageable = PageRequest.of(
                pageRequestDTO.getPage() - 1,
                pageRequestDTO.getSize(),
                Sort.by(Sort.Direction.DESC, "vno"));

        // 페이징 및 정렬 적용
        query = getQuerydsl().applyPagination(pageable, query);

        // 결과 조회
        List<VacationLand> results = query.fetch();




        // 전체 레코드 수 조회
        long total = query.fetchCount();

        log.info("vno: {}", results.get(0).getVno());
        log.info("title: {}", results.get(0).getTitle());

        return new PageImpl<>(results, pageable, total);
    }

    private BooleanExpression likeTitle(String title){
        if(StringUtils.hasText(title)){
            return vacationLand.title.like("%"+title+"%");
        }
        return null;
    }

    private BooleanExpression category(String category){
        if(StringUtils.hasText(category)){
            return vacationLand.category.eq(category);
        }
        return null;
    }
}
