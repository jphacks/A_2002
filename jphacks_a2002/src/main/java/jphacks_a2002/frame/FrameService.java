package jphacks_a2002.frame;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class FrameService {

	@Autowired
	FrameRepository frameRepository;

	//コマの追加時に呼び出される
	//MangaControllerから呼び出される予定だが、ちょっと微妙かもと思っている
	//返り値はコマID
	//このやり方だとFrameControllerがいらないのよな
	public int addNewFrame(FrameForm frameForm) {
		return frameRepository.insertOneFrame(this.formToData(frameForm));
	}

	public FrameData formToData(FrameForm frameForm) {
		Date date = new Date();
		//FrameIDはデータベースで採番するためここで割り振らない
		FrameData frameData = new FrameData();
		frameData.setCreater(frameForm.getCreater());
		frameData.setPath(frameForm.getPath());
		frameData.setCreateDate(date);
		frameData.setMangaID(frameForm.getMangaID());
		//これ画面から渡されるやつに1足すって処理でいいかわかんねえ
		frameData.setFrameID(frameForm.getFrameNo() + 1 );
		return frameData;
	}
}
