package com.me.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.me.model.BlockMaster;
import com.me.model.DomainMaster;
import com.me.model.TableMaster;

@Repository
public interface TableMasterRepository extends JpaRepository<TableMaster, Long> {

	List<TableMaster> findByTableId(long tableId);
}
