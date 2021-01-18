package testSuite;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Framework.Reports;

public class Listeners implements ITestListener {
	public ExtentReports report=Reports.getReport();
	public ExtentTest test;
	ThreadLocal<ExtentTest> t = new ThreadLocal<ExtentTest>();
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		test=report.createTest(result.getMethod().getMethodName());
		t.set(test);
	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
	t.get().log(Status.PASS, "TestCase Passed");
	}

	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		String TestName= result.getMethod().getMethodName();
		WebDriver driver=null;
		try {
			driver =(WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (Exception e) {
	    	e.printStackTrace();
		} 
		t.get().fail(result.getThrowable());
	     t.get().addScreenCaptureFromPath(getScreenshot(TestName,driver),"Failure_Screesnhot");
		
	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
	report.flush();
	}
	
	public String getScreenshot(String TestName, WebDriver driver) {
		Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
	
		 File Src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		 File destFile =null;
	        try {
	        	File reportDirectory = new File(System.getProperty("user.dir") + "/Reports/failure_screenshots/");
	        	if(!reportDirectory.exists())
                	reportDirectory.mkdirs();                
                destFile = new File(reportDirectory.getAbsolutePath() + "/" + formater.format(calendar.getTime())+"_"+TestName+".png");
				FileUtils.copyFile(Src, destFile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
			return destFile.getAbsolutePath();
	}

}
