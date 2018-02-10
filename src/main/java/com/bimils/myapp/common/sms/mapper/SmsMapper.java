package com.bimils.myapp.common.sms.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.bimils.myapp.common.sms.model.SmsMsg;

@Mapper
public interface SmsMapper {
	
	// 전송 대상 문자 메시지 목록조회
	public List<SmsMsg> selectSmsList(Map<String, Object> paramMap) throws Exception;
	
	// 전송 대상 문자 등록
	public int insertSms(SmsMsg smsMsg) throws Exception;
	
	// 문자 메시지 상태 수정
	public int updateSmsStatus(Map<String, Object> paramMap) throws Exception;

}
