package com.techmojo.service;

import java.util.List;
import com.techmojo.util.TweetRequest;
import com.techmojo.util.TweetResponse;

public interface ITweetService {

	void saveTweet(TweetRequest tweetRequest);
	List<TweetResponse> getTweets();
	List<String> getHashTags();
}
