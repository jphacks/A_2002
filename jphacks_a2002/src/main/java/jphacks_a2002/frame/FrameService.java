package jphacks_a2002.frame;

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
	public int addNewFrame(FrameData frameData) {
		return frameRepository.insertOneFrame(frameData);
	}

}
