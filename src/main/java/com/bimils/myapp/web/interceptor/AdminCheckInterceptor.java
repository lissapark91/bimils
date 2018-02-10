package com.bimils.myapp.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.bimils.myapp.common.util.ConstantUtil;
import com.bimils.myapp.member.model.Member;

public class AdminCheckInterceptor extends HandlerInterceptorAdapter{
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession(true); //default : 있으면 그대로 주고 , 없으면 생성해준다. true : 없으면 null
		
		Member member = (Member) session.getAttribute("LOGIN_USER");
		
		for(String admin : ConstantUtil.adminList) {
			
			if(admin.equals(member.getMem_id())) {
				return true;
			}
			
		}
		
		response.sendError(HttpServletResponse.SC_FORBIDDEN); // 403 접근금지
		return false;
	}
	
	
	
}
