/**
 * Copyright (c) MuleSoft, Inc. All rights reserved. http://www.mulesoft.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.md file.
 */

package org.mule.twitter.automation.testcases;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.twitter.automation.TwitterTestPlace;

import twitter4j.Place;
import twitter4j.ResponseList;

public class SearchPlacesTestCases extends TwitterTestParent {
    
    @Category({SanityTests.class, RegressionTests.class})
	@Test
	public void testSearchPlacesByIp() {
		
    	TwitterTestPlace place = (TwitterTestPlace) context.getBean("placeByIp");
    	
		try {

			flow = lookupFlowConstruct("search-places-by-ip");
			response = flow.process(getTestEvent(place.getIp()));
        	ResponseList<Place> placesList = (ResponseList<Place>) response.getMessage().getPayload();
        	
        	assertNotNull(placesList);
        	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace(); 
			fail();
		}
	}
    
    @Category({RegressionTests.class})
	@Test
	public void testSearchPlacesByCoordinates() {
    	
    	TwitterTestPlace place = (TwitterTestPlace) context.getBean("placeByCoordinates");
		
		try {
			
			Map<String,Object> operationParams = new HashMap<String,Object>();
			operationParams.put("latitude", place.getLatitude());
			operationParams.put("longitude", place.getLongitude());
			
			flow = lookupFlowConstruct("search-places-by-coordinates");
        	response = flow.process(getTestEvent(operationParams));
        	ResponseList<Place> placesList = (ResponseList<Place>) response.getMessage().getPayload();
        	
        	assertNotNull(placesList);
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}  
     
	}
    

}
