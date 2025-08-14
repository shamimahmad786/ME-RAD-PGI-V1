package com.me.pgi.modal;

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
@Table(name = "Pgi_Ex_Equity")
public class Equity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	private String sessionYear;
	private String stateName;
	private String stateCode;
	private String districtName;
	private String districtCode;
	private String aidsAppliancesr1;
	private String aidsAppliancesr2;
	private String aidsAppliancespercent;
	private String aidsAppliancespoint;
	private String girlBicycler1;
	private String girlBicycler2;
	private String girlBicyclepercent;
	private String girlBicyclepoint;
	private String femaleLiteracyr1;
	private String femaleLiteracyr2;
	private String femaleLiteracypercent;
	private String femaleLiteracypoint;
}
