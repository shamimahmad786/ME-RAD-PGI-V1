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
@Table(name = "category_master")
public class CategoryMaster {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "categoryid")
	private Long categoryId;
	@Column(name = "categoryname")
	private String categoryName;
}
