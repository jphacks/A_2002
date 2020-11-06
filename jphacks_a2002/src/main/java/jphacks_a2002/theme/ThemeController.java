package jphacks_a2002.theme;


import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



/**
 * HTMLから受け取ったパラメータを扱うクラス
 * @author 植村
 *
 */
@Controller
public class ThemeController {


	@Autowired
	ThemeService themeService;
	/**
	 * 新規作成画面を表示する
	 * @param form
	 * @param model
	 * @return
	 */
	@GetMapping("/createManga")
	public String getCreateDisplay(Principal principal, Model model) {
		ThemeData themeData = themeService.getOneTheme();
		model.addAttribute("themeData", themeData);
		return "/createManga";
	}


}
