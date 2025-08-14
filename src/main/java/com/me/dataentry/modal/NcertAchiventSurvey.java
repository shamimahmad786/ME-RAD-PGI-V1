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
@Table(name = "NCERT_CM_AchivementSurvey")
public class NcertAchiventSurvey {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	private Date surveyYear;
	private int noStudentAppearded;
	private int noTeacherParticipated;
	private int noSchoolParticipated;
	private String achievementSurveyYears; 
}
