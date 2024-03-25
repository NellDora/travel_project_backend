package com.nelldora.travel.vacationland.controller;

import com.nelldora.travel.utill.common.PageRequestDTO;
import com.nelldora.travel.utill.common.PageResponseDTO;
import com.nelldora.travel.utill.file.FileUtil;
import com.nelldora.travel.vacationland.dto.VacationLandDTO;
import com.nelldora.travel.vacationland.service.VacationLandService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/vacationlands")
public class VacationLandController {

    private final FileUtil fileUtil;
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
    public String register(VacationLandDTO vacationLandDTO){
        List<MultipartFile> files = vacationLandDTO.getFiles();

        List<String> uploadFileNames = fileUtil.saveFiles(files);

        vacationLandDTO.setUploadFileNames(uploadFileNames);
        String result = vacationLandService.register(vacationLandDTO);
        return result;
    }

    @GetMapping("/view/{fileName}")
    public ResponseEntity<Resource> viewFileGet(@PathVariable String fileName){
        log.info("VacationLand Controller 파일 이미지 출력 " + fileName);
        return fileUtil.getFile(fileName);
    }

    @GetMapping("/{vno}")
    public VacationLandDTO getVacationLand(@PathVariable("vno") Long vno){
        log.info("호출호출");

        return vacationLandService.getVacationLand(vno);
    }

    @PutMapping("/{vno}") // 수정용
    public Map<String, String> putVacationland(@PathVariable("vno") Long vno, @RequestBody VacationLandDTO vacationLandDTO){
        vacationLandService.modify(vacationLandDTO);
        return Map.of(vno.toString(), "Modify Success");
    }



}
