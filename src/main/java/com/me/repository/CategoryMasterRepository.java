package com.me.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.me.model.CategoryMaster;

@Repository
public interface CategoryMasterRepository extends JpaRepository<CategoryMaster, Long> {
	
	

}
