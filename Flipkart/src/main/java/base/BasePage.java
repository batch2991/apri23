package base;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class BasePage 
{
	public static WebDriver driver;

	public static ExtentSparkReporter reporter;
	public static ExtentReports report;
	public static ExtentTest test;
	public static Properties prop;

	@BeforeSuite
	public void initialize()
	{
		System.out.println("hello");
		prop=new Properties();
		try{prop.load(new FileInputStream("./src/main/java/conf/config.properties"));}catch(Exception e) {}
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		String d=formatter.format(new Date());
		String repname="Flipkart"+d+".html";

		reporter=new ExtentSparkReporter("./Reports/"+repname);
		report=new ExtentReports();
		report.attachReporter(reporter);
		reporter.config().setDocumentTitle("Flipkart");
		reporter.config().setReportName("Flipkart Functional Testing");
		reporter.config().setTheme(Theme.DARK);
		report.setSystemInfo("Environment", "TestEnv");
		report.setSystemInfo("TesterName", "Tester1");	  
	}  
	public void setUp()
	{
		if(prop.getProperty("browsername").matches("firefox"))
		{
			driver=new FirefoxDriver();
		}
		if(prop.getProperty("browsername").matches("chrome"))
		{
			driver=new ChromeDriver();
		}
		if(prop.getProperty("browsername").matches("edge"))
		{
			driver=new EdgeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));	  
	}
	public void openUrl()
	{
		driver.get(prop.getProperty("url"));
	}
	public void takescreenshot(String imagename)
	{
		File f=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try{FileUtils.copyFile(f,new File("./Reports/images/"+imagename));}catch(Exception e) {}
		test.addScreenCaptureFromPath(prop.getProperty("imagepath")+imagename);
	}
	public void tearDown()
	{
		driver.quit();
	}
	@AfterSuite
	public void saveReport()
	{
		report.flush();
	}

}
