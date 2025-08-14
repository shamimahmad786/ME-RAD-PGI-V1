package com.me.dto;

import java.util.HashMap;
import java.util.List;

import com.me.model.QueryModel;
import com.me.model.StateIndicatorScore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class IndicatorRequestDto {

	private int domainId;

	private int stateId;

	private String param;

	private String year;

	private int indId;

	private StateIndicatorScore stateIndicatorScore;
	
	List<QueryModel> queryModel;
}
