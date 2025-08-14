package com.me.dataentry.modal;

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
@Table(name = "Dashboard_CM_SchooleDataEntry")
public class SchooleDataEntry {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	private String schoolId;
	private String school_country;
	private String school_state;
	private String school_district;
	private String school_region;
	private String school_schoolName;
	private String school_operation;
	private String school_sanction_year;
	private int school_Enroll;
	private String school_category;
	
}
