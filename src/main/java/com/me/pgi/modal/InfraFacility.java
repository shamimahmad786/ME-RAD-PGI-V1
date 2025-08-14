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
@Table(name = "Pgi_Ex_Infrafacility")
public class InfraFacility {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	private String sessionYear;
	private String stateName;
	private String stateCode;
	private String districtName;
	private String districtCode;
	private String uploadDatar1;
	private String uploadDatar2;
	private String uploadDatarpercent;
	private String uploadDatarpoint;
	private String defenceTrainingr1;
	private String defenceTrainingr2;
	private String defenceTrainingrpercent;
	private String defenceTrainingrpoint;
	private String funcTrainingr1;
	private String funcTrainingr2;
	private String funcTrainingrpercent;
	private String funcTrainingrpoint;
	private String funcDrinkingwaterr1;
	private String funcDrinkingwaterr2;
	private String funcDrinkingwaterpercent;
	private String funcDrinkingwaterpoint;
	private String funcElectricityr1;
	private String funcElectricityr2;
	private String funcElectricityrpercent;
	private String funcElectricityrpoint;
	private String funcGirltoiletr1;
	private String funcGirltoiletr2;
	private String funcGirltoiletpercent;
	private String funcGirltoiletpoint;
	private String funcBoytoiletr1;
	private String funcBoytoiletr2;
	private String funcBoytoiletpercent;
	private String funcBoytoiletpoint;
	private String freeTextbookr1;
	private String freeTextbookr2;
	private String freeTextbookpercent;
	private String freeTextbookpoint;
	private String elementaryUniformr1;
	private String elementaryUniformr2;
	private String elementaryUniformpercent;
	private String elementaryUniformpoint;
	private String kitchenGardenr1;
	private String  kitchenGardenr2;
	private String  kitchenGardenpercent;
	private String  kitchenGardenpoint;
}
