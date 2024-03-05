package com.nelldora.travel.vacationland.repository;

import com.nelldora.travel.vacationland.domain.VacationLand;
import com.nelldora.travel.vacationland.repository.search.VacationLandSearch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VacationLandRepository extends JpaRepository<VacationLand, Long>, VacationLandSearch {
}
