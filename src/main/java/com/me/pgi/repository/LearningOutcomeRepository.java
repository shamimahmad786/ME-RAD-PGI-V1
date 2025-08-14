package com.me.pgi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.me.dataentry.modal.AcademicResults;
import com.me.pgi.modal.LearningOutcome;

public interface LearningOutcomeRepository extends JpaRepository<LearningOutcome, Integer> {
List<LearningOutcome>	findByDistrictCode(String distId);
}
