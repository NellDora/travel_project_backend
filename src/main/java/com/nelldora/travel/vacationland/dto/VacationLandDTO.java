package com.nelldora.travel.vacationland.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VacationLandDTO {

    private Long vno;

    private String title;

    private String content;

    private String category;

    private boolean delFlag;

    private Timestamp regDate;

    private Timestamp updateDate;

    private boolean reportFlag;

    private int likeCount;

    @Builder.Default
    private List<MultipartFile> files = new ArrayList<>();

    @Builder.Default
    private List<String> uploadFileNames = new ArrayList<>();
}
