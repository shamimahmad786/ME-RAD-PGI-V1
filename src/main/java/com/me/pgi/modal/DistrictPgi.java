package com.me.pgi.modal;

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
@Table(name = "Pgi_Ru_Performance")
public class DistrictPgi {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	private String stateName;
	private String stateCode;
	private String districtName;
	private String distCode;
	private String status;
	private String approveRemarks;
	private String points;
}
