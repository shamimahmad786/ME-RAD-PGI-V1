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
@Table(name = "district_question")
public class DistrictQuestion {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "questionid")
	private Long questionId;
	@Column(name = "questiondesc")
	private String quesDesc;
	@Column(name = "domainid")
	private Long domainId;
	@Column(name = "categoryid")
	private Long categoryId;
	@Column(name = "quesseries")
	private String quesSeries;
	@Column(name = "sourceid")
	private Long sourceId;
	@Column(name = "weight")
	private Integer weight;
	@Column(name = "benchmark")
	private Integer benchMark;
	@Column(name = "benchmarkdesc")
	private String benchMarkDesc;
	@Column(name = "sortid")
	private Integer sortId;
	@Column(name = "questiontype")
	private String questionType;
	@Column(name = "indicatorno")
	private String indicatorNo;
	@Column(name = "fieldcount")
	private String fieldcount;
	@Column(name = "subquestiondesc1")
	private String subquestiondesc1;
	@Column(name = "subquestiondesc2")
	private String subquestiondesc2;
	@Column(name = "subquestiondesc3")
	private String subquestiondesc3;
}
