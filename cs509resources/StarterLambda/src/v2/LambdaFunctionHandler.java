package v2;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;

public class LambdaFunctionHandler implements RequestHandler<Map<String,Object>, String> {
	private AmazonS3 s3 = AmazonS3ClientBuilder.standard().build();
	
	@Override
	public String handleRequest(Map<String,Object> input, Context context) {
		
		// There are warnings on this method. Pay attention to them
		S3Object pi = s3.getObject("teamstudent509/constants", "pi");
		
		S3ObjectInputStream pis = pi.getObjectContent();
		Scanner sc = new Scanner(pis);
		String val = sc.nextLine();
		sc.close();
		try {
			pis.close();
		} catch (IOException e) {
			
		}
		
		LambdaLogger logger = context.getLogger();
		String message = (String) input.get("message");
		String author = (String) input.get("author");
		String str = message + "(" + author + ")";
		logger.log("Received:" + str);
		return "Returned from AWS Lambda. pi=" + val;
	}
}
