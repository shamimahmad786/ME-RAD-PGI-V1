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
@Table(name = "domain_master")
public class DomainMaster {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "domainid")
	private Long domainId;
	@Column(name = "domainname")
	private String domainName;
	@Column(name = "categoryid")
	private Long categoryId;

}
