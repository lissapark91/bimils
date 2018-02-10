package com.bimils.myapp.common.scheduler;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.bimils.myapp.common.sms.model.SmsMsg;
import com.bimils.myapp.common.sms.service.SmsService;

public class SampleTask {
	
	@Autowired
	SmsService smsService;
	
	public void execute(){
		System.out.println("[" + new Date() + "] Sample Task is running... ");
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		
		try {
			paramMap.put("sms_status", "1");
			List<SmsMsg> smsList = smsService.getSmsList(paramMap);
			
			for(SmsMsg smsMsg : smsList){
				
				System.out.printf(">>> 문자 메시지를 전송했습니다. [%s : %s : %s]\n", smsMsg.getSms_phone_no(), smsMsg.getSms_title(), smsMsg.getSms_content());
				
				paramMap.clear();
				paramMap.put("sms_seq_no", smsMsg.getSms_seq_no());
				paramMap.put("sms_status", "2");
				
				// 문자 메시지 데이터 수정
				smsService.updateSmsStatus(paramMap);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
