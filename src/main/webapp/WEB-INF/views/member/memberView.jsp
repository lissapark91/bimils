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
				<td>닉네임</td>
				<td>${member.mem_name }</td>
			</tr>
			<tr>
				<td>이메일</td>
				<td>${member.mem_email }</td>
			</tr>
			<tr>
				<td>환전 가능 포인트</td>
				<td>${member.mem_point_cash}</td>
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
				<td>${member.mem_re_cash }</td>
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
			<tr>
				<td colspan="3">
					<input type="button" class="btn btn-default" value="삭제" onclick="fn_del('${member.mem_id}')">
					<input type="button" class="btn btn-default" value="목록" onclick="fn_list()">
				</td>
			</tr>
			
			
		</table>
	</div>
	
</body>
</html>