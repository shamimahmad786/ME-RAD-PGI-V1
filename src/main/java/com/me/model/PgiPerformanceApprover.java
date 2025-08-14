package com.me.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "PGI_RE_PERFORM_APPROVER", schema = "public")
public class PgiPerformanceApprover {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	@Column(name = "approver_type")
	private String approverType;
	@Column(name = "state_code")
	private Integer stateCode;
	@Column(name = "district_id")
	private Integer districtCode;
	@Column(name = "state_name")
	private String state_name;
	@Column(name = "district_name")
	private String district_name;
	@Column(name = "remarks")
	private String remarks;
	@Column(name = "approve_id")
	private String approve_id;
	@Column(name = "status")
	private String status;
	@Column(name = "stateRemarks")
	private String stateRemarks;
	@Column(name = "approverRemarks")
	private String approverRemarks;
	@Column(name = "inityear")
	private String inityear;
}
