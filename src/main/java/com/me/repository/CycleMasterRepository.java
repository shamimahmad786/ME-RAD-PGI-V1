package com.me.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.me.model.CycleMaster;

@Repository
public interface CycleMasterRepository extends JpaRepository<CycleMaster, Long> {
	
	List<CycleMaster> findByYear(int year);

}
