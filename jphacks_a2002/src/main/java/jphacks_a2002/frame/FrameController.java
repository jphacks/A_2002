package jphacks_a2002.frame;


import java.io.IOException;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jphacks_a2002.manga.MangaController;
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
	 * 漫画作成を確定する
	 * @param form
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/createManga/cofirm/{frameID}")
	public String confirmCreateManga(@ModelAttribute @Validated FrameForm form , Principal principal, Model model) throws IOException {
		FrameData frameData = new FrameData();
		/*
		 * frameService.フォームの中とガッチャマン(form);
			or
		   frameservice.addNewFrame(form);
		   に変更するか
		*/
		frameService.addNewFrame(form);
		return "/top";
	}

	/**
	 * 漫画のコマを追加する
	 * @param form
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@PostMapping("/join/add")
	public String addFrame(@ModelAttribute @Validated FrameForm form, Principal principal, Model model) throws IOException {
		frameService.addNewFrame(form);
		//四コマ目だったら詳細へ
		int frameNumber = mangaService.getStatus(form.getMangaID());
		return ((frameNumber == 4) ? mangaController.selectMangaDisplay(principal, model, form.getMangaID()) : "/top");
	}


}
