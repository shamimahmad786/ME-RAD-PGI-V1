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
@Table(name = "Pgi_Ex_Governaceprocess")
public class GovernanceProcess {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	private String sessionYear;
	private String stateName;
	private String stateCode;
	private String districtName;
	private String districtCode;
	private String isActionplan;
	private String isActionplanpercent;
	private String isActionplanpoint;
	private String isPlanstarted;
	private String isPlanstartedpercent;
	private String isPlanstartedpoint;
	private String sportsEquipmentsr1;
	private String sportsEquipmentsr2;
	private String sportsEquipmentspercent;
	private String sportsEquipmentspoint;
	private String libraryBookr1;
	private String libraryBookr2;
	private String libraryBookpercent;
	private String libraryBookpoint;
	private String activityDoner1;
	private String activityDoner2;
	private String activityDonepercent;
	private String activityDonepoint;
	private String ecoYouthclubr1;
	private String ecoYouthclubr2;
	private String ecoYouthclubpercent;
	private String ecoYouthclubpoint;
	private String saftyBoardsr1;
	private String saftyBoardsr2;
	private String saftyBoardspercent;
	private String saftyBoardspoint;
	private String boothLevelofficersr1;
	private String boothLevelofficersr2;
	private String boothLevelofficerspercent;
	private String boothLevelofficerspoint;
	private String teacherCountinsufficientr1;
	private String teacherCountinsufficientr2;
	private String teacherCountinsufficientpercent;
	private String teacherCountinsufficientpoint;
	private String teacherotherDutier1;
	private String teacherotherDutier2;
	private String teacherotherDutiepercent;
	private String teacherotherDutiepoint;
	private String isplanImproveeducation;
	private String isplanImproveeducationpercent;
	private String isplanImproveeducationpoint;
	private String isreviewedplan;
	private String isreviewedplanpercent;
	private String isreviewedplanpoint;
	private String isplanReviewed;
	private String isplanReviewedpercent;
	private String isplanReviewedpoint;
	private String isteacherDeployauth;
	private String isteacherDeployauthpercent;
	private String isteacherDeployauthpoint;
	private String inspectionReport;
	private String inspectionReportpercent;
	private String inspectionReportpoint;
	private String primaryPtr;
	private String primaryPtrpercent;
	private String primaryPtrpoint;
	private String upperPtr;
	private String upperPtrpercent;
	private String upperPtrpoint;
	private String fitIndiaschool;
	private String fitIndiaschoolpercent;
	private String fitIndiaschoolpoint;
	private String holisticReportcard;
	private String holisticReportcardpercent;
	private String holisticReportcardpoint;
	private String modelSchool;
	private String modelSchoolpercent;
	private String modelSchoolpoint;
}
