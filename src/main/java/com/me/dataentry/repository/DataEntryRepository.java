package com.me.dataentry.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.me.dataentry.modal.SchooleDataEntry;
import com.me.bean.ReportName;

public interface DataEntryRepository extends JpaRepository<SchooleDataEntry, Integer> {
List<SchooleDataEntry> findBySchoolId(String schoolId);
}
