package com.me.pgi.modal;

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
@Table(name = "Pgi_Ex_LearningOutcome")
public class LearningOutcome {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "id")
private int id;
private String sessionYear;
private String stateName;
private String stateCode;
private String districtName;
private String districtCode;
private String distReportCard;
private String distReportCardpercent;
private String distReportCardpoint;
private String avgLangScore3;
private String avgLangpercetScore3;
private String avgLangpointsScore3;
private String avgMathScore3;
private String avgMathpercentScore3;
private String avgMathpointsScore3;
private String avgLangScore5;
private String avgLangpercentScore5;
private String avgLangpointsScore5;
private String avgMathScore5;
private String avgMathpercentScore5;
private String avgMathpointsScore5;
private String avgLangScore8;
private String avgLangpercentScore8;
private String avgLangpointsScore8;
private String avgMathScore8;
private String avgMathpercentScore8;
private String avgMathpointsScore8;
}
