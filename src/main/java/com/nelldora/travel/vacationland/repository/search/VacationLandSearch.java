package com.nelldora.travel.vacationland.repository.search;

import com.nelldora.travel.board.utill.common.PageRequestDTO;
import com.nelldora.travel.vacationland.domain.VacationLand;
import org.springframework.data.domain.Page;

public interface VacationLandSearch {

    Page<VacationLand> searchList (PageRequestDTO pageRequestDTO);
}
