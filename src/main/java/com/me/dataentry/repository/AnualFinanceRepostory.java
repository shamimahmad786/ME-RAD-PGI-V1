package com.me.dataentry.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.me.dataentry.modal.AcademicResults;
import com.me.dataentry.modal.AnualFinance;

public interface AnualFinanceRepostory extends JpaRepository<AnualFinance, Integer>{
	List<AnualFinance> findByAutonomusTypeAndFinancialYearTo(String autonomusType, String financialYearTo);
}
