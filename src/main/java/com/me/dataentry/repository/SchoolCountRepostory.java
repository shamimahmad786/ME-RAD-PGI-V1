package com.me.dataentry.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.me.dataentry.modal.SchoolCMMembercount;
import com.me.dataentry.modal.SchooleDataEntry;

public interface SchoolCountRepostory extends JpaRepository<SchoolCMMembercount, Integer> {

	List<SchoolCMMembercount> findBySchoolIdAndAcademicYearTo(String schoolId,String academicYearTo);
}
