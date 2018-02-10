package com.bimils.myapp.common.image.service.impl;

import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bimils.myapp.common.image.mapper.ImageMapper;
import com.bimils.myapp.common.image.model.Image;
import com.bimils.myapp.common.image.service.ImageService;
import com.bimils.myapp.common.util.ConstantUtil;




@Service("imageService")
public class ImageServiceImpl implements ImageService{
	
	@Autowired
	ImageMapper imageMapper;
	
	private SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
	private DecimalFormat deciFmt = new DecimalFormat();
	
	
	private String getFancySize(long size) {
		
		String fancy = "";
		
		
		if(size < 1024) {
			fancy = deciFmt.format(size) + "bytes";
		}else if(size < 1024 * 1024) {
			fancy = deciFmt.format(size / 1024.0) + "KB";
		}else {
			fancy = deciFmt.format(size / (1024 * 1024.0)) + "MB";
		}
		
		return fancy;
	}


	@Override
	public Image uploadImage(MultipartFile imageFile) throws Exception {
		
		Image image = new Image();
		
		image.setImageFile(imageFile);
		
		image.setFile_content_type(imageFile.getContentType());
		image.setFile_size(imageFile.getSize());
		image.setFile_fancy_size(getFancySize(imageFile.getSize()));
		image.setFile_name(imageFile.getOriginalFilename());
		image.setFile_path("bimils/" + fmt.format(new Date()));
		
		//writer
		image.setReg_user("admin_lisa");
		
		image.setFile_save_name(UUID.randomUUID().toString() + "_" + imageFile.getOriginalFilename());
		
		//파일저장
		File file = new File(ConstantUtil.UPLOAD_PATH + "/" + image.getFile_path() + "/" + image.getFile_save_name());
		
		FileUtils.copyInputStreamToFile(imageFile.getInputStream(), file);
		
		//DB에 파일 정보 저장
		
		imageMapper.insertImage(image);

		return image;
	}


	@Override
	public Image getImage(int file_seq_no) throws Exception {
		
		Image image = imageMapper.selectImage(file_seq_no);
		
		return image;
	}
	
	@Override
	public int updateImage(int file_seq_no) throws Exception {
		
		return imageMapper.updateImage(file_seq_no);
	}

	
	
}
