package com.me.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.me.model.PgiDistrictPerformance;
import com.me.model.PgiPerformance;

public interface PgiDistrictPerformanceRepository extends JpaRepository<PgiDistrictPerformance, Long> {

}
