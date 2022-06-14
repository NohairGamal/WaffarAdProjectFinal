package Pages;

import static org.testng.Assert.assertEquals;
import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import TestBase.TestData;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Login extends TestData
{
	WebDriver driver;

	@BeforeMethod
	public void open_URL()
	{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.navigate().to("https://dev.waffarad.com/Merchant/Account/Login?ReturnURL=/");
	}
	@Test
	public void Login_Valid_Data() throws IOException
	{
		//	test = extent.startTest("Login_Valid_Data");

		WebElement UserName = driver.findElement(By.id("UserName"));
		UserName.clear();
		UserName.sendKeys("admin.merchant@waffarad.com");

		WebElement Password = driver.findElement(By.id("Password"));
		Password.clear();
		Password.sendKeys("M\"XwdU7]q)");

		driver.findElement(By.id("SubmitLogin")).click();

		assertEquals("https://dev.waffarad.com/Merchant/home/ChooseMerchant", driver.getCurrentUrl());

		WebElement Title_Choose_Merchant = driver.findElement(By.xpath("//*[@for='MerchantId']"));
		assertEquals(Title_Choose_Merchant.getText(), "Choose Merchant") ;
	}

	@Test
	public void Login_Correct_username_AndWrong_password() throws IOException //Correct user name & wrong password
	{
		//	test = extent.startTest("Login_Correct_username_AndWrong_password");

		WebElement UserName = driver.findElement(By.id("UserName"));
		UserName.clear();
		UserName.sendKeys("admin.merchant@waffarad.com");

		WebElement Password = driver.findElement(By.id("Password"));
		Password.clear();
		Password.sendKeys("mmnmnjnm");

		driver.findElement(By.id("SubmitLogin")).click();

		WebElement Error = driver.findElement(By.xpath("//*[@data-valmsg-for='Password']"));
		assertEquals(Error.getText(), "Wrong Username Or Password");
	}
	@Test
	public void Login_Wrong_username_Correct_password() throws IOException //Wrong user name & Correct password
	{
		//	test = extent.startTest("Login_Wrong_username_Correct_password");

		WebElement UserName = driver.findElement(By.id("UserName"));
		UserName.clear();
		UserName.sendKeys("Karimmohammed@TFK.me");

		WebElement Password = driver.findElement(By.id("Password"));
		Password.clear();
		Password.sendKeys("MXwdU7]q)");

		driver.findElement(By.id("SubmitLogin")).click();

		WebElement Error = driver.findElement(By.xpath("//*[@data-valmsg-for='Password']"));
		assertEquals(Error.getText(),"Wrong Username Or Password") ;
	}

	@Test
	public void Login_All_fields_empty() throws IOException //All fields empty
	{
		//test = extent.startTest("Login_All_fields_empty");

		WebElement UserName = driver.findElement(By.id("UserName"));
		UserName.clear();

		WebElement Password = driver.findElement(By.id("Password"));
		Password.clear();

		driver.findElement(By.id("SubmitLogin")).click();

		WebElement Error = driver.findElement(By.xpath("//*[@id='Password-error']"));
		assertEquals(Error.getText(),"Password is required");
	}

	@AfterMethod
	public void quit(ITestResult result)
	{
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
		driver.close();
	}
}