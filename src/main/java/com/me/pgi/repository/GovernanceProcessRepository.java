package com.me.pgi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.me.pgi.modal.DigitalLearning;
import com.me.pgi.modal.GovernanceProcess;
import com.me.pgi.modal.InfraFacility;

public interface GovernanceProcessRepository extends JpaRepository<GovernanceProcess, Integer> {
	List<GovernanceProcess>	findByDistrictCode(String distId);
	} 
