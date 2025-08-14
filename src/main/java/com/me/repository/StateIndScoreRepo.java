package com.me.repository;

import java.util.HashMap;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.me.model.StateIndicatorScore;

@Repository
public interface StateIndScoreRepo extends JpaRepository<StateIndicatorScore, Long> {
	
	List<StateIndicatorScore> findByStateIdAndYear(int stateId, String year);
	
	StateIndicatorScore findByStateIdAndYearAndIndId(int stateId, String year, int indId);
	
//	public  List < StateIndicatorScore >  getData ( HashMap < String , Object >  conditions );
	
}
