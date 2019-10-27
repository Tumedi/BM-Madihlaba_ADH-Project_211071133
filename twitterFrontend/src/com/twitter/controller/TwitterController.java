package com.twitter.controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;

import com.twitter.consts.TwitterActions;
import com.twitter.model.TwitterDTO;

@ManagedBean(name = "twitterController")
@SessionScoped
public class TwitterController {

	Client client = ClientBuilder.newClient();
	private List<TwitterDTO> tweetsList;
	private String tweetBody;

	public void getAllTweets() {
		this.tweetsList = client.target(TwitterActions.URL + TwitterActions.GET_ALL_TWEETS).request()
				.get(new GenericType<List<TwitterDTO>>() {
				});
	}

	public void saveTweet() {
		String status = client.target(TwitterActions.URL+TwitterActions.CREATE_NEW_TWEET).request().post(Entity.json(twitterBody()),String.class);
		if(status != null) {
		
		}
	}

	public void clearTweets() {
		if (this.tweetsList != null) {
			tweetsList.clear();
		}
	}

	private TwitterDTO twitterBody() {
		TwitterDTO dto = new TwitterDTO();
		dto.setTweetBody(tweetBody);
		return dto;
	}

	public String getTweetBody() {
		return tweetBody;
	}

	public void setTweetBody(String tweetBody) {
		this.tweetBody = tweetBody;
	}

	public List<TwitterDTO> getTweetsList() {
		return tweetsList;
	}

	public void setTweetsList(List<TwitterDTO> tweetsList) {
		this.tweetsList = tweetsList;
	}

}
