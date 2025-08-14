package com.me.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "mst_state")
public class StateMaster {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "state_id")
	private Long id;
	@Column(name = "state_name")
	private String stateName;
	@Column(name = "udise_state_code")
	private String udiseStateCode;
	@Column(name = "lgd_state_id")
	private Long lgdStateId;
	@Column(name = "status")
	private String status;
	@Column(name = "is_ut")
	private Long isUt;
	@Column(name = "path")
	private String path;
	@Column(name = "banner")
	private String banner;
	@OneToMany(targetEntity = DistrictMaster.class, mappedBy = "stateMaster", fetch = FetchType.LAZY)
	private List<DistrictMaster> districts;
	@Override
	public String toString() {
		return "StateMaster [id=" + id + ", stateName=" + stateName + ", udiseStateCode=" + udiseStateCode
				+ ", stateId=" + lgdStateId + ", status=" + status + ", isUt=" + isUt + ", path=" + path + ", banner="
				+ banner + ", districts=" + districts + "]";
	}

}
