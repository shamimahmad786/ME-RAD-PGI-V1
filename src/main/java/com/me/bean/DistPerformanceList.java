package com.me.bean;

import java.util.List;

import com.me.model.PgiDistrictPerformance;

public class DistPerformanceList {
	
	public List<PgiDistrictPerformance> getListPgiPerformance() {
		return listPgiPerformance;
	}

	public void setListPgiPerformance(List<PgiDistrictPerformance> listPgiPerformance) {
		this.listPgiPerformance = listPgiPerformance;
	}

	List<PgiDistrictPerformance> listPgiPerformance;

}
