package com.nelldora.travel.vacationland.service;

import com.nelldora.travel.board.utill.common.PageRequestDTO;
import com.nelldora.travel.board.utill.common.PageResponseDTO;
import com.nelldora.travel.vacationland.domain.VacationLand;
import com.nelldora.travel.vacationland.dto.VacationLandDTO;

import java.util.List;

public interface VacationLandService {

    String register(VacationLandDTO vacationLandDTO);
    PageResponseDTO<VacationLandDTO> getList(PageRequestDTO pageRequestDTO);
}
