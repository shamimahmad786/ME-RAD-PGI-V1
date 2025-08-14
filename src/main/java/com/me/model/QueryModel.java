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
public class QueryModel {
	private String className;
	
	private String fieldName;
	
	private String fieldAlias;
	
	private String fieldValue;
	
	private String fieldType;
	
	private String conditionType;
	
	private String filterName;
	
	private String joinTableName;
	
		private TableJoinDetails joinDetails;
	
}
