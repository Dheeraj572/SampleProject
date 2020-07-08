package com.techmojo.sample;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import com.techmojo.entity.Tweet;
import com.techmojo.repository.ITweetRepository;

@Component
public class SampleData {

	    @Autowired
	    private ITweetRepository iTweetRepository;

	    @EventListener
	    public void appReady(ApplicationReadyEvent event) {
	    	List<Tweet> tweetList = new ArrayList<>();
	    	List<String> sampleList = new ArrayList<>();
	    	sampleList.add("java");
	    	sampleList.add("java");
	    	sampleList.add("java");
	    	sampleList.add("java");
	    	sampleList.add("python");
	    	sampleList.add("python");
	    	sampleList.add("python");
	    	sampleList.add("C");
	    	sampleList.add("C");
	    	sampleList.add("C");
	    	sampleList.add("angular");
	    	sampleList.add("angular");
	    	sampleList.add("ruby");
	    	sampleList.add("ruby");
	    	sampleList.add("springboot");
	    	sampleList.add("springboot");
	    	sampleList.add("spring");
	    	sampleList.add("spring");
	    	sampleList.add("dotnet");
	    	sampleList.add("dotnet");
	    	sampleList.add("google");
	    	sampleList.add("google");
	    	sampleList.add("gmail");
	    	sampleList.add("gmail");
	    	sampleList.add("facebook");
	    	sampleList.add("instagram");
	    	
	    	for(String s : sampleList) {
	    		Tweet tweet = new Tweet(s+" is powerful #"+s);
	    		tweetList.add(tweet);
	    	}
	    	
	    	iTweetRepository.saveAll(tweetList);
	    
	    	
	    	
	    	
	    	
	    }
}
