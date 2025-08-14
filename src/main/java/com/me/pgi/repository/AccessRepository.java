package com.me.pgi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.me.pgi.modal.Access;
import com.me.pgi.modal.DigitalLearning;

public interface AccessRepository extends JpaRepository<Access, Integer> {
	List<Access>	findByDistrictCode(String distId);
	} 


