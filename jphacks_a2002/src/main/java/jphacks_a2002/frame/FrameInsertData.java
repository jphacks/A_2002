package jphacks_a2002.frame;

import java.util.Date;

import lombok.Data;

@Data
public class FrameInsertData {

	private int frameID;

	private String creater;

	private String path;

	private String imgData;

	private Date createDate;

	private int mangaID;

	private int frameNo;
}
