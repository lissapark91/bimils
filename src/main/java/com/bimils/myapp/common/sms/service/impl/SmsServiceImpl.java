package com.bimils.myapp.common.sms.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bimils.myapp.common.sms.mapper.SmsMapper;
import com.bimils.myapp.common.sms.model.SmsMsg;
import com.bimils.myapp.common.sms.service.SmsService;

@Service("smsService")
public class SmsServiceImpl implements SmsService{
	
	@Autowired
	private SmsMapper smsMapper;

	@Override
	public List<SmsMsg> getSmsList(Map<String, Object> paramMap) throws Exception {
		return smsMapper.selectSmsList(paramMap);
	}

	@Override
	public int updateSmsStatus(Map<String, Object> paramMap) throws Exception {
		return smsMapper.updateSmsStatus(paramMap);
	}

}
