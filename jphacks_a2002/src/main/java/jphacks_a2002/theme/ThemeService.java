package jphacks_a2002.theme;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ThemeService {

	@Autowired
	ThemeRepository themeRepository;

	//データベースからテーマ全部持ってきて一個分のデータだけ返す
	public ThemeData getOneTheme() {
		ThemeEntity themeEntity = themeRepository.selectAll();
		int randThemeIndex = new Random().nextInt(themeEntity.getThemeList().size());
		return themeEntity.getThemeList().get(randThemeIndex);

	}
}
