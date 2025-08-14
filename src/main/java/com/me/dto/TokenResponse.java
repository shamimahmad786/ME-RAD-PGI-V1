package com.me.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TokenResponse {
	
	private String username;
	
	private String token;
	
	private String paramName;

	private String paramValue;
	private String roleId;
	private String schema_name;
	private String stateId;
	private String stateName;
	private String districtName;
	private String groupId;

}
