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
import com.relevantcodes.extentreports.LogStatus;
import TestBase.testData;
import io.github.bonigarcia.wdm.WebDriverManager;

////////////  5 Test cases   /////////////

public class ChangePassword extends testData
{
	WebDriver driver;
	@BeforeMethod
	public void openURL()
	{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.navigate().to("https://dev.waffarad.com/Merchant/Account/Login?ReturnURL=/");
		driver.manage().window().maximize();
	}

	@Test
	public void ChangePassword_WithValidData() throws IOException, InterruptedException //All fields correct
	{
		test = extent.startTest("ChangePassword_WithValidData");

		driver.findElement(By.id("UserName")).sendKeys("kio@waffarad.com");

		driver.findElement(By.id("Password")).sendKeys("88888888");

		driver.findElement(By.id("SubmitLogin")).click();

		assertEquals("https://dev.waffarad.com/Merchant/Account/Login?ReturnURL=/", driver.getCurrentUrl());

		driver.findElement(By.linkText("User")).click();

		driver.findElement(By.linkText("Change password")).click();
		assertEquals("https://dev.waffarad.com/Merchant/User/Profile", driver.getCurrentUrl()) ;

		WebElement CurrentPassword = driver.findElement(By.id("password-show"));
		CurrentPassword.clear();
		CurrentPassword.sendKeys("88888888");

		WebElement NewPassword = driver.findElement(By.id("password-show1"));
		NewPassword.clear();
		NewPassword.sendKeys("99999999");

		WebElement ConfirmPassword = driver.findElement(By.id("password-show2"));
		ConfirmPassword.clear();
		ConfirmPassword.sendKeys("99999999");

		WebElement Save = driver.findElement(By.id("submitChangePassword"));
		Save.click();
		
		Thread.sleep(3000) ;
		
		driver.findElement(By.linkText("Log Out")).click();

		WebElement UserName = driver.findElement(By.id("UserName"));
		UserName.clear();
		UserName.sendKeys("kio@waffarad.com");

		WebElement Password = driver.findElement(By.id("Password"));
		Password.clear();
		Password.sendKeys("88888888");

		driver.findElement(By.id("SubmitLogin")).click();

		WebElement Error = driver.findElement(By.xpath("//*[@data-valmsg-for='Password']"));
		assertEquals(Error.getText(), "Wrong Username Or Password");
	}

	@Test
	public void ChangePassword_WithInsert_Wrongdata_In_CurrentPassword() throws IOException 
	//All fields correct but insert wrong data in current password
	{
		test = extent.startTest("ChangePassword_WithInsert_Wrongdata_In_CurrentPassword");

		driver.findElement(By.id("UserName")).sendKeys("kio@waffarad.com");

		driver.findElement(By.id("Password")).sendKeys("99999999");

		driver.findElement(By.id("SubmitLogin")).click();

		assertEquals("https://dev.waffarad.com/Merchant", driver.getCurrentUrl());

		driver.findElement(By.linkText("User")).click();

		driver.findElement(By.linkText("Change password")).click();
		assertEquals("https://dev.waffarad.com/Merchant/User/Profile", driver.getCurrentUrl()) ;

		WebElement CurrentPassword = driver.findElement(By.id("password-show"));
		CurrentPassword.clear();
		CurrentPassword.sendKeys("11111111");

		WebElement NewPassword = driver.findElement(By.id("password-show1"));
		NewPassword.clear();
		NewPassword.sendKeys("33333333");

		WebElement ConfirmPassword = driver.findElement(By.id("password-show2"));
		ConfirmPassword.clear();
		ConfirmPassword.sendKeys("33333333");

		WebElement Save = driver.findElement(By.id("submitChangePassword"));
		Save.click();
	}

	@Test
	public void ChangePassword_WithValue_LessThanMinLimitation_In_NewAndConfirmPassword() throws IOException 
	//All fields correct but insert value less than min limitation in new password and confirm password
	{
		test = extent.startTest("ChangePassword_WithValue_LessThanMinLimitation_In_NewAndConfirmPassword");

		driver.findElement(By.id("UserName")).sendKeys("kio@waffarad.com");

		driver.findElement(By.id("Password")).sendKeys("99999999");

		driver.findElement(By.id("SubmitLogin")).click();

		assertEquals("https://dev.waffarad.com/Merchant", driver.getCurrentUrl());

		driver.findElement(By.linkText("User")).click();

		driver.findElement(By.linkText("Change password")).click();
		assertEquals("https://dev.waffarad.com/Merchant/User/Profile", driver.getCurrentUrl()) ;

		WebElement CurrentPassword = driver.findElement(By.id("password-show"));
		CurrentPassword.clear();
		CurrentPassword.sendKeys("99999999");

		WebElement NewPassword = driver.findElement(By.id("password-show1"));
		NewPassword.clear();
		NewPassword.sendKeys("88");

		WebElement ConfirmPassword = driver.findElement(By.id("password-show2"));
		ConfirmPassword.clear();
		ConfirmPassword.sendKeys("88");

		WebElement Save = driver.findElement(By.id("submitChangePassword"));
		Save.click();

		WebElement Error = driver.findElement(By.id("password-show1-error"));
		assertEquals(Error.getText(), "New Password must be at least 8 characters");
	}

	@Test
	public void ChangePassword_WithInsert_diffrent_values_inNewAndConfirmPassword() throws IOException, InterruptedException 
	//All fields correct but insert less than greater limitation in new password and confirm password
	{
		test = extent.startTest("ChangePassword_WithInsert_diffrent_values_inNewAndConfirmPassword");	

		driver.findElement(By.id("UserName")).sendKeys("kio@waffarad.com");

		driver.findElement(By.id("Password")).sendKeys("99999999");

		driver.findElement(By.id("SubmitLogin")).click();

		assertEquals("https://dev.waffarad.com/Merchant", driver.getCurrentUrl());

		driver.findElement(By.linkText("User")).click();

		driver.findElement(By.linkText("Change password")).click();
		Thread.sleep(3000) ;
		System.out.println(driver.getCurrentUrl());
		//assertEquals("https://dev.waffarad.com/Merchant/User/Profile", driver.getCurrentUrl()) ;

		WebElement CurrentPassword = driver.findElement(By.id("password-show"));
		CurrentPassword.clear();
		CurrentPassword.sendKeys("99999999");

		WebElement NewPassword = driver.findElement(By.id("password-show1"));
		NewPassword.clear();
		NewPassword.sendKeys("66666666");

		WebElement ConfirmPassword = driver.findElement(By.id("password-show2"));
		ConfirmPassword.clear();
		ConfirmPassword.sendKeys("55555555");

		WebElement Save = driver.findElement(By.id("submitChangePassword"));
		Save.click();

		WebElement Error = driver.findElement(By.id("password-show1-error"));
		assertEquals(Error.getText(),"The new password and confirm password do not match.");
	}

	@Test
	public void ChangePassword_WithValue_GreaterThanMinLimitation_In_NewAndConfirmPassword () throws IOException 
	//All fields correct but insert less than greater limitation in new password and confirm password
	{
		test = extent.startTest("ChangePassword_WithValue_GreaterThanMinLimitation_In_NewAndConfirmPassword");

		driver.findElement(By.id("UserName")).sendKeys("kio@waffarad.com");

		driver.findElement(By.id("Password")).sendKeys("99999999");

		driver.findElement(By.id("SubmitLogin")).click();

		assertEquals("https://dev.waffarad.com/Merchant", driver.getCurrentUrl());

		driver.findElement(By.linkText("User")).click();

		driver.findElement(By.linkText("Change password")).click();
		assertEquals("https://dev.waffarad.com/Merchant/User/Profile", driver.getCurrentUrl()) ;

		WebElement CurrentPassword = driver.findElement(By.id("password-show"));
		CurrentPassword.clear();
		CurrentPassword.sendKeys("88888888");

		WebElement NewPassword = driver.findElement(By.id("password-show1"));
		NewPassword.clear();
		NewPassword.sendKeys("8888888888888888888888888"); //25 numbers

		WebElement ConfirmPassword = driver.findElement(By.id("password-show2"));
		ConfirmPassword.clear();
		ConfirmPassword.sendKeys("8888888888888888888888888");

		WebElement Save = driver.findElement(By.id("submitChangePassword"));
		Save.click();

		WebElement Error = driver.findElement(By.id("password-show2-error"));
		assertEquals(Error.getText(),"Confirm Password must be at max 20 characters");
	}

	@AfterMethod
	public void quit(ITestResult result)
	{
		if (result.getStatus() == ITestResult.SUCCESS)
		{
			test.log(LogStatus.PASS, "Test case passed");
		}
		else if(result.getStatus() == ITestResult.FAILURE)
		{
			test.log(LogStatus.FAIL, result.getThrowable());
		}
		else if(result.getStatus() == ITestResult.SKIP)
		{
			test.log(LogStatus.SKIP, "Test case skip");
		}
		driver.close();
	}
}