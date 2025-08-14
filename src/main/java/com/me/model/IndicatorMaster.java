package com.me.model;

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
@Table(name = "indicator_master")
public class IndicatorMaster

{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ind_id")
	private long indId;
	@Column(name = "domain_id")
	private int domainId;
	@Column(name = "serial")
	private String serial;
	@Column(name = "created_by")
	private String createdBy;
	@Column(name = "modified_by")
	private String modifiedBy;
	@Column(name = "created_time")
	private Date createdTime;
	@Column(name = "status")
	private String status;
	@Column(name = "weight")
	private int weight;
	@Column(name = "source")
	private String source;
	@Column(name = "description")
	private String description;
	@Column(name = "modified_time")
	private Date modifiedTime;
	@Column(name = "name")
	private String name;
	@Column(name = "benchmark")
	private int benchmark;

}
