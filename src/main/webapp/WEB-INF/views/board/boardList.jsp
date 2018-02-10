<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css" >
<title>* ** * 게 시 판 * ** *</title>

<style>
	
	.footer{
		width: 940px;
		margin: auto;
	}
	.bold {
		font-weight: bold;
	}
	.italic {
		font-style: italic;
	}
	.c_red {
		color: red;
	}
	.b_yellow{
		background-color: yellow;
	}

</style>
<script type="text/javascript">
	
		
	function fn_search(currentPage){
		var frm = document.searchForm
		frm.currentPage.value = currentPage;
		if(frm.searchType.value != "" && frm.searchWord.value == ""){
			alert('검색어를 입력하세요.')
			return false;
		}
		frm.action = "${pageContext.request.contextPath}/board/all/${bo_type}"
		frm.submit();
	}
	
	function fn_new(){
		location.href="${pageContext.request.contextPath}/board/form/${bo_type}"
	}
	
	$(document).ready(function(){
		$('.titleClick').on('click', function(){
			if('${LOGIN_USER.mem_id}' != '' ){
				var writer = $(this).attr('writer')
				if('${LOGIN_USER.mem_id}' == writer){
										
				}else{
					var a = confirm("게시글을 읽습니다.\n해당 게시글을 구매하셨다면 24시간 동안 무료로 다시 볼 수 있습니다.\n정액권이 없으시면 게시글 금액만큼 포인트(bm)가 차감 됩니다")
					if(a){
						return true;
					}else{
						return false;
					}
					
				}
				
			}else{
				var b = confirm("로그인해 주세요.\n로그인 페이지로 이동하시겠습니까?")
				if(b){
					location.href="${pageContext.request.contextPath}/login"
				}else{
					return false;
				}
			}
			
		})
		
		
	});

</script>
</head>
<body>

<div class="container">
<div class="header">
<h3>${bo_type_name} 게시판</h3>
<c:if test="${empty LOGIN_USER }">
<span  style="float: right;"><a href="${pageContext.request.contextPath}/login/loginForm">로그인</a></span>
<span  style="float: right;"> &nbsp;/&nbsp; </span>
</c:if>
<c:if test="${not empty LOGIN_USER }">
<span  style="float: right;"><a href="${pageContext.request.contextPath}/login/logout">로그아웃</a></span>
<span  style="float: right;"> &nbsp;/&nbsp; </span>
</c:if>
<span style="float: right;"><a href="${pageContext.request.contextPath}/">Home </a></span>


</div>
	<table class="table table-boardered table-hover">
		<thead>
		<tr><th>번호</th><th>제  목</th><th>작성자</th><th>추천수</th><th>가격 </th><th>조회수</th><th>작성일자</th></tr>
		</thead>
		<tbody>
		<!-- not empty boardList -->
		<c:if test="${not empty boardList}">
		<c:forEach var="board" items="${boardList}" varStatus="status">
		<c:url var="viewURL" value="/board/view/${board.bo_seq_no} ">
		</c:url>
		<tr>
			<td>${status.index + pagingUtil.startRow}</td>
			<td><a href="${viewURL}" class="titleClick 
			<c:forEach var="boEffect" items="${board.boEffectList}">
				${boEffect.effect_class} 
			</c:forEach>
			" writer="${board.bo_writer}">${board.bo_title } (${board.replyCnt})</a></td>
			
			<c:if test="${board.bo_writer_open == 'N'}">
				<td writer="${board.bo_writer}">비공개</td>				
			</c:if>

			<c:if test="${board.bo_writer_open == 'Y' }">
				<td writer="${board.bo_writer}">${board.bo_writer_name}</td>
			</c:if>
			<td>${board.bo_up_cnt }</td>
			<td>${board.bo_price }</td>
			<td>${board.bo_hit_cnt }</td>
			<td>${board.reg_date }</td>
		</tr>		
		</c:forEach>
		</c:if>
		<!-- //not empty boardList -->
		
		<!-- empty boardList -->
		<c:if test="${empty boardList}">
		<tr><td colspan="5" align="center">게시글이 존재하지 않습니다.</td></tr>
		</c:if>
		<!-- //empty boardList -->
		</tbody>
	</table>
	<!-- 페이지 네비게이션 -->
	<div class="text-center">
	<ul class="pagination" >
		${pagingUtil.pageHtml}
	

	</ul>
	
	<!-- 페이지네비게이션 끝 -->
	</div>

	<form class="form-inline" name="searchForm" method="get">
	<div class="form-group">
	<input type="hidden" name="currentPage" value="${param.currentPage }"/>
	<select name="searchType" class="form-control">
		<option value="">전체</option>
		<option value="01" ${param.searchType == "01"? 'selected' : '' } >제목</option>
	</select>
	</div>
	<div class="form-group">
	<input class="form-control" type="text" size="25" name="searchWord" placeholder="검색어를 입력해 주세요." value="${param.searchType == '' ? '' : param.searchWord}"/>
	</div>
	<div class="form-group">
	<input class="form-control" type="button" value="검색" onclick="fn_search(1)"/>
	</div>

</form>

	<c:if test="${bo_type_name != '인기글'}">
<div class="footer" align="right">

<input class="btn-default" type="button" value="글쓰기" onclick="fn_new()"  style="WIDTH: 100px"/>
</div>
</c:if>

<!-- container끝 -->
</div>
</body>
</html>