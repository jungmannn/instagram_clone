package com.mju.insta.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.mju.insta.model.User;
import com.mju.insta.repository.FollowRepository;
import com.mju.insta.repository.UserRepository;
import com.mju.insta.service.MyUserDetail;

@Controller
public class UserController {
	
	
	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Value("${file.path}")
	private String fileRealPath;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Autowired
	private UserRepository mUserRepository;
	
	@Autowired
	private FollowRepository mFollowRepository;
	
	@GetMapping("/auth/login")
	public String authLogin() {
		return "auth/login";
	}
	

	@GetMapping("/auth/join")
	public String authJoin() {
		return "auth/join";
	}
	
	@PostMapping("/auth/joinProc")
	public String authJoinProc(User user) {
		String rawPassword = user.getPassword();
		String encPassword = encoder.encode(rawPassword);
		user.setPassword(encPassword);
		
		log.info("rawPassword : " + rawPassword);
		log.info("encPassword : " + encPassword);
		
		mUserRepository.save(user);
		return "redirect:/auth/login";
	}
	
	@GetMapping("/user/{id}")
	public String profile(@PathVariable int id, @AuthenticationPrincipal MyUserDetail userDetail, Model model) {
		// id를 통해서 해당 유저를 검색(이미지 + 유저 정보)
		/*
		 * 1.imageCount
		 * 2.followerCount
		 * 3.followingCount
		 * 4.User object(Image(likeCount) 컬렉션)
		 * 5.followCheck 팔로우 유무 ( 1 팔로우중, 1아니면 언팔로우 )
		 */
		
		// 4번 임시(수정해야함)
		Optional<User> oUser = mUserRepository.findById(id);
		User user = oUser.get();
		model.addAttribute("user", user);
		
		
		// 5번
		User principal = userDetail.getUser();
		
		int followCheck = mFollowRepository.countByFromUserIdAndToUserId(principal.getId(), id);
		log.info("followCheck : " + followCheck);
		model.addAttribute("followCheck", followCheck);
		
		return "user/profile";
	}
	
	@GetMapping("user/edit/{id}")
	public String userEdit(@PathVariable int id) {
		// 해당 ID로 Select 해서 수정
		// findByUserInfo() 사용 (만들어야함)
		
		return "user/profile_edit";
	}
	
	@PostMapping("/user/profileUpload")
	public String userProfileUpload(
			@RequestParam("profileImage") MultipartFile file,
			@AuthenticationPrincipal MyUserDetail userDetail
			) throws IOException
	{
		User principal = userDetail.getUser();
		
		UUID uuid = UUID.randomUUID();
		String uuidFilename = uuid + "_" + file.getOriginalFilename();
		Path filePath = Paths.get(fileRealPath + uuidFilename);
		Files.write(filePath, file.getBytes());
		
		principal.setProfileImage(uuidFilename);
		
		mUserRepository.save(principal);
		
		return "redirect:/user/"+principal.getId();
	}
	
}
