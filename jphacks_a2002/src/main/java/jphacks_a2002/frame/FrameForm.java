package jphacks_a2002.frame;

import java.util.Date;

import lombok.Data;

@Data
public class FrameForm {

	//チェックに必要なもの作っといて
	//チェックに必要のない項目も、パラメータの受け渡しで使用するため消さないように
	private int frameID;

	private String creater;

	private String imageData;

	private String path;

	private Date createDate;

	private int mangaID;

	private int frameNo;
}
