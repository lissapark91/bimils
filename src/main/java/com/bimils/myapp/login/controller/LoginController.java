package com.bimils.myapp.login.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bimils.myapp.common.util.ConstantUtil;
import com.bimils.myapp.common.util.CookieBox;
import com.bimils.myapp.member.model.Member;
import com.bimils.myapp.member.service.MemberService;

@Controller
public class LoginController {
	
	@Autowired
	MemberService memberService;
	
	@RequestMapping("/login/check")
	public String login(@RequestParam("mem_id") String mem_id,
			@RequestParam("mem_pwd") String mem_pwd, HttpServletResponse response, Model model,
			String remember_me, HttpSession session
			) throws Exception{
		
		String preView = (String) session.getAttribute("preView");
		
		boolean isError = true;
		String message = "로그인 실패. 아이디와 비밀번호를 확인해 주세요.";
		
		Map<String, Object> paramMap = new HashMap<>();
		
		paramMap.put("searchType", "id");
		paramMap.put("searchWord", mem_id);
		
		Member member = memberService.getMember(paramMap);
		if(member != null) {
			if(mem_pwd.equals(member.getMem_pwd())){
				//로그인 성공
				message = member.getMem_name() + "님, 환영합니다.";
				session.setAttribute("LOGIN_USER", member);
				if(ConstantUtil.adminList.contains(member.getMem_id())) {
					session.setAttribute("isAdmin", "true");
				}
				isError = false;
				System.out.println(session.getAttribute("LOGIN_USER"));
				//아이디 저장
				if("Y".equals(remember_me)){
					response.addCookie(CookieBox.createCookie("USER_ID", member.getMem_id(), "/", (60 * 60 * 24 * 30)));
					response.addCookie(CookieBox.createCookie("REMEMBER_ME", "Y", "/", (60 * 60 * 24 * 30)));
				} else {
					response.addCookie(CookieBox.createCookie("USER_ID", member.getMem_id(), "/", 0));
					response.addCookie(CookieBox.createCookie("REMEMBER_ME", "Y", "/", 0));
					
				}
				
			}
		}
		
		session.setAttribute("message", message);
		session.setAttribute("isError", isError);
//		if(preView.equals("http://localhost:8080/bimils/member/form")) {
//			preView = "http://localhost:8080/bimils/";
//		}
		
		
		session.setAttribute("locationURL",preView);

		
//		System.out.println("preView : " + preView);
		
		return "common/message";
	}
	@RequestMapping("/login")
	public String loginForm(HttpServletRequest request, HttpSession session) {
		
		String preView = request.getHeader("Referer");
		session.setAttribute("preView", preView);
		return "login/loginForm";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {	
	
		if(session != null) {
			session.invalidate(); // 로그아웃			
		}
		
		return "redirect:/";
		
	}

}
