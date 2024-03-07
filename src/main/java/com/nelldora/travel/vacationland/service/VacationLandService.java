package com.nelldora.travel.vacationland.service;

import com.nelldora.travel.utill.common.PageRequestDTO;
import com.nelldora.travel.utill.common.PageResponseDTO;
import com.nelldora.travel.vacationland.dto.VacationLandDTO;

public interface VacationLandService {

    String register(VacationLandDTO vacationLandDTO);
    PageResponseDTO<VacationLandDTO> getList(PageRequestDTO pageRequestDTO);
}
