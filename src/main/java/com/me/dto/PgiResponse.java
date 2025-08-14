package com.me.dto;

import java.util.ArrayList;
import java.util.List;

import com.me.model.IndicatorMaster;
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
public class PgiResponse

{
	private List<IndicatorMaster> indicators = null;
	private List<StateIndicatorScore> scores = new ArrayList<StateIndicatorScore>();
	private String statusCode = null;
	private String statusDesc = null;

}
