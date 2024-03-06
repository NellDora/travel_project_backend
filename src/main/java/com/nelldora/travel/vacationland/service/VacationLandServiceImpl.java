package com.nelldora.travel.vacationland.service;

import com.nelldora.travel.board.utill.common.PageRequestDTO;
import com.nelldora.travel.board.utill.common.PageResponseDTO;
import com.nelldora.travel.vacationland.domain.VacationLand;
import com.nelldora.travel.vacationland.dto.VacationLandDTO;
import com.nelldora.travel.vacationland.repository.VacationLandRepository;
import com.querydsl.core.Tuple;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@RequiredArgsConstructor
@Service
public class VacationLandServiceImpl implements VacationLandService{

    private final VacationLandRepository vacationLandRepository;

    @Override
    public String register(VacationLandDTO vacationLandDTO) {
        return vacationLandRepository.save(dtoToEntity(vacationLandDTO)).getVno().toString();
    }

    @Override
    public PageResponseDTO<VacationLandDTO> getList(PageRequestDTO pageRequestDTO) {

        Page<VacationLandDTO> result = vacationLandRepository.searchList(pageRequestDTO);

        List<VacationLandDTO> dtoList = new ArrayList<>();


        
        log.info("VacationLandService 호출");
        for(VacationLandDTO vacationLandDTO : dtoList){
            log.info("호우"+vacationLandDTO.getVno() + vacationLandDTO.getTitle());
        }

        PageResponseDTO<VacationLandDTO> responseDTO =
                PageResponseDTO.<VacationLandDTO>withAll()
                        .dtoList(dtoList)
                        .pageRequestDTO(pageRequestDTO)
                        .total(result.getTotalElements())
                        .build();

        return responseDTO;
    }

    private VacationLandDTO entityToDTO(VacationLand vacationLand){
        return VacationLandDTO.builder()
                .vno(vacationLand.getVno())
                .title(vacationLand.getTitle())
                .content(vacationLand.getContent())
                .delFlag(vacationLand.isDelFlag())
                .regDate(vacationLand.getRegDate())
                .category(vacationLand.getCategory())
                .reportFlag(vacationLand.isReportFlag())
                .build();
    }

    private VacationLand dtoToEntity(VacationLandDTO vacationLandDTO){
        return VacationLand.builder()
                .vno(vacationLandDTO.getVno())
                .title(vacationLandDTO.getTitle())
                .content(vacationLandDTO.getTitle())
                .delFlag(vacationLandDTO.isDelFlag())
                .regDate(vacationLandDTO.getRegDate())
                .category(vacationLandDTO.getCategory())
                .reportFlag(vacationLandDTO.isReportFlag())
                .build();
    }
}
