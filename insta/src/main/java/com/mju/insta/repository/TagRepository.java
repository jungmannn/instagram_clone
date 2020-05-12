package com.mju.insta.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mju.insta.model.Tag;

public interface TagRepository extends JpaRepository<Tag, Integer> {

}
