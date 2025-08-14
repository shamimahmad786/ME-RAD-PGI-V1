package com.me.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "indicator_score_1")
@NamedQueries({
	@NamedQuery(name = "StateIndicatorScore.getAllData", query = "from StateIndicatorScore") })
public class StateIndicatorScore

{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "data_id")
	private long dataId;
	@Column(name = "ind_id")
	private int indId;
	@Column(name = "state_id")
	private int stateId;
	@Column(name = "domain_id")
	private int domainId;
	@Column(name = "cycle_id")
	private int cycleId;
	@Column(name = "year")
	private String year;
	@Column(name = "score")
	private int score;
	@Column(name = "raw_value")
	private Double calcRawValue;
	@Column(name = "created_by")
	private String createdBy;
	@Column(name = "modified_by")
	private String modifiedBy;
	@Column(name = "created_time")
	private Date createdTime;
	@Column(name = "status")
	private String status;
	@Column(name = "modified_time")
	private Date modifiedTime;
	@Transient
	private IndicatorMaster indData;
	@Transient
	private int quarter;
	@Transient
	private DomainScore domainScore;

}
