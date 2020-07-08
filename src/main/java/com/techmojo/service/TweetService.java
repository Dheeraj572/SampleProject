package com.techmojo.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techmojo.entity.Tweet;
import com.techmojo.repository.ITweetRepository;
import com.techmojo.util.TweetRequest;
import com.techmojo.util.TweetResponse;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class TweetService implements ITweetService{
	
	@Autowired
	private ITweetRepository iTweetRepository;
	@Autowired
	private ObjectMapper objectMapper;

	@Override
	public void saveTweet(TweetRequest tweetRequest) {
	
		log.info("Converting TweetRequest to Tweet entity");
		
		Tweet tweet = objectMapper.convertValue(tweetRequest, Tweet.class);
		
		iTweetRepository.save(tweet);
		
		log.info("Converted TweetRequest to Tweet entity");
		
	}

	@Override
	public List<TweetResponse> getTweets() {
		
		List<Tweet> tweetList = iTweetRepository.findAll();
		
		log.info("Converting list of Tweet to list of TweetResponse");
		
		List<TweetResponse> tweetResponseList = tweetList.stream().map(mapper -> objectMapper.convertValue(mapper, TweetResponse.class)).collect(Collectors.toList());
		
		log.info("Converted list of Tweet to list of TweetResponse");
		
		return tweetResponseList;
	}

	@Override
	public List<String> getHashTags() {
		
		List<TweetResponse> tweets = this.getTweets();
		List<String> hashTagTopList = new ArrayList<>();
		
		log.info("Retrieving hashtags from tweets");
		
		List<String> hashTagList = tweets.stream().map(mapper -> {
			String tweet = mapper.getTweet();
			int index = tweet.indexOf("#");
			String hashTagString = tweet.substring(index);
			
			if(hashTagString.contains(" ")) {
				return hashTagString.substring(hashTagString.indexOf("#"), hashTagString.indexOf(" "));
			}
			else {
				return hashTagString;
			}
			
		}).collect(Collectors.toList());
		
		
		log.info("Getting count of each hashtag");
		
		Map<String, Integer> hashTagCount = new HashMap<>();

		for(String s: hashTagList) {
			Integer value = hashTagCount.get(s);
			if(value ==  null) {
				hashTagCount.put(s, 1);
			}
			else {
				value++;
				hashTagCount.put(s, value);
			}
		}
		
		Set<Entry<String, Integer>> entrySet = hashTagCount.entrySet();
		
		Map<String, Integer> hashTagSorted = entrySet
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
		
		
		for(Map.Entry<String, Integer> entry : hashTagSorted.entrySet()) {
			if(entry.getValue() > 0) {
				hashTagTopList.add(entry.getKey().toString());
			}
			if(hashTagTopList.size() == 10) {
				break;
			}
		}
		log.info("Returning top 10 hashtags");
		return hashTagTopList;
	}

}
