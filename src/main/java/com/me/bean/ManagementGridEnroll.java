package com.me.bean;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class ManagementGridEnroll {
	List<Map<String,List<Object>>>  managementGridEnroll;

	public List<Map<String, List<Object>>> getManagementGridEnroll() {
		return managementGridEnroll;
	}

	public void setManagementGridEnroll(List<Map<String, List<Object>>> managementGridEnroll) {
		this.managementGridEnroll = managementGridEnroll;
	}
	

}
