package com.mju.insta.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mju.insta.model.Follow;

public interface FollowRepository extends JpaRepository<Follow, Integer>{
	
	// unFollow
	@Transactional
	void deleteByFromUserIdAndToUserId(int formUserId, int toUserId);
}
