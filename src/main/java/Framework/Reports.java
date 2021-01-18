package Framework;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Reports {
	public static ExtentReports report;
	public static ExtentReports getReport()	
	{
		SimpleDateFormat s= new SimpleDateFormat ("dd-mm-yyy hh_mm_ss");
		Calendar c=Calendar.getInstance();
		File directory= new File(System.getProperty("user.dir")+"/Reports/TestReports");
		if (!directory.exists()) directory.mkdir();
		File destination= new File (directory.getAbsolutePath()+"/"+s.format(c.getTime())+"_Report.html");
		ExtentSparkReporter reporter = new ExtentSparkReporter(destination.getAbsolutePath());
	reporter.config().setReportName("E2E Project");
	reporter.config().setDocumentTitle("TestResult Report");
	report =new ExtentReports();
	report.attachReporter(reporter);
	report.setSystemInfo("QA","Kamal");
	 return report;
	
	}

}
