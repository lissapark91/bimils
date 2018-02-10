package com.bimils.myapp.board.sevice;

import java.util.List;
import java.util.Map;


import com.bimils.myapp.board.model.Board;
import com.bimils.myapp.board.model.BoardEffect;

public interface BoardService {

	public int getBoardCnt(Map<String, Object> paramMap) throws Exception;

	public List<Board> getBoardList(Map<String, Object> paramMap) throws Exception;

	public List<Board> getHotBoardList() throws Exception;
	
	public Board getBoard(int bo_seq_no) throws Exception;

	public int insertBoard(Board board) throws Exception;
	
	public int updateBoardContent(Board board) throws Exception;

	public int updateBoardHitCnt(int bo_seq_no) throws Exception;
	
	public int updateBoardUp(int bo_seq_no, Map<String, Object> paramMap) throws Exception;
	
	public int updateBoardDown(int bo_seq_no, Map<String, Object> paramMap) throws Exception;

	public int deleteBoard(Map<String, Object> paramMap) throws Exception;
	
	//boEffect
	public int insertBoardEffect(BoardEffect boardEffect) throws Exception;
	
	public int deleteBoardEffect(Map<String, Object> paramMap) throws Exception;

	
	
	
	

}
