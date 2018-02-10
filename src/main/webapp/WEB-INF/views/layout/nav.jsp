<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript">
$(function(){
	$(".login").click(function(){		
		location.href="${pageContext.request.contextPath}/login";
	});
	 
	
});

</script>

	<div class="container-fluid">
 
    
    <ul class="nav navbar-nav">
      <li><a href="${pageContext.request.contextPath}/board/best">인기글 게시판</a></li>
      <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">게시판<span class="caret"></span></a>
        <ul class="dropdown-menu">
          <li><a href="${pageContext.request.contextPath}/board/all/00">내비밀</a></li>
          <li><a href="${pageContext.request.contextPath}/board/all/01">연예인</a></li>
          <li><a href="${pageContext.request.contextPath}/board/all/02">정치</a></li>
          <li><a href="${pageContext.request.contextPath}/board/all/03">학교</a></li>
          <li><a href="${pageContext.request.contextPath}/board/all/04">회사</a></li>
          <li><a href="${pageContext.request.contextPath}/board/all/05">자연</a></li>
          <li><a href="${pageContext.request.contextPath}/board/all/06">호러</a></li>
          <li><a href="${pageContext.request.contextPath}/board/all/07">요리</a></li>
        </ul>
      
      </li>
      <li><a href="${pageContext.request.contextPath}/member/shop">상점</a></li>
       <c:if test="${!sessionScope.isAdmin}">
      <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">마이페이지<span class="caret"></span></a>
      <ul class="dropdown-menu">
      <li><a href="${pageContext.request.contextPath}/member/me/${LOGIN_USER.mem_id}">나의 정보</a></li>
      <li><a href="${pageContext.request.contextPath}/member/sales/${LOGIN_USER.mem_id}">구매 내역</a></li>
      </ul>
	</c:if>
      <c:if test="${sessionScope.isAdmin}">
      <li><a href="${pageContext.request.contextPath}/member/members">회원 관리</a></li>		
	</c:if>
    </ul>
    
    <c:if test="${empty sessionScope.LOGIN_USER}">
	    <ul class="nav navbar-nav navbar-right">
			<li><a class="login"><span class="glyphicon glyphicon-log-in"></span>
			Login</a></li>
		</ul>
	</c:if>
    <c:if test="${not empty sessionScope.LOGIN_USER}">
	    <%-- <div style="display: inline; float: right; padding-top: 15px; padding-bottom: 15px;">
	    	${LOGIN_USER.mem_name} (${LOGIN_USER.mem_id}) 회원님.
	    	<a href="${pageContext.request.contextPath}/login/logout">로그아웃</a>
	    </div> --%>
	    <ul class="nav navbar-nav navbar-right">	    	
	    	<li><a href="${pageContext.request.contextPath}/member/view/${LOGIN_USER.mem_id}">${LOGIN_USER.mem_name} 회원님</a></li>
			<li><a href="${pageContext.request.contextPath}/logout" class="logout"><span class="glyphicon glyphicon-log-out"></span>
			Logout</a></li>
		</ul>
	    
    </c:if>
    
  </div>
