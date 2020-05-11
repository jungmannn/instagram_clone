package com.mju.insta.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mju.insta.model.Follow;
import com.mju.insta.model.User;
import com.mju.insta.repository.FollowRepository;
import com.mju.insta.repository.UserRepository;
import com.mju.insta.service.MyUserDetail;

@Controller
public class FollowController {
	
	@Autowired
	private UserRepository mUserRepository;
	
	@Autowired
	private FollowRepository mFollowRepository;
	
	@PostMapping("/follow/{id}")
	public @ResponseBody String follow(@AuthenticationPrincipal MyUserDetail userDetail, 
			@PathVariable int id){
		
		User fromUser = userDetail.getUser();
		Optional<User> oToUser = mUserRepository.findById(id);
		User toUser = oToUser.get();
		
		Follow follow = new Follow();
		follow.setFromUser(fromUser);
		follow.setToUser(toUser);
		
		mFollowRepository.save(follow);
		
		return "ok";
	}
	
	@DeleteMapping("/follow/{id}")
	public @ResponseBody List<Follow> unFollow(@AuthenticationPrincipal MyUserDetail userDetail, 
			@PathVariable int id){
		User fromUser = userDetail.getUser();
		Optional<User> oToUser = mUserRepository.findById(id);
		User toUser = oToUser.get();
		
		mFollowRepository.deleteByFromUserIdAndToUserId(fromUser.getId(), toUser.getId());
		
		List<Follow> follows = mFollowRepository.findAll();
		return follows;
	}
}
