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
		return "/top";
	}

	/**
	 * 漫画のコマを追加する
	 * @param form
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/join/add/{frameID}")
	public String addJoinFrame(@ModelAttribute @Validated FrameForm form, Principal principal, Model model,@PathVariable("frameID")int frameID) throws IOException {
		frameService.addJoinFrame(form,frameID);
		//四コマ目だったら詳細へ
		int frameNumber = mangaService.getStatus(form.getMangaID());
		return ((frameNumber == 4) ? mangaController.selectMangaDisplay(principal, model, form.getMangaID()) : "/top");
	}


}
