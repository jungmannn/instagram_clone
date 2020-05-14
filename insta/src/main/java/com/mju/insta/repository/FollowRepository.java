package com.mju.insta.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mju.insta.model.Follow;

public interface FollowRepository extends JpaRepository<Follow, Integer>{
	
	// unFollow
	@Transactional
	int deleteByFromUserIdAndToUserId(int formUserId, int toUserId);
	
	// follow check 팔로우 유무
	int countByFromUserIdAndToUserId(int formUserId, int toUserId);
	
	// follow List (하얀버튼)
	List<Follow> findByFromUserId(int fromUserId);

	// follower List (맞팔 체크 후 버튼 색깔 결졍)
	List<Follow> findByToUserId(int toUserId);
	
	// 팔로우 카운트
	int countByFromUserId(int fromUserId);

	//팔로워 카운트
	int countByToUserId(int toUserId);
}
