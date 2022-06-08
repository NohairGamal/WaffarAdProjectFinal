package Pages;

import static org.testng.Assert.assertEquals;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import TestBase.testData;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ChangeStatus extends testData
{
	WebDriver driver;

	@SuppressWarnings("deprecation")
	@BeforeTest
	public void open_URL() throws InterruptedException
	{WebDriverManager.chromedriver().setup();
	driver = new ChromeDriver();
	driver.manage().window().maximize();
	driver.navigate().to("https://dev.waffarad.com/Merchant/Account/Login?ReturnURL=/");

	WebElement UserName = driver.findElement(By.id("UserName"));
	UserName.clear();
	UserName.sendKeys("admin.merchant@waffarad.com");

	WebElement Password = driver.findElement(By.id("Password"));
	Password.clear();
	Password.sendKeys("M\"XwdU7]q)");

	driver.findElement(By.id("SubmitLogin")).click();

	assertEquals("https://dev.waffarad.com/Merchant/home/ChooseMerchant", driver.getCurrentUrl());

	WebElement Title_Choose_Merchant = driver.findElement(By.xpath("//*[@for='MerchantId']"));
	assertEquals(Title_Choose_Merchant.getText(), "Choose Merchant");

	WebElement Choose_Merchant_List = driver.findElement(By.id("MerchantId"));
	Select option = new Select (Choose_Merchant_List);
	option.selectByIndex(1); //Yashry

	WebElement GoToMerchant_Button = driver.findElement(By.id("GotoMerchantBtn"));
	GoToMerchant_Button.click();

	driver.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);

	}

	@Test
	public void ChangeStatusOFOrder() throws InterruptedException
	{
		test = extent.startTest("CheckThat_OrderWhichPaid_NotEdit");

		driver.findElement(By.xpath("//*[@href='/Merchant/Orders']")).click(); //To click on orders

		assertEquals("https://dev.waffarad.com/Merchant/Orders", driver.getCurrentUrl());

		Thread.sleep(3000);

		WebElement ListOFStatus = driver.findElement(By.id("40"));
		Select Statusoption = new Select(ListOFStatus);
		Statusoption.selectByIndex(3);
		
		WebElement popup = driver.findElement(By.xpath("(//*[@tabindex ='1'])"));
		popup.click() ;
		
		WebElement ListOFStatus2 = driver.findElement(By.id("40"));
		System.out.println(ListOFStatus2.getAttribute("value")) ;
	}
}