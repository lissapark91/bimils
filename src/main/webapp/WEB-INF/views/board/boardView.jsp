<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css" >
<style>
	textarea {
		resize: none;
		width: 80%
	}
	#replyDiv {
		margin-bottom: 10px;	
	}
	.replyBox {
		border-bottom: 1px dashed grey;
		padding: 5px;
	}
	.replyModi, .replyDate{
		float: right;
	}
	.replyWriter {
		float: left;
	}
	.replyBox .replyContent {
    	clear: left;
	}
	.replyContent {
		display: block;
	}
	.reply {
		display: inline-block;
		white-space: pre-line;
	}
	#replyTextArea {
		float: left;
		margin-right: 10px;
		margin-bottom: 10px;
	}
	#bigBtn {
		clear: both;

	}
	.btn-square {
		height: 50px !important;
	}
	.udrSpan {
		margin-right: 20px;
		font-size: 20px;
	}
	.udrSpan:last-child {
		margin-right: 0px;
		font-size: 20px;
	}
	
	.udrBtn {
		height: 40px !important;
		width: 100px !important;
	}
</style>

<script type="text/javascript">

	function fn_list(){
		location.href="${pageContext.request.contextPath}/board/all/${board.bo_type}"
	}
	function fn_del(seqNo){
		location.href="${pageContext.request.contextPath}/board/del/${board.bo_seq_no}"		
	}
	
	function fn_form(seqNo){
		var frm = document.boSeqNoForm
		
		frm.action="${pageContext.request.contextPath}/board/modi/${board.bo_type}"		
		frm.submit()
	}
	function fn_search(replyPage){
		var frm = document.searchForm
		frm.replyPage.value = replyPage;
		frm.action = "${pageContext.request.contextPath}/board/view/4?replyPage="+replyPage
				frm.submit();
	}
	
	$(function(){
		
		
		$('#replyInsertBtn').click(function(){
			var isChecked = 'Y';
			if($('#re_open_yn').prop("checked")){
				isChecked = 'N';
			}
			$.ajax({
				type: 'post',
				url: '${pageContext.request.contextPath}/reply/insert',
				data: "bo_seq_no="+'${board.bo_seq_no}'+"&re_content="+$('#re_content').val()+"&re_open_yn="+isChecked,
				error: function(error){
					console.log(error);
				}
			
			})
			$('#re_content').val("");
			$('#re_open_yn').val("");
			
			location.reload();

		})
		
		function upDownReport(id){
			
			$.ajax({
				type: 'post',
				url: '${pageContext.request.contextPath}/board/'+id+'/${board.bo_seq_no}',
				error: function(error){
					console.log(error);
				},
				success: function(data, status){
//						console.log(status);
//						console.log(JSON.stringify(data));

					var str = "";
					if(id == "up"){
						str = "추천";
					}else if(id == "down"){
						str = "비추";
					
					}
					
					if(data.result == "already"){
						
						alert("이미 추천 or 비추한 비밀입니다.")
					}else{
						alert(str + " 하였습니다.")
					}
					$('#'+id+'Cnt').text(data.cnt)

				}
			
			})
		}
		
		$('#up').on('click', function(){
			upDownReport($(this).attr('id'))
		})
		$('#down').on('click', function(){
			upDownReport($(this).attr('id'))
		})
		
		$('.replyUpdate').click(function(){
			var $this = $(this)
			
			$this.text('')
			
			var replyNo = $this.attr('replyNo')
			var $divContent = $('.'+replyNo+".reply")
			var origin = $divContent.text()

			var checked = '';
			if($this.attr('open_data') == 'N'){
				checked = 'checked'
			}

			var newHtml = '<textarea id="re_content_modi" rows="3" cols="80">'+origin+'</textarea>'
			newHtml += '<button id="replyUpdateBtn" replyNo="'+ replyNo +'" class="btn btn-default">확인</button>'
			newHtml += '<lable for="re_open_yn"><input type="checkbox" name="re_open_yn" id="re_open_yn_modi" '+ checked +'/> 비공개</lable>'
			$divContent.html(newHtml)

		})
		$('div.reply').on('click','#replyUpdateBtn',function(){
			console.log('click')
			var isChecked = 'Y';
			if($('#re_open_yn_modi').prop("checked")){
				isChecked = 'N';
			}
			$.ajax({
				type: 'post',
				url: '${pageContext.request.contextPath}/reply/update',
				data: "re_content="+$('#re_content_modi').val()+"&re_open_yn="+isChecked+"&re_seq_no="+$(this).attr('replyNo'),
				error: function(error){
					console.log(error);
				}
		
			})
			
			location.reload();
			
		})
		
		$('.replyDelete').click(function(){
			var a = confirm("정말로 삭제 하시겠습니까?")
			if(a){
				$.ajax({
					type: 'post',
					url: '${pageContext.request.contextPath}/reply/delete',
					data: "re_seq_no="+$(this).attr('replyNo'),
					error: function(error){
						console.log(error)
					}
				})
				location.reload();
			}else{
				return false;
			}
			
		})
	
		
	})
	
</script>
</head>
<body>
<div class="container">
	<h3>쉿! 비밀이야</h3>
	<form name="boSeqNoForm" method="post">
		<input type="hidden" name="bo_seq_no" value="${board.bo_seq_no}">
	</form>

		<table class="table">
	
			<tr>
				<td  width="15%">제  목</td>
				<td>
					${board.bo_title}
				</td>
			</tr>
		
			<tr>
				<td>작성자</td>
				<td>
					${board.bo_writer_name}
				</td>
			</tr>

		
			<tr>
				<td colspan="2" style="white-space: pre-wrap;">${board.bo_content}</td>
			</tr>
		
		</table>
		<!-- 추천비추신고 -->
		<div id="upDownReport">
		<p align="center">
			<button id="up" class="btn btn-primary udrBtn">UP</button> <span id="upCnt" class="udrSpan">${board.bo_up}</span>
			<button id="down" class="btn btn-basic udrBtn">DOWN</button> <span id="downCnt" class="udrSpan">${board.bo_down}</span>
			<button id="report" class="btn btn-warning udrBtn">REPORT</button> <span id="reportCnt" class="udrSpan">${board.bo_report}</span>
		</p>
		</div>

<!-- 댓글 -->
<hr/>
		<div id="replyDiv">

		<c:forEach var="reply" items="${replyList}">
		<div class="${reply.re_seq_no} replyBox" replyNo="${reply.re_seq_no}">
			<div class="${reply.re_seq_no} replyWriter" style="color: #000080; margin-bottom:2px;">
				${reply.re_writer_name}
			</div>
			<c:if test="${reply.re_writer == LOGIN_USER.mem_id}">
			<div class="${reply.re_seq_no} replyModi">
			<a class="replyUpdate ${reply.re_seq_no}" open_data="${reply.re_open_yn}" replyNo="${reply.re_seq_no}"> <span class="glyphicon glyphicon-erase"></span></a>  <a class="replyDelete" replyNo="${reply.re_seq_no}"> <span class="glyphicon glyphicon-trash"></span></a>
			</div>
			</c:if>
			<div class="${reply.re_seq_no} replyDate" style="margin-right: 10px; color: grey;">
				${reply.reg_date}
			</div>
			
			<!-- 댓글 공개 비공개 처리.. -->
			<div class="replyContent ${reply.re_seq_no}">
			<c:if test="${reply.re_open_yn =='N'}">
			<c:if test="${reply.re_writer == LOGIN_USER.mem_id or board.bo_writer == LOGIN_USER.mem_id}">
			<div class="reply ${reply.re_seq_no}" open_data="${reply.re_open_yn}">${reply.re_content}</div>
			</c:if>
			<c:if test="${reply.re_writer != LOGIN_USER.mem_id and board.bo_writer != LOGIN_USER.mem_id }">
			<div class="reply ${reply.re_seq_no}" open="${reply.re_open_yn}"><c:out value="비공개 댓글 입니다."></c:out></div>
			</c:if>
			</c:if>
			<c:if test="${reply.re_open_yn =='Y'}">
			<div class="reply ${reply.re_seq_no}" open="${reply.re_open_yn}">${reply.re_content}</div>
			</c:if>
			</div>
		</div>
		</c:forEach>
		</div>
		
		<ul class="list-inline" style="width: 100px; margin: 0 auto; height: 50px; magin-top: 10px;">
		${pagingUtil.pageHtml}
		</ul>

		<form name="searchForm">
			<input type="hidden" name="replyPage"/>
		</form>
		<div id="replyTextArea">
		<textarea name="re_content" class="form-control" id="re_content" rows="3" cols="120"></textarea>
		</div>
		<div id="replyBtnGroup">
		<lable for="re_open_yn"><input type="checkbox" name="re_open_yn" id="re_open_yn" /> 비공개</lable>
		<input type="button" value="댓글 등록" class="btn btn-default btn-square" id="replyInsertBtn" />
		</div>
		
		<div id="bigBtn">
		<p align="center">
		<c:if test="${not empty LOGIN_USER and board.bo_writer == LOGIN_USER.mem_id}">
			<input type="button" value="수정" class="btn btn-primary" onclick="fn_form('${board.bo_seq_no}')"/>
			<input type="button" value="삭제" class="btn btn-primary" onclick="fn_del('${board.bo_seq_no}')"/>
		</c:if>
			<input type="button" value="목록" class="btn btn-primary" onclick="fn_list()"/>
		</p>
		</div>

	</div>

</body>
</html>