package jphacks_a2002.manga;

import java.util.Collections;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jphacks_a2002.PreviewEntity;
import jphacks_a2002.frame.FrameRepository;
import jphacks_a2002.frame.FrameService;

@Transactional
@Service
public class MangaService {

	@Autowired
	MangaRepository mangaRepository;
	@Autowired
	FrameRepository frameRepository;
	@Autowired
	FrameService frameService;

	//データベースから既に完成した漫画のリストをランダムに10件取得する
	//処理の内容が漫画の登録件数によってかなり左右されるためあとで修正の必要あり
	public PreviewEntity selectRandomManga() {
		//漫画マスタのstatusが4になってるやつだけ頂戴
		PreviewEntity previewEntityAll = frameRepository.selectAll();
		Collections.shuffle(previewEntityAll.getPreviewList());
		PreviewEntity previewEntity = new PreviewEntity();
		int index = 0;
		while(previewEntity.getPreviewList().size() < 10 && previewEntityAll.getPreviewList().size() > index) {
			previewEntity.getPreviewList().add(previewEntityAll.getPreviewList().get(index));
			index++;
		}
		return previewEntity;
	}

	//データベースから既に完成した漫画を作成者で検索して取得する
	public PreviewEntity searchMangaCreater(String Creater) {
		//Frameベースで検索を行うためFrameRepositoryに問い合わせを行う
		return frameRepository.searchCreater(Creater);
	}

	//新規に作成された漫画をデータベースに登録（一応登録件数を返す）
	//新規漫画作成時に必要なテーマなどはthemeパッケージにて記述
	//Controller側でテーマIDとコマのデータ頂戴
	public MangaData addNewManga(int themeID) {
		//漫画ごとの一意なIDの割り振りは話し合ってからRepositoryかこっちに記述
		return mangaRepository.insertManga(themeID);
	}

	//コマ追加画面遷移時に未完成な漫画のデータを一つ取得する
	public MangaData getRandomManga() {
		//未完成漫画のリストを作成
		//漫画マスタのstatusが4以外のものを抽出
		MangaEntity undoneMangaEntity = mangaRepository.selectUndoneAll();
		int randGetIndex = new Random().nextInt(undoneMangaEntity.getMangaList().size());
		return undoneMangaEntity.getMangaList().get(randGetIndex);
	}

	//ここの処理については明日先生に聞くかも
	//コントローラでコマテーブルの情報を作成した後こっちを呼び出して欲しい
	//コマのパッケージの方でコマIDをコントローラに返すように記述する
	//正直このやり方が最良かはよくわかんね
	public int statusUpdate(int mangaId) {
		//リポジトリ側でコマFlagみたいなやつ1進めるように作っとてん
		//frameService.addNewFrame(frameData);
		return mangaRepository.updateManga(mangaId);
	}

	//トップ画面で一覧から漫画選択または最終コマの作成が完了した時点で漫画ページへ遷移
	//渡す項目がIdでいいかは正直わかんね
	public MangaData getOneManga(int mangaId) {
		return mangaRepository.getOneManga(mangaId);
	}

	public int getStatus(int mangaId) {
		return mangaRepository.getStatus(mangaId);
	}

	public MangaData getOneMangaData(int frameID) {
		return mangaRepository.getMangaData(frameID);
	}

	public int getMangaID(int frameID) {

		return mangaRepository.getMangaID(frameID);
	}
}
