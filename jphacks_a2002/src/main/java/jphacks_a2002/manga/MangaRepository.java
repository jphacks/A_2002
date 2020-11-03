package jphacks_a2002.manga;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MangaRepository {

	@Autowired
	JdbcTemplate jdbc;

	public MangaEntity selectAll() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}
}
