package com.me.pgi.service;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.me.bean.DomainData;
import com.me.bean.ManagementGridEnroll;
import com.me.bean.QueryResult;
import com.me.bean.StaticReportBean;
import com.me.model.DistrictMaster;
import com.me.model.PgiDistrictPerformance;
import com.me.model.PgiPerformance;
import com.me.model.PgiPerformanceApprover;
import com.me.model.StateMaster;
import com.me.pgi.modal.Access;
import com.me.pgi.modal.DigitalLearning;
import com.me.pgi.modal.DistrictPgi;
import com.me.pgi.modal.Equity;
import com.me.pgi.modal.GovernanceProcess;
import com.me.pgi.modal.InfraFacility;
import com.me.pgi.modal.LearningOutcome;
import com.me.pgi.repository.AccessRepository;
import com.me.pgi.repository.DigitalLearningRepository;
import com.me.pgi.repository.DistrictPgiRepository;
import com.me.pgi.repository.EquityRepository;
import com.me.pgi.repository.GovernanceProcessRepository;
import com.me.pgi.repository.InfraFacilityRepository;
import com.me.pgi.repository.LearningOutcomeRepository;
import com.me.bean.NativeRepository;
import com.me.repository.DistrictRepository;
import com.me.repository.PgiApproverRepository;
import com.me.repository.PgiDistrictPerformanceRepository;
import com.me.repository.PgiPerformanceRepository;
import com.me.repository.StateRepository;
import com.fasterxml.jackson.core.JsonProcessingException;

@Service
public class PgiImpl {
	@Autowired
	NativeRepository nativeRepository;
	@Autowired
	StaticReportBean staticReportBean;
	@Autowired
	StateRepository stateRepository;
	
	@Autowired
	LearningOutcomeRepository learningOutcomeRepository;
	@Autowired
	DigitalLearningRepository digitalLearningRepository;
	@Autowired
	AccessRepository accessRepository;
	@Autowired
	InfraFacilityRepository infraFacilityRepository;
	@Autowired
	EquityRepository equityRepository;
	@Autowired
	GovernanceProcessRepository governanceProcessRepository;
	@Autowired
	DistrictPgiRepository districtPgiRepository;
	@Autowired
	PgiPerformanceRepository pgiPerformanceRepository;
	@Autowired
	PgiApproverRepository pgiApproverRepository;
	@Autowired
	DistrictRepository districtRepository;
	@Autowired
	PgiDistrictPerformanceRepository  pgiDistrictPerformanceRepository;
	
	public List<StateMaster> getState(){
		System.out.println("in impl");
		return stateRepository.findAllByOrderByStateNameAsc();
//		return stateRepository.findAll();
	}
	
	public StaticReportBean getStateDomain(DomainData domainValue){
		String sql="select * from pgi_all_values_state where state_id="+domainValue.getStateId()+ " and year='"+domainValue.getYear()+"' order by p_id";
		System.out.println(sql);
		QueryResult qrObj = nativeRepository.executeQueries(sql);
		staticReportBean.setColumnName(qrObj.getColumnName());
		staticReportBean.setRowValue(qrObj.getRowValue());
		return staticReportBean;
	}
	
	public StaticReportBean getSateData(String stateId){
		String sql="select * from mst_state where state_id="+stateId;
		QueryResult qrObj = nativeRepository.executeQueries(sql);
		staticReportBean.setColumnName(qrObj.getColumnName());
		staticReportBean.setRowValue(qrObj.getRowValue());
		return staticReportBean;
	}
	
	public  LearningOutcome saveDistLoQuestion(LearningOutcome LoData) throws JsonProcessingException {
		System.out.println(LoData.getAvgLangScore5());
		return learningOutcomeRepository.save(LoData);
	}
	
	public  List<LearningOutcome> getLOPGI(String distId) throws JsonProcessingException {
		return learningOutcomeRepository.findByDistrictCode(distId);
	}
	
	public  List<DigitalLearning> getDLPGI(String distId) throws JsonProcessingException {
		return digitalLearningRepository.findByDistrictCode(distId);
	}

	public  DigitalLearning saveDistDLQuestion(DigitalLearning DLData) throws JsonProcessingException {
		return digitalLearningRepository.save(DLData);
	}
	
	public  Access saveDistAQuestion(Access DLData) throws JsonProcessingException {
		return accessRepository.save(DLData);
	}
	public  InfraFacility saveDistIFQuestion(InfraFacility DLData) throws JsonProcessingException {
		return infraFacilityRepository.save(DLData);
	}
	public  Equity saveDistEQuestion(Equity DLData) throws JsonProcessingException {
		return equityRepository.save(DLData);
	}
	public  GovernanceProcess saveDistGPQuestion(GovernanceProcess DLData) throws JsonProcessingException {
		return governanceProcessRepository.save(DLData);
	}
	
	public  List<Access> getAPGI(String distId) throws JsonProcessingException {
		return accessRepository.findByDistrictCode(distId);
	}
	
	public  List<InfraFacility> getIFPGI(String distId) throws JsonProcessingException {
		return infraFacilityRepository.findByDistrictCode(distId);
	}
	public  List<Equity> getEPGI(String distId) throws JsonProcessingException {
		return equityRepository.findByDistrictCode(distId);
	}
	public  List<GovernanceProcess> getGPPGI(String distId) throws JsonProcessingException {
		return governanceProcessRepository.findByDistrictCode(distId);
	}
	
	public  DistrictPgi saveDistPGI(@RequestBody DistrictPgi dpData) throws JsonProcessingException {
		return districtPgiRepository.save(dpData);
	}
	public  List<DistrictPgi> getDistrictPGI(String stateId) throws JsonProcessingException {
		return districtPgiRepository.findByStateCode(stateId);
	}
	
	
	public  List<DistrictPgi> getFinalPGIStatus(@RequestBody String distCode) throws JsonProcessingException {
		return districtPgiRepository.findByDistCode(distCode);
	}
	
	public  List<PgiPerformance> getStatePerformance(HashMap<String,Object> mp) throws JsonProcessingException {
		return pgiPerformanceRepository.findByStateIdAndInityear(Long.parseLong(String.valueOf(mp.get("stateId"))),String.valueOf(mp.get("inityear")));
	}
	
	public void updatePerformance(List<PgiPerformance> obj) {
		System.out.println(obj.get(0).getId());
		System.out.println(obj.get(0).getInityear());
		pgiPerformanceRepository.saveAll(obj);
	}
	
	public void updateDistPerformance(List<PgiDistrictPerformance> obj) {
		for(int i=0;i<obj.size();i++) {
			if(pgiDistrictPerformanceRepository.existsById(obj.get(i).getId())){
				pgiDistrictPerformanceRepository.save(obj.get(i));
			}
		}
	
//		pgiDistrictPerformanceRepository.existsById(id)
		
	}
	
	
	public PgiPerformanceApprover saveApprover(PgiPerformanceApprover data) {
		return pgiApproverRepository.save(data);
	}
	public List<PgiPerformanceApprover> getApproveData(Integer data,String type,String inityear) {
//		return null;
		System.out.println("get approve data--->"+data);
		if(type.equalsIgnoreCase("STLU")) {
//		return pgiApproverRepository.findByStateCode(data);
			
			System.out.println("data---->"+data);
			System.out.println("inityear---->"+inityear);
			
		return	pgiApproverRepository.findByStateCodeAndApproverTypeAndInityear(data,"S",inityear);
		}else {
//			return null;
			System.out.println("get approve data--->"+String.valueOf(data));
		return pgiApproverRepository.findByDistrictCodeAndInityear(data,inityear);
		}
	}
	
	public List<PgiPerformanceApprover> getAllApproveData(String initYear) {
//		return null;
//		return pgiApproverRepository.findAll();
		List<PgiPerformanceApprover> obj=pgiApproverRepository.findByApproverTypeAndInityear("S",initYear);
		QueryResult qrObj = nativeRepository.executeQueries("select * from spgicategories_4");
		
		for(int i=0;i<obj.size();i++) {
			for(int j=0;j<qrObj.getRowValue().size();j++) {
				if(obj.get(i).getStateCode() == qrObj.getRowValue().get(j).get("statecode")) {
					obj.get(i).setGrade(String.valueOf(qrObj.getRowValue().get(j).get("grade")));
					obj.get(i).setScore(String.valueOf(qrObj.getRowValue().get(j).get("gtotal")));
				}
			}
		}
		
		
		return obj;
	}
	
	public List<PgiPerformanceApprover> getAllDistrictApproveData(Integer stateCode, String approverType,String inityear) {

		return pgiApproverRepository.findByStateCodeAndApproverTypeAndInityear(stateCode,approverType,inityear);
	}
	
	
	
	public int deletePgiDocs(String filePath,String filename) {
	File f=new File(filePath+File.separator+filename);
	if(f.exists()) {
		f.delete();
		return 1;
	}
	return 0;
	}
	
	public StaticReportBean getPreviousYearPgi(Integer stateCode){
		QueryResult qrObj = nativeRepository.executeQueries("select dom_id,year,sum(weight_val::decimal) from pgi_all_values_state where state_id='"+stateCode+"' group by dom_id,year order by dom_id,year");
//				+ "order by  psq.domainid , psq.questionid ");
		staticReportBean.setColumnName(qrObj.getColumnName());
		staticReportBean.setRowValue(qrObj.getRowValue());
		staticReportBean.setColumnDataType(qrObj.getColumnDataType());
		staticReportBean.setStatus("1");
		return staticReportBean;
	}
	
	public List<DistrictMaster> getDistrictName(Integer districtId) {
//		return null;
		System.out.println(districtRepository.findByDistrictId(districtId));
		return districtRepository.findByDistrictId(districtId);
	}
	
	public List<DistrictMaster> getAllDistrict(Long stateId) {
		
		return districtRepository.findByStateId(stateId);
	}
	
	
	
	
}
