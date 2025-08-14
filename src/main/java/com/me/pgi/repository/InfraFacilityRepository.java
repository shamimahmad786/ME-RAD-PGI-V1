package com.me.pgi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.me.pgi.modal.DigitalLearning;
import com.me.pgi.modal.InfraFacility;

public interface InfraFacilityRepository extends JpaRepository<InfraFacility, Integer> {
	List<InfraFacility>	findByDistrictCode(String distId);
	} 
