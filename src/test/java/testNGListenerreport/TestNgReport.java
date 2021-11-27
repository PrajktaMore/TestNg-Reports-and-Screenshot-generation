package testNGListenerreport;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class TestNgReport {

	public WebDriver driver;
	public ExtentReports extent;
	public ExtentTest extentTest;

	@BeforeTest

	public void setExtent()
	{
		extent =new ExtentReports(System.getProperty("user.dir")+"/test-output/ExtentReport.html",true);

		extent.addSystemInfo("Host Name", "LP-5CD120NLR8");
		extent.addSystemInfo("User Name", "Prajkta Selenium");
		extent.addSystemInfo("Environment ", "QA");

	}


	@AfterTest

	public void endReport()
	{
		extent.flush();
		extent.close();
	}@BeforeMethod

	public void setup()
	{
		System.setProperty("webdriver.chrome.driver", "C:\\Apps\\ChromeDriver\\chromedriver.exe");

		driver= new ChromeDriver();

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.get("https://www.freecrm.com/");

	}

	@Test
	public void freeCrmTitleTest()
	{
		extentTest=extent.startTest("freeCrmTitleTest");//In each @Test method need to give this line

		String title =driver.getTitle();
		System.out.println(title);
		Assert.assertEquals("#1 Free CRM customer relationship management software cloud", title);
	}
	
	@Test
	public void LoginByfacebook()
	{
		extentTest=extent.startTest("LoginByfacebook");//In each @Test method need to give this line

		System.out.println("Login By facebook");
		Assert.assertEquals(true, false);//false
	}

	@AfterMethod

	public void teardown(ITestResult result) throws IOException
	{
		if(result.getStatus()==ITestResult.FAILURE)
		{
			extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS"+result.getName());//to ADD name in extent report
			extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS"+result.getThrowable());//give error and exception


		}

		else if(result.getStatus()==ITestResult.SKIP)
		{
			extentTest.log(LogStatus.SKIP, "Test Case Skipped is" + result.getName());
		}

		else if(result.getStatus()==ITestResult.SUCCESS)
		{
			extentTest.log(LogStatus.PASS, "Test Case Passed is" + result.getName());

		}


		extent.endTest(extentTest); //ending test and ends the current test and prepare to create html report
		driver.quit();
	}
}