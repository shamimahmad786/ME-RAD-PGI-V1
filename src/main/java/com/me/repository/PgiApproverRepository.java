package com.me.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.me.model.PgiPerformance;
import com.me.model.PgiPerformanceApprover;

public interface PgiApproverRepository extends JpaRepository<PgiPerformanceApprover, Long>{
	List<PgiPerformanceApprover> findByStateCode(Integer stateId);
	List<PgiPerformanceApprover> findByDistrictCodeAndInityear(Integer distId,String inityear);
	List<PgiPerformanceApprover> findByStateCodeAndApproverType(Integer stateId, String approvetype);
	List<PgiPerformanceApprover> findByStateCodeAndApproverTypeAndInityear(Integer stateId, String approvetype,String inityear);
	List<PgiPerformanceApprover> findByApproverType(String approverType);
	List<PgiPerformanceApprover> findByApproverTypeAndInityear(String approverType, String initYear);
	
}
