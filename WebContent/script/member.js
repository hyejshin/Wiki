/*	member/loginForm.jsp ���� �α��� ��ư�� ��������
 *  loginCheck() �Լ����� ���̵�� ��й�ȣ�� �ԷµǾ����� Ȯ��  */
function loginCheck(){
	if(document.frm.user_id.value.length == 0){ //���̵� �Էµ��� �ʾ��� ���
		alert('���̵� �Է����ּ���');
		frm.user_id.focus(); //���콺Ŀ���� ���̵� �Է»��� ���� ������
		return false; //�α��� �۾� ���� �ߴ�
	}
	
	if(document.frm.user_pw.value == " "){ //��й�ȣ�� �Էµ��� �ʾ��� ���
		alert('��й�ȣ�� �Է����ּ���');
		frm.user_pw.focus(); 
		return false;
	}
	
	return true;
}

/*	member/joinForm.jsp ���� ���̵� �Է¹޾Ҵ��� Ȯ��
 *	���̵� �ߺ�üũ�� ���ο� â���� Ȯ�� ������  */
function idCheck(){
	if(document.frm.user_id.value == ""){
		alert('���̵� �Է����ּ���');
		document.frm.user_id.focus();
		return;
	}
	
	var url="idCheck.do?user_id=" + document.frm.user_id.value; //�Է¹��� ���̵� idCheck.do �������� ���� DB�� ���̵� �ִ��� Ȯ��
	window.open(url,"_blank_1", "toolbar=no, menubar=no, scrollbars=yes, resizable=no, width=450, height=200"); //���ο�â ����
}

/* member/withdrawForm.jsp���� ��й�ȣ Ȯ���� �޾Ҵ��� Ȯ��.*/
function pwCheck(){
	if(!document.frm.pw_check.value){
		alert("��й�ȣ�� �Է����ּ���.");
		frm.pw_check.focus();
		return false;
	}
	if(document.frm.pw_check.value != document.frm.user_pw.value){
		alert("��й�ȣ�� ��ġ���� �ʽ��ϴ٤Ф�");
		frm.user_pw.focus();
		return false;
	}
	return true;
}

/* ID�ߺ� Ȯ���� �ϰ� ����Ѵٰ� Ŭ�������� â �ڱ�*/
function idok(){
	opener.frm.reid.value="${user_id}";
	self.close(); //�ߺ�Ȯ���ϴ� â �ݱ�
}

function joinCheck(){
	if(document.frm.user_name.value.length == 0){
		alert("�̸��� ���ּ���.");
		frm.user_name.focus();
		return false;
	}
	if(document.frm.user_id.value.length == 0){
		alert("���̵� ���ּ���.");
		frm.user_id.focus();
		return false;
	}
	if(document.frm.user_pw.value == ""){
		alert("��й�ȣ�� �Է����ּ���.");
		frm.user_pw.focus();
		return false;
	}
	if(document.frm.pw_check.value == ""){
		alert("��й�ȣȮ���� ���ּ���.");
		frm.pw_check.focus();
		return false;
	}
	if(document.frm.user_email.value == ""){
		alert("������ �Է� ���ּ���.");
		frm.user_email.focus();
		return false;
	}
	if(document.frm.user_pw.value != document.frm.pw_check.value){
		alert("��й�ȣ�� ��ġ���� �ʽ��ϴ٤Ф�");
		frm.pw_check.focus();
		return false;
	}
	if(document.frm.reid.value.length == 0){
		alert("�ߺ� üũ�� ���� �ʾҽ��ϴ�!!");
		frm.user_id.focus();
		return false;
	}
	return true;
}