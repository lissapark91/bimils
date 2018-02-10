package com.bimils.myapp.common.sms.service;

import java.util.List;
import java.util.Map;

import com.bimils.myapp.common.sms.model.SmsMsg;

public interface SmsService {
	
		// 전송 대상 문자 메시지 목록조회
		public List<SmsMsg> getSmsList(Map<String, Object> paramMap) throws Exception;
		
		// 문자 메시지 상태 수정
		public int updateSmsStatus(Map<String, Object> paramMap) throws Exception;

}
