package com.me.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "mst_district")
@NamedQueries({
		@NamedQuery(name = "DistrictMaster.getDistrict", query = "Select d from DistrictMaster d where d.stateId = :stateId"), })
public class DistrictMaster {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "district_id")
	private Long districtId;

	@Column(name = "state_id")
	private Long stateId;

	@Column(name = "district_name")
	private String districtName;
	@Column(name = "udise_district_code")
	private String udiseDisCode;
	@Column(name = "udise_state_code")
	private String udiseStaCode;
	@Column(name = "inityear")
	private String inityear;
	
	@ManyToOne(targetEntity = StateMaster.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "state_id", insertable = false, updatable = false)
	@JsonIgnore
	private StateMaster stateMaster;
	@Override
	public String toString() {
		return "DistrictMaster [districtId=" + districtId + ", stateId=" + stateId + ", districtName=" + districtName
				+ ", udiseDisCode=" + udiseDisCode + ", udiseStaCode=" + udiseStaCode + "]";
	}

	
}
