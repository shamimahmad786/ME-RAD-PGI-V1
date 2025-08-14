package com.me.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "master_table")
@NamedQueries({
		@NamedQuery(name = "TableMaster.getTable", query = "Select b from TableMaster b where b.tableId = :tableId"), })
public class TableMaster {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "table_id")
	private Long tableId;
	@Column(name = "table_name")
	private String tableName;
	@Column(name = "display_Name")
	private String displayName;
	@Column(name = "table_class_name")
	private String className;

}
