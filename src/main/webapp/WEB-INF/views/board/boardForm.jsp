<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- include summernote css/js-->
<style type="text/css">
	.table-boarderless td, .table-boarderless tr {
		boarder: none;
	}
</style>
<link href="${pageContext.request.contextPath}/summernote/summernote.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/summernote/summernote.js"></script>

<title>${mode == 'form' ? '글 쓰기' : '글 수정' }</title>
<script type="text/javascript">
	
	function fn_list(){
		location.href="${pageContext.request.contextPath}/board/all/${bo_type}"
	}
	
	function fn_save(type){
		if(!validate()){
			return false;
		}
		
		var frm = document.boardForm;
		if(type == 'I'){
			frm.action="${pageContext.request.contextPath}/board/insert"
			
		}else if(type == 'U'){
			frm.action="${pageContext.request.contextPath}/board/update"
			
		}

		frm.submit();
	}
	function fn_cancel(){
		var a = confirm("작성한 내용이 모두 사라집니다. 취소하시겠습니까?")
		var frm = document.boardForm;
		if(a){
			location.href="${pageContext.request.contextPath}/board/all/${bo_type}"
		}else{
			return false;
		}
	
	}
	
	function validate(){
		var frm = document.boardForm;
		if(frm.bo_title.value==""){
			alert("제목을 입력하세요.");
			frm.bo_title.focus();
			return false;
		}
		
		if(frm.bo_type.value==""){
			alert("게시판을 선택하세요.");
			console.log(frm.bo_type.value)
			frm.bo_type.focus();
			return false;
		}

		if(frm.bo_writer_open.value==""){
			alert("작성자 공개여부를 선택하세요.");
			frm.bo_type.focus();
			return false;
		}

		
		if(frm.bo_content.value==""){
			alert("내용을 입력하세요.");
			frm.bo_title.focus();
			return false;
		}
		
		return true;
		
	}
	
	$(function(){
		
		$('[name=bo_title]').focus()
		
		
		var $frm = $('#boardForm');
		
		$('#summernote').summernote({
			  height: 300,                 // set editor height
			  minHeight: null,             // set minimum height of editor
			  maxHeight: null,             // set maximum height of editor
			  lang: 'ko-KR'
			  , callbacks: {
				  onImageUpload: function(files, editor, welEditable) {
			              sendFile(files[0], this);

			      }

			  }

			});
	
	})
	
	function sendFile(file, el) {
      var form_data = new FormData();
      form_data.append('file', file);
      $.ajax({
        data: form_data,
        type: "POST",
        url: '${pageContext.request.contextPath}/image/upload',
        cache: false,
        contentType: false,
        enctype: 'multipart/form-data',
        processData: false,
        success: function(file_seq_no) {
          $(el).summernote('insertImage',"${pageContext.request.contextPath}/image/" + file_seq_no);// board/
//           $('#imageBoard > ul').append('<li><img src="'+url+'" width="480" height="auto"/></li>');
        }
      });
    }




</script>
</head>
<body>
	<div class="container">
	<h3>당신의 비밀을 알려주세요.</h3>
	<form name="boardForm" id="boardForm" method="post" enctype="multipart/form-data">
	<input type="hidden" name="bo_seq_no" value="${board.bo_seq_no}"/>


		<table class="table table-boarderless">
	
			<tr>
<!-- 				<td>카테고리</td> -->
				<td>
		<select name="bo_type" class="form-control">
		<option value="" ${bo_type == "" ? 'selected' : ''} >게시판 선택</option>
		<option value="00" ${bo_type == "00" ? 'selected' : ''} >내비밀</option>
		<option value="01" ${bo_type == "01" ? 'selected' : ''} >연   예</option>
		<option value="02" ${bo_type == "02" ? 'selected' : ''} >정   치</option>
		<option value="03" ${bo_type == "03" ? 'selected' : ''} >학   교</option>
		<option value="04" ${bo_type == "04" ? 'selected' : ''} >회   사</option>
		<option value="05" ${bo_type == "05" ? 'selected' : ''} >자   연</option>
		<option value="06" ${bo_type == "06" ? 'selected' : ''} >호   러</option>
		<option value="07" ${bo_type == "07" ? 'selected' : ''} >요   리</option>
		</select>
				</td>
<!-- 				<td  width="15%">제  목</td> -->
				<td colspan="3">
				<div class="col-xs-12">
				
					<input type="text" name="bo_title" value="<c:out value='${board.bo_title}'/>" class="form-control" placeholder="제목을 입력하세요..."/>
			
				</div>
				</td>
			</tr>
			<tr>
			<td>작성자 공개여부</td>
			<td>
			<label for="w_open_y" class="radio-inline">
			<input type="radio" name="bo_writer_open" value="Y" id="w_open_y" ${board.bo_writer_open == "Y" ? 'checked' : '' }>
			공개</label>
			<label for="w_open_n" class="radio-inline">
			<input type="radio" name="bo_writer_open" value="N" id="w_open_n" ${board.bo_writer_open == "N" ? 'checked' : '' }>
			비공개</label>
			
			
			</td>
			
			<!-- effect 구매 -->
			
			<td>
				제목 효과 (개당 10bm)
			</td>
			<c:set var="bold" value="false" />
			<c:set var="italic" value="false" />
			<c:set var="c_red" value="false" />
			<c:set var="b_yellow" value="false" />
			<c:forEach var="item" items="${board.boEffectList}">
			  <c:if test="${item.effect_class eq 'bold'}">
			    <c:set var="bold" value="true" />
			  </c:if>
			  <c:if test="${item.effect_class eq 'italic'}">
			    <c:set var="italic" value="true" />
			  </c:if>
			  <c:if test="${item.effect_class eq 'c_red'}">
			    <c:set var="c_red" value="true" />
			  </c:if>
			  <c:if test="${item.effect_class eq 'b_yellow'}">
			    <c:set var="b_yellow" value="true" />
			  </c:if>
			</c:forEach>
			<td>
				<label class="checkbox-inline"><input name="boEffectClassList" type="checkbox" value="bold" ${bold ? 'checked' : ''} }><span style="font-weight: bold;">굵게</span></label>
				<label class="checkbox-inline" ><input name="boEffectClassList" type="checkbox" value="italic" ${italic ? 'checked' : ''}><span style="font-style: italic;">기울이기</span></label>
				<label class="checkbox-inline" ><input name="boEffectClassList" type="checkbox" value="c_red" ${c_red ? 'checked' : ''}><span style="color: red;">글자색 - 빨강</span></label>
				<label class="checkbox-inline" ><input name="boEffectClassList" type="checkbox" value="b_yellow" ${b_yellow ? 'checked' : ''}><span style="background-color: yellow;">배경색 - 노랑</span></label>
			</td>
			
			</tr>

			<tr>
				<td colspan="4">
				<textarea id="summernote" name="bo_content" class="form-control" >${board.bo_content}</textarea>
				</td>
			</tr>
		
		</table>
		
		<p align="center">
			<c:if test="${mode eq 'form'}">
			<input type="button" value="등록" id="savebutton" class="btn btn-primary" onclick="fn_save('I')"/>
			</c:if>
			<c:if test="${mode eq 'modi'}">
			<input type="button" value="수정" class="btn btn-primary" onclick="fn_save('U')"/>
			</c:if>

			<input type="button" value="취소" class="btn btn-primary" onclick="fn_cancel()"/>
			<input type="button" value="목록" class="btn btn-primary" onclick="fn_list()"/>
		</p>
		
	</form>
	</div>
</body>
</html>