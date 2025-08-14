package com.me.dto;

import javax.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RegistirationRequest {

	private Long id;
	private String username;

	private String password;

	private String firstname;

	private String lastname;

	private String email;

	private String mobileNo;
	
	private String paramName;

	private String paramValue;
	
	private String roleId;
	private String createdBy;
	private String stateId;
	private String stateName;
	private String districtName;
	private String blockName;
	private String groupId;
}
