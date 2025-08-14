package com.me.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@NamedQueries({
		@NamedQuery(name = "User.getUserList", query = "Select s from User s where s.status = :search"), })
@Table(name = "pgi_user_mst")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "record_id")
	private Long id;

	@Column(name = "user_id", length = 100, unique = true)
	private String username;

	@Column(name = "password", length = 300)
	private String password;

	@Column(name = "first_name", length = 100)
	private String firstname;

	@Column(name = "last_name", length = 100)
	private String lastname;

	@Column(name = "mobile_no", length = 11)
	private String mobileNo;

	@Column(name = "email", length = 100, unique = true)
	private String email;

	@Column(name = "realpwd", length = 300)
	private String realPassword;

	@Column(name = "role_id", length = 100 )
	private String roleId;

	@Column(name = "designation", length = 100)
	private String designation;

	@Column(name = "param_name", length = 100)
	private String paramName;

	@Column(name = "param_value", length = 100)
	private String paramValue;

	@Column(name = "created_time")
	private Date creationTime;

	@Column(name = "modified_time")
	private Date modificationTime;

	@Column(name = "status", length = 2)
	private String status;

	@Column(name = "created_by", length = 100)
	private String createdby;

	@Column(name = "modified_by", length = 100)
	private String modifiedBy;
	
	@Column(name = "schema_name")
	private String schema_name;
	
	@Column(name = "stateId")
	private String stateId;
	
	@Column(name = "stateName")
	private String stateName;
	
	@Column(name = "districtName")
	private String districtName;
	@Column(name = "blockName")
	private String blockName;
	
	@Column(name = "groupId")
	private String groupId;
	

}
