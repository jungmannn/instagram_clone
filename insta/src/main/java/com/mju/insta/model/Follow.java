package com.mju.insta.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@Entity
public class Follow {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id; // 시퀀스
	
	//중간테이블 생성됨.
	//fromUser가 toUser를 Following함
	//toUser를 fromUser가 Follower함
	
	@ManyToOne
	@JoinColumn(name = "fromUserId")
	@JsonIgnoreProperties({"images"})
	private User fromUser;
	
	@ManyToOne
	@JoinColumn(name = "toUserId")
	@JsonIgnoreProperties({"images"})
	private User toUser;
	
	@Transient
	private boolean fromUsermatpal;
	
	@Transient
	private boolean principalMatpal;
	
	@CreationTimestamp // 자동으로 현재 시간이 세팅
	private Timestamp createDate;
	@CreationTimestamp
	private Timestamp updateDate;
	
}
