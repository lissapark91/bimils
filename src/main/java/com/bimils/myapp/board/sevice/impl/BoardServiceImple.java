package com.bimils.myapp.board.sevice.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bimils.myapp.board.mapper.BoardEffectMapper;
import com.bimils.myapp.board.mapper.BoardMapper;
import com.bimils.myapp.board.model.Board;
import com.bimils.myapp.board.model.BoardEffect;
import com.bimils.myapp.board.sevice.BoardService;
import com.bimils.myapp.reply.service.ReplyService;


@Service(value="boardService")
public class BoardServiceImple implements BoardService {
	
	@Autowired
	BoardMapper boardMapper;
	@Autowired
	BoardEffectMapper boardEffectMapper;
	@Autowired
	ReplyService replyService;
	

	@Override
	public int getBoardCnt(Map<String, Object> paramMap) throws Exception {
		
		return boardMapper.selectBoardCnt(paramMap);
	}

	@Override
	public List<Board> getBoardList(Map<String, Object> paramMap) throws Exception {
		
		List<Board> boardList = boardMapper.selectBoardList(paramMap);
		
		for(Board board : boardList) {
			//제목효과
			List<BoardEffect> boEffectList = 
					boardEffectMapper.selectBoardEffectList(board.getBo_seq_no());
			board.setBoEffectList(boEffectList);
			
			//댓글 개수
			int replyCnt = replyService.getReplyCnt(board.getBo_seq_no());
			board.setReplyCnt(replyCnt);
		}
		
		return boardList;
	}

	@Override
	public List<Board> getHotBoardList() throws Exception {
		List<Board> boardList = boardMapper.selectHotBoardList();
		
		for(Board board : boardList) {
			List<BoardEffect> boEffectList = 
					boardEffectMapper.selectBoardEffectList(board.getBo_seq_no());
			board.setBoEffectList(boEffectList);
		}
		
		
		return boardList;
	}

	@Override
	public Board getBoard(int bo_seq_no) throws Exception {
		
		Board board = boardMapper.selectBoard(bo_seq_no);
		//boardEffect
		board.setBoEffectList(boardEffectMapper.selectBoardEffectList(bo_seq_no));

		
		return board;
	}

	@Override
	public int insertBoard(Board board) throws Exception {
		int updCnt = boardMapper.insertBoard(board); 
		
		if(board.getBoEffectList().size() !=0 ) {
			for(BoardEffect boEffect : board.getBoEffectList()) {
				boEffect.setBo_seq_no(board.getBo_seq_no());
				boardEffectMapper.insertBoardEffect(boEffect);
			}			
		}
		
		
		return updCnt;
	}

	@Override
	public int updateBoardContent(Board board) throws Exception {
		
		
		int updCnt = boardMapper.updateBoardContent(board);
	
		
		return updCnt;
	}

	@Override
	public int updateBoardHitCnt(int bo_seq_no) throws Exception {
		return boardMapper.updateBoardHitCnt(bo_seq_no);
	}

	@Override
	public int updateBoardUp(int bo_seq_no, Map<String, Object> paramMap) throws Exception {
		int saveCnt = boardMapper.selectUpdownSave(paramMap);
		System.out.println(saveCnt);
		if(saveCnt == 0) {
			
			boardMapper.updateUpdownSave(paramMap);
			return boardMapper.updateBoardUp(bo_seq_no);
			
		}else {
			return -1;			
		}
		
	}

	@Override
	public int updateBoardDown(int bo_seq_no, Map<String, Object> paramMap) throws Exception {
		
		if(boardMapper.selectUpdownSave(paramMap) == 0) {			
			boardMapper.updateUpdownSave(paramMap);
			return boardMapper.updateBoardDown(bo_seq_no);
			
		}else {
			return -1;			
		}
	}

	@Override
	public int deleteBoard(Map<String, Object> paramMap) throws Exception {
		
		return boardMapper.deleteBoard(paramMap);
	}
	
	@Override
	public int deleteBoardEffect(Map<String, Object> paramMap) throws Exception {
		return boardEffectMapper.deleteBoardEffect(paramMap);
	}
	
	@Override
	public int insertBoardEffect(BoardEffect boardEffect) throws Exception {
		return boardEffectMapper.insertBoardEffect(boardEffect);
	}
	


}
