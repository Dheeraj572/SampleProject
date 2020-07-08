package com.techmojo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.techmojo.service.ITweetService;
import com.techmojo.util.TweetRequest;
import com.techmojo.util.TweetResponse;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("tweets")
@CrossOrigin
@Log4j2
public class TweetController {
	
	@Autowired
	private ITweetService iTweetService;

	@ApiOperation(value="Save tweet")
	@ApiResponses(value= {@ApiResponse(code=201,message="Tweet saved")})
	@ResponseStatus(value = HttpStatus.CREATED)
	@PostMapping
	public void saveTweet(@RequestBody TweetRequest tweetRequest) {
		
		log.info("Persisting the tweet");
		
		iTweetService.saveTweet(tweetRequest);		
		
		log.info("Tweet persisted");
		
	}
	
	@ApiOperation(value="Get Tweets")
	@ApiResponses(value= {@ApiResponse(code=200,message="Tweets Retrieved",response = TweetResponse.class, responseContainer = "List"),
			@ApiResponse(code=204,message="No Content")})
	@GetMapping
	public ResponseEntity<?> getTweets(){
		
		log.info("Retrieving tweets");
		
		List<TweetResponse> tweets = iTweetService.getTweets();
		Optional<List<TweetResponse>> optionalTweetList = Optional.ofNullable(tweets);
		
		if(optionalTweetList.isPresent() && optionalTweetList.get().isEmpty()) {
			
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
		log.info("Retrieved tweets");
	
		return new ResponseEntity<>(tweets, HttpStatus.OK);
	}
	
	@ApiOperation(value="Get Hashtags")
	@ApiResponses(value= {@ApiResponse(code=200,message="Hashtags Retrieved",response = String.class, responseContainer = "List"),
			@ApiResponse(code=204,message="No Content")})
	@GetMapping("hashtags")
	public ResponseEntity<?> getHashTags(){
		
		log.info("Retrieving hashtags");
		
		List<String> hashTags = iTweetService.getHashTags();
		
		log.info("Retrieved hashtags");
		
		return new ResponseEntity<>(hashTags, HttpStatus.OK);
	}
}
