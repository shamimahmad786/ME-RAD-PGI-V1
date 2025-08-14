package com.me.dataentry.modal;

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
@Table(name = "NCERT_CM_Textbookdetails")
public class NcertTextBookDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	private int noTextbookTitles;
	private String languageTextbook;
	private int noTextbookVocational;
	private int stCopyrightTestbook;
	private int stDirectpurchaseTestbook;
	private long bookPublished;
}
