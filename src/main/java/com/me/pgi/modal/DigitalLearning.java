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
@Table(name = "Pgi_Ex_DigitalLearning")
public class DigitalLearning {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	private String sessionYear;
	private String stateName;
	private String stateCode;
	private String districtName;
	private String districtCode;
	private String ICAFacilityr1;
	private String ICAFacilityr2;
	private String ICAFacilityrpercentage;
	private String ICAFacilityr2points;
	private String InternetAccessr1;
	private String InternetAccessr2;
	private String InternetAccessrpercentage;
	private String InternetAccessrpoints;
	private String onlineClassesr1;
	private String onlineClassesr2;
	private String onlineClassesrpercentage;
	private String onlineClassesrpoints;
	private String onlineAttendancer1;
	private String onlineAttendancer2;
	private String onlineAttendancerpercentage;
	private String onlineAttendancerpoints;
	private String onlineCourcer1;
	private String onlineCourcer2;
	private String onlineCourcerpercentage;
	private String onlineCourcerpoints;
	private String usedSwamChanelr1;
	private String usedSwamChanelr2;
	private String usedSwamChanelrpercentage;
	private String usedSwamChanelrpoints;
	private String otherMediar1;
	private String otherMediar2;
	private String otherMediarpercentage;
	private String otherMediarpoints;
	private String tdititalcontentr1;
	private String tdititalcontentr2;
	private String tdititalcontentrpercentage;
	private String tdititalcontentrpoints;
	private String contentDeveloperr1;
	private String contentDeveloperr2;
	private String contentDeveloperrpercentage;
	private String contentDeveloperrpoints;
	private String digitalTeachr1;
	private String digitalTeachr2;
	private String digitalTeachrpercentage;
	private String digitalTeachrpoints;

}
