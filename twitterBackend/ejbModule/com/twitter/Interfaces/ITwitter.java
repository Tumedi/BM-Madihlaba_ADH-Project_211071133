package com.twitter.Interfaces;

import java.util.List;

import com.twitter.dto.TwitterDTO;
import com.twitter.entities.TwitterDetail;

import twitter4j.TwitterException;

public interface ITwitter {
	
	List<TwitterDTO> getAllTweetsByID();
	TwitterDetail saveTweet(TwitterDTO twitterDto);
	void postTweet(String message) throws TwitterException;
	
}
