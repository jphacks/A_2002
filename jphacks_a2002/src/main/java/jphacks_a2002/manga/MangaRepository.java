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

	private static final String SELECT_PERFECT_MANGA = "SELECT * FROM manga_table as m ,frame_table as f1,frame_table as f2,frame_table as f3,frame_table as f4 "
			+ "WHERE m.frame_ID1 = f1.frame_ID AND m.frame_ID2 = f2.frame_ID AND  m.frame_ID3 = f3.frame_ID AND m.frame_ID4 = f4.frame_ID AND status = 4";
	private static final String SELECT_UNDONE_MANGA = "SELECT * FROM manga_table as m ,frame_table as f1,frame_table as f2,frame_table as f3,frame_table as f4 "
			+ "WHERE m.frame_ID1 = f1.frame_ID AND m.frame_ID2 = f2.frame_ID AND  m.frame_ID3 = f3.frame_ID AND m.frame_ID4 = f4.frame_ID AND status < 4";
	private static final String SELECT_SEARCH_MANGA = "SELECT * FROM manga_table as m ,frame_table as f1,frame_table as f2,frame_table as f3,frame_table as f4 "
			+ "WHERE m.frame_ID1 = f1.frame_ID AND m.frame_ID2 = f2.frame_ID AND  m.frame_ID3 = f3.frame_ID AND m.frame_ID4 = f4.frame_ID AND status = 4 AND ("
			+ "m.frame_ID1 IN(SELECT frame_ID FROM frame_table WHERE creater = ?) OR m.frame_ID2 IN(SELECT frame_ID FROM frame_table WHERE creater = ?) OR "
			+ "m.frame_ID3 IN(SELECT frame_ID FROM frame_table WHERE creater = ?) OR m.frame_ID4 IN(SELECT frame_ID FROM frame_table WHERE creater = ?))";
	private static final String INSERT_MANGA = "INSERT INTO manga_table(manga_id,theme_id,frame_id1,status) VALUES((SELECT MAX(manga_id) + 1 FROM manga_table),?,?,1)";


	public MangaEntity selectAll() {

		List<Map<String, Object>> resultList = jdbc.queryForList(SELECT_PERFECT_MANGA);

		MangaEntity mangaEntity = mappingSelectResultEntity(resultList);

		return mangaEntity;
	}

	private MangaEntity mappingSelectResultEntity(List<Map<String, Object>> resultList)
			throws DataAccessException{

		MangaEntity entity = new MangaEntity();

		for (Map<String, Object> map : resultList) {
			MangaData mangaData = new MangaData();
			FrameData frameData = new FrameData();
			mangaData.setMangaID((Integer) map.get("m.manga_id"));
			mangaData.setThemeID((Integer)map.get("m.theme_id"));

			//これに置き換えたら超短くなってきもちンゴかもしんね
			/*String prstmtStrList[] = {"m.frame_ID%d","f%d.creater","f%d.path","f%d.create_date"};
			for( int i = 0; i < 4;i++ ) {
				frameData.setFrameID((Integer)map.get(String.format(prstmtStrList[0],i)));
				frameData.setCreater((String)map.get(String.format(prstmtStrList[0],i)));
				frameData.setPath((String)map.get(String.format(prstmtStrList[0],i)));
				frameData.setCreateDate((Date)map.get(String.format(prstmtStrList[0],i)));
				mangaData.getFramelist().add(frameData);
			}*/

			frameData.setFrameID((Integer)map.get("m.frame_ID1"));
			frameData.setCreater((String)map.get("f1.creater"));
			frameData.setPath((String)map.get("f1.path"));
			frameData.setCreateDate((Date)map.get("f1.create_date"));
			mangaData.getFramelist().add(frameData);

			frameData.setFrameID((Integer)map.get("m.frame_ID2"));
			frameData.setCreater((String)map.get("f2.creater"));
			frameData.setPath((String)map.get("f2.path"));
			frameData.setCreateDate((Date)map.get("f2.create_date"));
			mangaData.getFramelist().add(frameData);

			frameData.setFrameID((Integer)map.get("m.frame_ID3"));
			frameData.setCreater((String)map.get("f3.creater"));
			frameData.setPath((String)map.get("f3.path"));
			frameData.setCreateDate((Date)map.get("f3.create_date"));
			mangaData.getFramelist().add(frameData);

			frameData.setFrameID((Integer)map.get("m.frame_ID4"));
			frameData.setCreater((String)map.get("f4.creater"));
			frameData.setPath((String)map.get("f4.path"));
			frameData.setCreateDate((Date)map.get("f4.create_date"));
			mangaData.getFramelist().add(frameData);

			mangaData.setStatus((Integer)map.get("m.status"));

			entity.getMangaList().add(mangaData);

		}
		return entity;
	}

	public MangaEntity searchTheme(String createrName) {

		List<Map<String, Object>> resultList = jdbc.queryForList(SELECT_SEARCH_MANGA,createrName,createrName,createrName,createrName);

		MangaEntity mangaEntity = mappingSelectResultEntity(resultList);
		return mangaEntity;
	}

	public int insertManga(int themeId,int frameID) {
		int rowNumber = jdbc.update(INSERT_MANGA,themeId,frameID);
		return rowNumber;

	}

	public MangaEntity selectUndoneAll() {
		
		List<Map<String, Object>> resultList = jdbc.queryForList(SELECT_UNDONE_MANGA);

		MangaEntity mangaEntity = mappingSelectResultEntity(resultList);
		return mangaEntity;
	}
	
	public MangaEntity updateManga(MangaData data ,int frameID) {
		
		List<Map<String, Object>> resultList = jdbc.queryForList(SELECT_UNDONE_MANGA);

		MangaEntity mangaEntity = mappingSelectResultEntity(resultList);
		return mangaEntity;
	}

	public MangaData getOneManga(int mangaID) {
		
		List<Map<String, Object>> resultList = jdbc.queryForList(SELECT_UNDONE_MANGA,mangaID);
		MangaData mangaData = mappingSelectResultData(resultList);
		
		return mangaData;
	}
	
	private MangaData mappingSelectResultData(List<Map<String, Object>> resultList)
			throws DataAccessException{

		

		Map<String, Object> map = resultList.get(0);
		MangaData mangaData = new MangaData();
		FrameData frameData = new FrameData();
		mangaData.setMangaID((Integer) map.get("m.manga_id"));
		mangaData.setThemeID((Integer)map.get("m.theme_id"));

		//これに置き換えたら超短くなってきもちンゴかもしんね
		/*String prstmtStrList[] = {"m.frame_ID%d","f%d.creater","f%d.path","f%d.create_date"};
		for( int i = 0; i < 4;i++ ) {
			frameData.setFrameID((Integer)map.get(String.format(prstmtStrList[0],i)));
			frameData.setCreater((String)map.get(String.format(prstmtStrList[0],i)));
			frameData.setPath((String)map.get(String.format(prstmtStrList[0],i)));
			frameData.setCreateDate((Date)map.get(String.format(prstmtStrList[0],i)));
			mangaData.getFramelist().add(frameData);
		}*/

		frameData.setFrameID((Integer)map.get("m.frame_ID1"));
		frameData.setCreater((String)map.get("f1.creater"));
		frameData.setPath((String)map.get("f1.path"));
		frameData.setCreateDate((Date)map.get("f1.create_date"));
		mangaData.getFramelist().add(frameData);

		frameData.setFrameID((Integer)map.get("m.frame_ID2"));
		frameData.setCreater((String)map.get("f2.creater"));
		frameData.setPath((String)map.get("f2.path"));
		frameData.setCreateDate((Date)map.get("f2.create_date"));
		mangaData.getFramelist().add(frameData);

		frameData.setFrameID((Integer)map.get("m.frame_ID3"));
		frameData.setCreater((String)map.get("f3.creater"));
		frameData.setPath((String)map.get("f3.path"));
		frameData.setCreateDate((Date)map.get("f3.create_date"));
		mangaData.getFramelist().add(frameData);

		frameData.setFrameID((Integer)map.get("m.frame_ID4"));
		frameData.setCreater((String)map.get("f4.creater"));
		frameData.setPath((String)map.get("f4.path"));
		frameData.setCreateDate((Date)map.get("f4.create_date"));
		mangaData.getFramelist().add(frameData);

		mangaData.setStatus((Integer)map.get("m.status"));

		
		return mangaData;
	}
}
