package com.me.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.me.model.DataSourceMaster;

@Repository
public interface DataSourceRepository extends JpaRepository<DataSourceMaster, Long> {

}
