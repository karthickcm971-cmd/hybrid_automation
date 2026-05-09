package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testCases.BaseClass;

public class ExtentReportManager implements ITestListener{
     
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;
	
	String repName;
	public void onStart(ITestContext testContext)
	{
			/*	SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
				Date dt = new Date();
				String currentDateTimeStamp = df.format(dt);
			*/
		 String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		 repName = "Test-Report-"+timestamp+".html";
		 sparkReporter = new ExtentSparkReporter(".\\reports\\"+repName);
		 
		 sparkReporter.config().setDocumentTitle("opencart Automation Report"); //Title of the report
		 sparkReporter.config().setReportName("opencart Functional Testing"); //specify location of the report
		 sparkReporter.config().setTheme(Theme.DARK);
		 
		 extent = new ExtentReports();
		 extent.attachReporter(sparkReporter);
		 extent.setSystemInfo("Application", "opencart");
		 extent.setSystemInfo("Module", "Admin");
		 extent.setSystemInfo("Sub Module", "Customers");
		 extent.setSystemInfo("User Name", System.getProperty("user.name"));
		 extent.setSystemInfo("Environment", "QA");
		 
		 String os = testContext.getCurrentXmlTest().getParameter("os");
		 extent.setSystemInfo("Operating System", os);
		 
		 String browser = testContext.getCurrentXmlTest().getParameter("browser");
		 extent.setSystemInfo("Browser", browser);
		 
		 List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();
		 if(!includedGroups.isEmpty())
		 {
			 extent.setSystemInfo("Groups", includedGroups.toString());
		 }
	}
	
	public void onTestSuccess(ITestResult result)
	{
		 test = extent.createTest(result.getTestClass().getName());
		 test.assignCategory(result.getMethod().getGroups()); // to display groups in report
		 test.log(Status.PASS, result.getName()+" got successfully executed");
	}
	
	public void onTestFailure(ITestResult result)
	{
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		
		test.log(Status.FAIL, result.getName()+" got failed");
		test.log(Status.INFO, result.getThrowable().getMessage());
		
		try
		{
			//String imgPath = new BaseClass().CaptureScreen(result.getName());
			String imgPath = ((BaseClass) result.getInstance()).CaptureScreen(result.getName());
			test.addScreenCaptureFromPath(imgPath);
		}
		catch(IOException e1)
		{
			e1.printStackTrace();
		}
	}
	
	public void onTestSkipped(ITestResult result)
	{
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, result.getName()+" got skipped");
		test.log(Status.INFO, result.getThrowable().getMessage());
	}
	public void onFinish(ITestContext testContext)
	{
	    extent.flush();

	    // Create file path properly
	    File extentReport = new File(System.getProperty("user.dir"), "reports/" + repName);

	    // Open report in browser
	    try {
	        Desktop.getDesktop().browse(extentReport.toURI());
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    // Send email
	    try {
	        URL url = extentReport.toURI().toURL();

	        ImageHtmlEmail email = new ImageHtmlEmail();
	        email.setDataSourceResolver(new DataSourceUrlResolver(url));
	        email.setHostName("smtp.googlemail.com");
	        email.setSmtpPort(465);

	        //  Use App Password here
	        email.setAuthenticator(new DefaultAuthenticator("karthickcm97.2@gmail.com", "emwi igzg hamz bvbx"));
                                                                                       //YOUR_APP_PASSWORD = emwi igzg hamz bvbx
	        email.setSSLOnConnect(true);
	        email.setFrom("karthickcm97.2@gmail.com");
	        email.setSubject("Test Results");
	        email.setMsg("Please find Attached Report .... ");
	        email.addTo("karthickcm97.1@gmail.com");

	        email.attach(url, "Extent Report", "Automation Test Report");
	        email.send();

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

}
