package jphacks_a2002.frame;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Date;
import java.util.Random;

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

	Random random = new Random();

	//コマの追加時に呼び出される
	//MangaControllerから呼び出される予定だが、ちょっと微妙かもと思っている
	//返り値はコマID
	//このやり方だとFrameControllerがいらないのよな
	public int addNewFrame(FrameForm frameForm,MangaData mangaData) throws IOException {
		int frameNameLast = frameRepository.getLastFrameID();
		String path = this.writeImgFile(frameForm.getImageData(),random.nextInt()+".png");
		frameForm.setPath(path);
		FrameData data = this.createMangaFormToData(frameForm,mangaData);
		data.setFrameNo(1);
		return frameRepository.insertOneFrame(data);
	}

	public int addJoinFrame(FrameForm frameForm,int mangaID) throws IOException {
		int frameNameLast = frameRepository.getLastFrameID();
		String path = this.writeImgFile(frameForm.getImageData(),random.nextInt()+".png");
		MangaData mangaData = mangaService.getOneMangaData(mangaID);
		mangaService.statusUpdate(mangaData.getMangaID());
		frameForm.setPath(path);
		return frameRepository.insertOneFrame(this.createMangaFormToData(frameForm,mangaData));
	}

	//返り値はDBで使うためdestinationFileとは別
	private String writeImgFile(String imgStr,String imgName) throws IOException {
		imgStr = imgStr.split(",")[1];
		System.out.println(imgName);
		byte[] decodedImg = Base64.getDecoder().decode(imgStr.getBytes(StandardCharsets.UTF_8));
		Path destinationFile = Paths.get("src/main/resources/static/img/frame", imgName);
		File newpng = new File(destinationFile.toString());
		newpng.createNewFile();
		Files.write(destinationFile, decodedImg);

		return "img/frame/" + imgName;
	}

	public FrameData createMangaFormToData(FrameForm frameForm,MangaData mangaData) {
		Date date = new Date();
		//FrameIDはデータベースで採番するためここで割り振らない
		FrameData data = new FrameData();
		data.setCreater(frameForm.getCreater());
		data.setPath(frameForm.getPath());
		data.setCreateDate(date);
		data.setMangaID(mangaData.getMangaID());
		//これ画面から渡されるやつに1足すって処理でいいかわかんねえ
		data.setFrameNo(mangaData.getStatus() + 1 );
		return data;
	}



	public void addFrame(FrameForm form) throws IOException {
		Date date = new Date();
		form.setCreateDate(date);
		int frameNameLast = frameRepository.getLastFrameID();
		String path = this.writeImgFile(form.getImageData(),Integer.toString(frameNameLast)+".png");
		form.setPath(path);
		FrameData data = formToData(form);
		frameRepository.insertOneFrameJoin(data);
		mangaService.statusUpdate(data.getMangaID());
	}

	private FrameData formToData(FrameForm form) {
		FrameData data = new FrameData();
		data.setCreateDate(form.getCreateDate());
		data.setCreater(form.getCreater());
		data.setFrameNo(form.getFrameNo());
		data.setMangaID(form.getMangaID());
		data.setPath(form.getPath());
		return data;
	}
}
