package com.nelldora.travel.vacationland.service;

import com.nelldora.travel.utill.common.PageRequestDTO;
import com.nelldora.travel.utill.common.PageResponseDTO;
import com.nelldora.travel.vacationland.domain.VacationLand;
import com.nelldora.travel.vacationland.domain.VacationLandImage;
import com.nelldora.travel.vacationland.dto.VacationLandDTO;
import com.nelldora.travel.vacationland.repository.VacationLandRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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

        Page<VacationLand> result = vacationLandRepository.searchList(pageRequestDTO);


        List<VacationLandDTO> dtoList = result.get().map(vacationLand -> entityToDTO(vacationLand)).collect(Collectors.toList());


        
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

    @Override
    public void modify(VacationLandDTO vacationLandDTO) {
        Optional<VacationLand> result = vacationLandRepository.findById(vacationLandDTO.getVno());

        VacationLand vacationLand = result.orElseThrow();

        vacationLand.changeTitle(vacationLandDTO.getTitle());
        vacationLand.changeContent(vacationLandDTO.getContent());
        vacationLand.changeCategory(vacationLandDTO.getCategory());
        vacationLand.changeUpdateDate(vacationLandDTO.getUpdateDate());
    }

    @Override
    public void delete(Long vno) {
        Optional<VacationLand> result = vacationLandRepository.findById(vno);
        VacationLand vacationLand = result.orElseThrow();

        vacationLand.changeDelFlag(true);
    }

    private VacationLandDTO entityToDTO(VacationLand vacationLand){
        VacationLandDTO vacationLandDTO = VacationLandDTO.builder()
                .vno(vacationLand.getVno())
                .title(vacationLand.getTitle())
                .content(vacationLand.getContent())
                .delFlag(vacationLand.isDelFlag())
                .regDate(vacationLand.getRegDate())
                .category(vacationLand.getCategory())
                .reportFlag(vacationLand.isReportFlag())
                .likeCount(vacationLand.getLikeCount())
                .build();

        log.info("entityToDTO : title :"+ vacationLand.getTitle());
        log.info("entityToDTO : content "+ vacationLand.getContent());

        if (!vacationLand.getImageList().isEmpty()){
            List<String> tmpUploadFileNames = new ArrayList<>();
            log.info("entityToDTO : 이미지 사이즈 "+ vacationLand.getImageList().size());
            for(VacationLandImage tmp : vacationLand.getImageList()){
                tmpUploadFileNames.add(tmp.getFileName());
            }
            vacationLandDTO.setUploadFileNames(tmpUploadFileNames);
            log.info("entityToDTO : 파일명 "+ vacationLand.getImageList().get(0).getFileName().toString());
        }
        return vacationLandDTO;
    }

    private VacationLand dtoToEntity(VacationLandDTO vacationLandDTO){
        VacationLand vacationLand = VacationLand.builder()
                .vno(vacationLandDTO.getVno())
                .title(vacationLandDTO.getTitle())
                .content(vacationLandDTO.getContent())
                .delFlag(vacationLandDTO.isDelFlag())
                .regDate(vacationLandDTO.getRegDate())
                .category(vacationLandDTO.getCategory())
                .reportFlag(vacationLandDTO.isReportFlag())
                .likeCount(vacationLandDTO.getLikeCount())

                .build();

        List<String> uploadFileNames = vacationLandDTO.getUploadFileNames();

        if(uploadFileNames == null){
            return vacationLand;
        }

        uploadFileNames.stream().forEach(uploadName ->{
            vacationLand.addImageString(uploadName);
        });

        return vacationLand;
    }

    @Override
    public VacationLandDTO getVacationLand(Long vno) {
        Optional<VacationLand> result = vacationLandRepository.findById(vno);
        VacationLand vacationLand = result.orElseThrow();
        VacationLandDTO vacationLandDTO = entityToDTO(vacationLand);
        return vacationLandDTO;
    }

    @Override
    public String likePush(Long vno) {
        //해야 할 것 1. 아이디당 좋아요를 1번 눌렀으면 한번 더 누르면 감소가 되어야함
        // 좋아요를 어떻게 할 것인가 -> 좋아요 테이블을 따로 만들어야하는가? -> 유저 Entity에 좋아요한 것을 따로 추가



        return null;
    }
}
