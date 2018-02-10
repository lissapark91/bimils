package com.bimils.myapp.web.info;

import javax.servlet.http.HttpSession;

import com.bimils.myapp.member.model.Member;

public class SessionInfo {
	
	
	public boolean loginUser(HttpSession session) {
		
		Member member = (Member) session.getAttribute("LOGIN_USER");
		
		if(member == null) {
			return false;			
		}else {
			return true;
		}
		
	}
	

	
	
}
