package jphacks_a2002.frame;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import jphacks_a2002.PreviewData;
import jphacks_a2002.PreviewEntity;

@Repository
public class FrameRepository {

	@Autowired
	JdbcTemplate jdbc;

	private static final String SELECT_PREVIEW_MANGA = "SELECT f.manga_id,path FROM frame_table as f,manga_table as m WHERE m.manga_id = f.manga_id AND f.frame_no = 1 AND m.status = 4";
	private static final String SELECT_SEARCH_PREVIEW_MANGA = "SELECT f.manga_id,path FROM frame_table as f,manga_table as m WHERE m.manga_id = f.manga_id AND f.frame_no = 1 AND m.status = 4 AND"
															+ " manga_id IN(SELECT manga_id FROM frame_table WHERE creater = ?)";
	private static final String SQL_INSERT_FRAME_ONE = "INSERT INTO frame_table (frame_id,creater,path,create_date,manga_id,frame_no) VALUES ((SELECT MAX(frame_id) + 1 FROM frame_table),?,?,?,? + 1,?)";
	private static final String SQL_INSERT_FRAME_ONE_JOIN = "INSERT INTO frame_table (frame_id,creater,path,create_date,manga_id,frame_no) VALUES ((SELECT MAX(frame_id) + 1 FROM frame_table),?,?,?,?,?)";
	private static final String SQL_GET_LAST_IMGID = "SELECT MAX(frame_id) + 1 as imgid FROM frame_table";
	public PreviewEntity selectAll() {

		List<Map<String, Object>> resultList = jdbc.queryForList(SELECT_PREVIEW_MANGA);

		PreviewEntity previewEntity = mappingSelectResultEntity(resultList);

		return previewEntity;
	}

	public PreviewEntity searchCreater(String createrName) {

		List<Map<String, Object>> resultList = jdbc.queryForList(SELECT_SEARCH_PREVIEW_MANGA,createrName);

		PreviewEntity previewEntity = mappingSelectResultEntity(resultList);
		return previewEntity;
	}

	private PreviewEntity mappingSelectResultEntity(List<Map<String, Object>> resultList)
			throws DataAccessException{

		PreviewEntity entity = new PreviewEntity();

		for (Map<String, Object> map : resultList) {
			PreviewData previewData = new PreviewData();

			previewData.setMangaID((Integer)map.get("manga_id"));
			previewData.setPath((String)map.get("path"));

			entity.getPreviewList().add(previewData);

		}
		return entity;
	}

	public int insertOneFrame(FrameData data) throws DataAccessException {
		int rowNumber = jdbc.update(SQL_INSERT_FRAME_ONE,
			data.getCreater(),
			data.getPath(),
			data.getCreateDate(),
			data.getMangaID(),
			data.getFrameNo());
	return rowNumber;
	}

	public int insertOneFrameJoin(FrameData data) throws DataAccessException {
		int rowNumber = jdbc.update(SQL_INSERT_FRAME_ONE_JOIN,
			data.getCreater(),
			data.getPath(),
			data.getCreateDate(),
			data.getMangaID(),
			data.getFrameNo());
	return rowNumber;
	}

	public int getLastFrameID() {
		return (Integer)jdbc.queryForList(SQL_GET_LAST_IMGID).get(0).get("imgid");
	}

}
