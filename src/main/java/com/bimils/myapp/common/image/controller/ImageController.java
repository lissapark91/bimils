package com.bimils.myapp.common.image.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bimils.myapp.common.image.model.Image;
import com.bimils.myapp.common.image.service.ImageService;
import com.bimils.myapp.common.util.ConstantUtil;

@Controller
@RequestMapping("/image")
public class ImageController {
	
	@Autowired
	ImageService imageService;
	
	
	@RequestMapping(value="/upload", method=RequestMethod.POST)
	@ResponseBody
	public String imageUpload(@RequestParam("file") MultipartFile imageFile) {
		
		if(!imageFile.isEmpty()) {
			Image image = null;
			try {
				image = imageService.uploadImage(imageFile);
				
				return image.getFile_seq_no()+"";
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return null;
		
	}
	
	@RequestMapping(value="/{file_seq_no}", method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> imageURL(@PathVariable("file_seq_no") int file_seq_no) {
		
		Image image = null;
		Resource resource = null;
		try {
			
			image = imageService.getImage(file_seq_no);
			
			String path = ConstantUtil.UPLOAD_PATH + "/" + image.getFile_path()
			+"/"+image.getFile_save_name();
			resource = new UrlResource("file:"+path);
			
			HttpHeaders headers = new HttpHeaders();
			String content_disposition = "attachment; filename=\"" + image.getFile_name() + "\"";
			headers.add(HttpHeaders.CONTENT_DISPOSITION, content_disposition);
			headers.setContentType(MediaType.valueOf(image.getFile_content_type()));
			
			return ResponseEntity.ok().headers(headers).body(resource);


		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().build();

		}
		
		
		
	}

}
