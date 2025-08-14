package com.me.dataentry.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.me.dataentry.modal.AcademicResults;
import com.me.dataentry.modal.SchooleDataEntry;

public interface AcademicResultsRepostory extends JpaRepository<AcademicResults, Integer> {
	List<AcademicResults> findBySchoolIdAndAcademicsYear(String schoolId,String academicsYear);
}
