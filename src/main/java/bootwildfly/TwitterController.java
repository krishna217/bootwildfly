package bootwildfly;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

import twitter4j.JSONArray;
import twitter4j.JSONException;
import twitter4j.JSONObject;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;


@RestController
public class TwitterController {


    @RequestMapping("getTweetsDetails")
    @ResponseBody
    public String getTweetsDetails(@RequestBody String inputDetails) {
    	String twitterData = null;
    	try {
    	    //TODO
            twitterData = new TwitterController().getTwitterData(inputDetails);
    	} catch (Exception e) {
    	    e.printStackTrace();
    	    //throw e;	
    	}
    	return twitterData;
    }
    
    public String getTwitterData(String hashTag) throws JSONException{
		JSONArray outputList= new JSONArray();
		 ConfigurationBuilder cb = new ConfigurationBuilder();
		    cb.setDebugEnabled(true)
		      .setOAuthConsumerKey("JM8uFCfNfK1ngGnyTMHECBo73")
		      .setOAuthConsumerSecret("pLtE2plNbfU9c3So5GWEMkdfoVKJmZSsgsnfit0zL0H7lbgfAa")
		      .setOAuthAccessToken("3071661381-3UCzkZrHAo8s1PWTh08u7VwyZF2WOzrIMhr89bc")
		      .setOAuthAccessTokenSecret("iEbtvaEol14gpYnFq1VIGOMNwkrLuLVePwKiMepZGoADn");
		   // cb.setHttpProxyHost("10.68.248.98").setHttpProxyPort(80);
		    Twitter twitter = new TwitterFactory(cb.build()).getInstance();
		    Query query = new Query(hashTag);
		    //int numberOfTweets = 5000;
		    long lastID = Long.MAX_VALUE;
		    ArrayList<Status> tweets = new ArrayList<Status>();
		    try {
		        QueryResult result = twitter.search(query);
		        tweets.addAll(result.getTweets());
		        System.out.println("Gathered " + tweets.size() + " tweets"+"\n");
		        for (Status t: tweets) 
		          if(t.getId() < lastID) 
		              lastID = t.getId();

		      }

		      catch (TwitterException te) {
		        System.out.println("Couldn't connect: " + te);
		      }; 
		      
		      for (int i = 0; i < tweets.size(); i++) {
		          Status t = (Status) tweets.get(i);
		          JSONObject outpt = new JSONObject();
		          

		         // GeoLocation loc = t.getGeoLocation();

		          String user = t.getUser().getScreenName();
		          outpt.put("text",t.getText());
		          outpt.put("size", (int)(Math.random()*100));
		          outputList.put(outpt);

		          } 
		          //else 
		            //System.out.println(i + " USER: " + user + " wrote: " + msg+"\n");
		      return outputList.toString();
	}
}
