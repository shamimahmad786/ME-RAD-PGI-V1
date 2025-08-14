package com.me.service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.me.model.CategoryMaster;
import com.me.model.CycleMaster;
import com.me.model.DistrictQuestion;
import com.me.model.DomainMaster;
import com.me.model.IndicatorMaster;
import com.me.model.StateQuestion;
import com.me.model.TableFields;
import com.me.model.TableJoinDetails;
import com.me.model.TableMaster;
import com.me.repository.CategoryMasterRepository;
import com.me.repository.CycleMasterRepository;
import com.me.repository.DataSourceRepository;
import com.me.repository.DistrictQuestionRepository;
import com.me.repository.DomainMasterRepository;
import com.me.repository.IndicatorMasterRepo;
import com.me.repository.PgiPerformanceRepository;
import com.me.repository.StateQuestionRepository;
import com.me.repository.TableFieldRepository;
import com.me.repository.TableJoinRepository;
import com.me.repository.TableMasterRepository;
import com.fasterxml.jackson.core.JsonProcessingException;

@Service
public class MasterDataServiceImpl {

	@Autowired
	private CategoryMasterRepository categoryRepository;

	@Autowired
	private DomainMasterRepository domainMasterRepository;

	@Autowired
	private CycleMasterRepository cycleMasterRepository;

	@Autowired
	private DataSourceRepository dataSourceRepository;

	@Autowired
	private DistrictQuestionRepository districtQuestionRepository;
	
	@Autowired
	private StateQuestionRepository stateQuestionRepository;
	
	@Autowired
	private TableMasterRepository tableMasterRepository;
	
	@Autowired
	private TableFieldRepository tableFieldRepository;
	
	@Autowired
	private TableJoinRepository tableJoinRepository;
	
	@Autowired
	IndicatorMasterRepo indicatorMasterRepo;
//	@Autowired
//	PgiPerformanceRepository  pgiPerformanceRepository;
//	
	
	public List<CategoryMaster> getCategory() {
		return categoryRepository.findAll();
	}

	public List<DomainMaster> getDomain(long categoryId) {
		return domainMasterRepository.findByCategoryId(categoryId);
	}

	public List<CycleMaster> getCycle(int year) {
		return cycleMasterRepository.findByYear(year);
	}

	public List<DistrictQuestion> getDistrictQuestions(String domainid) {
//		List<DistrictQuestion> districtQuestion = districtQuestionRepository.findAll().stream()
//				.sorted(Comparator.comparing(DistrictQuestion::getSortId)).collect(Collectors.toList());
		System.out.println("Before get question");
		List<DistrictQuestion> districtQuestion = districtQuestionRepository.findByDomainIdOrderBySortIdAsc(Long.parseLong(domainid));
		System.out.println("return question--->"+districtQuestion);
		return districtQuestion;
	}
	
	public List<StateQuestion> getStateQuestion(String domainid) {
//		List<DistrictQuestion> districtQuestion = districtQuestionRepository.findAll().stream()
//				.sorted(Comparator.comparing(DistrictQuestion::getSortId)).collect(Collectors.toList());
		System.out.println("Before get question");
		List<StateQuestion> districtQuestion = stateQuestionRepository.findByDomainIdOrderBySortIdAsc(Long.parseLong(domainid));
		System.out.println("return question--->"+districtQuestion);
		return districtQuestion;
	}
	
	
	public List<DistrictQuestion> getAllDistrictQuestion() throws JsonProcessingException {
		// log.info("User requested data " + objectWriter.writeValueAsString(search));

		return districtQuestionRepository.findAllByOrderBySortIdAsc();
	}
	
	public List<StateQuestion> getAllStateQuestion() throws JsonProcessingException {
		// log.info("User requested data " + objectWriter.writeValueAsString(search));
//		return pgiPerformanceRepository.findByStateIdOrderByDomainidAsc(stateId);
		return stateQuestionRepository.findAllByOrderBySortIdAsc();
	}
	
	public List<TableMaster> getAllTables() {
		return tableMasterRepository.findAll();
	}
	
	public List<TableMaster> getTablebyId(long tableId) {
		return tableMasterRepository.findByTableId(tableId);
	}
	
	public List<TableFields> getTableFields(long tableId) {
		return tableFieldRepository.findByTableId(tableId);
	}
	

	public List<TableJoinDetails> getJoinDetails(int masterTableId) {
		return tableJoinRepository.findByMasterTableId(masterTableId);
	}
	
	
	public List<IndicatorMaster> getIndicators(int domainId) throws JsonProcessingException {
		// log.info("User requested data " + objectWriter.writeValueAsString(search));
		return indicatorMasterRepo.findByDomainIdOrderByIndIdAsc(domainId);
	}
	
	

}
