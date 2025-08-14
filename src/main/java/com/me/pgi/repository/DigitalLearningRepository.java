package com.me.pgi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.me.pgi.modal.DigitalLearning;
import com.me.pgi.modal.LearningOutcome;

public interface DigitalLearningRepository extends JpaRepository<DigitalLearning, Integer> {
	List<DigitalLearning>	findByDistrictCode(String distId);
	}