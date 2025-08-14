package com.me.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.me.model.IndicatorMaster;
import com.me.model.PgiPerformance;

public interface PgiPerformanceRepository extends JpaRepository<PgiPerformance, Long>{
	List<PgiPerformance> findByStateId(Long stateId);
//	List<PgiPerformance>  findByStateIdOrderByDomainidAsc(Long stateId);
	List<PgiPerformance> findByStateIdAndInityear(Long stateId,String inityear);
}
