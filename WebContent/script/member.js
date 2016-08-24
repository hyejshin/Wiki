/*	member/loginForm.jsp 에서 로그인 버튼이 눌려지면
 *  loginCheck() 함수에서 아이디와 비밀번호가 입력되었는지 확인  */
function loginCheck(){
	if(document.frm.user_id.value.length == 0){ //아이디가 입력되지 않았을 경우
		alert('아이디를 입력해주세요');
		frm.user_id.focus(); //마우스커서가 아이디 입력상자 위에 놓여짐
		return false; //로그인 작업 진행 중단
	}
	
	if(document.frm.user_pw.value == " "){ //비밀번호가 입력되지 않았을 경우
		alert('비밀번호를 입력해주세요');
		frm.user_pw.focus(); 
		return false;
	}
	
	return true;
}

/*	member/joinForm.jsp 에서 아이디를 입력받았는지 확인
 *	아이디 중복체크는 새로운 창에서 확인 가능함  */
function idCheck(){
	if(document.frm.user_id.value == ""){
		alert('아이디를 입력해주세요');
		document.frm.user_id.focus();
		return;
	}
	
	var url="idCheck.do?user_id=" + document.frm.user_id.value; //입력받은 아이디를 idCheck.do 서블릿으로 보내 DB에 아이디가 있는지 확인
	window.open(url,"_blank_1", "toolbar=no, menubar=no, scrollbars=yes, resizable=no, width=450, height=200"); //새로운창 띄우기
}

/* member/withdrawForm.jsp에서 비밀번호 확인을 받았는지 확인.*/
function pwCheck(){
	if(!document.frm.pw_check.value){
		alert("비밀번호를 입력해주세요.");
		frm.pw_check.focus();
		return false;
	}
	if(document.frm.pw_check.value != document.frm.user_pw.value){
		alert("비밀번호가 일치하지 않습니다ㅠㅠ");
		frm.user_pw.focus();
		return false;
	}
	return true;
}

/* ID중복 확인을 하고 사용한다고 클릭했으면 창 닥기*/
function idok(){
	opener.frm.reid.value="${user_id}";
	self.close(); //중복확인하는 창 닫기
}

function joinCheck(){
	if(document.frm.user_name.value.length == 0){
		alert("이름을 써주세요.");
		frm.user_name.focus();
		return false;
	}
	if(document.frm.user_id.value.length == 0){
		alert("아이디를 써주세요.");
		frm.user_id.focus();
		return false;
	}
	if(document.frm.user_pw.value == ""){
		alert("비밀번호를 입력해주세요.");
		frm.user_pw.focus();
		return false;
	}
	if(document.frm.pw_check.value == ""){
		alert("비밀번호확인을 해주세요.");
		frm.pw_check.focus();
		return false;
	}
	if(document.frm.user_email.value == ""){
		alert("메일을 입력 해주세요.");
		frm.user_email.focus();
		return false;
	}
	if(document.frm.user_pw.value != document.frm.pw_check.value){
		alert("비밀번호가 일치하지 않습니다ㅠㅠ");
		frm.pw_check.focus();
		return false;
	}
	if(document.frm.reid.value.length == 0){
		alert("중복 체크를 하지 않았습니다!!");
		frm.user_id.focus();
		return false;
	}
	return true;
}