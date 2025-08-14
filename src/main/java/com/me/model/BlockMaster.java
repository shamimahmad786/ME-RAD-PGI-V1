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
@Table(name = "mst_block")
@NamedQueries({
		@NamedQuery(name = "BlockMaster.getBlock", query = "Select b from BlockMaster b where b.districtId = :districtId"), })
public class BlockMaster {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "block_id")
	private Long blockId;
	@Column(name = "district_id")
	private Long districtId;
	@Column(name = "block_name")
	private String blockName;
	@Column(name = "udise_block_code")
	private String udiseBlockCode;
	@Column(name = "udise_dist_code")
	private String udiseDistCode;

}
