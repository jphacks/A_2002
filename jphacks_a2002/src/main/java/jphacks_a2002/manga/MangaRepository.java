package jphacks_a2002.manga;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MangaRepository {

	@Autowired
	JdbcTemplate jdbc;

	private static final String SELECT_PERFECT_MANGA = "SELECT * FORM mangatable WHERE flag = 4";
	
	public MangaEntity selectAll() {
		
		List<Map<String, Object>> resultList = jdbc.queryForList(SELECT_PERFECT_MANGA);
		
		MangaEntity mangaEntity = mappingSelectResultEntity(resultList);
				
		return mangaEntity;
	}
	
	private MangaEntity mappingSelectResultEntity(List<Map<String, Object>> resultList)
			throws DataAccessException{

		MangaEntity entity = new MangaEntity();

		for (Map<String, Object> map : resultList) {
			MangaData data = new MangaData();
			data.setJob_search_id((Integer) map.get("job_search_id"));
			data.setStclass((String)map.get("stclass"));
			data.setStno((String)map.get("stno"));
			data.setStname((String)map.get("stname"));
			data.setDate_to((Date) map.get("date_to"));
			data.setDate_do((Date) map.get("date_do"));
			data.setLocation((String)map.get("location"));
			data.setDetail((String)map.get("detail"));
			data.setCompany_name((String)map.get("company_name"));
			data.setForward((boolean) map.get("forward"));
			data.setCategory((String)map.get("category"));
			data.setAbsence_date_to((Date) map.get("absence_date_to"));
			data.setAbsence_date_do((Date) map.get("absence_date_do"));
			data.setLeave_early_date((Date) map.get("leave_early_date"));
			data.setTardy_date((Date) map.get("tardy_date"));
			data.setSchedule((String)map.get("schedule"));
			data.setMessage((String)map.get("message"));
			data.setContents_report((String)map.get("contents_report"));

			entity.getRequestlist().add(data);

		}
		return entity;
	}
}
