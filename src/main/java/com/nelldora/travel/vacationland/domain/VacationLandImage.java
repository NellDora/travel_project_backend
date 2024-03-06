package com.nelldora.travel.vacationland.domain;


import jakarta.persistence.Embeddable;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Embeddable
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VacationLandImage {

    private String fileName;

    private  int ord;

    public void setOrd(int ord){
        this.ord=ord;
    }

}
