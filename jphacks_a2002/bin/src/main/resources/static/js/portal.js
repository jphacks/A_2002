function check(){
	if(!register.stclass){
		alert("クラスは必須項目です。再入力してください。");
		return false;
	}else if(!register.stno){
		alert("番号は必須項目です。再入力してください。");
		return false;
	}else if(!register.gakuseki_no){
		alert("学籍番号は必須項目です。再入力してください。");
		return false;
	}else if(!register.user_name){
		alert("名前は必須項目です。再入力してください。");
		return false;
	}else if(!register.department_code){
		alert("学科コードは必須項目です。再入力してください。");
		return false;
	}else if(!register.kana){
		alert("企業名先頭三文字は必須項目です。再入力してください。");
		return false;
	}else if(!register.entrance_exam_report_no){
		alert("求人番号は必須項目です。再入力してください。");
		return false;
	}else if(!register.company_name){
		alert("企業名は必須項目です。再入力してください。");
		return false;
	}else if(!register.kubun){
		alert("試験の申込経路は必須項目です。再入力してください。");
		return false;
	}else if(!register.entrance_exam_day){
		alert("日時は必須項目です。再入力してください。");
		return false;
	}else if(!register.entrance_exam_location){
		alert("受験場所は必須項目です。再入力してください。");
		return false;
	}else if(!register.entrance_exam_location_kubun){
		alert("受験場所は必須項目です。再入力してください。");
		return false;
	}else if(!register.entrance_exam_category){
		alert("試験内容は必須項目です。再入力してください。");
		return false;
	}else if(!register.entrance_exam_detail_kubun){
		alert("試験区分は必須項目です。再入力してください。");
		return false;
	}else if(!register.result_notification){
		alert("結果通知は結果通知は必須項目です。再入力してください。");
		return false;
	}else if(register.kubun.value=="6"){
		if(!register.kubun_other){
			alert("試験の申込経路のその他を入力してください。");
			return false;
		}
	}else if(register.entrance_exam_location_kubun.value=="6"){
		if(!register.entrance_exam_location_kubun_other){
			alert("受験場所のその他を入力してください。");
			return false;
		}
	}else if(register.entrance_exam_detail_kubun.value=="5"){
		if(!register.entrance_exam_detail_kubun_other){
			alert("試験区分の？試験を入力してください。");
			return false;
		}
	}else if(register.entrance_exam_category.value=="7"){
		if(!register.interview_overview_style){
			alert("試験内容のその他を入力してください。");
			return false;
		}
	}else if(register.result_notification.value=="1"){
		if(!register.result_cnt){
			alert("結果通知までの日数を入力してください。");
			return false;
		}
	}else if(register.appropriate.value=="5"){
		if(!register.appropriate_other){
			alert("その他適正を入力してください。");
			return false;
		}
	}else{
		return true;
	}
}

function money(){

		var price1 = 300 * document.syomeisyoForm.fullset.value;
		var price2 = 100 * syomeisyoForm.enrolled_cnt.value;
		var price3 = 100 * syomeisyoForm.record_cnt.value;
		var price4 = 100 * syomeisyoForm.graduation_cnt.value;
		var price5 = 100 * syomeisyoForm.health.value;
		var price6 = 100 * syomeisyoForm.other_cnt.value;

		var total = price1 + price2 + price3 + price4 + price5 + price6;
		syomeisyoForm.total_money.value = total;

}