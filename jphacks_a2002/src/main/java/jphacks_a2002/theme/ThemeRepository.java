package jphacks_a2002.theme;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ThemeRepository {

	@Autowired
	JdbcTemplate jdbc;


	private static final String SELECT_THEME_ALL = "SELECT * FROM theme_table";

	public ThemeEntity selectAll() {
		List<Map<String, Object>> resultList = jdbc.queryForList(SELECT_THEME_ALL);

		ThemeEntity themeEntity = mappingSelectResultEntity(resultList);
		return themeEntity;
	}

	private ThemeEntity mappingSelectResultEntity(List<Map<String, Object>> resultList)
			throws DataAccessException{

		ThemeEntity entity = new ThemeEntity();

		for (Map<String, Object> map : resultList) {
			ThemeData data = new ThemeData();
			data.setThemeId((Integer) map.get("theme_id"));
			data.setThemeName((String)map.get("theme_name"));

			entity.getThemeList().add(data);
		}
		return entity;
	}
}
