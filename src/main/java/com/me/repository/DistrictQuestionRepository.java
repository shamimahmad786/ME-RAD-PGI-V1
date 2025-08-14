package com.me.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.me.model.DistrictQuestion;

@Repository
public interface DistrictQuestionRepository extends JpaRepository<DistrictQuestion, Long> {
List<DistrictQuestion> findByDomainIdOrderBySortIdAsc(Long domainid);
List<DistrictQuestion>   findAllByOrderBySortIdAsc();
}
