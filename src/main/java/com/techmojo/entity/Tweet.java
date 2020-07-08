package com.techmojo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Tweet {
	
	@Id
	@GeneratedValue
	private Long id;
	private String tweet;
}
