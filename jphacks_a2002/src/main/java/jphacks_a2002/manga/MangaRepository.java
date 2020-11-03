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
			+ "WHERE m.frame_ID1 = f1.frame_ID AND m.frame_ID2 = f2.frame_ID AND  m.frame_ID3 = f3.frame_ID AND m.frame_ID4 = f4.frame_ID AND status = 4 ORDER BY";
	private static final String SELECT_SEARCH_MANGA = "SELECT * FROM manga_table as m ,frame_table as f1,frame_table as f2,frame_table as f3,frame_table as f4 "
			+ "WHERE m.frame_ID1 = f1.frame_ID AND m.frame_ID2 = f2.frame_ID AND  m.frame_ID3 = f3.frame_ID AND m.frame_ID4 = f4.frame_ID AND status = 4 AND ("
			+ "m.frame_ID1 IN(SELECT frame_ID FROM frame_table WHERE creater = ?) OR m.frame_ID2 IN(SELECT frame_ID FROM frame_table WHERE creater = ?) OR "
			+ "m.frame_ID3 IN(SELECT frame_ID FROM frame_table WHERE creater = ?) OR m.frame_ID4 IN(SELECT frame_ID FROM frame_table WHERE creater = ?))";

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
			mangaData.setManga_id((Integer) map.get("m.manga_id"));
			mangaData.setTheme_id((Integer)map.get("m.theme_id"));

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

	public MangaEntity searchTheme(String themeName) {

		List<Map<String, Object>> resultList = jdbc.queryForList(SELECT_SEARCH_MANGA);

		MangaEntity mangaEntity = mappingSelectResultEntity(resultList);
		return mangaEntity;
	}
}
