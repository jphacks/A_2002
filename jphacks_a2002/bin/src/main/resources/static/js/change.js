/**
 * 権限によってクラス番号の表示を変更する
 */
function buttonChange() {
	radio = document.getElementsByName('role')

	if(radio[0].checked) {
		document.getElementById('firstBox').style.display = "";
		document.getElementById('secondBox').style.display = "none";
	} else if(radio[1].checked) {
		document.getElementById('firstBox').style.display = "none";
		document.getElementById('secondBox').style.display = "none";
	} else if(radio[2].checked) {
		document.getElementById('firstBox').style.display = "";
		document.getElementById('secondBox').style.display = "";
	}
}