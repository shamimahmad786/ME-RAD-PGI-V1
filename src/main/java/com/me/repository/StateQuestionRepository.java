package com.me.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.me.model.DistrictQuestion;
import com.me.model.StateQuestion;

@Repository
public interface StateQuestionRepository extends JpaRepository<StateQuestion, Long> {
	List<StateQuestion> findByDomainIdOrderBySortIdAsc(Long domainid);
	List<StateQuestion>   findAllByOrderBySortIdAsc();
}
