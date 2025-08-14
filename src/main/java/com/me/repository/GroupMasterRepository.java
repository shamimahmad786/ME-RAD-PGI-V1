package com.me.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//import com.example.demo.model.DomainMaster;
import com.me.model.GroupMaster;
@Repository
public interface GroupMasterRepository extends JpaRepository<GroupMaster, Integer>{
	
List<GroupMaster>	findAllByGroupName(String groupName);

}
