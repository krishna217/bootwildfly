package com.camp.twitter;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TwitterController {


    @RequestMapping("getTweetsDetails")
    @ResponseBody
    public String getTweetsDetails(@RequestBody String inputDetails){
    	String twitterData = null;
        //twitterData = getTwitterData();
    	return twitterData;
    }
}