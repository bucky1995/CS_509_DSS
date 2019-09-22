package v4;

import java.io.IOException;
import java.util.Scanner;

import com.amazonaws.SdkClientException;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;

/**
 * Convert to have two parameters 'arg1' and 'arg2' and return their sum.
 * If either 'arg1' or 'arg2' is a string, then try to load up its value (as a double)
 * from the S3 bucket.
 * 
 */
public class LambdaFunctionHandler implements RequestHandler<CalculatorRequest, CalculatorResponse> {   
 
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
	 public CalculatorResponse handleRequest(CalculatorRequest request, Context context){
        LambdaLogger logger = context.getLogger();
        logger.log("Loading Java Lambda handler of Lambda-Function");


		logger.log("Received Add(" + request.arg1 + "," + request.arg2 + ")");
		
		double val1 = 0.0;
		try {
			val1 = Double.parseDouble(request.arg1);
		} catch (NumberFormatException e) {
			val1 = loadValueFromBucket(request.arg1);
		}
		
		double val2 = 0.0;
		try {
			val2 = Double.parseDouble(request.arg2);
		} catch (NumberFormatException e) {
			val2 = loadValueFromBucket(request.arg2);
		}
		
		return new CalculatorResponse("" + (val1 + val2));
	}
}
