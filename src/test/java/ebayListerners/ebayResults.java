package ebayListerners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ebayResults implements ITestListener  {

	public void onTestStart(ITestResult result) {
		
		System.out.println("TestCases Started and details are" + result.getName());
		
	}

	public void onTestSuccess(ITestResult result) {
		
		System.out.println("TestCases success and details are" + result.getName());
		
	}

	public void onTestFailure(ITestResult result) {
	
		System.out.println("TestCases failed and details are" + result.getName());
		
	}

	public void onTestSkipped(ITestResult result) {
		
		System.out.println("TestCases skipped and details are" + result.getName());
		
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
