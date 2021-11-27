package testNGListenerreport;

	import java.io.File;
import java.sql.Timestamp;
	 
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
	import org.testng.annotations.BeforeClass;
	import org.testng.annotations.Test;
	 
	import com.relevantcodes.extentreports.ExtentReports;
	import com.relevantcodes.extentreports.ExtentTest;
	import com.relevantcodes.extentreports.LogStatus;
	 
//	import io.github.bonigarcia.wdm.WebDriverManager;
	 
	public class TestNgListnerReport{
	    WebDriver driver = null;
	    public ExtentReports reports;
	    public ExtentTest test;
	 
	    File reportDir = new File(System.getProperty("user.dir"));
	 
	    String reportFilePath = reportDir.toString() + "/ExtentReports/";
	 
	    @BeforeClass
	    public void beforeClass() {
	 
	       // WebDriverManager.chromedriver().setup();
	 
			System.setProperty("webdriver.chrome.driver", "C:\\Apps\\ChromeDriver\\chromedriver.exe");

	        driver = new ChromeDriver();
	 
	        reports = new ExtentReports(reportFilePath + this.getClass().getSimpleName() + getRandomNumber() + ".html", false);
	 
	        test = reports.startTest("Extent Report Sample");
	 
	    }
	 
	    @Test
	    public void test_with_extentReporting() {
	 
	        driver.get("https://techndeck.com/");
	        driver.manage().window().maximize();
	 
	        String title = driver.getTitle();
	 
	        if (title.contains("Techndeck")) {
	            test.log(LogStatus.PASS, "Validate title contain text: 'Techndeck'", "Text exist");
	        } else {
	            test.log(LogStatus.FAIL, "Validate title contain text: 'Techndeck'", "Text doesn't exist");
	        }
	 
	 
	        
	    }
	    @Test
        public void loginByFacebook()
    	{
	    	
	    	driver.get("https://www.facebook.com/login/");
	        driver.manage().window().maximize();
	 
	        String title1 = driver.getTitle();
	 
	        System.out.println(title1);
	        if (title1.contains("Facebook")) {
	            test.log(LogStatus.PASS, "Validate title contain text: 'Techndeck'", "Text exist");
	        } else {
	            test.log(LogStatus.FAIL, "Validate title contain text: 'Techndeck'", "Text doesn't exist");
	        }
	 
    		
    	}
	 
	    @AfterClass
	    public void afterClass() {
	        driver.close();
	        driver.quit();
	        reports.endTest(test);
	        reports.flush();
	        reports.close();
	    }
	 
	 
	    public long getRandomNumber() {
	        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	        return timestamp.getTime();
	    }
	 
	}

