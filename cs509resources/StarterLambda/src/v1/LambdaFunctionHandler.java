package v1;

import java.util.Map;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class LambdaFunctionHandler implements RequestHandler<Map<String,Object>, String> {

	@Override
	public String handleRequest(Map<String,Object> input, Context context) {
		LambdaLogger logger = context.getLogger();
		String message = (String) input.get("message");
		String author = (String) input.get("author");
		String str = message + "(" + author + ")";
		logger.log("Received:" + str);
		final String resultStr = "Returned from AWS Lambda. Input string was " + str;
		return resultStr;
	}
}
