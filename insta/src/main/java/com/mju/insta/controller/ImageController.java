package com.mju.insta.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.mju.insta.model.Image;
import com.mju.insta.model.Tag;
import com.mju.insta.model.User;
import com.mju.insta.repository.ImageRepository;
import com.mju.insta.repository.TagRepository;
import com.mju.insta.service.MyUserDetail;
import com.mju.insta.util.Utils;

@Controller
public class ImageController {
	
	private static final Logger log = LoggerFactory.getLogger(ImageController.class);
	
	@Value("${file.path}")
	private String fileRealPath;
	
	@Autowired
	private ImageRepository mImageRepository;
	
	@Autowired
	private TagRepository mTagRepository;
	
	@GetMapping({"/", "/image/feed"})
	public String imageFeed(@AuthenticationPrincipal MyUserDetail userDetail) {
		log.info("username : "+userDetail.getUsername());
		return "image/feed";
	}
	
	@GetMapping("/image/upload")
	public String imageUpload() {
		return "image/image_upload";
	}
	
	@PostMapping("/image/uploadProc")
	public String imageUploadProc(
			@AuthenticationPrincipal MyUserDetail userDetail,
			@RequestParam("file") MultipartFile file,
			@RequestParam("caption") String caption,
			@RequestParam("location") String location,
			@RequestParam("tags") String tags
			) {
		
		UUID uuid = UUID.randomUUID();
		String uuidFilename = uuid + "_" + file.getOriginalFilename();
		
		Path filePath = Paths.get(fileRealPath+uuidFilename);
		
		try {
			Files.write(filePath, file.getBytes()); // 하드디스크 기록
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		User principal = userDetail.getUser();
		
		Image image = new Image();
		image.setCaption(caption);
		image.setLocation(location);
		image.setUser(principal);
		image.setPostImage(uuidFilename);
		
		//<img src="/upload/파일명"/>
		
		mImageRepository.save(image);
		
		//Tag 객체생성 집어넣기
		List<String> tagList = Utils.tagParser(tags);
		
		
		for(String tag : tagList) {
			Tag t = new Tag();
			t.setName(tag);
			t.setImage(image);
			mTagRepository.save(t);
			image.getTags().add(t);
		}

		return "redirect:/";
	}
	
	
	
}
