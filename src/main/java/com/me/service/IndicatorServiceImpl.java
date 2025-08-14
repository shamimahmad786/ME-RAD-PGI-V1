package com.me.service;

import java.net.URI;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.ListJoin;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.transaction.Transactional;
//import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;

import com.me.bean.QueryResult;
import com.me.bean.StateIndicatorScores;
import com.me.bean.StaticReportBean;
import com.me.dto.IndicatorRequestDto;
import com.me.dto.PgiResponse;
import com.me.model.DistrictMaster;
import com.me.model.DomainScore;
import com.me.model.IndicatorMaster;
import com.me.model.QueryModel;
import com.me.model.StateIndicatorScore;
import com.me.model.StateMaster;
import com.me.bean.NativeRepository;
import com.me.repository.IndicatorMasterRepo;
import com.me.repository.StateIndScoreRepo;
import com.fasterxml.jackson.core.JsonProcessingException;

@Service
public class IndicatorServiceImpl {

	@Autowired
	IndicatorMasterRepo indicatorMasterRepo;

	@Autowired
	StateIndScoreRepo stateIndScoreRepo;

	@Autowired
	StaticReportBean staticReportBean;
	
	@Autowired
	KieSession session;
	@Autowired
	NativeRepository nativeRepository;

	@PersistenceContext
	private EntityManager entityManager;

	@PersistenceUnit
	private EntityManagerFactory entityManagerFactory;

	public List<IndicatorMaster> getIndicators() {
		return indicatorMasterRepo.findAll();
	}

	private final String classPackage = "com.me.model.";

	public PgiResponse getStateScores(IndicatorRequestDto inRequest) {
		PgiResponse masterResponse = new PgiResponse();
		List<IndicatorMaster> indList = getIndicators();
		List<StateIndicatorScore> indScores = stateIndScoreRepo.findByStateIdAndYear(inRequest.getStateId(),
				inRequest.getYear());
		System.out.println(inRequest.getParam());
		if (StringUtils.pathEquals(inRequest.getParam(), "S")) {
			for (IndicatorMaster ind : indList) {
				boolean isInd = false;
				for (StateIndicatorScore score : indScores) {
					if (score.getIndId() == ind.getIndId()) {
						isInd = true;
						score.setIndData(ind);
						masterResponse.getScores().add(score);
					}
				}
				if (!isInd) {
					StateIndicatorScore sc = new StateIndicatorScore();
					sc.setIndData(ind);
					sc.setIndId((int) ind.getIndId());
					masterResponse.getScores().add(sc);
				}
			}
			List<StateIndicatorScore> indScoresList = masterResponse.getScores().stream()
					.sorted(Comparator.comparing(StateIndicatorScore::getIndId)).collect(Collectors.toList());

			masterResponse.setScores(indScoresList);
		}
		if (StringUtils.pathEquals(inRequest.getParam(), "D")) {
			Map<Integer, List<StateIndicatorScore>> indMap = new HashMap<Integer, List<StateIndicatorScore>>();
			Map<Integer, Map<Integer, Integer>> domMap = new HashMap<Integer, Map<Integer, Integer>>();
			for (StateIndicatorScore indScore : indScores) {
				if (indMap.containsKey(indScore.getStateId())) {
					indMap.get(indScore.getStateId()).add(indScore);
				} else {
					List<StateIndicatorScore> score = new ArrayList<StateIndicatorScore>();
					score.add(indScore);
					indMap.put(indScore.getStateId(), score);
				}
			}
			if (!(indMap.isEmpty())) {
				for (Map.Entry<Integer, List<StateIndicatorScore>> indEntry : indMap.entrySet()) {
					for (StateIndicatorScore indScore : indEntry.getValue()) {
						if (domMap.containsKey(indScore.getStateId())) {
							if (domMap.get(indScore.getStateId()).containsKey(indScore.getDomainId())) {
								int indValue = domMap.get(indScore.getStateId()).get(indScore.getDomainId());
								domMap.get(indScore.getStateId()).put(indScore.getDomainId(),
										(indValue + indScore.getScore()));
							} else {
								domMap.get(indScore.getStateId()).put(indScore.getDomainId(), (indScore.getScore()));
							}
						} else {
							Map<Integer, Integer> domScore = new HashMap<Integer, Integer>();
							domScore.put(indScore.getDomainId(), indScore.getScore());
							domMap.put(indScore.getStateId(), domScore);
						}
					}
				}
			}
			if (!(domMap.isEmpty())) {
				List<StateIndicatorScore> domIndScores = new ArrayList<StateIndicatorScore>();
				for (Map.Entry<Integer, Map<Integer, Integer>> domEntry : domMap.entrySet()) {
					StateIndicatorScore score = new StateIndicatorScore();
					score.setStateId(domEntry.getKey());
					score.setYear(inRequest.getYear());
					DomainScore domainScore = new DomainScore();
					domainScore.setDomain1(domEntry.getValue().get(1));
					domainScore.setDomain2(domEntry.getValue().get(2));
					domainScore.setDomain3(domEntry.getValue().get(3));
					domainScore.setDomain4(domEntry.getValue().get(4));
					domainScore.setDomain5(domEntry.getValue().get(5));
					int totalScore = domainScore.getDomain1() + domainScore.getDomain2() + domainScore.getDomain3()
							+ domainScore.getDomain4() + domainScore.getDomain5();
					domainScore.setTotalScore(totalScore);
					session.insert(domainScore);
					session.fireAllRules();
//					if (totalScore >= 801 && totalScore <= 850) {
//						domScore.setGrade("1");
//					} else if (totalScore >= 751 && totalScore <= 800) {
//						domScore.setGrade("2");
//					} else if (totalScore >= 701 && totalScore <= 750) {
//						domScore.setGrade("3");
//					} else if (totalScore >= 651 && totalScore <= 700) {
//						domScore.setGrade("4");
//					} else if (totalScore >= 601 && totalScore <= 650) {
//						domScore.setGrade("5");
//					} else if (totalScore >= 551 && totalScore <= 600) {
//						domScore.setGrade("6");
//					} else if (totalScore >= 1 && totalScore <= 550) {
//						domScore.setGrade("7");
//					}
					score.setDomainScore(domainScore);
					domIndScores.add(score);
				}
				masterResponse.setScores(domIndScores);
				masterResponse.setStatusCode("NPA001");
			}
		}

		return masterResponse;
	}

	public List<IndicatorMaster> findByDomainId(int domainId) {
		List<IndicatorMaster> indMaster = indicatorMasterRepo.findByDomainIdOrderByIndIdAsc(domainId).stream()
				.sorted(Comparator.comparing(IndicatorMaster::getIndId)).collect(Collectors.toList());
		return indMaster;
	}

	public StateIndicatorScore getIndValueById(IndicatorRequestDto inRequest) {
		return stateIndScoreRepo.findByStateIdAndYearAndIndId(inRequest.getStateId(), inRequest.getYear(),
				inRequest.getIndId());
	}

	@Transactional
	public Boolean saveStateIndScore(StateIndicatorScore score) throws Exception {

//		List<User> userList = userRepository.findByEmail(registirationRequest.getEmail());
//
//		if (userList.size() > 0) {
//			throw new Exception("User exist with this : " + registirationRequest.getEmail());
//		}
//
//		if (userRepository.getByUsername(registirationRequest.getUsername()).size() > 0) {
//			throw new Exception("User exist with this name called : " + registirationRequest.getUsername());
//		}
		score.setCreatedTime(Calendar.getInstance().getTime());
		score.setModifiedTime(Calendar.getInstance().getTime());
		stateIndScoreRepo.save(score);

		return true;

	}

	public <T> List<T> getData(List<QueryModel> queryModel) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery query = null;
		try {
			query = cb.createQuery(Class.forName(classPackage + queryModel.get(0).getClassName()));
			Root root = query.from(Class.forName(classPackage + queryModel.get(0).getClassName()));
			List<Predicate> predicates = new ArrayList<>();
			queryModel.forEach((field) -> {
				System.out.println(field.getFilterName());
				System.out.println(field.getFieldName());
				switch (field.getFilterName()) {
				case "greater":
					predicates.add(
							cb.greaterThan(root.get(field.getFieldName()), Double.parseDouble(field.getFieldValue())));
					break;
				case "less":
					predicates.add(
							cb.lessThan(root.get(field.getFieldName()), Double.parseDouble(field.getFieldValue())));
					break;
				case "equal":
					predicates.add(cb.equal(root.get(field.getFieldName()), field.getFieldValue()));
					break;
				case "notEqual":
					predicates.add(cb.notEqual(root.get(field.getFieldName()), field.getFieldValue()));
					break;
				case "like":
					predicates.add(cb.like(root.get(field.getFieldName()), "%" + field.getFieldValue() + "%"));
					break;

				}
			});
			query.select(root).where(predicates.toArray(new Predicate[predicates.size()]));

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(entityManager.createQuery(query).getResultList().size());
		return entityManager.createQuery(query).getResultList();
	}

	public <T> List<T> getJoinData(List<QueryModel> queryModel) {

		EntityManager em = entityManagerFactory.createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery query = null;
		try {
			query = cb.createQuery(Class.forName(classPackage + queryModel.get(0).getClassName()));
			Root employee = query.from(Class.forName(classPackage + queryModel.get(0).getClassName()));
			List<Predicate> predicates = new ArrayList<>();
			EntityType Master_ = employee.getModel();
//			employee.join(StateMaster_.getList("districts"));
			ListJoin tasks = employee.join(Master_.getList(queryModel.get(0).getJoinDetails().getJoinFieldName()));
//			tasks.on(cb.equal(tasks.get("districtId"), 1001));
			predicates.add(cb.equal(tasks.get(queryModel.get(1).getFieldName()), queryModel.get(1).getFieldValue()));
					
			query.select(employee).where(predicates.toArray(new Predicate[predicates.size()])).distinct(true);
//			query.select(employee).where(cb.equal(employee.get("stateId"), 135));
//			query.select(employee).distinct(true);
			TypedQuery typedQuery = em.createQuery(query);
			typedQuery.getResultList().forEach(System.out::println);
			return typedQuery.getResultList();
		} catch (Exception e) {

		}
		
		return null;

		/*
		 * CriteriaBuilder cb = entityManager.getCriteriaBuilder(); CriteriaQuery<Tuple>
		 * query = cb.createTupleQuery(); Root<StateMaster> root =
		 * query.from(StateMaster.class);
		 * 
		 * Join<StateMaster, DistrictMaster> join = root.join("districts",
		 * JoinType.LEFT); query.multiselect(root.get("id").alias("id"),
		 * root.get("stateName").alias("stateName"), join.get("districtId"),
		 * join.get("districtName").alias("districtName")); List<Tuple> x =
		 * entityManager.createQuery(query).getResultList(); //
		 * x.forEach(System.out::println); for (Tuple tuple : x) {
		 * System.out.printf("State: %s, district: %s%n", tuple.get("stateName",
		 * String.class), tuple.get("districtName", String.class)); }
		 */

		/*
		 * CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		 * CriteriaQuery<StateMaster> criteriaQuery =
		 * criteriaBuilder.createQuery(StateMaster.class);
		 */

//	        Root<StateMaster> state = criteriaQuery.from(StateMaster.class);
//	        EntityType<StateMaster> StateMaster_ = state.getModel();
////	        state.join(StateMaster_.getList("districts"));
//	        ListJoin<StateMaster, DistrictMaster> tasks = (ListJoin<StateMaster, DistrictMaster>) state.join(StateMaster_.getList("districts"),JoinType.LEFT);
////	        criteriaQuery.select(state)
////	           .where(criteriaBuilder.equal(tasks.get("stateId"),state.get("id")));
////	        tasks.on(criteriaBuilder.equal(tasks.get("stateId"), 135));
//	        criteriaQuery.select(criteriaBuilder.tuple(state.get("id").alias("stateId"),
//	                tasks.get("stateId").alias("disStateId")));
//	        TypedQuery<Tuple> typedQuery = entityManager.createQuery(criteriaQuery);
//	        System.out.println(typedQuery.getHints());
//	        for (Tuple tuple : typedQuery.getResultList()) {
//	            System.out.printf("State: %s, district: %s%n",
//	                    tuple.get("stateId", Long.class),
//	                    tuple.get("disStateId", Integer.class));
//	        }

		/*
		 * Root<StateMaster> state = criteriaQuery.from(StateMaster.class);
		 * EntityType<StateMaster> StateMaster_ = state.getModel();
		 * ListJoin<StateMaster, DistrictMaster> tasks = (ListJoin<StateMaster,
		 * DistrictMaster>) state.join(StateMaster_.getList("districts"),
		 * JoinType.LEFT);
		 * criteriaQuery.select(state).where(criteriaBuilder.equal(state.get("id"),
		 * tasks.get("stateId"))); TypedQuery<StateMaster> typedQuery =
		 * entityManager.createQuery(criteriaQuery);
		 * System.out.println(typedQuery.getHints()); List<StateMaster> resultList =
		 * typedQuery.getResultList(); resultList.forEach(prop -> {
		 * System.out.println(prop.getId()); System.out.println(prop.getStateName());
		 * });
		 */

	}

	public Specification<StateMaster> barName(String name) {
		return (root, query, builder) -> {
			Root<StateMaster> foo = query.distinct(true).from(StateMaster.class);
			Join<StateMaster, DistrictMaster> bar = foo.join("bars", JoinType.LEFT);

			Predicate pred = builder.equal(bar.get("name"), name);

			return pred;
		};
	}
	
	
	public Map<String,List<StateIndicatorScores>> getAllIndicator(@RequestBody String Year)  {
		Map<String,List<StateIndicatorScores>> mp=new HashMap<String,List<StateIndicatorScores>>();
		System.out.println("called");
//		String sql="select mst_state.state_name,mst_state.path, mst_state.banner, sm.*  from\n" + 
//				"(\n" + 
//				"\n" + 
//				"SELECT\n" + 
//				" state_id,\n" + 
//				" SUM(score) FILTER (WHERE domainid=1) AS \"Domain1\",\n" + 
//				" SUM(score) FILTER (WHERE domainid=2) AS \"Domain2\",\n" + 
//				" SUM(score) FILTER (WHERE domainid=3) AS \"Domain3\",\n" + 
//				" SUM(score) FILTER (WHERE domainid=4) AS \"Domain4\",\n" + 
//				" SUM(score) FILTER (WHERE domainid=5) AS \"Domain5\"\n" + 
//				"from indicator_score i ,  indicator_master im , domain_master d\n" + 
//				"where i.year= '"+Year+"' \n" + 
//				"and im.domain_id = d.domainid\n" + 
//				"and im.ind_id= i.ind_id\n" + 
//				"GROUP BY state_id\n" + 
//				"   ) sm , mst_state\n" + 
//				"   where sm.state_id= mst_state.state_id";
		
		String sql=" select mst_state.state_name,mst_state.path, mst_state.banner, sm.*  from\n" + 
				"(\n" + 
				"   select\n" + 
				"    state_id,\n" + 
				" SUM(weight_val) FILTER (WHERE dom_id=1) AS \"Domain1\",\n" + 
				" SUM(weight_val) FILTER (WHERE dom_id=2) AS \"Domain2\",\n" + 
				" SUM(weight_val) FILTER (WHERE dom_id=3) AS \"Domain3\",\n" + 
				" SUM(weight_val) FILTER (WHERE dom_id=4) AS \"Domain4\",\n" + 
				" SUM(weight_val) FILTER (WHERE dom_id=5) AS \"Domain5\"\n" + 
				"   from  pgi_all_values_state\n  where year='"+Year+"' " + 
				"   GROUP BY state_id \n" + 
				"   ) sm , mst_state\n" + 
				"   where sm.state_id= mst_state.state_id ";
		
//		String sql="select mst_state.state_name,mst_state.path, mst_state.banner, sm.*  from\n" + 
//				"(\n" + 
//				"   select\n" + 
//				"    state_id,\n" + 
//				" SUM(weight_val) FILTER (WHERE dom_id=1) AS \"Domain1\",\n" + 
//				" SUM(weight_val) FILTER (WHERE dom_id=2) AS \"Domain2\",\n" + 
//				" SUM(weight_val) FILTER (WHERE dom_id=3) AS \"Domain3\",\n" + 
//				" SUM(weight_val) FILTER (WHERE dom_id=4) AS \"Domain4\",\n" + 
//				" SUM(weight_val) FILTER (WHERE dom_id=5) AS \"Domain5\"\n" + 
//				"   from  pgi_all_values_state\n where year='" + Year +"'" +
//				"   GROUP BY state_id\n" + 
//				"   ) sm , mst_state\n" + 
//				"   where sm.state_id= mst_state.state_id";
		
		QueryResult qrObj = nativeRepository.executeQueries(sql);
		staticReportBean.setColumnName(qrObj.getColumnName());
		staticReportBean.setRowValue(qrObj.getRowValue());
		System.out.println("All indicator sql---->"+sql);
//		System.out.println("datasss-->"+qrObj.getRowValue());
		List<StateIndicatorScores> lst=new ArrayList<StateIndicatorScores>();
		try {
		for(Map<String, Object> map: qrObj.getRowValue()) {
//			System.out.println("map value--->"+map.get("state_name"));
			DomainScore ds=new DomainScore();
			StateIndicatorScores si=new StateIndicatorScores();
			ds.setDomain1(Integer.valueOf(String.valueOf((map.get("Domain1")))));
			ds.setDomain2(Integer.valueOf(String.valueOf((map.get("Domain2")))));
			ds.setDomain3(Integer.valueOf(String.valueOf((map.get("Domain3")))));
			ds.setDomain4(Integer.valueOf(String.valueOf((map.get("Domain4")))));
			ds.setDomain5(Integer.valueOf(String.valueOf((map.get("Domain5")))));
			ds.setTotalScore(Integer.valueOf(String.valueOf((map.get("Domain1")))) + Integer.valueOf(String.valueOf((map.get("Domain2"))))+Integer.valueOf(String.valueOf((map.get("Domain3"))))+Integer.valueOf(String.valueOf((map.get("Domain4"))))+ Integer.valueOf(String.valueOf((map.get("Domain5")))));
			ds.setGrade(gradValue(Integer.valueOf(String.valueOf((map.get("Domain1")))) + Integer.valueOf(String.valueOf((map.get("Domain2"))))+Integer.valueOf(String.valueOf((map.get("Domain3"))))+Integer.valueOf(String.valueOf((map.get("Domain4"))))+ Integer.valueOf(String.valueOf((map.get("Domain5")))),Year));
			si.setDomainScore(ds);
			si.setBanner((String)map.get("banner"));
			si.setPath((String)map.get("path"));
			si.setState_id((int)map.get("state_id"));
			si.setState_name((String)map.get("state_name"));
			lst.add(si);
		}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		mp.put("scores", lst);
		System.out.println("mp--->"+mp);
		return mp;
	}
	
	public String gradValue(int totalScore, String year) {
		String grad="";
//		if(year.equalsIgnoreCase("2017")){
//			if(totalScore>=551 && totalScore<=600) {
//				grad="6";
//			}else if(totalScore>=601 && totalScore<=650) {
//				grad="5";
//			}else if(totalScore>=651 && totalScore<=700) {
//				grad="4";
//			}else if(totalScore>=701 && totalScore<=750) {
//				grad="3";
//			}else if(totalScore>=751 && totalScore<=800) {
//				grad="2";
//			}else if(totalScore>=801 && totalScore<=850) {
//				grad="1";
//			}
//		}else {
//		if(totalScore>=551 && totalScore<=600) {
//			grad="7";
//		}else if(totalScore>=601 && totalScore<=650) {
//			grad="6";
//		}else if(totalScore>=651 && totalScore<=700) {
//			grad="5";
//		}else if(totalScore>=701 && totalScore<=750) {
//			grad="4";
//		}else if(totalScore>=751 && totalScore<=800) {
//			grad="3";
//		}else if(totalScore>=801 && totalScore<=850) {
//			grad="2";
//		}else if(totalScore>=851) {
//			grad="1";
//		}
//		}
		
		
		
//		
//		if(totalScore>=551 && totalScore<=600) {
//			grad="7";
//		}else if(totalScore>=601 && totalScore<=650) {
//			grad="6";
//		}else if(totalScore>=651 && totalScore<=700) {
//			grad="5";
//		}else if(totalScore>=701 && totalScore<=750) {
//			grad="4";
//		}else if(totalScore>=751 && totalScore<=800) {
//			grad="3";
//		}else if(totalScore>=801 && totalScore<=850) {
//			grad="2";
//		}else if(totalScore>=851) {
//			grad="1";
//		}
		
		
		if(totalScore>=0 && totalScore<=550) {
			grad="7";
		}else if(totalScore>=551 && totalScore<=600) {
			grad="6";
		}else if(totalScore>=601 && totalScore<=650) {
			grad="5";
		}else if(totalScore>=651 && totalScore<=700) {
			grad="4";
		}else if(totalScore>=701 && totalScore<=750) {
			grad="3";
		}else if(totalScore>=751 && totalScore<=800) {
			grad="2";
		}else if(totalScore>=801 && totalScore<=850) {
			grad="1";
		}else if(totalScore>=851 && totalScore<=900) {
			grad="L3";
		}else if(totalScore>=901 && totalScore<=950) {
			grad="L2";
		}else if(totalScore>=951 && totalScore<=1000) {
			grad="L1";
		}
		
		
		return grad;
	}

}
