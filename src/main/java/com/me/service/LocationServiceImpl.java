package com.me.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.me.model.BlockMaster;
import com.me.model.DistrictMaster;
import com.me.model.StateMaster;
import com.me.repository.BlockRepository;
import com.me.repository.DistrictRepository;
import com.me.repository.StateRepository;

@Service
public class LocationServiceImpl {

	@Autowired
	StateRepository stateRepository;

	@Autowired
	DistrictRepository districtRepository;

	@Autowired
	BlockRepository blockRepository;

	public List<StateMaster> getState() {

		return stateRepository.findAllByOrderByStateNameAsc();
	}

	public StateMaster findById(String id) {
		return stateRepository.findByUdiseStateCode(id);
	}

	public List<DistrictMaster> findByUdiseStaCode(String stateId) {
//		return districtRepository.getDistrict(stateId);
		return districtRepository.findByUdiseStaCode(stateId);
	}

	public List<BlockMaster> getBlock(String districtId) {
		System.out.println("At service district id---0>"+districtId);
//		return blockRepository.getBlock(districtId);
		return blockRepository.findByUdiseDistCode(districtId);
	}
}
