package com.bimils.myapp.common.image.model;

import org.springframework.web.multipart.MultipartFile;

public class Image {
	private int	file_seq_no;             
	private String  file_path;          
	private String  file_name;          
	private String  file_save_name;     
	private String  file_content_type;  
	private long  file_size;          
	private String  file_fancy_size;    
	private String  reg_user;           
	private String  reg_date;
	
	private MultipartFile imageFile;
	
	
	public int getFile_seq_no() {
		return file_seq_no;
	}
	public void setFile_seq_no(int file_seq_no) {
		this.file_seq_no = file_seq_no;
	}
	public String getFile_path() {
		return file_path;
	}
	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}
	public String getFile_name() {
		return file_name;
	}
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	public String getFile_save_name() {
		return file_save_name;
	}
	public void setFile_save_name(String file_save_name) {
		this.file_save_name = file_save_name;
	}
	public String getFile_content_type() {
		return file_content_type;
	}
	public void setFile_content_type(String file_content_type) {
		this.file_content_type = file_content_type;
	}
	public long getFile_size() {
		return file_size;
	}
	public void setFile_size(long file_size) {
		this.file_size = file_size;
	}
	public String getFile_fancy_size() {
		return file_fancy_size;
	}
	public void setFile_fancy_size(String file_fancy_size) {
		this.file_fancy_size = file_fancy_size;
	}
	public String getReg_user() {
		return reg_user;
	}
	public void setReg_user(String reg_user) {
		this.reg_user = reg_user;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	public MultipartFile getImageFile() {
		return imageFile;
	}
	public void setImageFile(MultipartFile imageFile) {
		this.imageFile = imageFile;
	}
	
	
     

}
