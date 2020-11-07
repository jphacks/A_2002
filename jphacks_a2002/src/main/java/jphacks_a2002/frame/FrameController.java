package jphacks_a2002.frame;


import java.io.IOException;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import jphacks_a2002.manga.MangaController;
import jphacks_a2002.manga.MangaData;
import jphacks_a2002.manga.MangaService;



/**
 * HTMLから受け取ったパラメータを扱うクラス
 * @author 植村
 *
 */
@Controller
public class FrameController {

	@Autowired
	FrameService frameService;
	@Autowired
	MangaService mangaService;
	@Autowired
	MangaController mangaController;


	/**
	 * 漫画を新規作成する
	 * @param form
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/create/manga/{themeID}")
	public String addNewFrame(@ModelAttribute @Validated FrameForm form, Principal principal, Model model,@PathVariable("themeID")int themeID ) throws IOException {
		MangaData mangaData =  mangaService.addNewManga(themeID);
		frameService.addNewFrame(form,mangaData);
		//return "/top";
		return "redirect:/";
	}

	/**
	 * 漫画のコマを追加する
	 * @param form
	 * @param model
	 * @return
	 * @throws IOException
	 */
//	@RequestMapping("/join/add/{mangaID}")
	public String addJoinFrame(@PathVariable("mangaID")int mangaID, @ModelAttribute @Validated FrameForm form, Principal principal, Model model) throws IOException {
//		int mangaID = mangaService.getMangaID(mangaID);
		form.setMangaID(mangaID);
		frameService.addJoinFrame(form,mangaID);
		//四コマ目だったら詳細へ

		int frameNumber = mangaService.getStatus(mangaID);
//		return ((frameNumber == 4) ? mangaController.selectMangaDisplay(principal, model, form.getMangaID()) : "/top");
		return ((frameNumber == 4) ? mangaController.selectMangaDisplay(principal, model, form.getMangaID()) : "redirect:/");
	}

	@RequestMapping("/join/add/{mangaID}")
	public String addJoinFram(@PathVariable("mangaID")int mangaID, @ModelAttribute @Validated FrameForm form, Principal principal, Model model) throws IOException {

		int frameNumber = mangaService.getStatus(mangaID) + 1;
		System.out.println(frameNumber);
		form.setFrameNo(frameNumber);
		frameService.addFrame(form);

		//四コマ目だったら詳細へ
		return ((frameNumber == 4) ? "redirect:/artwork/{mangaID}": "redirect:/");
	}

}
