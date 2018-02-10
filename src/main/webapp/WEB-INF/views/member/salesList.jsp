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
	function fn_search(currentPage) {
		var frm = document.searchForm;
		if(frm.searchType.value != "" && frm.searchWord.value == ""){
			alert("검색어를 입력하세요.")
			return false;
		}
		frm.submit();
	}
	$(function(){
		$('#salesDel').click(function(){
			confirm('선택한 구매 목록을 삭제하시겠습니까?')
			alert('아직 준비중인 기능입니다.')
		})
	})
</script>
</head>
<body>
	<div class="container">
		<h2>구매 목록</h2>
		
		
		<hr>
		<table class="table table-striped table-bordered">
			<thead>
				<tr>
					<th>check</th>
					<th>게시글 번호</th>
					<th>구매일자</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="sale" items="${salesList}">
			<c:url var="viewURL" value="/board/view/${sale.bo_seq_no}"></c:url>
				<tr>
					<td><input type="checkbox"></td>
					<td><a href="${viewURL}" >${sale.bo_title}</a></td>
					<td>${sale.pu_sales_date}</td>
				</tr>
			</c:forEach>
			<c:if test="${empty salesList}">
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
	<div>
		<p align="right">
		<button type="button" class="btn btn-warning" id="salesDel">삭제</button>
		</p>
	</div>
	</div>
</body>
</html>