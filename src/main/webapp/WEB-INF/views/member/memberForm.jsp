<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">

<script type="text/javascript">
	
//중복체크 플래그
var duplicateIdCheck = false;
var duplicateNameCheck = false;
var duplicateEmailCheck = false;

$(function(){
	//id
	$('input[name=mem_id]').on('keyup', function(){
		duplicateIdCheck = false;
		$("#resultMsgId").text("")
	})
	
	$("#btnIdCheck").click(function(){
		var $mem_id = $('input[name=mem_id]')
		
		if($mem_id.val()){
			duplicateIdCheck = false;
			$.ajax({
				type: 'post',
				url: '${pageContext.request.contextPath}/member/exists',
				data: "type=id&info=" + $mem_id.val(),
				success: function(data, status){
					console.log(status);
					console.log(JSON.stringify(data));
//						alert(JSON.stringify(data));
					if(data.result == "true"){
						$("#resultMsgId").text("사용중인 아이디 입니다.")
						$mem_id.focus()
						$mem_id.val('')
					}else{
						$("#resultMsgId").text("사용가능한 아이디 입니다.")							
//							$mem_id.attr('readOnly',true)
						$('input[name=mem_name]').focus()
						duplicateIdCheck = true;
					}

				},
				error: function(error){
					console.log(error);
				}
				
			});
		}else{
			alert("아이디를 입력 해주세요.")
		}	
	});
	
	//name
	$('input[name=mem_name]').on('keyup', function(){
		duplicateNameCheck = false;
		$("#resultMsgName").text("")
	})
	
	$("#btnNameCheck").click(function(){
		var $mem_name = $('input[name=mem_name]')
		
		if($mem_name.val()){
			duplicateNameCheck = false;
			$.ajax({
				type: 'post',
				url: '${pageContext.request.contextPath}/member/exists',
				data: "type=name&info=" + $mem_name.val(),
				success: function(data, status){
					console.log(status);
					console.log(JSON.stringify(data));
//						alert(JSON.stringify(data));
					if(data.result == "true"){
						$("#resultMsgName").text("사용중인 닉네임 입니다.")
						$mem_name.focus()
						$mem_name.val('')
					}else{
						$("#resultMsgName").text("사용가능한 닉네임 입니다.")							
						$('input[name=mem_pwd]').focus()
						duplicateNameCheck = true;
					}

				},
				error: function(error){
					console.log(error);
				}
				
			});
		}else{
			alert("닉네임을 입력 해주세요.")
		}	
	});
	
	//email
	$('input[name=mem_email]').on('keyup', function(){
		duplicateEmailCheck = false;
		$("#resultMsgEmail").text("")
	})
	
	$("#btnEmailCheck").click(function(){
		var $mem_email = $('input[name=mem_email]')
		
		if($mem_email.val()){
			duplicateCheck = false;
			$.ajax({
				type: 'post',
				url: '${pageContext.request.contextPath}/member/exists',
				data: "type=email&info=" + $mem_email.val(),
				success: function(data, status){
					console.log(status);
					console.log(JSON.stringify(data));
//						alert(JSON.stringify(data));
					if(data.result == "true"){
						$("#resultMsgEmail").text("사용중인 이메일 입니다.")
						$mem_email.focus()
						$mem_email.val('')
					}else{
						$("#resultMsgEmail").text("사용가능한 이메일 입니다.")							
						duplicateEmailCheck = true;
					}

				},
				error: function(error){
					console.log(error);
				}
				
			});
		}else{
			alert("이메일을 입력 해주세요.")
		}	
	});
	
	
	$("input[name=mem_pwd_confirm]").on('keyup', function(){
		if($(this).val() != $("input[name=mem_pwd]").val()){
			$("#resultMsgPwd").text("비밀번호가 일치하지 않습니다.")
		}else{
			$("#resultMsgPwd").text("사용 가능한 비밀번호 입니다.")
		}
	})
	
	
	
})



	function validate(){
		var frm = document.memberForm;
		
		if(!document.querySelector('#agreement').checked){
			alert("약관 동의에 체크해 주세요.")
			return false;
		}
		

		if(!duplicateIdCheck){
			alert("아이디 중복 체크를 해주세요.")
			return false;
		}
		if(!duplicateNameCheck){
			alert("닉네임 중복 체크를 해주세요.")
			return false;
		}
		if(!duplicateEmailCheck){
			alert("이메일 중복 체크를 해주세요.")
			return false;
		}
		
		if(frm.mem_id.value == ""){
			alert("아이디를 입력하세요.");
			frm.mem_id.focus();
			return false;
		}
		if(frm.mem_name.value == ""){
			alert("닉네임을 입력하세요.");
			frm.mem_name.focus();
			return false;
		}
		if(frm.mem_pwd.value == "" || frm.mem_pwd_confirm == ""){
			alert("비밀번호를 입력하세요.");
			frm.mem_pwd.focus();
			return false;
		}else if(frm.mem_pwd.value != frm.mem_pwd_confirm.value){
			alert("비밀번호가 일치하지 않습니다.");
			frm.mem_pwd.value = "";
			frm.mem_pwd_confirm.value = "";
			frm.mem_pwd.focus();
			return false;			
		}

		if(frm.mem_email.value == ""){
			alert("이메일 주소를 입력하세요.");
			frm.mem_email.focus();
			return false;
		}
		

		
		return true;
	}
	
	function fn_save(){
		/*   
		* 회원등록함수
		*/
		var frm = document.memberForm;
		if(!validate()){
			return;
		}

		frm.action="add";
		
		frm.submit();

	}
	function fn_cancel(){
		history.go(-1)
	}

</script>
</head>
<body>
<div>

<h2>회원가입</h2>

	<form name="memberForm" method="post">
		<table class="table table-bordered">
			<tr>
				<td>아이디</td>
				<img src="123/20180103/ed636d14-651e-4e30-9439-7547ceff3571_background.jpg"/>
				<td><input type="text" name="mem_id" size="20" > 
				<button type="button" class="btn btn-primary" id="btnIdCheck">중복체크</button> 8~20자 내의 영문, 숫자 조합 
				<label id="resultMsgId" style="color: red;"></label>
				</td>
			</tr>		
			<tr>
				<td>닉네임</td>
				<td><input type="text" name="mem_name" size="20" > 
				<button type="button" class="btn btn-primary" id="btnNameCheck">중복체크</button> 닉네임을 입력 하세요.
				<label id="resultMsgName" style="color: red;"></label>
				</td>
			</tr>		
			<tr>
				<td>비밀번호</td>
				<td><input type="password" name="mem_pwd" size="20" > 8~20자 내의 영문, 숫자 조합</td> 
			</tr>		
			<tr>
				<td>비밀번호 확인</td>
				<td><input type="password" name="mem_pwd_confirm" size="20" ><label id="resultMsgPwd" style="color: red;"></label></td> 
			</tr>			

			<tr>
				<td>이메일</td>
				<td><input type="text" name="mem_email" size="20" > <button type="button" class="btn btn-primary" id="btnEmailCheck">중복체크</button>
				<label id="resultMsgEmail" style="color: red;"></label>
				</td> 
			</tr>		
			<tr>
				<td>추천인</td>
				<td><input type="text" name="mem_reco" size="20" > 
				</td> 
			</tr>		
			<tr>
				<td>약관 동의</td>
				<td><input type="checkbox" name="agreement" id="agreement" checked size="20" value="Y"> <a>이용약관</a> 
				</td> 
			</tr>		
			
			<tr>
				<td colspan="2">
					<input id="btn_submit" type="button" value="가입하기" class="btn btn-default" onclick="fn_save()">

					<input type="button" value="취소" class="btn btn-default" onclick="fn_cancel()">
				</td>
			</tr>
		</table>
	</form>
</div>

</body>
</html>