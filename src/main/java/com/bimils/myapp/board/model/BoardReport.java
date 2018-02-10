package com.bimils.myapp.board.model;

public class BoardReport {
	
	private int bo_seq_no;
	private String report_writer;
	private String report_content;
	
	
	public int getBo_seq_no() {
		return bo_seq_no;
	}
	public void setBo_seq_no(int bo_seq_no) {
		this.bo_seq_no = bo_seq_no;
	}
	public String getReport_writer() {
		return report_writer;
	}
	public void setReport_writer(String report_writer) {
		this.report_writer = report_writer;
	}
	public String getReport_content() {
		return report_content;
	}
	public void setReport_content(String report_content) {
		this.report_content = report_content;
	}

	
	
}
