package jphacks_a2002.manga;


import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * HTMLから受け取ったパラメータを扱うクラス
 * @author 植村
 *
 */
@Controller
public class MangaController {

	@Autowired
	MangaService mangaService;

	/**
	 * 一覧画面を表示する
	 * @param principal
	 * @param model
	 * @return
	 */
	@PostMapping("/top")
	public String getMangaListDisplay(Principal principal, Model model) {
		MangaEntity mangaEntity = mangaService.selectRandomManga();
		model.addAttribute("mangaEntity", mangaEntity);
		return "/top";
	}

	/**
	 * 新規作成画面を表示する
	 * @param form
	 * @param model
	 * @return
	 */
	@GetMapping("/top/createManga")
	public String getCreateDisplay(Principal principal, Model model) {
		return "top/createManga";
	}

	/**
	 * 漫画の詳細画面を表示する
	 * @param principal
	 * @param model
	 * @param request_id
	 * @return
	 */
	@RequestMapping("/top/artwork/{manga_id}")
	public String selectMangaDisplay(Principal principal, Model model, @PathVariable("manga_id") int manga_id) {
		MangaData mangaData = mangaService.getOneManga(manga_id);
		model.addAttribute("mangaData", mangaData);
		return "top/artwork";
	}

	/**
	 * 漫画作成にランダムで参加する
	 * @param form
	 * @param model
	 * @return
	 */
	@GetMapping("/top/join")
	public String getJoinDisplay(Principal principal, Model model) {
		MangaData mangaData = mangaService.getRandomManga();
		model.addAttribute("mangaData",mangaData);
		return "top/join";
	}



	/**
	 * TOP画面にて作者名でマンガ検索をする
	 * @param principal
	 * @param model
	 * @param keyword
	 * @return
	 */
	@PostMapping("/top/serch")
	public String serchMangaListWithCreater(Principal principal, Model model, @RequestParam("creater") String creater) {
			MangaEntity mangaEntity = mangaService.searchMangaTheme(creater);
			model.addAttribute("mangaEntity", mangaEntity);
		return "/top";
	}
}
