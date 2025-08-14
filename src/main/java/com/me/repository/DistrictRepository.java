package com.me.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.me.model.DistrictMaster;

@Repository
public interface DistrictRepository extends JpaRepository<DistrictMaster, Long> {

	List<DistrictMaster> getDistrict(int stateId);
	List<DistrictMaster> findByStateId(Long stateId);
	List<DistrictMaster> findByUdiseStaCode(String stateId);
	List<DistrictMaster> findByDistrictId(Integer districtId);
}