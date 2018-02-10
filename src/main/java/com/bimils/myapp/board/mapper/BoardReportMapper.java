package com.bimils.myapp.board.mapper;

import java.util.List;
import java.util.Map;

import com.bimils.myapp.board.model.BoardReport;

public interface BoardReportMapper {
	
	public int selectBoardReportCnt(Map<String, Object> paramMap) throws Exception;

	public List<BoardReport> selectBoardReportList(Map<String, Object> paramMap) throws Exception;
	
	public BoardReport selectBoardReport(int report_seq_no) throws Exception;
	
	public int insertBoardReport(BoardReport boardReport) throws Exception;
	
	public int updateBoardReportResult(int report_seq_no) throws Exception;
	

}
