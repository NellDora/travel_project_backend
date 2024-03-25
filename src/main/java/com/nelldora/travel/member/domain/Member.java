package com.nelldora.travel.member.domain;

import com.nelldora.travel.vacationland.domain.VacationLand;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Member {

    @Id
    private String memeberNo;

    private String id;

    private String pw;

    private String nickname;

    private boolean social; // 나중에 추가 할 소셜 로그인 계정 여부

    @OneToMany(mappedBy = "member")
    private List<VacationLand> likeVacationland = new ArrayList<>();
}
