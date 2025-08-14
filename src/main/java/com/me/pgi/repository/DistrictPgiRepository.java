package com.me.pgi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.me.pgi.modal.DigitalLearning;
import com.me.pgi.modal.DistrictPgi;

public interface DistrictPgiRepository extends JpaRepository<DistrictPgi, Integer> {
	List<DistrictPgi>	findByStateCode(String stateCode);
	List<DistrictPgi>	findByDistCode(String distCode);
	}
