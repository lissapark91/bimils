package com.bimils.myapp.reply.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Reply {
	
	private int re_seq_no;
	private String re_writer;
	private String re_writer_name;
	private int ref_seq_no;
	private String re_content;
	private String re_open_yn;
	private String re_del_yn;
	private String reg_date;
	private String upd_date;
	
	
	public int getRe_seq_no() {
		return re_seq_no;
	}
	public void setRe_seq_no(int re_seq_no) {
		this.re_seq_no = re_seq_no;
	}
	public String getRe_writer() {
		return re_writer;
	}
	public void setRe_writer(String re_writer) {
		this.re_writer = re_writer;
	}
	public String getRe_content() {
		return re_content;
	}
	public void setRe_content(String re_content) {
		this.re_content = re_content;
	}
	public String getRe_open_yn() {
		return re_open_yn;
	}
	public void setRe_open_yn(String re_open_yn) {
		this.re_open_yn = re_open_yn;
	}
	public String getRe_del_yn() {
		return re_del_yn;
	}
	public void setRe_del_yn(String re_del_yn) {
		this.re_del_yn = re_del_yn;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	public String getUpd_date() {
		return upd_date;
	}
	public void setUpd_date(String upd_date) {
		this.upd_date = upd_date;
	}
	public int getRef_seq_no() {
		return ref_seq_no;
	}
	public void setRef_seq_no(int ref_seq_no) {
		this.ref_seq_no = ref_seq_no;
	}
	public String getRe_writer_name() {
		return re_writer_name;
	}
	public void setRe_writer_name(String re_writer_name) {
		this.re_writer_name = re_writer_name;
	}

}
