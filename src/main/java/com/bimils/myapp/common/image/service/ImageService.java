package com.bimils.myapp.common.image.service;

import org.springframework.web.multipart.MultipartFile;

import com.bimils.myapp.common.image.model.Image;

public interface ImageService {
	
	public Image uploadImage(MultipartFile imageFile) throws Exception;
	
	public Image getImage(int file_seq_no) throws Exception;
	
	public int updateImage(int file_seq_no) throws Exception;
	


}
