package jphacks_a2002.manga;


import java.io.IOException;
import java.security.Principal;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import lombok.extern.slf4j.Slf4j;


/**
 * HTMLから受け取ったパラメータを扱うクラス
 * @author 植村
 *
 */
@Slf4j
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
	 * 漫画の詳細画面を表示する
	 * @param principal
	 * @param model
	 * @param request_id
	 * @return
	 */
	@RequestMapping("/top/artwork/{漫画のID}")
	public String selectMangaDisplay(Principal principal, Model model, @PathVariable("漫画のID") int /*漫画のID*/) {
		MangaEntity mangaEntity = mangaService.requestDetailDisplay(/*漫画のID*/);
		model.addAttribute("mangaEntity", mangaEntity);
		return //漫画の詳細画面を表示するメソッド(principal, model);
	}

	/**
	 * 新規作成画面を表示する
	 * @param form
	 * @param model
	 * @return
	 */
	@GetMapping("/top/createManga")
	public String getCreateDisplay(Principal principal, Model model) {
		return "top/create";
	}

	/**
	 * 差戻にするか、報告を承認するか
	 * @param form
	 * @param bindingResult
	 * @param principal
	 * @param model
	 * @return
	 */
	@PostMapping("/request/updateReport")
	public String updateRequestReportContet(@ModelAttribute @Validated RequestForm form, BindingResult bindingResult,
			Principal principal, Model model,@RequestParam("submit_id") String submitId )  throws ParseException{

		if (bindingResult.hasErrors()) {
			return getInsertDisplay(form, model);
		}
		RequestData requestData = mangaService.createRequestContents(form);
		if(submitId.equals("承認")){
			requestData.setApplicationStatus(EnumApplicationStatus.valueOf("RDONE"));
			requestData.setRemand("");
		}else if(submitId.equals("差し戻し")){
			requestData.setApplicationStatus(EnumApplicationStatus.valueOf("RCREATE"));
			requestData.setRemand(form.getRemand());
		}else if(submitId.equals("報告・修正")){
			requestData.setApplicationStatus(EnumApplicationStatus.valueOf("RWAIT"));
			requestData.setRemand(form.getRemand());
			requestData.setReportContent(form.getReportContent());
		}else{
			requestData.setApplicationStatus(EnumApplicationStatus.valueOf("CANCELED"));
			requestData.setRemand("");
		}
		mangaService.updateReportContent(requestData);

		return getRequestList(principal, model);//	正しい画面を渡す
	}

	/**
	 * 一覧画面にて名前検索をする
	 * @param principal
	 * @param model
	 * @param keyword
	 * @return
	 */
	@PostMapping("/request/serch")
	public String serchRequestList(Principal principal, Model model, @RequestParam("keyword") String keyword) {
			MangaEntity mangaEntity = mangaService.serchRequestList(keyword);
			model.addAttribute("mangaEntity", mangaEntity);
		return "request/requestListTeacher";
	}

	/**
	 * 情報をCSVファイルとしてダウンロードさせる.
	 * @param principal ログイン情報
	 * @param model
	 * @return CSVファイル
	 */

	@PostMapping("/request/csv")
	public ResponseEntity<byte[]> getCsv(Principal principal, Model model) {
		final String OUTPUT_FULLPATH = WebConfig.OUTPUT_PATH + WebConfig.FILENAME_REPORT_CSV;

		log.info("[" + principal.getName() + "]CSVファイル作成:" + OUTPUT_FULLPATH);

		// タスク情報のCSVファイルをサーバ上に保存
		mangaService.saveCsv();

		// CSVファイルをサーバから読み込み
		byte[] bytes = null;
		try {
			bytes = RequestService.loadCsv(OUTPUT_FULLPATH);
			log.info("[" + principal.getName() + "]CSVファイル読み込み成功:" + OUTPUT_FULLPATH);
		} catch (IOException e) {
			log.warn("[" + principal.getName() + "]CSVファイル読み込み失敗:" + OUTPUT_FULLPATH);
			e.printStackTrace();
		}

		// CSVファイルのダウンロード用ヘッダー情報設定
		HttpHeaders header = new HttpHeaders();
		header.add("Content-Type", "text/csv; charset=UTF-8");
		header.setContentDispositionFormData("filename", WebConfig.FILENAME_REPORT_CSV);

		// CSVファイルを端末へ送信
		return new ResponseEntity<byte[]>(bytes, header, HttpStatus.OK);
	}

}
