<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
<title>MemberList</title>
<script type="text/javascript">
	function fn_form(){
		location.href="memberForm"
	}
	function fn_search() {
		var frm = document.searchForm;
		if(frm.searchType.value != "" && frm.searchWord.value == ""){
			alert("검색어를 입력하세요.")
			return false;
		}
		frm.submit();
	}
</script>
</head>
<body>
	<div class="container">
		<h2>회원 목록</h2>
		
		<!-- 검색폼 -->
		<form class="form-inline" name="searchForm" action="members" method="post">
			<div class="form-group">
			<input type="hidden" name="currentPage" value="${param.currentPage }"/>
			<select name="searchType" class="form-control">
				<option value="">전체</option>
				<option value="id" ${param.searchType == 'id' ? 'selected' : '' }>아이디</option>
				<option value="name" ${param.searchType == 'name' ? 'selected' : '' }>이름</option>
				<option value="email" ${param.searchType == 'email' ? 'selected' : '' }>이메일</option>
			</select>
			</div>
			<div class="form-group">
			<input class="form-control" type="text" name="searchWord" value="${param.searchWord }"/>
			</div>
			<div class="form-group">
			<input class="form-control" type="button" value="검색" onclick="fn_search()"/>
			</div>
		</form>
		<!-- //검색폼 -->
		<hr>
		<table class="table table-striped table-bordered">
			<thead>
				<tr>
					<th>아이디</th>
					<th>닉네임</th>
					<th>이메일</th>
					<th>가입 일자</th>
				</tr>
			</thead>
			<tbody>
			<c:if test="${not empty memberList}">
			<c:forEach var="member" items="${memberList }">
				<c:url var="viewURL" value="view/${member.mem_id }">
				</c:url>
				<tr>
					<td>${member.mem_id }</td>
					<td><a href="${viewURL}">${member.mem_name }</a></td>
					<td>${member.mem_email}</td>
					<td>${member.reg_date}</td>
				</tr>
			</c:forEach>
			</c:if>
			<c:if test="${empty memberList}">
				<tr><td colspan="5" align="center">조회된 결과가 없습니다.</td></tr>
			</c:if>
			
			</tbody>
		
		</table>
		<div class="text-center">
		<ul class="pagination" >
		${pagingUtil.pageHtml}
		</ul>
		<!-- 페이지네비게이션 끝 -->
	</div>
	</div>
</body>
</html>