package com.me.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.me.model.PgiColorCode;
import com.me.model.PgiPerformanceApprover;

@Repository
public interface PgiColorCodeRepository extends JpaRepository<PgiColorCode, Long>{

	PgiColorCode   findAllByYear(String year);
}
