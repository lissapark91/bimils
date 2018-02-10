package com.bimils.myapp.common.image.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.bimils.myapp.common.image.model.Image;

@Mapper
public interface ImageMapper {
	
	public Image selectImage(int file_seq_no) throws Exception;
	
	public int insertImage(Image image) throws Exception;
	
	public int updateImage(int file_seq_no) throws Exception;
	
}
