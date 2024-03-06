package com.nelldora.travel.vacationland.repository.search;

import com.nelldora.travel.board.utill.common.PageRequestDTO;
import com.nelldora.travel.vacationland.domain.VacationLand;
import com.nelldora.travel.vacationland.dto.VacationLandDTO;
import com.querydsl.core.Tuple;
import org.springframework.data.domain.Page;

public interface VacationLandSearch {

    Page<VacationLandDTO> searchList (PageRequestDTO pageRequestDTO);
}
