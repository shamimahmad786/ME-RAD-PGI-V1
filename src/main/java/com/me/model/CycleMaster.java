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
@Table(name = "cycle_master")
public class CycleMaster {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cycleid")
	private Long cycleId;
	@Column(name = "cyclecode")
	private String cycleCode;
	@Column(name = "year")
	private int year;
	@Column(name = "cycledesc")
	private String cycleDesc;

}
