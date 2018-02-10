<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
	<title>bimils.com에 오신 것을 환영합니다.</title>
	<script type="text/javascript">
		function fn_login(){
			
			var frm = document.loginForm
			
			if(frm.mem_id.value==""){
				alert("아이디를 입력해 주세요.")
				//frm.mem_id.focus()
				return false;
			}
			if(frm.mem_pwd.value==""){
				alert("비밀번호를 입력해 주세요.")
				//frm.mem_pwd.focus()
				return false;
			}
			
			frm.action = "${pageContext.request.contextPath}/login/check";
			frm.submit();
		}	
	
	
	
	</script>
	
</head>
<body>

<div class="homeBoard col-xs-8">
<h3>인 기 글</h3>
	<table class="table table-boardered table-hover">
		<thead>
		<tr><th>제  목</th><th>조회수</th></tr>
		</thead>
		<tbody>
		<!-- not empty boardList -->
		<c:if test="${not empty hotBoardList}">
		<c:forEach var="board" items="${hotBoardList}">
		<c:url var="viewURL" value="board/view/${board.bo_seq_no}">
		</c:url>
		<tr>
			<td><a href="${viewURL}">${board.bo_title } (${board.replyCnt })</a></td>
			<td>${board.bo_hit_cnt }</td>
		</tr>		
		</c:forEach>
		</c:if>
	</tbody>
	</table>
</div>


<c:if test="${empty sessionScope.LOGIN_USER}">
<div class="homeBoard col-xs-4">
	<div class="panel panel-default">
    <div class="panel-body">
    	 <form class="form-horizontal" name="loginForm" method="post">
				<div class="form-group">
					<div class="col-xs-12">
					<input type="text" name="mem_id" value="${empty cookie.REMEBER_ME ? '' : cookie.USER_ID.value}" class="form-control" placeholder="ID">
					</div>
				</div>
				<div class="form-group">
					<div class="col-xs-12">
					<input type="password" name="mem_pwd" value="" class="form-control" placeholder="Password">
					</div>
				</div>
				<div class="form-group">
					<div class="col-xs-12">
					<label for="remember_me"><input type="checkbox" name="remember_me" id="remember_me" value="Y" ${empty cookie.REMEBER_ME ? '' : 'checked'}> 아이디 저장</label>
					</div>
				</div>
				<div class="form-group">
					<div class="col-xs-12">
					<input type="button" value="Login" class="btn btn-warning btn-block" onclick="fn_login()">
					</div>
				</div>
				<p align="center"><a href="${pageContext.request.contextPath}/member/form">가입하기!</a></p>
			</form> 
    </div>
</div>
</div>
</c:if>

</body>
</html>
