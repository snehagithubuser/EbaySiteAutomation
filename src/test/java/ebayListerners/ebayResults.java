package ebayListerners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.Test;

@Test
public class ebayResults implements ITestListener  {

	public void onTestStart(ITestResult result) {
		
		System.out.println("TestCases " + result.getName() + "started" );
		
	}

	public void onTestSuccess(ITestResult result) {
		
		System.out.println("TestCases " + result.getName() + "ran successfully");
		
	}

	public void onTestFailure(ITestResult result) {
	
		System.out.println("TestCases " + result.getName() + "failed");
		
	}

	public void onTestSkipped(ITestResult result) {
		
		System.out.println("TestCases "+ result.getName() + "skipped");
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	
}
