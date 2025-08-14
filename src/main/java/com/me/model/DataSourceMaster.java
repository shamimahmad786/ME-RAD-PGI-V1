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
@Table(name = "datasource_master")
public class DataSourceMaster {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "sourceid")
	private Long sourceId;
	@Column(name = "sourcename")
	private String sourceName;
}
