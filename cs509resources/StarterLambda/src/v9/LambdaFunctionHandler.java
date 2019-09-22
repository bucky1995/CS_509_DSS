package com.amazonaws.lambda.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Scanner;

import com.amazonaws.SdkClientException;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * Convert to have two parameters 'arg1' and 'arg2' and return their sum.
 * If either 'arg1' or 'arg2' is a string, then try to load up its value (as a double)
 * from the S3 bucket.
 * 
 */
public class LambdaFunctionHandler implements RequestStreamHandler {
	String quot = "\"";
	
	private AmazonS3 s3 = AmazonS3ClientBuilder.standard().build();
	
	/** Load up S3 Bucket with given key and interpret contents as double. */
	public double loadValueFromBucket(String arg) {
		try {
			S3Object pi = s3.getObject("teamstudent509/constants", arg);
			if (pi == null) {
				return 0;
			} else {
				S3ObjectInputStream pis = pi.getObjectContent();
				Scanner sc = new Scanner(pis);
				String val = sc.nextLine();
				sc.close();
				try { pis.close(); } catch (IOException e) { }
				try {
					return Double.valueOf(val);
				} catch (NumberFormatException nfe) {
					return 0.0;
				}
			}
		} catch (SdkClientException sce) {
			return 0;
		}
	}
	
	@Override
    public void handleRequest(InputStream inputStream, OutputStream outputStream, Context context) throws IOException {
		JSONParser parser = new JSONParser();
	    LambdaLogger logger = context.getLogger();
        logger.log("Loading Java Lambda handler of RequestStreamHandler");

        JSONObject headerJson = new JSONObject();
        headerJson.put("Content-Type",  "application/json");  // not sure if needed anymore?

        // annoyance to ensure integration with S3 can support CORS
        headerJson.put("Access-Control-Allow-Origin",  "*");
        headerJson.put("Access-Control-Allow-Methods", "GET,POST");
        
        JSONObject responseJson = new JSONObject();
        responseJson.put("isBase64Encoded", false);
        responseJson.put("headers", headerJson);

        JSONObject responseBody = new JSONObject();
        
        try {        
	        String arg1 = "0";
	        String arg2 = "0";
	        
	        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
	        JSONObject event = (JSONObject) parser.parse(reader);
	        logger.log("event:" + event.toJSONString());
	      
	        // when passed as POST, appears as 'body' in the request, so must be extracted.
	        String body = (String)event.get("body");
	        if (body != null) {
	        	event = (JSONObject) parser.parse(body);
	        }
	        
	        //responseBody.put("input", event.toJSONString());
	        logger.log("event:" + event.toString());
	        if (event != null) {
		        if ( event.get("arg1") != null) {
	                arg1 = (String)event.get("arg1");
	                logger.log("arg1=" + arg1);
	            }
	            if ( event.get("arg2") != null) {
	                arg2 = (String)event.get("arg2");
	                logger.log("arg2=" + arg2);
	            }
	        }
	        
			logger.log("Received Add(" + arg1 + "," + arg2 + ")");
				
			double val1 = 0.0;
			try {
				val1 = Double.parseDouble(arg1);
			} catch (NumberFormatException e) {
				val1 = loadValueFromBucket(arg1);
			}
			
			double val2 = 0.0;
			try {
				val2 = Double.parseDouble(arg2);
			} catch (NumberFormatException e) {
				val2 = loadValueFromBucket(arg2);
			}
	        
			// must go in as a String.
			String result = "" + (val1 + val2);
			
	        responseBody.put("add", result);
	        responseJson.put("statusCode", 200);
	        responseJson.put("body", responseBody.toString());  
				
	    } catch (Exception e) {
	    	logger.log(e.toString());

	        responseBody.put("add", "Unable to process input");
	        responseJson.put("statusCode", 422);
	        responseJson.put("body", responseBody.toString());  
	    }
        
        logger.log("end result:" + responseJson.toJSONString());
        logger.log(responseJson.toJSONString());
        OutputStreamWriter writer = new OutputStreamWriter(outputStream, "UTF-8");
        writer.write(responseJson.toJSONString());  
        writer.close();
	}
}
