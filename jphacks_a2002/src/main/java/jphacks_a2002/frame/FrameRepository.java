package jphacks_a2002.frame;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class FrameRepository {

	@Autowired
	JdbcTemplate jdbc;

	private static final String SQL_INSERT_FRAME_ONE = "INSERT INTO frame (frame_id,creater,path,create_date) VALUES ((SELECT MAX(id) + 1 FROM frame),?,?,?)";


	public int insertOneFrame(FrameData data) throws DataAccessException {
		int rowNumber = jdbc.update(SQL_INSERT_FRAME_ONE,
			data.getFrameID(),
			data.getCreater(),
			data.getPath(),
			data.getCreateDate());
	return rowNumber;
	}

}
