package com.nelldora.travel.vacationland.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

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
}
