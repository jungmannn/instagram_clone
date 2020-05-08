package com.mju.insta.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mju.insta.model.Image;

public interface ImageRepository extends JpaRepository<Image, Integer>{
	
}
