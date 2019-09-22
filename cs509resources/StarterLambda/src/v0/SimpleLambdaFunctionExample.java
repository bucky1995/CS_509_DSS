package com.amazonaws.lambda.demo;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class SimpleLambdaFunctionExample implements RequestHandler<SimpleLambdaMessage, String> {
	public final static String lambdaFunctionName = "SimpleLambdaFunctionExample";

	@Override
	public String handleRequest(SimpleLambdaMessage message, Context context) {
		LambdaLogger logger = context.getLogger();
		logger.log("Received" + message.getMessage());
		final String resultStr = "Returned from AWS Lambda. Input string was " + message.getMessage();
		return resultStr;
	}   
}
