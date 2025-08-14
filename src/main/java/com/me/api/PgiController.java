package com.me.api;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.me.bean.AllValaueState;
import com.me.bean.DistrictBean;
import com.me.bean.DistrictReport;
import com.me.bean.DomainData;
import com.me.bean.PgiDocument;
import com.me.bean.QueryResult;
import com.me.bean.StaticReportBean;
import com.me.model.DistrictMaster;
import com.me.model.PgiColorCode;
import com.me.model.PgiPerformance;
import com.me.model.PgiPerformanceApprover;
import com.me.model.StateMaster;
import com.me.pgi.service.PgiImpl;
import com.me.repository.DistrictRepository;
import com.me.repository.PgiColorCodeRepository;
import com.me.bean.NativeRepository;
import com.me.util.ApiPaths;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
@RequestMapping(ApiPaths.PgiCtrl.CTRL)
@CrossOrigin(origins = {"https://demopgi.udiseplus.gov.in","http://10.25.26.251:4200","http://10.25.26.251:8086","http://demo.sdmis.gov.in","http://localhost:4200","http://pgi.seshagun.gov.in","https://pgi.udiseplus.gov.in","http://pgi.udiseplus.gov.in"}, allowedHeaders = "*",allowCredentials = "true")
public class PgiController {
	@Autowired
	PgiImpl  pgiImpl;
	@Autowired
	NativeRepository nativeRepository;
//	@Autowired
//	StaticReportBean staticReportBean;

	@Autowired
	PgiColorCodeRepository  pgiColorCodeRepository;
	
	@Value("${downloadDocumentPath}")
	private String downloadDocumentPath;
	
	@Value("${pgiCertificatePath}")
	private String pgiCertificatePath;
	private PgiPerformanceApprover data;
	
	@RequestMapping(value = "/getStateList", method = RequestMethod.POST, produces={"application/json","application/xml"})
	public List<StateMaster> getState() throws JsonProcessingException {
		System.out.println("get state");
//		System.out.println("called state service"+pgiImpl.getState());
		return pgiImpl.getState();
//		return null;
		// return locationServiceImpl.getState();
	}
	
	@RequestMapping(value = "/getStateDomain", method = RequestMethod.POST)
	public StaticReportBean getStateDomain(@RequestBody DomainData domainValue) throws JsonProcessingException {
		System.out.println("domainValue------->"+domainValue);
		return pgiImpl.getStateDomain(domainValue);
		// return locationServiceImpl.getState();
//		return null;
	}
	
	@RequestMapping(value = "/getSateData", method = RequestMethod.POST)
	public StaticReportBean getSateData(@RequestBody String stateId) throws JsonProcessingException {		
		return pgiImpl.getSateData(stateId);
	}
	
	@GetMapping("/download/{fileName:.+}")
	public ResponseEntity downloadFileFromLocal(@PathVariable String fileName) {
//		System.out.println("path--->"+downloadDocumentPath+File.separator+fileName+File.separator+"1.pdf");
//		Path path = Paths.get("1.pdf");
		Path path = Paths.get(downloadDocumentPath+File.separator+fileName+File.separator+"1.pdf");
//		Path path = Paths.get("C:\\apache-tomcat-9.0.30\\webapps\\performance"+File.separator+fileName+File.separator+"1.pdf");
//		System.out.println("path--->"+"C:\\apache-tomcat-9.0.30\\webapps\\performance"+File.separator+fileName+File.separator+"1.pdf");
		Resource resource = null;
		try {
			resource = new UrlResource(path.toUri());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		String contentType = null;
		try {
			contentType = Files.probeContentType(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + getSateName(fileName)+"_performance"+".pdf" + "\"")
				.body(resource);
	}
	
	@RequestMapping(value = "/getStateReview", method = RequestMethod.POST)
	public StaticReportBean getStateReview(@RequestBody String year) throws JsonProcessingException {	
		System.out.println("in get state review called");
		StaticReportBean sobj=new StaticReportBean();
		QueryResult qrObj = nativeRepository.executeQueries("select ms.state_id,ms.state_name,per.status \r\n"
				+ "from mst_state ms left join pgi_re_perform_approver per\r\n"
				+ "on ms.state_id = per.state_code and  per.approver_type='S' and per.inityear='"+year+"' \r\n"
				+ "group by ms.state_id,ms.state_name,\r\n"
				+ "	per.status order by ms.state_name ; ");
//				+ "order by  psq.domainid , psq.questionid ");
		sobj.setColumnName(qrObj.getColumnName());
		sobj.setRowValue(qrObj.getRowValue());
		sobj.setColumnDataType(qrObj.getColumnDataType());
		sobj.setStatus("1");
		return sobj;
//		return null;
//		return pgiImpl.getSateData(stateId);
	}
	
	@RequestMapping(value = "/getStateDistrictCount", method = RequestMethod.POST)
	public StaticReportBean getStateDistrictCount(@RequestBody String yearDist) throws JsonProcessingException {	
		System.out.println("yearDist--->"+yearDist);
		StaticReportBean sobj=new StaticReportBean();
		QueryResult qrObj = nativeRepository.executeQueries("select ms.state_name, ms.state_id ,count(md.state_id) from mst_state ms left join mst_district md  on ms.state_id =md.state_id and md.inityear='"+yearDist+"' group by md.state_id,ms.state_name,ms.state_id order by ms.state_name");
//				+ "order by  psq.domainid , psq.questionid ");
		sobj.setColumnName(qrObj.getColumnName());
		sobj.setRowValue(qrObj.getRowValue());
		sobj.setColumnDataType(qrObj.getColumnDataType());
		sobj.setStatus("1");
		return sobj;
//		return null;
//		return pgiImpl.getSateData(stateId);
	}
	
	@RequestMapping(value = "/getDistrictReview", method = RequestMethod.POST)
	public StaticReportBean getDistrictReview(@RequestBody String yearDist) throws JsonProcessingException {	
		System.out.println("yearDist--->"+yearDist);
		StaticReportBean sobj=new StaticReportBean();
		QueryResult qrObj = nativeRepository.executeQueries("select ms.state_name ,md.status,count(md.status) from mst_state ms left join pgi_re_perform_approver md on ms.state_id =md.state_code and md.inityear='"+yearDist+"' and md.approver_type='D' group by md.status,ms.state_name order by ms.state_name ");
//				+ "order by  psq.domainid , psq.questionid ");
		sobj.setColumnName(qrObj.getColumnName());
		sobj.setRowValue(qrObj.getRowValue());
		sobj.setColumnDataType(qrObj.getColumnDataType());
		sobj.setStatus("1");
		return sobj;
//		return null;
//		return pgiImpl.getSateData(stateId);
	}
	
	
	@RequestMapping(value = "/getDistrictData", method = RequestMethod.POST)
	public StaticReportBean getDistrictData(@RequestBody DistrictMaster dmObj) throws JsonProcessingException {	
//		System.out.println("yearDist--->"+yearDist);
		
		System.out.println("init year--->"+dmObj.getInityear());
		StaticReportBean sobj=new StaticReportBean();
		System.out.println("Year------>"+Arrays.asList(dmObj.getInityear().split("-")).get(0));
		String query="select * from (\r\n"
				+ "select * \r\n"
				+ "from mst_district ms \r\n"
				+ "where ms.state_id = "+dmObj.getStateId()+"\r\n"
				+ "and ms.inityear='"+dmObj.getInityear()+"' ) aa \r\n"
				+ "left join pgi_re_perform_approver per on per.district_id =  aa.district_id and per.inityear ='"+ Arrays.asList(dmObj.getInityear().split("-")).get(0)+"' order by aa.district_name  ";
//				+ "order by  psq.domainid , psq.questionid "
//		QueryResult qrObj = nativeRepository.executeQueries("select ms.district_name,per.* from mst_district ms left join pgi_re_perform_approver per on ms.district_id =CAST (per.district_code AS INTEGER) where ms.state_id="+dmObj.getStateId()+" and ms.inityear='"+dmObj.getInityear()+"' order by ms.district_name");
		System.out.println("query--->"+query);
		QueryResult qrObj = nativeRepository.executeQueries(query);
		sobj.setColumnName(qrObj.getColumnName());
		sobj.setRowValue(qrObj.getRowValue());
		sobj.setColumnDataType(qrObj.getColumnDataType());
		sobj.setStatus("1");
		return sobj;
//		return null;
//		return pgiImpl.getSateData(stateId);
	}
	
	
	
	@RequestMapping(value = "/pgiCertificate", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public void handleFileUpload(@RequestParam("file") MultipartFile file, @RequestParam("docname") String docname,
			@RequestParam("stateCode") String stateCode) throws IOException {
		System.out.println("uploadDocumentPath ---->"+pgiCertificatePath);
		System.out.println("called for upload--->" + docname);
		try {
			File f = new File(pgiCertificatePath + File.separator + stateCode);
			if (!f.exists()) {
				f.mkdir();
			}
			String ext = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."),
					file.getOriginalFilename().length());
			// Path filePath = Paths.get("C:\\Users\\NIC\\SRC" + "\\" +
			// file.getOriginalFilename());
			Path filePath = Paths.get(pgiCertificatePath + File.separator + stateCode + File.separator + docname + ext);
			Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
			String userDirectory = System.getProperty("user.dir");
			System.out.println("userDirectory--->" + userDirectory);
			System.out.println("id--->" + docname);	
		}catch(Exception ex) {
			ex.printStackTrace();
		}

	}
	
	
	@RequestMapping(value = "/getPgiCertificate", method = RequestMethod.POST)
	public List<Map<String, String>> getDocumentName(@RequestBody String stateCode) {
		System.out.println("folder name--->" + stateCode);
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		File file = new File(pgiCertificatePath + File.separator + stateCode);
		String[] fileList = file.list();
		for (String name : fileList) {
			System.out.println("name--->" + name);
			Map<String, String> mp = new HashMap<String, String>();
			System.out.println(name.split("\\.")[0]);
			mp.put("docName", name.split("\\.")[0]);
			mp.put("extension", name.split("\\.")[1]);
			result.add(mp);
		}

		System.out.println(result);
		return result;
	}

	
	
	@RequestMapping(value = "/saveApprover", method = RequestMethod.POST)
	public PgiPerformanceApprover saveApprover(@RequestBody PgiPerformanceApprover data) throws JsonProcessingException {	
		System.out.println("In save approver--->"+data.getState_name());
		return pgiImpl.saveApprover(data);
	}
	
	@RequestMapping(value = "/getApproveData", method = RequestMethod.POST)
	public List<PgiPerformanceApprover> getApproveData(@RequestBody PgiPerformanceApprover data) throws JsonProcessingException {		
		System.out.println("get approve data");
		System.out.println(data.getApproverType());
		if(data.getApproverType().equalsIgnoreCase("STLU")) {
		return pgiImpl.getApproveData(data.getStateCode(),data.getApproverType(),data.getInityear());
		}else  {
		return pgiImpl.getApproveData(data.getStateCode(),data.getApproverType(),data.getInityear());
		}
	}
	
	@RequestMapping(value = "/getAllApproveData", method = RequestMethod.POST)
	public List<PgiPerformanceApprover> getAllApproveData(@RequestBody String inityear) throws JsonProcessingException {		
//		System.out.println("get all approve data--->"+stateCode);
		return pgiImpl.getAllApproveData(inityear);
	}
	
	@RequestMapping(value = "/getAllDistrictApproveData", method = RequestMethod.POST)
	public List<PgiPerformanceApprover> getAllDistrictApproveData(@RequestBody PgiPerformanceApprover data) throws JsonProcessingException {		
		System.out.println("get all approve data--->"+data.getStateCode());
		return pgiImpl.getAllDistrictApproveData(data.getStateCode(),"D",data.getInityear());
	}
	
//	
//	@RequestMapping(value = "/getDistQuestion", method = RequestMethod.POST)
//	public staticReportBean getDistQuestion() throws JsonProcessingException {		
//		System.out.println("In dist question------------------");
//		QueryResult qrObj = nativeRepository.executeQueries("select * from pgi_district_question order by questionid");
////				+ "order by  psq.domainid , psq.questionid ");
//		staticReportBean.setColumnName(qrObj.getColumnName());
//		staticReportBean.setRowValue(qrObj.getRowValue());
//		staticReportBean.setColumnDataType(qrObj.getColumnDataType());
//		staticReportBean.setStatus("1");
//		return staticReportBean;
////		return null;
////		return pgiImpl.getAllApproveData();
//	}
	
	
	@RequestMapping(value = "/getDistQuestion", method = RequestMethod.POST)
	public StaticReportBean getDistQuestion(@RequestBody DistrictBean obj) throws JsonProcessingException {		
		StaticReportBean sObj=new StaticReportBean();
		System.out.println("In dist question------------------");
		 System.out.println(obj.getDistId());
		 System.out.println(obj.getYear());
		QueryResult qrObj = nativeRepository.executeQueries("select * \r\n" + 
				"from pgi_re_dist_performance pf , pgi_district_question pd\r\n" + 
				"where pf.state_id="+obj.getState_id()+" and pf.dist_id = "+obj.getDistId()+" \r\n" + 
				"      and refrence_year="+obj.getYear()+"\r\n" + 
				"	  and pd.questionid = pf.question_id order by pd.sortid ");
//				+ "order by  psq.domainid , psq.questionid ");
		sObj.setColumnName(qrObj.getColumnName());
		sObj.setRowValue(qrObj.getRowValue());
		sObj.setColumnDataType(qrObj.getColumnDataType());
		sObj.setStatus("1");
		return sObj;

	}
	
	@RequestMapping(value = "/getDistReview", method = RequestMethod.POST)
	public StaticReportBean getDistReview(@RequestBody DistrictReport data) throws JsonProcessingException {	
		StaticReportBean sObj=new StaticReportBean();
		
		
		String sql="select mst.district_id,mst.district_name,pert.final_status \r\n"
				+ "from pgi_re_dist_performance pert, mst_district mst \r\n"
				+ "where pert.dist_id=mst.district_id \r\n"
				+ "and mst.state_id="+data.getStateId()+"  and mst.inityear = '"+data.getYear()+"'\r\n"
				+ "group by mst.district_id,mst.district_name,\r\n"
				+ "pert.final_status order by mst.district_name;";
		
		
		System.out.println("sql--->"+sql);
		
		QueryResult qrObj = nativeRepository.executeQueries(sql);
		
//		QueryResult qrObj = nativeRepository.executeQueries("select mst.district_id,mst.district_name,pert.final_status from pgi_re_dist_performance pert, mst_district mst where pert.dist_id=mst.district_id and mst.state_id="+Integer.parseInt(stateId)+" group by mst.district_id,mst.district_name,\r\n" 
//				+ "pert.final_status order by mst.district_name; ");
//				+ "order by  psq.domainid , psq.questionid ");
		sObj.setColumnName(qrObj.getColumnName());
		sObj.setRowValue(qrObj.getRowValue());
		sObj.setColumnDataType(qrObj.getColumnDataType());
		sObj.setStatus("1");
		return sObj;
//		return null;
//		return pgiImpl.getSateData(stateId);
	}
	
	@RequestMapping(value = "/getStatePriviousData", method = RequestMethod.POST)
	public StaticReportBean getStatePriviousData(@RequestBody AllValaueState obj ) throws JsonProcessingException {		
//		System.out.println("In dist question------------------");
		StaticReportBean sobj=new StaticReportBean();
		QueryResult qrObj = nativeRepository.executeQueries("select q_s_no,question_desc,weight_val from pgi_all_values_state pavs where year='"+obj.getYear()+"' and state_id ="+obj.getState_id()+" order by dom_id,p_id");
		sobj.setColumnName(qrObj.getColumnName());
		sobj.setRowValue(qrObj.getRowValue());
		sobj.setColumnDataType(qrObj.getColumnDataType());
		sobj.setStatus("1");
		return sobj;
	}
	
	
	@RequestMapping(value = "/downloadPgiDocs", method = RequestMethod.GET)
	public ResponseEntity<Resource> downloadPgiDocs(HttpServletRequest request, HttpServletResponse response, @RequestParam("stateCode") String stateCode,
			@RequestParam("fileName") String fileName, @RequestParam("fileExtension") String fileExtension) throws IOException {
		 Resource resource=null;
		 Path filePath=null;
		try {
			System.out.println(pgiCertificatePath+File.separator +stateCode+File.separator+fileName+"."+fileExtension);
//			Path filePath = fileStorageLocation.resolve("C:\\Users\\NIC\\SRC"+File.separator +schoolType+File.separator+fileName+"."+fileExtension).normalize();
			 String dataDirectory = request.getServletContext().getRealPath(pgiCertificatePath+File.separator +stateCode);
		        filePath  = Paths.get(pgiCertificatePath+File.separator +stateCode, fileName+"."+fileExtension);
	         resource = new UrlResource(filePath.toUri());
	        System.out.println(filePath.toUri());
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		String contentType = Files.probeContentType(filePath);
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);
	}
	
	@RequestMapping(value = "/deletePgiDocs", method = RequestMethod.POST)
	public int deletePgiDocs(@RequestBody PgiDocument pgiDoc) throws JsonProcessingException {		
		System.out.println("get all approve data");
		return pgiImpl.deletePgiDocs(pgiCertificatePath+File.separator+pgiDoc.getStatecode(),pgiDoc.getDocName()+"."+pgiDoc.getExtension());
	}
	
	@RequestMapping(value = "/getPreviousYearPgi", method = RequestMethod.POST)
	public StaticReportBean getPreviousYearPgi(@RequestBody PgiPerformanceApprover objData) throws JsonProcessingException {		
//		System.out.println("get all approve data");
		return pgiImpl.getPreviousYearPgi(objData.getStateCode());
	}
	
	@RequestMapping(value = "/getDistrictName", method = RequestMethod.POST)
	public StaticReportBean getDistrictName(@RequestBody String distId) throws JsonProcessingException {		
		System.out.println("distId------------->"+distId);
		StaticReportBean sObj=new StaticReportBean();
		QueryResult qrObj = nativeRepository.executeQueries("select * from mst_district where district_id="+Integer.parseInt(distId));
		sObj.setColumnName(qrObj.getColumnName());
		sObj.setRowValue(qrObj.getRowValue());
		sObj.setColumnDataType(qrObj.getColumnDataType());
		sObj.setStatus("1");
		return sObj;
//		return pgiImpl.getDistrictName(Integer.getInteger(distId));
	}
	
	@RequestMapping(value = "/getAllDistrict", method = RequestMethod.POST)
	public List<DistrictMaster> getAllDistrict(@RequestBody String stateId) throws JsonProcessingException {		
		return pgiImpl.getAllDistrict(Long.parseLong(stateId));
	}
	
	@RequestMapping(value = "/getColorJson", method = RequestMethod.POST)
	public PgiColorCode getColorJson(@RequestBody PgiColorCode data) throws JsonProcessingException {	
		System.out.println("color code--->"+data.getYear());
		
//		List<PgiColorCode> obj=	pgiColorCodeRepository.findAll();
//		System.out.println(obj.get(0).getColor());
		
		return pgiColorCodeRepository.findAllByYear(data.getYear());
	}
	
//	select dom_id,year,sum(weight_val::decimal) from pgi_all_values_state where state_id=110    group by dom_id,year;
	
	
	
	
//		return null;
//	}
	
	@RequestMapping(value = "/getDistrictReport", method = RequestMethod.POST)
	public StaticReportBean getDistrictReport(@RequestBody DistrictReport data) throws JsonProcessingException {
		
		String year="";
		
		if(data.getYear().equalsIgnoreCase("2018")) {
			year="2018-19";
		}else if(data.getYear().equalsIgnoreCase("2019")) {
			year="2019-20";
		}else if(data.getYear().equalsIgnoreCase("2020")) {
			year="2020-21";
		}else if(data.getYear().equalsIgnoreCase("2021")) {
			year="2021-22";
		}else if(data.getYear().equalsIgnoreCase("2022")) {
			year="2022-23";
		}else if(data.getYear().equalsIgnoreCase("2023")) {
			year="2023-24";
		}
		
		
		StaticReportBean sObj=new StaticReportBean();
		System.out.println("In dist question------------------"+data.getDistrictId());
		String sql;
		QueryResult qrObj=null;
		
		if(data.getDistrictId().equalsIgnoreCase("99") &&  data.getStateId().equalsIgnoreCase("all")) {
//			 sql="select md.udise_district_code , md.district_name ,pdq.questionid , pdq.quesseries , pdq.questiondesc ,pdq.weight , ms.state_name , \r\n"
//						+ "pdq.subquestiondesc1 ,prdp.r1 ,\r\n"
//						+ "pdq.subquestiondesc2 ,prdp.r2 ,\r\n"
//						+ "pdq.data_source ,\r\n"
//						+ "prdp.cal_vlaue ,\r\n"
//						+ "prdp.cal_point \r\n"
//						+ "from pgi_re_dist_performance prdp , pgi_district_question pdq , mst_district md  , mst_state ms \r\n"
//						+ "where refrence_year = '"+data.getYear()+"'\r\n"
//						+ "and prdp.question_id = pdq.questionid \r\n"
//						+ "and prdp.udise_district_code = md.udise_district_code \r\n"
//						+" and ms.udise_state_code = md.udise_state_code  "
//						+ "and md.inityear = '"+year+"'\r\n"
//						
////						+ "and prdp.state_id ='"+data.getStateId()+"'\r\n"
////						+ "order by  md.udise_district_code ,md.district_name, pdq.sortid";
//				  + "order by  md.district_name, pdq.sortid";
			System.out.println("year--"+year);
			System.out.println("data.getYear()---"+data.getYear());
			
			sql="select md.udise_district_code\r\n"
					+ ", md.district_name \r\n"
					+ ",pdq.questionid , \r\n"
					+ "pdq.quesseries , \r\n"
					+ "pdq.questiondesc \r\n"
					+ ",pdq.weight \r\n"
					+ ", ms.state_name , \r\n"
					+ "pdq.subquestiondesc1 ,prdp.r1 ,\r\n"
					+ "pdq.subquestiondesc2 ,prdp.r2 ,\r\n"
					+ "pdq.data_source ,\r\n"
					+ "prdp.cal_vlaue ,\r\n"
					+ "prdp.cal_point \r\n"
					+ "from pgi_re_dist_performance prdp join pgi_district_question pdq on (prdp.question_id = pdq.questionid )\r\n"
					+ "join  mst_district md on (prdp.dist_id = md.district_id )\r\n"
					+ "join  mst_state ms on (md.state_id  = ms.state_id )\r\n"
					+ "where prdp.refrence_year = '"+data.getYear()+"' and  md.inityear = '"+year+"'\r\n"
					+ "and ms.inityear =  '"+data.getYear()+"'  order by ms.state_name , md.district_name, pdq.sortid";
			
			 qrObj = nativeRepository.executeQueries(sql);
			 System.out.println("final query---"+sql);
		}
		
		else if(data.getDistrictId().equalsIgnoreCase("99")) {
			
			
			
//			  sql="select md.udise_district_code , md.district_name ,pdq.questionid , pdq.quesseries , pdq.questiondesc ,pdq.weight , ms.state_name , \r\n"
//					+ "pdq.subquestiondesc1 ,prdp.r1 ,\r\n"
//					+ "pdq.subquestiondesc2 ,prdp.r2 ,\r\n"
//					+ "pdq.data_source ,\r\n"
//					+ "prdp.cal_vlaue ,\r\n"
//					+ "prdp.cal_point \r\n"
//					+ "from pgi_re_dist_performance prdp , pgi_district_question pdq , mst_district md  , mst_state ms \r\n"
//					+ "where refrence_year = '"+data.getYear()+"'\r\n"
//					+ "and prdp.question_id = pdq.questionid \r\n"
//					+ "and prdp.udise_district_code = md.udise_district_code \r\n"
//					+" and ms.udise_state_code = md.udise_state_code  "
//					+ "and md.inityear = '"+year+"'\r\n"
//					
//					+ "and prdp.state_id ='"+data.getStateId()+"'\r\n"
////					+ "order by  md.udise_district_code ,md.district_name, pdq.sortid";
//			  + "order by  md.district_name, pdq.sortid";
			
			sql="select md.udise_district_code\r\n"
					+ ", md.district_name \r\n"
					+ ",pdq.questionid , \r\n"
					+ "pdq.quesseries , \r\n"
					+ "pdq.questiondesc \r\n"
					+ ",pdq.weight \r\n"
					+ ", ms.state_name , \r\n"
					+ "pdq.subquestiondesc1 ,prdp.r1 ,\r\n"
					+ "pdq.subquestiondesc2 ,prdp.r2 ,\r\n"
					+ "pdq.data_source ,\r\n"
					+ "prdp.cal_vlaue ,\r\n"
					+ "prdp.cal_point \r\n"
					+ "from pgi_re_dist_performance prdp join pgi_district_question pdq on (prdp.question_id = pdq.questionid )\r\n"
					+ "join  mst_district md on (prdp.dist_id = md.district_id )\r\n"
					+ "join  mst_state ms on (md.state_id  = ms.state_id )\r\n"
					+ "where prdp.refrence_year = '"+data.getYear()+"' and  md.inityear = '"+year+"'\r\n"
					+ "and ms.inityear =  '"+data.getYear()+"'"
							+ " and prdp.state_id ='"+data.getStateId()+"' "
							+ "  order by ms.state_name , md.district_name, pdq.sortid";
			
		System.out.println("sql---->"+sql);
					qrObj = nativeRepository.executeQueries(sql);
		}else if(data.getDistrictId().equalsIgnoreCase("all")) {
			
System.out.println("get all data");
			  sql="select md.udise_district_code , md.district_name ,pdq.questionid , pdq.quesseries , pdq.questiondesc ,pdq.weight , ms.state_name , \r\n"
					+ "pdq.subquestiondesc1 ,prdp.r1 ,\r\n"
					+ "pdq.subquestiondesc2 ,prdp.r2 ,\r\n"
					+ "pdq.data_source ,\r\n"
					+ "prdp.cal_vlaue ,\r\n"
					+ "prdp.cal_point \r\n"
					+ "from pgi_re_dist_performance prdp , pgi_district_question pdq , mst_district md ,  mst_state ms  \r\n"
					+ "where refrence_year = '"+data.getYear()+"'\r\n"
					+ "and prdp.question_id = pdq.questionid \r\n"
					+ "and prdp.udise_district_code = md.udise_district_code \r\n"
					+" and ms.udise_state_code = md.udise_state_code  "
					+ "and md.inityear = '"+year+"'\r\n"
					
					
//					+ "order by  md.udise_district_code ,md.district_name, pdq.sortid";
			  + "order by ms.state_name, md.district_name, pdq.sortid";
		System.out.println("sql---->"+sql);
					qrObj = nativeRepository.executeQueries(sql);
		}
		else {
			
			
			
//			String sql1="select md.udise_district_code , md.district_name ,pdq.questionid , pdq.quesseries , pdq.questiondesc ,pdq.weight ,  ms.state_name ,  \r\n"
//					+ "pdq.subquestiondesc1 ,prdp.r1 ,\r\n"
//					+ "pdq.subquestiondesc2 ,prdp.r2 ,\r\n"
//					+ "pdq.data_source ,\r\n"
//					+ "prdp.cal_vlaue ,\r\n"
//					+ "prdp.cal_point \r\n"
//					+ "from pgi_re_dist_performance prdp , pgi_district_question pdq , mst_district md ,  mst_state ms \r\n"
//					+ "where refrence_year = '"+data.getYear()+"'\r\n"
//					+ "and prdp.question_id = pdq.questionid \r\n"
//					+ "and prdp.udise_district_code = md.udise_district_code \r\n"
//					+ "and md.inityear = '"+year+"'\r\n"
//					+ "and prdp.dist_id ='"+data.getDistrictId()+"'\r\n"
//					+ "and prdp.state_id ='"+data.getStateId()+"'\r\n"
//					+" and ms.udise_state_code = md.udise_state_code  "
//					+ "order by  md.district_name, pdq.sortid";	
//			
//			System.out.println("sql1--->"+sql1);
			
			sql="select md.udise_district_code\r\n"
					+ ", md.district_name \r\n"
					+ ",pdq.questionid , \r\n"
					+ "pdq.quesseries , \r\n"
					+ "pdq.questiondesc \r\n"
					+ ",pdq.weight \r\n"
					+ ", ms.state_name , \r\n"
					+ "pdq.subquestiondesc1 ,prdp.r1 ,\r\n"
					+ "pdq.subquestiondesc2 ,prdp.r2 ,\r\n"
					+ "pdq.data_source ,\r\n"
					+ "prdp.cal_vlaue ,\r\n"
					+ "prdp.cal_point \r\n"
					+ "from pgi_re_dist_performance prdp join pgi_district_question pdq on (prdp.question_id = pdq.questionid )\r\n"
					+ "join  mst_district md on (prdp.dist_id = md.district_id )\r\n"
					+ "join  mst_state ms on (md.state_id  = ms.state_id )\r\n"
					+ "where prdp.refrence_year = '"+data.getYear()+"' and  md.inityear = '"+year+"'\r\n"
					+ "and ms.inityear =  '"+data.getYear()+"'"
							+ " and prdp.state_id ='"+data.getStateId()+"' "
                            + " and prdp.dist_id ='"+data.getDistrictId()+"' "
							+ "  order by ms.state_name , md.district_name, pdq.sortid";
			
		System.out.println("sql---->"+sql);
					qrObj = nativeRepository.executeQueries(sql);
					
//					qrObj = nativeRepository.executeQueries(sql1);
		}
		 
//		System.out.println("sql---->"+qrObj);
		
//				+ "order by  psq.domainid , psq.questionid ");
		sObj.setColumnName(qrObj.getColumnName());
		sObj.setRowValue(qrObj.getRowValue());
		sObj.setColumnDataType(qrObj.getColumnDataType());
		sObj.setStatus("1");
		return sObj;
		
		
		
	}
	
	@RequestMapping(value = "/getYearlyDistrictCount", method = RequestMethod.POST)
	public StaticReportBean getYearlyDistrictCount(@RequestBody String year) {
		System.out.println("called for year count--->"+year);
		StaticReportBean sObj=new StaticReportBean();
		QueryResult qrObj=null;
		String sql1="select count(*) from mst_district md where md.inityear='"+year+"'";	
				qrObj = nativeRepository.executeQueries(sql1);
	sObj.setColumnName(qrObj.getColumnName());
	sObj.setRowValue(qrObj.getRowValue());
	sObj.setColumnDataType(qrObj.getColumnDataType());
	sObj.setStatus("1");
	return sObj;
	}
	
	public String getSateName(String fileName) {
		String orgFileName="";
		if(fileName.equalsIgnoreCase("117")){
		orgFileName="Meghalaya";
		}else if(fileName.equalsIgnoreCase("106")){
		orgFileName="Haryana";
		}else if(fileName.equalsIgnoreCase("103")){
		orgFileName="Punjab";
		}else if(fileName.equalsIgnoreCase("102")){
		orgFileName="Himachal Pradesh";
		}else if(fileName.equalsIgnoreCase("199")){
		orgFileName="Test";
		}else if(fileName.equalsIgnoreCase("122")){
		orgFileName="Chhattisgarh";
		}else if(fileName.equalsIgnoreCase("120")){
		orgFileName="Jharkhand";
		}else if(fileName.equalsIgnoreCase("119")){
		orgFileName="West Bengal";
		}else if(fileName.equalsIgnoreCase("118")){
		orgFileName="Assam";
		}else if(fileName.equalsIgnoreCase("121")){
		orgFileName="Odisha";
		}else if(fileName.equalsIgnoreCase("124")){
		orgFileName="Gujarat";
		}else if(fileName.equalsIgnoreCase("123")){
		orgFileName="Madhya Pradesh";
		}else if(fileName.equalsIgnoreCase("110")){
		orgFileName="Bihar";
		}else if(fileName.equalsIgnoreCase("109")){
		orgFileName="Uttar Pradesh";
		}else if(fileName.equalsIgnoreCase("108")){
		orgFileName="Rajasthan";
		}else if(fileName.equalsIgnoreCase("105")){
		orgFileName="Uttarakhand";
		}else if(fileName.equalsIgnoreCase("133")){
		orgFileName="Tamil Nadu";
		}else if(fileName.equalsIgnoreCase("129")){
		orgFileName="Karnataka";
		}else if(fileName.equalsIgnoreCase("128")){
		orgFileName="Andhra Pradesh";
		}else if(fileName.equalsIgnoreCase("127")){
		orgFileName="Maharashtra";
		}else if(fileName.equalsIgnoreCase("111")){
		orgFileName="Sikkim";
		}else if(fileName.equalsIgnoreCase("112")){
		orgFileName="Arunachal Pradesh";
		}else if(fileName.equalsIgnoreCase("130")){
		orgFileName="Goa";
		}else if(fileName.equalsIgnoreCase("116")){
		orgFileName="Tripura";
		}else if(fileName.equalsIgnoreCase("115")){
		orgFileName="Mizoram";
		}else if(fileName.equalsIgnoreCase("114")){
		orgFileName="Manipur";
		}else if(fileName.equalsIgnoreCase("113")){
		orgFileName="Nagaland";
		}else if(fileName.equalsIgnoreCase("136")){
		orgFileName="Telangana";
		}else if(fileName.equalsIgnoreCase("132")){
		orgFileName="Kerala";
		}else if(fileName.equalsIgnoreCase("104")){
		orgFileName="Chandigarh";
		}else if(fileName.equalsIgnoreCase("131")){
		orgFileName="Lakshadweep";
		}else if(fileName.equalsIgnoreCase("101")){
		orgFileName="Jammu & Kashmir";
		}else if(fileName.equalsIgnoreCase("107")){
		orgFileName="Delhi";
		}else if(fileName.equalsIgnoreCase("125")){
		orgFileName="Daman & Diu";
		}else if(fileName.equalsIgnoreCase("126")){
		orgFileName="Dadra & Nagar Haveli";
		}else if(fileName.equalsIgnoreCase("135")){
		orgFileName="Andaman & Nicobar Islands";
		}else if(fileName.equalsIgnoreCase("134")){
		orgFileName="Puducherry";
		}else if(fileName.equalsIgnoreCase("137")){
		orgFileName="Ladhak";
		}
		return orgFileName;
	}
	
}
