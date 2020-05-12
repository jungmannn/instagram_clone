package com.mju.insta.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mju.insta.model.Follow;
import com.mju.insta.model.Image;
import com.mju.insta.model.Likes;
import com.mju.insta.model.User;
import com.mju.insta.repository.ImageRepository;
import com.mju.insta.repository.UserRepository;

@Controller
public class TestController {
	
	@Autowired // 의존성 주입
	private UserRepository mUserRepository;
	
	@Autowired
	private ImageRepository mImageRepository;
	
	@GetMapping("/test/image/feed")
	public @ResponseBody List<Image> testImageFeed(@PageableDefault(size=2, sort="id", direction = Sort.Direction.DESC) Pageable pageable){
		int userId = 2;
		
		Page<Image> images = mImageRepository.findImage(userId, pageable);
		return images.getContent();
		
	}
	
	@GetMapping("/test/user/{id}")
	public @ResponseBody User testUser(@PathVariable int id) {
		Optional<User> oUser = mUserRepository.findById(id);
		User user = oUser.get();
		return user;
	}
	
	@GetMapping("/test/home")
	public String testHome() {
		return "home";
	}
	
	@GetMapping("/test/user")
	public @ResponseBody User getUser() {
		User user = new User();
		user.setId(1);
		user.setUsername("cos");
		user.setName("홍길동");
		user.setEmail("cos@nate.com");
		user.setProfileImage("my.jpg");
		
		Image img1 = new Image();
		img1.setId(1);
		img1.setCaption("음식 사진");
		img1.setLocation("food.jpg");
		img1.setLocation("부산 서면");
		img1.setUser(user);
		
		Image img2 = new Image();
		img2.setId(2);
		img2.setCaption("장난감 사진");
		img2.setLocation("game.jpg");
		img2.setLocation("서울 강남");
		img2.setUser(user);
		
		List<Image> images = new ArrayList<>();
		images.add(img1);
		images.add(img2);
		user.setImages(images);
		
		return user;
	}
	
	@GetMapping("/test/image")
	public @ResponseBody Image getImage() {
		User user = new User();
		user.setId(1);
		user.setUsername("cos");
		user.setName("홍길동");
		user.setEmail("cos@nate.com");
		user.setProfileImage("my.jpg");
		
		Image img1 = new Image();
		img1.setId(1);
		img1.setCaption("음식 사진");
		img1.setLocation("food.jpg");
		img1.setLocation("부산 서면");
		img1.setUser(user);
		
		return img1;
	}
	
	@GetMapping("/test/images")
	public @ResponseBody List<Image> getImages(){
		User user = new User();
		user.setId(1);
		user.setUsername("cos");
		user.setName("홍길동");
		user.setEmail("cos@nate.com");
		user.setProfileImage("my.jpg");
		
		Image img1 = new Image();
		img1.setId(1);
		img1.setCaption("음식 사진");
		img1.setLocation("food.jpg");
		img1.setLocation("부산 서면");
		img1.setUser(user);
		
		Image img2 = new Image();
		img2.setId(2);
		img2.setCaption("장난감 사진");
		img2.setLocation("game.jpg");
		img2.setLocation("서울 강남");
		img2.setUser(user);
		
		List<Image> images = new ArrayList<>();
		images.add(img1);
		images.add(img2);
		user.setImages(images);
		
		return images;
	}
	
	@GetMapping("/test/likes")
	public @ResponseBody Likes getLike() {
		User user = new User();
		user.setId(1);
		user.setUsername("cos");
		user.setName("홍길동");
		user.setEmail("cos@nate.com");
		user.setProfileImage("my.jpg");
		
		Image img1 = new Image();
		img1.setId(1);
		img1.setCaption("음식 사진");
		img1.setLocation("food.jpg");
		img1.setLocation("부산 서면");
		img1.setUser(user);
		
		Likes like = new Likes();
		like.setId(1);
		like.setUser(user);
		like.setImage(img1);
		
		return like;
	}
	
	@GetMapping("/test/follows")
	public @ResponseBody List<Follow> getFollows() {
		User user1 = new User();
		user1.setId(1);
		user1.setUsername("cos");
		user1.setName("홍길동");
		user1.setEmail("cos@nate.com");
		user1.setProfileImage("my.jpg");
		
		User user2 = new User();
		user2.setId(2);
		user2.setUsername("ssar");
		user2.setName("장동건");
		user2.setEmail("ssar@nate.com");
		user2.setProfileImage("you.jpg");

		User user3 = new User();
		user3.setId(3);
		user3.setUsername("task11");
		user3.setName("김정현");
		user3.setEmail("task11@nate.com");
		user3.setProfileImage("me.jpg");
		
		Follow follow1 = new Follow();
		follow1.setId(1);
		follow1.setFromUser(user1);
		follow1.setToUser(user2);

		Follow follow2 = new Follow();
		follow2.setId(2);
		follow2.setFromUser(user1);
		follow2.setToUser(user3);
		
		Follow follow3 = new Follow();
		follow3.setId(3);
		follow3.setFromUser(user2);
		follow3.setToUser(user1);
		
		List<Follow> follows = new ArrayList<Follow>();
		follows.add(follow1);
		follows.add(follow2);
		follows.add(follow3);
		
		return follows;
	}
	@GetMapping("/test/login")
	public String testLogin() {
		return "auth/login";
	}
	@GetMapping("/test/join")
	public String testJoin() {
		return "auth/join";
	}
	@GetMapping("/test/profile")
	public String testProfile() {
		return "user/profile";
	}
	@GetMapping("/test/profileEdit")
	public String testProfileEdit() {
		return "user/profile_edit";
	}
	@GetMapping("/test/feed")
	public String testFeed() {
		return "image/feed";
	}
	@GetMapping("/test/imageUpload")
	public String testImageUpload() {
		return "image/image_upload";
	}
	@GetMapping("/test/explore")
	public String testExplore() {
		return "image/explore";
	}
	
}