package Pages;

import java.io.IOException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import com.relevantcodes.extentreports.ExtentReports;
import TestBase.testData;

public class Report extends testData
{	
	public Report() throws IOException
	{
	super();
	}
	
	@BeforeSuite
	public void start()
	{
		extent = new ExtentReports("D:\\Eclipse\\WaffarAd_Project\\Report\\WaffarAd_Report.html", true );
		//True mean when run code again, remove old report and make new reportss

		extent.addSystemInfo("TesterName", "Nohair");
		extent.addSystemInfo("OS", "windows");
		extent.addSystemInfo("Browser", "chrome");
		extent.addSystemInfo("TestName", "WaffarAD");
		extent.addSystemInfo("Website", "https://dev.waffarad.com/Merchant/Account/Login?ReturnURL=/");
		extent.addSystemInfo("Language", "Java");	
	}
	
	@AfterSuite
	public void End()
	{
		extent.flush();
	}
}