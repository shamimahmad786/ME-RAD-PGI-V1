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
@Table(name = "master_table_field")
@NamedQueries({
		@NamedQuery(name = "TableFields.getTableField", query = "Select b from TableFields b where b.tableId = :tableId"), })
public class TableFields {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "field_id")
	private Long filedId;
	@Column(name = "table_id")
	private Long tableId;
	@Column(name = "field_name")
	private String fieldName;
	@Column(name = "display_Name")
	private String displayName;
	@Column(name = "field_type")
	private String fieldType;
	@Column(name = "field_input")
	private String fieldInput;

}
