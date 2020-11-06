package jphacks_a2002.manga;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import jphacks_a2002.frame.FrameData;

@Repository
public class MangaRepository {

	@Autowired
	JdbcTemplate jdbc;


	private static final String SELECT_UNDONE_MANGA = "SELECT * FROM manga_table as m JOIN frame_table as f on(m.manga_id = f.manga_id) JOIN theme_table as t on(m.theme_id = t.theme_id) WHERE m.status < 4";

	private static final String SELECT_ONE_MANGA = "SELECT * FROM manga_table as m JOIN frame_table as f on(m.manga_id = f.manga_id) JOIN theme_table as t on(m.theme_id = t.theme_id) WHERE m.status = 4 AND f.manga_id = ? ORDER BY frame_no";
//	private static final String SELECT_SEARCH_MANGA = "SELECT * FROM manga_table as m ,frame_table as f1,frame_table as f2,frame_table as f3,frame_table as f4 "
//			+ "WHERE m.frame_ID1 = f1.frame_ID AND m.frame_ID2 = f2.frame_ID AND  m.frame_ID3 = f3.frame_ID AND m.frame_ID4 = f4.frame_ID AND status = 4 AND ("
//			+ "m.frame_ID1 IN(SELECT frame_ID FROM frame_table WHERE creater = ?) OR m.frame_ID2 IN(SELECT frame_ID FROM frame_table WHERE creater = ?) OR "
//			+ "m.frame_ID3 IN(SELECT frame_ID FROM frame_table WHERE creater = ?) OR m.frame_ID4 IN(SELECT frame_ID FROM frame_table WHERE creater = ?))";
	private static final String INSERT_MANGA = "INSERT INTO manga_table(manga_id,theme_id,status) VALUES((SELECT MAX(manga_id) + 1 FROM manga_table),?,1)";
	private static final String UPDATE_MANGA = "UPDATE manga_table SET status = status + 1 WHERE manga_id = ?";
	private static final String SELECT_STATUS = "SELECT * FROM manga_table WHERE manga_id = ?";
	private static final String GET_MANGA_DATA = "SELECT * FROM manga_table ORDER BY manga_id";
	private static final String SELECT_MANGA_DATA = "SELECT * FROM manga_table m JOIN frame_table f ON (m.manga_id = f.manga_id) WHERE f.frame_id = ?";



//	public MangaEntity selectAll() {
//
//		List<Map<String, Object>> resultList = jdbc.queryForList(SELECT_PERFECT_MANGA);
//
//		MangaEntity mangaEntity = mappingSelectResultEntity(resultList);
//
//		return mangaEntity;
//	}

	private MangaEntity mappingSelectResultEntity(List<Map<String, Object>> resultList)
			throws DataAccessException{

		MangaEntity entity = new MangaEntity();
		for (Map<String, Object> map : resultList) {
			MangaData mangaData = new MangaData();
			FrameData frameData = new FrameData();
			if(entity.getMangaList().size() > 0 ) {
				if(entity.getMangaList().get(entity.getMangaList().size() - 1).getMangaID() == (Integer)map.get("manga_id")){
					FrameData sameFrameData = new FrameData();
					sameFrameData.setFrameID((Integer)map.get("frame_ID"));
					sameFrameData.setCreater((String)map.get("creater"));
					sameFrameData.setPath((String)map.get("path"));
					sameFrameData.setCreateDate((Date)map.get("create_date"));
					sameFrameData.setMangaID((Integer)map.get("manga_id"));
					sameFrameData.setFrameNo((Integer)map.get("frame_no"));
					entity.getMangaList().get(entity.getMangaList().size() - 1).getFramelist().add(sameFrameData);
					continue;
				}
			}
			mangaData.setMangaID((Integer) map.get("manga_id"));
			mangaData.setThemeID((Integer)map.get("theme_id"));
			mangaData.setThemeName((String)map.get("theme_name"));
			mangaData.setStatus((Integer)map.get("status"));
			frameData.setFrameID((Integer)map.get("frame_ID"));
			frameData.setCreater((String)map.get("creater"));
			frameData.setPath((String)map.get("path"));
			frameData.setCreateDate((Date)map.get("create_date"));
			frameData.setMangaID((Integer)map.get("manga_id"));
			frameData.setFrameNo((Integer)map.get("frame_no"));
			mangaData.getFramelist().add(frameData);
			entity.getMangaList().add(mangaData);
		}
		return entity;
	}

//	public MangaEntity searchCreater(String createrName) {
//
//		List<Map<String, Object>> resultList = jdbc.queryForList(SELECT_SEARCH_MANGA,createrName,createrName,createrName,createrName);
//
//		MangaEntity mangaEntity = mappingSelectResultEntity(resultList);
//		return mangaEntity;
//	}

	public MangaData insertManga(int themeId) {
		jdbc.update(INSERT_MANGA,themeId);
		MangaEntity mangaEntity = mappingSelectResultEntity(jdbc.queryForList(GET_MANGA_DATA));
		return mangaEntity.getMangaList().get(mangaEntity.getMangaList().size());
	}

	public MangaEntity selectUndoneAll() {

		List<Map<String, Object>> resultList = jdbc.queryForList(SELECT_UNDONE_MANGA);

		MangaEntity mangaEntity = mappingSelectResultEntity(resultList);
		return mangaEntity;
	}



//	public MangaData getOneManga(int mangaID) {
//
//		List<Map<String, Object>> resultList = jdbc.queryForList(SELECT_UNDONE_MANGA,mangaID);
//		MangaData mangaData = mappingSelectResultData(resultList);
//
//		return mangaData;
//	}


	private MangaData mappingSelectResultData(List<Map<String, Object>> resultList)
			throws DataAccessException{


		Map<String, Object> map = resultList.get(0);
			MangaData mangaData = new MangaData();

			mangaData.setMangaID((Integer) map.get("manga_id"));
			mangaData.setThemeID((Integer)map.get("theme_id"));
			mangaData.setThemeName((String)map.get("theme_name"));
			for (Map<String, Object> result : resultList) {
			//これに置き換えたら超短くなってきもちンゴかもしんね
				FrameData frameData = new FrameData();
				frameData.setFrameID((Integer)result.get("frame_id"));
				frameData.setCreater((String)result.get("creater"));
				frameData.setPath((String)result.get("path"));
				frameData.setCreateDate((Date)result.get("create_date"));
				mangaData.getFramelist().add(frameData);
			}


			mangaData.setStatus((Integer)map.get("status"));


		return mangaData;
	}

	private MangaData mappingSelectResultMangaData(List<Map<String, Object>> resultList)
			throws DataAccessException{


		Map<String, Object> map = resultList.get(0);
			MangaData mangaData = new MangaData();

			mangaData.setMangaID((Integer)map.get("manga_id"));
			mangaData.setThemeID((Integer)map.get("theme_id"));
			mangaData.setThemeName((String)map.get("theme_name"));
			mangaData.setStatus((Integer)map.get("status"));
		return mangaData;
	}

	public int updateManga(int mangaID) {
		int rowNumber = jdbc.update(UPDATE_MANGA,mangaID);
		return rowNumber;
	}

	public MangaData getOneManga(int mangaID) {
		List<Map<String, Object>> resultList = jdbc.queryForList(SELECT_ONE_MANGA,mangaID);

		MangaData mangaData = mappingSelectResultData(resultList);
		return mangaData;
	}


	public MangaData getMangaData(int frameID) {
		List<Map<String, Object>> resultList = jdbc.queryForList(SELECT_ONE_MANGA,frameID);
		MangaData mangaData = mappingSelectResultMangaData(resultList);
		return mangaData;
	}

	public int getStatus(int mangaId) {
		List<Map<String, Object>> resultList = jdbc.queryForList(SELECT_STATUS,mangaId);

		MangaData mangaData = mappingSelectResultData(resultList);
		return mangaData.getStatus();
	}

//	public manga
}
