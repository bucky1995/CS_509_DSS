package edu.wpi.cs.heineman.calculator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.json.Jackson;
import com.fasterxml.jackson.databind.JsonNode;

public class CalculatorHandler implements RequestStreamHandler {

	// overly precise.... shouldn't have to live life this way...
	private AmazonS3 s3 = null;
	
	double getDoubleFromBucket(String constantName) throws Exception {
		if (s3 == null) {
			s3 = AmazonS3ClientBuilder.standard().withRegion(Regions.US_EAST_1).build();
		}
		S3Object obj = s3.getObject("f19cs509/constants", constantName);

		try (S3ObjectInputStream constantStream = obj.getObjectContent()) {
			Scanner sc = new Scanner(constantStream);
			String val = sc.nextLine();
			sc.close();
			return Double.parseDouble(val);
		}
	}
	
    @Override
    public void handleRequest(InputStream input, OutputStream output, Context context) throws IOException {
    	LambdaLogger logger = null;
    	if (context != null) { context.getLogger(); }
    	
    	// load entire input into a String (since it contains JSON)
    	StringBuilder incoming = new StringBuilder();
    	try (BufferedReader br = new BufferedReader(new InputStreamReader(input))) {
    		String line = null;
    		while ((line = br.readLine()) != null) {
    			incoming.append(line);
    		}
    	}
    	
    	// When coming in from Lambda function is pure JSON. When coming from API Gateway or the
    	// real thing, then is wrapped inside more complicated JSON and you only want the BODY
    	// in most cases.
        JsonNode node = Jackson.fromJsonString(incoming.toString(), JsonNode.class);
        
        if (node.has("body")) {
        	node = Jackson.fromJsonString(node.get("body").asText(), JsonNode.class);
        }
        
    	double arg1 = 0, arg2 = 0;
    	
    	String param = node.get("arg1").asText();
    	boolean error = false;
		try {
    		arg1 = Double.parseDouble(param);
    	} catch (NumberFormatException nfe) {
    		try {
    			arg1 = getDoubleFromBucket(param);
    		} catch (Exception e) {
    			if (logger != null) {logger.log("Unable to parse:" + param + " as arg1"); }
    			error = true;
    		}
    	}
    	
		param = node.get("arg2").asText();
		try {
    		arg2 = Double.parseDouble(param);
		} catch (NumberFormatException nfe) {
    		try {
    			arg2 = getDoubleFromBucket(param);
    		} catch (Exception e) {
    			if (logger != null) { logger.log("Unable to parse:" + param + " as arg2"); }
    			error = true;
    		}
    	}
    	
        PrintWriter pw = new PrintWriter(output);

        int statusCode;
        double sum = 0;
		if (error) {
			statusCode = 400;
		} else {
	    	sum = arg1 + arg2; 
	    	statusCode = 200;
		}
    	
		// Needed for CORS integration...
		String response = "{ \n" + 
				         "  \"isBase64Encoded\" : false, \n" +
				         "  \"statusCode\"      : " + statusCode + ", \n" +
				         "  \"headers\" : { \n " +
		                 "     \"Access-Control-Allow-Origin\" : \"*\", \n" + 
				         "     \"Access-Control-Allow-Method\"  : \"GET,POST,OPTIONS\" \n" + 
		                 "  }, \n" +
				         "  \"body\" : \"" + "{ \\\"result\\\" : \\\"" + sum + "\\\" }" + "\" \n" +
				         "}";
		
        // write out.
        pw.print(response);
        pw.close();
    }
}
