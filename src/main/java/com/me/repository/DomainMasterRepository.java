package com.me.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.me.model.DomainMaster;

@Repository
public interface DomainMasterRepository extends JpaRepository<DomainMaster, Long> {

	List<DomainMaster> findByCategoryId(long categoryId);

}
