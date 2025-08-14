package com.me.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.me.bean.StaticReportBean;
import com.me.model.GroupMapping;
import com.me.model.GroupMaster;
//import com.me.report.repository.StaticReportRepository;
import com.me.repository.GroupMappingRepository;
import com.me.repository.GroupMasterRepository;




@Service
public class GroupServiceImpl {

@Autowired
GroupMasterRepository  groupMasterRepository;

@Autowired
GroupMappingRepository  groupMappingRepository;
@Autowired
//StaticReportRepository staticReportRepository;

public GroupMaster	addGroup(GroupMaster gmm){
		return groupMasterRepository.save(gmm);
	}

public List<GroupMapping> addGroupMapping(List<GroupMapping> finalGmObj) {
	return  groupMappingRepository.saveAll(finalGmObj);
}

public List<GroupMaster> getAllGroup(){
	return groupMasterRepository.findAll();
}

public Optional<GroupMaster> getGroupById(int id){
	return groupMasterRepository.findById(id);
}


public List<GroupMapping> getGroupMappingByGroupId(int id){
	return  groupMappingRepository.findByGroupId(id);  
}


public List<GroupMapping> getReportIdFromGroup(List<Integer> groupId) {
	System.out.println("In service");
	return	groupMappingRepository.findByGroupIdIn(groupId);
}

public long deleteGroup(int groupId) {
	long deleteId;
	try {
		groupMasterRepository.deleteById(groupId);
	}catch(Exception ex) {
		ex.printStackTrace();
	}
	return groupMappingRepository.deleteByGroupId(groupId);
}



}
