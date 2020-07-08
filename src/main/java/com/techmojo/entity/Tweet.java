package com.techmojo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tweet {
	
	@Id
	@GeneratedValue
	private Long id;
	private String tweet;
	
	public Tweet(String tweet) {
		super();
		this.tweet = tweet;
	}
	
	
}
