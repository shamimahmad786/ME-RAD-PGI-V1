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
@Table(name = "Pgi_Ex_Access")
public class Access {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	private String sessionYear;
	private String stateName;
	private String stateCode;
	private String districtName;
	private String districtCode;
	private String tratePtoupperprimaryr1;
	private String tratePtoupperprimaryr2;
	private String tratePtoupperprimaryrpercentage;
	private String tratePtoupperprimaryrpoints;
	private String trateUptosecondaryr1;
	private String trateUptosecondaryr2;
	private String trateUptosecondaryrpercentage;
	private String trateUptosecondaryrpoints;
	private String schoolIdentified;
	private String schoolIdentifiedpercentage;
	private String schoolIdentifiedpoints;
	private String rrateElementryr1;
	private String rrateElementryr2;
	private String rrateElementryrpercentage;
	private String rrateElementryrpoints;
	private String rrateSecondaryr1;
	private String rrateSecondaryr2;
	private String rrateSecondaryrpercentage;
	private String rrateSecondaryrpoints;
	private String outofchildCountr1;
	private String outofchildCountr2;
	private String outofchildCountrpercentage;
	private String outofchildCountrpoints;

}
