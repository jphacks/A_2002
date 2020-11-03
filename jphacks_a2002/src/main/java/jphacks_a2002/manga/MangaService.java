package jphacks_a2002.manga;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class MangaService {

	@Autowired
	MangaRepository mangaRepository;

	//データベースから既に完成した漫画のリストをランダムに10件取得する
	public MangaEntity selectRandomManga() {
		MangaEntity mangaEntity = mangaRepository.selectAll();
		Collections.shuffle(mangaEntity);
		while(mangaEntity.size() > 10) {
			mangaEntity.remove(mangaEntity.size() - 1);
		}
		return mangaEntity;
	}

	//データベースから既に完成した漫画をテーマ名で検索して取得する
	public MangaEntity searchMangaTheme(Stirng ThemeName) {
		return mangaRepository.searchTheme(TemeName);
	}

	//新規に作成された漫画をデータベースに登録（一応登録件数を返す）
	public int addNewManga(MangaData mangaData) {
		return mangaRepository.InsertManga(mangaData);
	}
}
