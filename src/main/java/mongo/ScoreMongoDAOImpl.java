package mongo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
public class ScoreMongoDAOImpl implements ScoreMongoDAO {
	// spring-data-MongoDB 에서 제공하는 lib
	MongoTemplate mongoTemplate;
	// 페이징 처리와 소트를 쉽게 구현하기 위해서 제공하는 PagingAndSortingRepository 를 상속 - CRUD(CLRUD) 를 위한 메소드를 제공
//	   → spring-data-commons lib 에서 제공
	ScoreRepository scoreRepository; // SimpleMongoRepository 이용해서 작업
	
	public ScoreMongoDAOImpl() {
		super();
	}

	@Autowired
	public ScoreMongoDAOImpl(MongoTemplate mongoTemplate, ScoreRepository scoreRepository) {
		super();
		this.mongoTemplate = mongoTemplate;
		this.scoreRepository = scoreRepository;
	}

	@Override
	public List<ScoreDTO> findCriteria(String key, String value) {
		// 1. 선택한 Key 에 따른 Value 가 정확하게 일치하는 Document 을 검색
//		Criteria criteria = new Criteria(key);
//		criteria.is(value);
//		Query query = new Query(criteria);
		
		// 2. Key와 조건을 Value 함께 적용 하기
//		String[] data = key.split(",");
//		Criteria criteria = new Criteria(data[0]);
//		if(data[1].equals("is")) {
//			criteria.is(value);
//		} else if (data[1].equals("gt")) {
//			criteria.gt(Integer.parseInt(value));
//		}
//		Query query = new Query(criteria);
		
		// 3. where 메소드를 이용해서 작업하기
//		String[] data = key.split(",");
//		Query query = new Query();
//		query.addCriteria(Criteria.where(data[0]).is(value));
		
		// 4. 정규표현식으로 검색
		String[] data = key.split(",");
		Criteria criteria = new Criteria(data[0]);
//		criteria.regex("^" + value);
//		^ → 해당 필드가 ^ 다음의 문자열로 시작하는 데이터 → Like 연산자와 동일 where dept like '인사%'
		criteria.regex(".*" + value + ".*");
//		dept like '%사%'
		Query query = new Query(criteria);
		
		// 1-find 의 조건 정보가 담긴 객체, 2-document 가 매핑 될 객체, 3-컬렉션명
		List<ScoreDTO> docs = mongoTemplate.find(query, ScoreDTO.class, "score");
		System.out.println("ScoreMongoDAO : " + docs);
		return docs;
	}

	@Override
	public ScoreDTO findById(String key, String value) {
		// Criteria 객체는 Spring data MongoDB 에서 조건을 모델링 한 객체
//		어떤 필드에서 어떤 조건을 적용할 것 인지 정의
//		필드명과 필드의 조건을 정의하면 내부에서 json 의 쿼리 조건을 만들어 처리
		
		// 1. 조건을 객체로 정의
		Criteria criteria = new Criteria(key);
		// 조건에 대한 설정
		criteria.is(value);
		
		// 2. Criteria 객체를 이용해서 Query 를 생성
		Query query = new Query(criteria);
		ScoreDTO doc = mongoTemplate.findOne(query, ScoreDTO.class, "score");
		
		return doc;
	}

	@Override
	public void insertDocument(ScoreDTO doc) {
		mongoTemplate.insert(doc);
	}

	@Override
	public void insertAllDocument(List<ScoreDTO> docs) {
		mongoTemplate.insertAll(docs);
	}

	@Override
	public void update(ScoreDTO document) {
		// 조건 - 조건에 만족하는 document 를 수정
		Criteria criteria = new Criteria("id");
		criteria.is(document.getId());
		Query query = new Query(criteria);
		
		// 업데이트 기능을 수행하는 객체를 생성하고 적절한 값을 세팅
		Update update = new Update();
		update.set("addr", document.getAddr());
		update.set("java", document.getJava());
		mongoTemplate.updateMulti(query, update, "score");
	}

	@Override
	public void test1() {
	}

	@Override
	public List<ScoreDTO> findAll() {
		return mongoTemplate.findAll(ScoreDTO.class, "score");
	}

	@Override
	public List<ScoreDTO> findAll(int pageNo) {
		// PagingAndSortingRepository 의 findAll 메소드를 호출하면 페이징 처리 된 객체를 전달 받을 수 있다.
		// findAll 메소드 내부에서 페이징 처리를 할 수 있도록 PageRequest 객체를 이용해서 실행할 페이지 번호와 한 페이지를 구성할 다큐먼트를 매개변수로 정의한다.
		
		// Page 객체는 한 페이지에 보여질 document 들의 정보를 갖고 있는 객체
		Page<ScoreDTO> page = scoreRepository.findAll(new PageRequest(pageNo, 5));
		
		// 한 페이지에 출력할 document 꺼내기
		return page.getContent();
	}

}
