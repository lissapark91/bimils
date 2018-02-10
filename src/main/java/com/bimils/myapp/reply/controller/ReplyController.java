package com.bimils.myapp.reply.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bimils.myapp.common.util.PagingUtil;
import com.bimils.myapp.member.model.Member;
import com.bimils.myapp.reply.model.Reply;
import com.bimils.myapp.reply.service.ReplyService;

@Controller
@RequestMapping(value="/reply")
public class ReplyController {
	
	@Autowired
	ReplyService replyService;
	
	@RequestMapping("/list/{bo_seq_no}")
	@ResponseBody
	public void replyList(@PathVariable int bo_seq_no, Model model,
			@RequestParam(value="currentPage", defaultValue="1") int currentPage) throws Exception {
		
		Map<String, Object> paramMap = new HashMap<>();
		
		paramMap.put("bo_seq_no", bo_seq_no);
		
		
		//paging
		
		int totalCnt = replyService.getReplyCnt(bo_seq_no);
		int pageCnt = 5;
		
		PagingUtil pagingUtil = new PagingUtil(currentPage, totalCnt, 25, pageCnt);
		
		paramMap.put("startRow", pagingUtil.getStartRow());
		paramMap.put("endRow", pagingUtil.getEndRow());
		
		List<Reply> replyList = replyService.getReplyList(paramMap);
		
		model.addAttribute(replyList);
		
		
	}
	
	@RequestMapping("/insert")
	@ResponseBody
	public void replyInsert( 
			@RequestParam int bo_seq_no,
			@RequestParam String re_content,
			@RequestParam String re_open_yn,
			HttpSession session,
			Model model
			) throws Exception {
		
		Reply reply = new Reply();
		Member member = (Member) session.getAttribute("LOGIN_USER");
		reply.setRe_content(re_content);
		reply.setRe_open_yn(re_open_yn);
		reply.setRe_writer(member.getMem_id());
		reply.setRef_seq_no(bo_seq_no);
		
		replyService.replyInsert(reply);
		
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public void replyDelete(@RequestParam int re_seq_no) throws Exception {
		replyService.replyDelete(re_seq_no);
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public void replyUpdate(
			@RequestParam int re_seq_no,
			@RequestParam String re_content,
			@RequestParam String re_open_yn,
			HttpSession session,
			Model model) throws Exception {
		
		Reply reply = new Reply();
		reply.setRe_content(re_content);
		reply.setRe_open_yn(re_open_yn);
		reply.setRe_seq_no(re_seq_no);
		
		replyService.replyUpdate(reply); 
	}
	
	
	
}
