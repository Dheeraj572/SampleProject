package com.techmojo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techmojo.entity.Tweet;

public interface ITweetRepository extends JpaRepository<Tweet, Long>{

}
