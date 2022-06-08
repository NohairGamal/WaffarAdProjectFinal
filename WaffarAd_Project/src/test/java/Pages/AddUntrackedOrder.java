package Pages;

import static org.testng.Assert.assertEquals;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.LogStatus;
import TestBase.testData;
import io.github.bonigarcia.wdm.WebDriverManager;

public class AddUntrackedOrder extends testData
{
	WebDriver driver;
	@BeforeMethod
	public void open_URL()
	{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize() ;
		driver.navigate().to("https://dev.waffarad.com/Merchant/Account/Login?ReturnURL=/");

		driver.findElement(By.id("UserName")).sendKeys("admin.merchant@waffarad.com");

		driver.findElement(By.id("Password")).sendKeys("M\"XwdU7]q)");

		driver.findElement(By.id("SubmitLogin")).click();

		WebElement List = driver.findElement(By.id("MerchantId"));
		Select option = new Select (List);
		option.selectByIndex(1); //Choice of Yashry

		WebElement AddUntrackedOrder_Button = driver.findElement(By.id("AddUntrackedOrder"));
		AddUntrackedOrder_Button.click();

		WebElement Title= driver.findElement(By.className("panel-title"));
		assertEquals("Add Yashry Order", Title.getText());	
	}

	@SuppressWarnings("deprecation")
	@Test
	public void AddOrder() throws IOException, InterruptedException
	{
		test = extent.startTest("AddOrder");

		driver.findElement(By.id("SubId")).sendKeys("3000"); //Field of SubId

		driver.findElement(By.id("OrderId")).sendKeys("3000");

		driver.findElement(By.id("OrderTotal")).sendKeys("2000");

		driver.findElement(By.id("OrderShipping")).sendKeys("200");
		
		driver.findElement(By.id("OrderDiscounts")).sendKeys("100");
		
		driver.findElement(By.id("OrderEmail")).sendKeys("create@waffarx.com");
		
		driver.findElement(By.id("OrderDateTime")).sendKeys("18/5/2022");

		WebElement Save = driver.findElement(By.xpath("//*[@type ='submit']"));
		Save.click();
		
		driver.quit();
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.navigate().to("https://dev.waffarad.com/Merchant/Account/Login?ReturnURL=/");

		driver.findElement(By.id("UserName")).sendKeys("admin.merchant@waffarad.com");

		driver.findElement(By.id("Password")).sendKeys("M\"XwdU7]q)");

		driver.findElement(By.id("SubmitLogin")).click();

		assertEquals("https://dev.waffarad.com/Merchant/home/ChooseMerchant", driver.getCurrentUrl());

		WebElement Title_Choose_Merchant = driver.findElement(By.xpath("//*[@for='MerchantId']"));
		assertEquals(Title_Choose_Merchant.getText(), "Choose Merchant");

		WebElement Choose_Merchant_List = driver.findElement(By.id("MerchantId"));
		Select option2= new Select (Choose_Merchant_List);
		option2.selectByIndex(1); //TFK

		WebElement GoToMerchant_Button = driver.findElement(By.id("GotoMerchantBtn"));
		GoToMerchant_Button.click();

		driver.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);

		WebElement orders = driver.findElement(By.xpath("//*[@href='/Merchant/Orders']"));
		orders.click();

		assertEquals("https://dev.waffarad.com/Merchant/Orders", driver.getCurrentUrl());

		Thread.sleep(3000);
	}

//	@AfterMethod
//	public void quit(ITestResult result)
//	{
//		if (result.getStatus() == ITestResult.SUCCESS)
//		{
//			test.log(LogStatus.PASS, "Test case passed");
//		}
//		else if(result.getStatus() == ITestResult.FAILURE)
//		{
//			test.log(LogStatus.FAIL, result.getThrowable());
//		}
//		else if(result.getStatus() == ITestResult.SKIP)
//		{
//			test.log(LogStatus.SKIP, "Test case skip");
//		}
//		driver.close();
//	}
}