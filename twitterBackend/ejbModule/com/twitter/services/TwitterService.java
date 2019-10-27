package com.twitter.services;

import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.twitter.Interfaces.ITwitter;
import com.twitter.dto.TwitterDTO;
import com.twitter.entities.TwitterDetail;

import twitter4j.DirectMessage;
import twitter4j.Status;
import twitter4j.StatusUpdate;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.conf.ConfigurationBuilder;

@Stateless
@LocalBean
public class TwitterService implements ITwitter {

	@PersistenceContext
	EntityManager em;

	
	TwitterFactory twitterFactory = new TwitterFactory();

	Twitter twitter = twitterFactory.getInstance();

	@SuppressWarnings("unchecked")
	@Override
	public List<TwitterDTO> getAllTweetsByID() {
		Query query = em.createNamedQuery("TwitterDetail.findAll");
		return (List<TwitterDTO>) query.getResultList();
	}

	@Override
	public TwitterDetail saveTweet(TwitterDTO twitterDTO) {
		String recipiantName = "@AdhLecturer 2564 ADH Project";
		TwitterDetail twitterDetails = new TwitterDetail();
		if (twitterDTO != null) {
			twitterDetails.setTweetBody( twitterDTO.getTweetBody());
			twitterDetails.setTimeStamp(new Date());
			em.persist(twitterDetails);
			try {
				postTweet(recipiantName + " " +twitterDetails.getTweetBody());
			} catch (TwitterException e) {
				e.printStackTrace();
			}
		}
		return twitterDetails;
	}

	@Override
	public void postTweet(String tweetBody) throws TwitterException {

		StatusUpdate statusUpdate = new StatusUpdate(tweetBody);

		getInstance().updateStatus(statusUpdate);

	}

	private Twitter getInstance() {
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
		  .setOAuthConsumerKey("W1ALc4diEjl1kdj3Is2Rp98MX")
		  .setOAuthConsumerSecret("HdGpIxPf4u4mHHBh8jIiXHx5qeqNsXflrgavbKkVK844TqDEuf ")
		  .setOAuthAccessToken("487371147-qgLyLbnhjaXnSEcRFdC1jKPvTAirXvIu6NMxbh2k")
		  .setOAuthAccessTokenSecret("ujYaVtkw2ubTMHfdnIuuJ9gENvIGgS5bq6XVB5fVS8zvX");
		TwitterFactory tf = new TwitterFactory(cb.build());
		return tf.getInstance();
	}
}
