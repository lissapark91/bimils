package com.bimils.myapp.board.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.bimils.myapp.board.mapper.BoardMapper;
import com.bimils.myapp.board.model.Board;
import com.bimils.myapp.board.model.BoardEffect;
import com.bimils.myapp.board.sevice.BoardService;
import com.bimils.myapp.common.image.service.ImageService;
import com.bimils.myapp.common.sales.mapper.SalesMapper;
import com.bimils.myapp.common.sales.model.Sales;
import com.bimils.myapp.common.sales.service.SalesService;
import com.bimils.myapp.common.util.ConstantUtil;
import com.bimils.myapp.common.util.PagingUtil;
import com.bimils.myapp.member.model.Member;
import com.bimils.myapp.member.service.MemberService;
import com.bimils.myapp.reply.model.Reply;
import com.bimils.myapp.reply.service.ReplyService;


@Controller
@RequestMapping(value="/board")
public class BoardController {
	
	@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="잘못된 접근 입니다.")
	public class UrlNotFoundException extends RuntimeException { }
	
	@Autowired
	BoardService boardService;
	@Autowired
	ImageService imageService;
	@Autowired
	MemberService memberService;
	@Autowired
	SalesService salesService;
	@Autowired
	ReplyService replyService;
	
	//bo_type별 게시판, search기능, 페이징기능*(현재 페이지, 사이즈) 
	@RequestMapping("/all/{bo_type}")
	public String getBoardList(@PathVariable String bo_type, 
			@RequestParam(value="searchWord", defaultValue="", required=false) String searchWord, 
			@RequestParam(value="searchType", defaultValue="", required=false) String searchType,
			@RequestParam(value="currentPage", defaultValue="1") int currentPage,
			@RequestParam(value="pageSize", defaultValue="25") int pageSize,
			Model model) throws Exception {
		
		//검색기능
		Map<String, Object> paramMap = new HashMap<>();
		
		if(ConstantUtil.boTypeList.contains(bo_type)) {
			paramMap.put("bo_type", bo_type);
			model.addAttribute("bo_type_name", ConstantUtil.boNameList.get(ConstantUtil.boTypeList.indexOf(bo_type)));
		} else {
			throw new UrlNotFoundException();

		}
		
		if(StringUtils.isNotBlank(searchType) && StringUtils.isNotBlank(searchWord)) {
			//검색어
			paramMap.put("searchWord", searchWord);
			//검색타입
			paramMap.put("searchType", searchType);			
		}
		
		//페이징처리
		int totalCnt = 0;//전체 게시글 수
		int pageCount = 5; //기본값..
		
		totalCnt = boardService.getBoardCnt(paramMap);
		
		PagingUtil pagingUtil = new PagingUtil(currentPage, totalCnt, pageSize, pageCount);
		
		paramMap.put("startRow", pagingUtil.getStartRow());
		paramMap.put("endRow", pagingUtil.getEndRow());
		
		List<Board> boardList = boardService.getBoardList(paramMap);
		
		
		model.addAttribute(boardList);
		model.addAttribute(pagingUtil);
		model.addAttribute(bo_type);
		
		return "board/boardList";
	}
	//bo_type별 게시판, search기능, 페이징기능*(현재 페이지, 사이즈) 
	@RequestMapping("/best")
	public String getHotBoardList(
			@RequestParam(value="searchWord", defaultValue="", required=false) String searchWord, 
			@RequestParam(value="searchType", defaultValue="", required=false) String searchType,
			@RequestParam(value="currentPage", defaultValue="1") int currentPage,
			@RequestParam(value="pageSize", defaultValue="25") int pageSize,
			Model model) throws Exception {
		
		//검색기능
		Map<String, Object> paramMap = new HashMap<>();
		
		model.addAttribute("bo_type_name", "인기글");
		
		if(StringUtils.isNotBlank(searchType) && StringUtils.isNotBlank(searchWord)) {
			//검색어
			paramMap.put("searchWord", searchWord);
			//검색타입
			paramMap.put("searchType", searchType);			
		}
		
		paramMap.put("isHotboard", "true");
		
		//페이징처리
		int totalCnt = 0;//전체 게시글 수
		int pageCount = 5; //기본값..
		
		totalCnt = boardService.getBoardCnt(paramMap);
		
		PagingUtil pagingUtil = new PagingUtil(currentPage, totalCnt, pageSize, pageCount);
		
		paramMap.put("startRow", pagingUtil.getStartRow());
		paramMap.put("endRow", pagingUtil.getEndRow());
		
		List<Board> boardList = boardService.getBoardList(paramMap);
		
		model.addAttribute(boardList);
		model.addAttribute(pagingUtil);
		
		return "board/boardList";
	}

	@RequestMapping(value="/{mode}/{bo_type}")
	public String boardForm(@PathVariable String mode,
			@PathVariable String bo_type,
			@RequestParam(value="bo_seq_no", required=false, defaultValue="0") int bo_seq_no,
			Model model,
			HttpSession session ) throws Exception {
		//로그인 기능!!!!

		
		if(ConstantUtil.boTypeList.contains(bo_type)) {
			model.addAttribute(mode);
			model.addAttribute(bo_type);
			
			if("form".equals(mode)) {
				//새글, 게시판 코드 맞추기.
				return "board/boardForm";
				
			}else if("modi".equals(mode)) {
				//수정 
				if(bo_seq_no != 0) {
					
					Member member = (Member) session.getAttribute("LOGIN_USER");
					
					Board board = boardService.getBoard(bo_seq_no);
					
					if(member.getMem_id().equals(board.getBo_writer())) {
						model.addAttribute(board);
						return "board/boardForm";										
					}else {
						
						throw new UrlNotFoundException(); 
					}
					
				} else {
					throw new UrlNotFoundException(); 
				}
				
			}else {
				throw new UrlNotFoundException(); 
			}
			
		} else {
			throw new UrlNotFoundException();

		}
		
		
		
	}
	
	@RequestMapping(value="/insert")
	public String boardInsert(@RequestParam String bo_content,
			@RequestParam String bo_type,
			@RequestParam String bo_title,
			@RequestParam String bo_writer_open,
			@RequestParam List<String> boEffectClassList,
			HttpSession session,
			Model model
			) throws Exception{
		
		boolean isError = true;
		String message = "게시글 등록에 실패하였습니다.";
		String locationURL = "error/error500";
		
		//login여부
		Member member = (Member) session.getAttribute("LOGIN_USER");
		
		if(member == null) {
			locationURL = "error/error404";
		}else {
			
			//img 태그 src 추출 정규표현식
			Pattern pattern = Pattern.compile("<img[^>]*src=[\"']?([^>\"']+)[\"']?[^>]*>"); 
	        Matcher matcher = pattern.matcher(bo_content);
	
	        while(matcher.find()){
	        	//이미지 실제 등록된 것만 체크
	            int file_seq_no = Integer.parseInt(matcher.group(1).substring(matcher.group(1).lastIndexOf("/")+1));
	            imageService.updateImage(file_seq_no);
	        }
	        
	        Board board = new Board();
	        board.setBo_content(bo_content);
	        board.setBo_title(bo_title);
	        board.setBo_type(bo_type);
	        board.setBo_writer(member.getMem_id());
	        board.setBo_writer_open(bo_writer_open);
	        List<BoardEffect> boEffectList = new ArrayList<>();
	        for(String effect : boEffectClassList) {
	        	BoardEffect boardEffect = new BoardEffect();
	        	boardEffect.setEffect_class(effect);
	        	boEffectList.add(boardEffect);
	        }
	        board.setBoEffectList(boEffectList);
	        //이펙트 개수만큼 포인트 차감
	        int point = 50 - 10 * boEffectClassList.size();
	        
	        //insert
	        int updCnt = boardService.insertBoard(board);
	        //point지급
	        member.setMem_point_cash(member.getMem_point_cash()+point);
	        int updPoint = memberService.updateMemberPoint(member);
	        
	        if(updCnt != 0 && updPoint != 0) {
	        	isError = false;
	        	message = "게시글을 등록하였습니다." + point + " bm이 지급됩니다.";
	        	locationURL = "/board/all/"+bo_type;
	        }
	        

		}
		
		session.setAttribute("message",message);
		session.setAttribute("locationURL",locationURL);
		session.setAttribute("isError",isError);
		
		return "common/message";
	}
	
	@RequestMapping("/view/{bo_seq_no}")
	public String boardView(@PathVariable int bo_seq_no, Model model, HttpSession session,
			@RequestParam(value="replyPage", defaultValue="1") int replyPage) throws Exception{
		
		Member member = (Member) session.getAttribute("LOGIN_USER");

		Board board = boardService.getBoard(bo_seq_no);
		
		Map<String, Object> paramMap = new HashMap<>();
		
		paramMap.put("login_user", member.getMem_id());
		paramMap.put("bo_seq_no", bo_seq_no);
		
		List<Sales> salesList = salesService.getSalesList(paramMap);
		List<Integer> salesBoSeqNo = new ArrayList<>();
		for(Sales sale : salesList) {
			salesBoSeqNo.add(sale.getBo_seq_no());
		}
		
		//reply		
		
		//paging
		
		int totalCnt = replyService.getReplyCnt(bo_seq_no);
		int pageCnt = 5;
		
		PagingUtil pagingUtil = new PagingUtil(replyPage, totalCnt, 10, pageCnt);
		
		paramMap.put("startRow", pagingUtil.getStartRow());
		paramMap.put("endRow", pagingUtil.getEndRow());
		
		List<Reply> replyList = replyService.getReplyList(paramMap);
		
		model.addAttribute(replyList);
		model.addAttribute(pagingUtil);
		
		boolean isError = true;
		String message = "권한이 없습니다.";
		String locationURL = "board/boardList";
		
		if(member.getMem_id().equals(board.getBo_writer())) {
			model.addAttribute("board", board);
			return "board/boardView";
			
		} else {
			//티켓 정보가 있을 ㄸㅐ -> 티켓 기간이면 볼 수 있다.
			if(member.getTicket_date() != null) {
				//티켓 만료일
				SimpleDateFormat frm = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				Date exDate = frm.parse(member.getTicket_date());
				
				//현재 시각
				Date now = new Date();
		
				//변수1.compareTo(변수2)가 0보다 큰 경우는 변수1이 변수2보다 이후의 날짜
				if(exDate.compareTo(now) >= 0) {
					model.addAttribute("board", board);
					boardService.updateBoardHitCnt(bo_seq_no);
					
					//글쓴이 포인트 증가
					Map<String, Object> writerMap = new HashMap<>();
					writerMap.put("searchWord", board.getBo_writer());
					writerMap.put("searchType", "id");
					Member writer = memberService.getMember(writerMap);
					writer.setMem_point_cash(writer.getMem_point_cash()+10);
					memberService.updateMemberPoint(writer);
					
					return "board/boardView";
				}
			//구매목록에 해당 게시글이 있고 구매일이 하루 지나기 전이면 바로 볼 수 있음.	
			//티켓 정보가 없을 때 포인트를 확인해서 차감한다.(1. 환전불가포인트에서 2. 환전가능포인트)
			}else if(salesBoSeqNo.contains(bo_seq_no)){
				model.addAttribute("board", board);
				return "board/boardView";		
			}else {
		
				//총 포인트가 게시글 금액보다 크거나 같을 때
				int price = board.getBo_price();
				int point = member.getMem_point();
				int cash_point = member.getMem_point_cash();
				if(member.getMem_total_point() >= board.getBo_price()) {
					//환전 불가포인트
					if(member.getMem_point() >= board.getBo_price()) {
						member.setMem_point(point-price);
						memberService.updateMemberPoint(member);
					}else {
						//환전 가능 포인트
						member.setMem_point(0);
						price -= point;
						member.setMem_point_cash(cash_point - price);
						memberService.updateMemberPoint(member);
					}
					
					//hit cnt 증가
					boardService.updateBoardHitCnt(bo_seq_no);
					
					
					//구매 목록에 추가
					paramMap.clear();
					paramMap.put("cust_id", member.getMem_id());
					paramMap.put("bo_seq_no", bo_seq_no);
					salesService.salesInsert(paramMap);
					
					model.addAttribute("board", board);
					
					//글쓴이 포인트 증가
					Map<String, Object> writerMap = new HashMap<>();
					writerMap.put("searchWord", board.getBo_writer());
					writerMap.put("searchType", "id");
					Member writer = memberService.getMember(writerMap);
					writer.setMem_point_cash(writer.getMem_point_cash()+10);
					memberService.updateMemberPoint(writer);
					
					return "board/boardView";
				}
			}
			
			
		}
		
		session.setAttribute("message",message);
		session.setAttribute("locationURL",locationURL);
		session.setAttribute("isError",isError);
		
		return "common/message";
	}
	
	@RequestMapping("/up/{bo_seq_no}")
	@ResponseBody
	public Map<String, String> boardUP(@PathVariable int bo_seq_no,
			HttpSession session) throws Exception {
		
		Member member =  (Member) session.getAttribute("LOGIN_USER");
		
		Map<String, Object> paramMap = new HashMap<>();
		Map<String, String> resultMap = new HashMap<>();
		
		paramMap.put("save_id", member.getMem_id());
		paramMap.put("bo_seq_no", bo_seq_no);
		
		int updCnt = boardService.updateBoardUp(bo_seq_no, paramMap);
		if(updCnt == -1) {
			resultMap.put("result", "already");
		}else {
			resultMap.put("result", "ok");			
		}
		int cnt = boardService.getBoard(bo_seq_no).getBo_up();
		resultMap.put("cnt", cnt+"");
		
		return resultMap;
	}
	
	@RequestMapping("/down/{bo_seq_no}")
	@ResponseBody
	public Map<String, String> boardDOWN(@PathVariable int bo_seq_no,
			HttpSession session) throws Exception {
		
		Member member =  (Member) session.getAttribute("LOGIN_USER");
		
		Map<String, Object> paramMap = new HashMap<>();
		Map<String, String> resultMap = new HashMap<>();
		
		paramMap.put("save_id", member.getMem_id());
		paramMap.put("bo_seq_no", bo_seq_no);
		
		int updCnt = boardService.updateBoardDown(bo_seq_no, paramMap);
		if(updCnt == -1) {
			resultMap.put("result", "already");
		}else {
			resultMap.put("result", "ok");			
		}
		int cnt = boardService.getBoard(bo_seq_no).getBo_down();
		resultMap.put("cnt", cnt+"");
		
		return resultMap;
	}







	
	

}
