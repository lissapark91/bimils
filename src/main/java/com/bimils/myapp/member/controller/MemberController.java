package com.bimils.myapp.member.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bimils.myapp.common.sales.model.Sales;
import com.bimils.myapp.common.sales.service.SalesService;
import com.bimils.myapp.common.util.PagingUtil;
import com.bimils.myapp.member.model.Member;
import com.bimils.myapp.member.service.MemberService;


@Controller
@RequestMapping("/member")
public class MemberController {
	
	@Resource(name="memberService")
	private MemberService memberService;

	@Resource(name="salesService")
	private SalesService salesService;
	
	@RequestMapping("/form")
	public String memberForm() throws Exception {
		return "member/memberForm";
	}

	@RequestMapping("/members")
	public String memberList(String searchType, String searchWord,
			@RequestParam(value="currentPage", defaultValue="1") int currentPage,
			Model model) throws Exception {
		//전체 회원수 (기본값 0)
		int totalCnt = 0;
		
		Map<String, Object> paramMap = new HashMap<>();
		
		if(StringUtils.isNotBlank(searchType) && StringUtils.isNotBlank(searchWord)) {
			paramMap.put("searchType", searchType);
			paramMap.put("searchWord", searchWord);			
		}
		
		totalCnt = memberService.getMemberCnt(paramMap);
		
		PagingUtil pagingUtil = new PagingUtil(currentPage, totalCnt);
		
		paramMap.put("startRow", pagingUtil.getStartRow());
		paramMap.put("endRow", pagingUtil.getEndRow());
		
		List<Member> memberList = memberService.getMemberList(paramMap);
		
		model.addAttribute(memberList);
		model.addAttribute("pagingUtil", pagingUtil);
		
		return "member/memberList";
	}
	
	@RequestMapping("/view/{mem_id}")
	public String memberView(@PathVariable("mem_id") String mem_id, Model model) throws Exception {
		
		Member member = null;
		
		if(StringUtils.isNotBlank(mem_id)) {
			
			Map<String, Object> paramMap = new HashMap<>();
			
			paramMap.put("searchType", "id");
			paramMap.put("searchWord", mem_id);
			
			member = memberService.getMember(paramMap);
		}
		
		model.addAttribute(member);
		
		return "member/memberView";
	}
	
	@RequestMapping("del/{mem_id}")
	public String memberDelete(@PathVariable("mem_id") String mem_id, Model model, HttpSession session) throws Exception {

		
		boolean isError = false;
		String message = "정상적으로 삭제되었습니다.";
		String locationURL = "/member/members";
		String viewPage = "common/message";
		
		
		if(StringUtils.isNotBlank(mem_id)) {
			
			Map<String, Object> paramMap = new HashMap<>();
			
			//로그인 정보로 수정 필요
			paramMap.put("upd_user", "admin_lisa");
			paramMap.put("mem_id", mem_id);
			
			int updCnt = memberService.deleteMember(mem_id);
			
			if(updCnt == 0) {
				isError = true;
				message = "해당 회원을 삭제하지 못하였습니다.";
				locationURL = "/member/view/" + mem_id;
			}
		}
		session.setAttribute("isError",isError);
		session.setAttribute("message",message);
		session.setAttribute("locationURL",locationURL);
		
		return viewPage;
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String memberInsert(Member member, HttpServletRequest request,
			Model model) throws Exception{
		
		int updCnt = memberService.insertMember(member);
		
		//welcome페이지로 수정해야 함
		return "common/welcome";
	}
	
	
	
	
	@RequestMapping("/exists")
	@ResponseBody
	public Map<String,Object> memberExists(@RequestParam("type") String type
			, @RequestParam("info") String info) throws Exception{
		
		Map<String, Object> paramMap = new HashMap<>();
		
		paramMap.put("searchType", type);
		paramMap.put("searchWord", info);
		
		Member member = memberService.getMember(paramMap);
		
		Map<String, Object> resultMap = new HashMap<>();

		if(member != null) {
			resultMap.put("result", "true");
		}else {
			resultMap.put("result", "false");
		}
		
		return resultMap;
	}
	
	@RequestMapping(value="/me/{mem_id}")
	public String myPage(@PathVariable String mem_id, HttpSession session,
			Model model) throws Exception{
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("searchWord", mem_id);
		paramMap.put("searchType", "id");
		Member member = memberService.getMember(paramMap);
		Member loginUser = (Member) session.getAttribute("LOGIN_USER");
		if(member != null) {
			if(loginUser.getMem_id().equals(member.getMem_id())){
				
				model.addAttribute(member);
			}			
		}
		
		
		return "member/myPage";
	}

	@RequestMapping(value="/sales/{mem_id}")
	public String mySalesList(@PathVariable String mem_id, HttpSession session,
			@RequestParam(value="currentPage", defaultValue="1") int currentPage,
			Model model) throws Exception{
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("searchWord", mem_id);
		paramMap.put("searchType", "id");
		Member member = memberService.getMember(paramMap);
		Member loginUser = (Member) session.getAttribute("LOGIN_USER");
		if(member != null) {
			if(loginUser.getMem_id().equals(member.getMem_id())){
				paramMap.clear();
				
				paramMap.put("login_user", member.getMem_id());
				
				int totalCnt = salesService.getSalesCnt(paramMap);
				int pageCnt = 5;
				
				PagingUtil pagingUtil = new PagingUtil(currentPage, totalCnt, 10, pageCnt);
				
				paramMap.put("startRow", pagingUtil.getStartRow());
				paramMap.put("endRow", pagingUtil.getEndRow());
				
				
				List<Sales> salesList = salesService.getSalesList(paramMap);
				
				model.addAttribute(salesList);
				
				return "member/salesList";
				
			}			
		}
		
		
		return "member/";
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public void updatePwd(@RequestParam("mem_pwd") String mem_pwd,
			@RequestParam("mem_id") String mem_id) throws Exception {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("searchWord", mem_id);
		paramMap.put("searchType", "id");
		Member member = memberService.getMember(paramMap);
		
		member.setMem_pwd(mem_pwd);
		
		memberService.updateMemberInfo(member);
		
	}
	
	@RequestMapping("/shop")
	public String goShop() {
		
		return "member/shop";
	}
	
	
	
}
