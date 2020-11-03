package jphacks_a2002.manga;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class MangaEntity {

	private List<MangaData> mangaList = new ArrayList<MangaData>();
}
