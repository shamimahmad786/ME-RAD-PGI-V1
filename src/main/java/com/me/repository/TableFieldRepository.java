package com.me.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.me.model.BlockMaster;
import com.me.model.TableFields;
import com.me.model.TableMaster;
import com.me.model.User;

@Repository
public interface TableFieldRepository extends JpaRepository<TableFields, Long> {

	List<TableFields> findByTableId(long tableId);
}
