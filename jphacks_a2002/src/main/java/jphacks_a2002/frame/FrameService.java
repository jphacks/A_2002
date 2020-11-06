package jphacks_a2002.frame;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jphacks_a2002.manga.MangaData;
import jphacks_a2002.manga.MangaService;

@Transactional
@Service
public class FrameService {

	@Autowired
	FrameRepository frameRepository;
	@Autowired
	MangaService mangaService;

	//コマの追加時に呼び出される
	//MangaControllerから呼び出される予定だが、ちょっと微妙かもと思っている
	//返り値はコマID
	//このやり方だとFrameControllerがいらないのよな
	public int addNewFrame(FrameForm frameForm,MangaData mangaData) throws IOException {
		int frameNameLast = frameRepository.getLastFrameID();
		this.writeImgFile(frameForm.getImageData(),Integer.toString(frameNameLast)+".png");
		return frameRepository.insertOneFrame(this.formToData(frameForm,mangaData));
	}

	private void writeImgFile(String imgStr,String imgName) throws IOException {
		byte[] decodedImg = Base64.getDecoder().decode(imgStr.getBytes(StandardCharsets.UTF_8));
		Path destinationFile = Paths.get("/img/frame", imgName);
		Files.write(destinationFile, decodedImg);
	}

	public FrameData formToData(FrameForm frameForm,MangaData mangaData) {
		Date date = new Date();
		//FrameIDはデータベースで採番するためここで割り振らない
		FrameData data = new FrameData();
		data.setCreater(frameForm.getCreater());
		data.setPath(frameForm.getPath());
		data.setCreateDate(date);
		data.setMangaID(mangaData.getMangaID());
		//これ画面から渡されるやつに1足すって処理でいいかわかんねえ
		data.setFrameID(mangaData.getFrameNo() + 1 );
		return data;
	}
}
