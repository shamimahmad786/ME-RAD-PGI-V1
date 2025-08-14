package com.me.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.me.model.IndicatorMaster;

@Repository
public interface IndicatorMasterRepo extends JpaRepository<IndicatorMaster, Long>{
	
	List<IndicatorMaster> findByDomainIdOrderByIndIdAsc(int domainId);
	
	
	
	
}
