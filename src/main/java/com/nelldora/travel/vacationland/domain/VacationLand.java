package com.nelldora.travel.vacationland.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class VacationLand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vno;

    private String title;

    private String content;

    private String category;

    private boolean delFlag;

    private Timestamp regDate;

    private Timestamp updateDate;

    private boolean reportFlag;

    //이미지
    @ElementCollection
    @Builder.Default
    private List<VacationLandImage> imageList = new ArrayList<>();

    public void addImage(VacationLandImage vacationLandImage){
        vacationLandImage.setOrd(imageList.size());
        imageList.add(vacationLandImage);
    }

    public void addImageString(String fileName){
        VacationLandImage vacationLandImage = VacationLandImage.builder()
                .fileName(fileName)
                .build();

        addImage(vacationLandImage);
    }

}
