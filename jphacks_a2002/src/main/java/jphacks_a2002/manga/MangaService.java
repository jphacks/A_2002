package jphacks_a2002.manga;

import java.util.Collections;
import java.util.Random;

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
	//新規漫画作成時に必要なテーマなどはthemeパッケージにて記述
	public int addNewManga(MangaData mangaData) {
		//漫画ごとの一意なIDの割り振りは話し合ってからRepositoryかこっちに記述
		return mangaRepository.insertManga(mangaData);
	}

	//コマ追加画面遷移時に未完成な漫画のデータを一つ取得する
	public MangaData getRandomManga() {
		//未完成漫画のリストを作成
		MangaEntity undoneMangaEntity = mangaRepository.selectUndoneAll();
		int randGetIndex = new Random().randInt(undoneMangaEntity.size());
		return undoneMangaEntity.get(randGetIndex);
	}

	//ここの処理については明日先生に聞くかも
	//コントローラでコマテーブルの情報を作成した後こっちを呼び出して欲しい
	//コマのパッケージの方でコマIDをコントローラに返すように記述する
	//正直このやり方が最良かはよくわかんね
	public int addNewFrame(int mangaId,int frameId) {
		//リポジトリ側でコマFlagみたいなやつ1進めるように作っとてん
		return mangaRepository.insertFrame(mangaId,frameId);
	}
}
