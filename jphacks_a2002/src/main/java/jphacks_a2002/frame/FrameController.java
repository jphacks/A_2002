package jphacks_a2002.frame;


import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


/**
 * HTMLから受け取ったパラメータを扱うクラス
 * @author 植村
 *
 */
@Controller
public class FrameController {

	@Autowired
	FrameService frameService;


	/**
	 * 漫画作成を確定する
	 * @param form
	 * @param model
	 * @return
	 */
	@PostMapping("/createManga/cofirm")
	public String confirmCreateManga(@ModelAttribute @Validated FrameData form, Principal principal, Model model) {
		FrameData frameData = new FrameData();
		frameService.addNewFrame(frameData);
		return "/top";
	}

	/**
	 * 漫画のコマを追加する
	 * @param form
	 * @param model
	 * @return
	 */
	@PostMapping("/join/add")
	public String addFrame(@ModelAttribute @Validated FrameData form, Principal principal, Model model) {
		FrameData frameData = new FrameData();
		frameService.addNewFrame(frameData);
		//四コマ目だったら詳細へ
		return "/top";
	}

}
