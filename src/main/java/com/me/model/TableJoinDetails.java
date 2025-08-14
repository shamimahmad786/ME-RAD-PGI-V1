package com.me.model;

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
@Table(name = "table_join_details")
@NamedQueries({
		@NamedQuery(name = "TableJoinDetails.getTableJoin", query = "Select b from TableJoinDetails b where b.joinId = :joinId"), })
public class TableJoinDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "join_id")
	private Long joinId;
	@Column(name = "join_table_id")
	private int joinTableId;
	@Column(name = "master_table_id")
	private int masterTableId;
	@Column(name = "join_field")
	private String joinFieldName;
	@Transient
	private String joinTableClassName;

}
