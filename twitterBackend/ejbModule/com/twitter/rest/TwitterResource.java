package com.twitter.rest;

import java.util.List;

import javax.inject.Inject;
import javax.websocket.server.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.twitter.dto.TwitterDTO;
import com.twitter.entities.TwitterDetail;
import com.twitter.services.TwitterService;

@Path("twitter")
public class TwitterResource {
	
	@Inject
	TwitterService twitterService;
	
	@POST
	@Path("/createNewTweet")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createNewTweet(TwitterDTO twitterDto) {
		TwitterDetail dto = twitterService.saveTweet(twitterDto);
		return Response.ok(dto).build();
	}
	
	@GET
	@Path("/findAllTweet")
	@Produces(MediaType.APPLICATION_JSON)
	public List<TwitterDTO> findAllTweet() {
		return twitterService.getAllTweetsByID();
	}
}
