<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/bootstrap.css">
<title>Member View</title>
<script>

	//삭제
	function fn_del(mem_id){
		location.href="${pageContext.request.contextPath }/member/del/"+mem_id;
		//자바스크립트가 jsp와 분리되어 있는 상황일 때.. 문제가 된다.
	}
	//목록으로
	function fn_list(){
		location.href="${pageContext.request.contextPath }/member/members";
	}
	
	$(function(){
		
		$('#pwdTd').on('click', '#pwdBtn', function(){
			var newHtml = '<input type="password" name="pwd_modi" id="pwd_modi" size="20"> (8~20자 내의 영문, 숫자 조합)'
			newHtml += ' &nbsp;&nbsp;&nbsp;<button type="button" id="pwd_modi_btn" class="btn btn-primary">확인</button>'
			$('#pwdTd').html(newHtml)
			
		})
		
		$('#pwdTd').on('click', '#pwd_modi_btn', function(){
			var newPwd = $('#pwd_modi').val()
			if(newPwd == ''){
				alert('새로운 비밀번호를 입력해 주세요.')
				$('#pwd_modi').focus()
			}else{
				
				$.ajax({
					type: 'post',
					url: '${pageContext.request.contextPath}/member/update',
					data: "mem_pwd="+newPwd+"&mem_id="+'${member.mem_id}',
					success: function(data){
						$('#pwdTd').html('<b>수정완료</b> &nbsp;&nbsp;&nbsp; <button class="btn btn-default" id="pwdBtn">수정하기</button>')			
					}
				})
			}
				
			
		})
		
		$('#cashBtn').click(function(){
			
			confirm('환전 하시겠습니까?')
			alert('아직 준비중인 기능 입니다.')
			
		})
		
	})
</script>

</head>
<body>
	
	<div class="container">
		<h2>회원 상세 정보</h2>
		<table class="table table-bordered">
			<tr>
				<td>아이디</td>
				<td>${member.mem_id }</td>
			</tr>
			<tr>
			<td>비밀번호</td>
			<td id="pwdTd"><button class="btn btn-default" id="pwdBtn">수정하기</button></td>
			</tr>
			<tr>
				<td>닉네임</td>
				<td>${member.mem_name }</td>
			</tr>
			<tr>
				<td>이메일</td>
				<td>${member.mem_email }</td>
			</tr>
			<tr>
				<td>환전 가능 포인트</td>
				<td id="cashTd">${member.mem_point_cash} &nbsp;&nbsp;&nbsp;<button class="btn btn-default" id="cashBtn">환전하기</button></td>
			</tr>
			<tr>
				<td>일반 포인트</td>
				<td>${member.mem_point}</td>
			</tr>
			<tr>
				<td>총 포인트</td>
				<td>${member.mem_total_point }</td>
			</tr>
			<tr>
				<td>환전 금액</td>
				<td >${member.mem_re_cash }</td>
			</tr>
			<tr>
				<td>이용권</td>
				<td>${member.mem_ticket}</td>
			</tr>
			<tr>
				<td>이용권 만료일자</td>
				<td>${member.ticket_date}</td>
			</tr>
			
			<tr>
				<td>등록일자</td>
				<td>${member.reg_date }</td>
			</tr>

			
			
		</table>
	</div>
	
</body>
</html>