package com.nelldora.travel.vacationland.controller;

import com.nelldora.travel.board.utill.common.PageRequestDTO;
import com.nelldora.travel.board.utill.common.PageResponseDTO;
import com.nelldora.travel.vacationland.dto.VacationLandDTO;
import com.nelldora.travel.vacationland.service.VacationLandService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/vacationlands")
public class VacationLandController {

    private final VacationLandService vacationLandService;

    @GetMapping("/")
    public PageResponseDTO<VacationLandDTO> list(PageRequestDTO pageRequestDTO)
    {
        log.info("vacationsList");
        log.info("searchTitle : "+ pageRequestDTO.getSearchTitle());
        return vacationLandService.getList(pageRequestDTO);
    }

    @GetMapping("/test")
    public String test(){
        return "testOk";
    }

    @PostMapping("/")
    public String register(@RequestBody VacationLandDTO vacationLandDTO){
        String result = vacationLandService.register(vacationLandDTO);
        return result;
    }

}
