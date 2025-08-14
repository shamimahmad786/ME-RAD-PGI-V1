package com.me.dataentry.modal;

import java.util.Date;

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
@Table(name = "School_CM_Membercount")
public class SchoolCMMembercount {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	private String schoolId;
	private Date academicYear;
	private int generalBoys;
	private int generalGirls;
	private int scBoys;
	private int scGirls;
	private int stBoys;
	private int stGirls;
	private int obcBoys;
	private int obcGirls;
	private int  diffAbleBoys;
	private int diffAbleGirls;
	private String academicYearTo;
	private String academicYearFrom;
	
}
