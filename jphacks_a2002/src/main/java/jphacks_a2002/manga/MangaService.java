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
	//処理の内容が漫画の登録件数によってかなり左右されるためあとで修正の必要あり
	public MangaEntity selectRandomManga() {
		//漫画マスタのstatusが4になってるやつだけ頂戴
		MangaEntity mangaEntity = mangaRepository.selectAll();
		Collections.shuffle(mangaEntity.getMangaList());
		while(mangaEntity.getMangaList().size() > 10) {
			mangaEntity.getMangaList().remove(mangaEntity.getMangaList().size() - 1);
		}
		return mangaEntity;
	}

	//データベースから既に完成した漫画をテーマ名で検索して取得する
	public MangaEntity searchMangaTheme(String ThemeName) {
		return mangaRepository.searchTheme(ThemeName);
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
		//漫画マスタのstatusが4以外のものを抽出
		MangaEntity undoneMangaEntity = mangaRepository.selectUndoneAll();
		int randGetIndex = new Random().randInt(undoneMangaEntity.size());
		return undoneMangaEntity.getRandomManga(randGetIndex);
	}

	//ここの処理については明日先生に聞くかも
	//コントローラでコマテーブルの情報を作成した後こっちを呼び出して欲しい
	//コマのパッケージの方でコマIDをコントローラに返すように記述する
	//正直このやり方が最良かはよくわかんね
	public int addNewFrame(int mangaId,int frameId) {
		//リポジトリ側でコマFlagみたいなやつ1進めるように作っとてん
		return mangaRepository.insertFrame(mangaId,frameId);
	}

	//トップ画面で一覧から漫画選択または最終コマの作成が完了した時点で漫画ページへ遷移
	//渡す項目がIdでいいかは正直わかんね
	public MangaData getOneManga(int mangaId) {
		return mangaRepository.getOneManga(mangaId);
	}
}
