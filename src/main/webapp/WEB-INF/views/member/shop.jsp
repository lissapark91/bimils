<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
	#ticketBox {
		width: 320px;
		margin: 0 auto;
		padding: 5px;
		padding-top: 20px;
	}
	.ticketDiv {
		margin-bottom: 50px;
	}


</style>
<script type="text/javascript">
	$(function(){
		$('#purchaseBtn').click(function(){
			
			var ticket = $(':radio[name="ticketKind"]:checked').val();
			var guide = '';
			
			if(ticket == 'A'){
				guide = "하루"
			}else if(ticket == 'B'){
				guide = "일주일"
			}else if(ticket == 'C'){
				guide = "한달"
			}
			
			confirm(guide + '정액권을 구입하시겠습니까?')
			alert('아직 준비중인 기능입니다.')
			
		})
		
		


	})
</script>

</head>
<body>

<div id="ticketBox">
	<form name="ticket" method="post">
		
		<div id="ticketA" class="radio ticketDiv">
			<div>
			 <label><input type="radio" name="ticketKind" value="A">정액권 A (1000bm)</label>
			</div>
			<div>
				하루동안 자유롭게 게시글을 읽을 수 있습니다.
			</div>
		</div>
		
		<div id="ticketB" class="radio ticketDiv">
			<div>
		 	<label><input type="radio" name="ticketKind" value="B">정액권 B (5000bm)</label>
		 	</div>
		 	<div>
		 		일주일동안 자유롭게 게시글을 읽을 수 있습니다.
		 	</div>
		</div>
		
		<div id="ticketC" class="radio ticketDiv">
			 <div>
			 <label><input type="radio" name="ticketKind" value="C">정액권 C (15000bm)</label>
		 	</div>
		 	<div>
		 		한달동안 자유롭게 게시글을 읽을 수 있습니다.
		 	</div>
		</div>
	
	<div style="margin-top: 20px;">
	<p align="center">
		<button type="button" class="btn btn-primary" id="purchaseBtn">구 입</button>
	</p>
	</div>
	
	</form>
</div>
	
	
	
	
</body>
</html>