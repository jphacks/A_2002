package jphacks_a2002.manga;

import java.util.ArrayList;
import java.util.List;

import jphacks_a2002.frame.FrameData;
import lombok.Data;

@Data
public class MangaData {

	int mangaID;
	int themeID;
	String themeaName;
	List<FrameData> framelist = new ArrayList<FrameData>();
	int status;

}
