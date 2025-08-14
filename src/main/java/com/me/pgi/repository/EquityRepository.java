package com.me.pgi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.me.pgi.modal.DigitalLearning;
import com.me.pgi.modal.Equity;
import com.me.pgi.modal.InfraFacility;

public interface EquityRepository extends JpaRepository<Equity, Integer> {
	List<Equity>	findByDistrictCode(String distId);
	} 

