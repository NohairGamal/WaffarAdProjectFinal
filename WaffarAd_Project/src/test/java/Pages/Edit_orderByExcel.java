package Pages;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
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

public class Edit_orderByExcel extends testData
{
	WebDriver driver;

	@BeforeMethod
	public void open_URL()
	{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.navigate().to("https://dev.waffarad.com/Merchant/Account/Login?ReturnURL=/");
		driver.manage().window().maximize();

		WebElement UserName = driver.findElement(By.id("UserName"));
		UserName.clear();
		UserName.sendKeys("admin.merchant@waffarad.com");

		WebElement Password = driver.findElement(By.id("Password"));
		Password.clear();
		Password.sendKeys("M\"XwdU7]q)");

		driver.findElement(By.id("SubmitLogin")).click();

		WebElement List = driver.findElement(By.id("MerchantId"));
		Select option = new Select (List);
		option.selectByIndex(1);//Choice of Yashry

		WebElement GoToMerchant_Button = driver.findElement(By.id("GotoMerchantBtn"));
		GoToMerchant_Button.click();
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void ExportExcel_And_BulkUpdate() throws IOException, InterruptedException
	{
		driver.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);

		test =extent.startTest("ExportExcel_And_BulkUpdate");

		WebElement orders = driver.findElement(By.xpath("//*[@href='/Merchant/Orders']"));
		orders.click();

		assertEquals("https://dev.waffarad.com/Merchant/Orders", driver.getCurrentUrl()) ;

		WebElement Upload = driver.findElement(By.xpath("//*[@id='modalBtn']"));
		Upload.click();

		driver.findElement(By.xpath("//*[@id='OrdersFile']")).sendKeys("D:\\Nohair\\YashryOrders.xlsx");

		driver.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);

		WebElement UploadFile_button = driver.findElement(By.xpath("//*[@type='submit']"));
		UploadFile_button.click();

		WebElement Title = driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div/div[2]/div/div/div[4]/div[1]/h3"));

		assertEquals("Errors On Getting Data From Excel Sheet", Title.getText());
	}

	@SuppressWarnings("deprecation")
	@Test
	public void TestCases_OrderTotal() throws IOException, InterruptedException
	{
		test =extent.startTest("TestCases_OrderTotal");

		driver = new ChromeDriver();

		driver.navigate().to("https://dev.waffarad.com/Merchant/Account/Login?ReturnURL=/");
		driver.manage().window().maximize();

		WebElement UserName = driver.findElement(By.id("UserName"));
		UserName.clear();
		UserName.sendKeys("admin.merchant@waffarad.com");

		WebElement Password = driver.findElement(By.id("Password"));
		Password.clear();
		Password.sendKeys("M\"XwdU7]q)");

		driver.findElement(By.id("SubmitLogin")).click();

		WebElement List = driver.findElement(By.id("MerchantId"));
		Select option = new Select (List);
		option.selectByIndex(1); //Choice of Yashry

		driver.findElement(By.id("GotoMerchantBtn")).click();

		driver.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);

		driver.findElement(By.xpath("//*[@href='/Merchant/Orders']")).click();

		assertEquals("https://dev.waffarad.com/Merchant/Orders", driver.getCurrentUrl());

		/////////// First TC (Insert letters in column of order total) ///////////
		driver.findElement(By.xpath("(//*[@title ='edit'])[1]")).click();

		Thread.sleep(2000);
		assertEquals("713982",driver.findElement(By.xpath("(//*[@id ='OrderId'])[2]")).getAttribute("value"));

		assertNotEquals("jkjkjkjk", driver.findElement(By.id("OrderTotal")).getAttribute("value"));

		/////////// Second TC (Insert negative number in column of order total)  ///////////

		driver.navigate().back() ;
		assertEquals("https://dev.waffarad.com/Merchant/Orders", driver.getCurrentUrl());

		Thread.sleep(2000);

		driver.findElement(By.xpath("(//*[@title ='edit'])[2]")).click(); //Edit icon

		Thread.sleep(2000);

		assertEquals("714688", driver.findElement(By.xpath("(//*[@id ='OrderId'])[2]")).getAttribute("value"));

		assertNotEquals("-1000",driver.findElement(By.id("OrderTotal")).getAttribute("value"));
	}

	@SuppressWarnings("deprecation")
	@Test
	public void TestCases_OrderNet() throws IOException, InterruptedException
	{
		test =extent.startTest("TestCases_OrderNet");

		driver = new ChromeDriver();

		driver.navigate().to("https://dev.waffarad.com/Merchant/Account/Login?ReturnURL=/");
		driver.manage().window().maximize() ;

		driver.findElement(By.id("UserName")).sendKeys("admin.merchant@waffarad.com");

		driver.findElement(By.id("Password")).sendKeys("M\"XwdU7]q)");

		driver.findElement(By.id("SubmitLogin")).click();

		WebElement List = driver.findElement(By.id("MerchantId"));
		Select option = new Select (List);
		option.selectByIndex(1); //Choice of Yashry

		driver.findElement(By.id("GotoMerchantBtn")).click();

		driver.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);

		driver.findElement(By.xpath("//*[@href='/Merchant/Orders']")).click();

		assertEquals("https://dev.waffarad.com/Merchant/Orders", driver.getCurrentUrl());

		/////////// First TC (Insert letters in column of order Net) ///////////
		driver.findElement(By.xpath("(//*[@title ='edit'])[3]")).click();

		Thread.sleep(2000);

		assertEquals("715037", driver.findElement(By.xpath("(//*[@id ='OrderId'])[2]")).getAttribute("value"));

		assertNotEquals("jkjkjkjk", driver.findElement(By.id("OrderNet")).getAttribute("value"));

		/////////// Second TC (Insert negative number in column of order Net) ///////////
		driver.navigate().back() ;
		assertEquals("https://dev.waffarad.com/Merchant/Orders", driver.getCurrentUrl());

		Thread.sleep(2000);

		driver.findElement(By.xpath("(//*[@title ='edit'])[4]")).click();

		Thread.sleep(2000);

		assertEquals("716904", driver.findElement(By.xpath("(//*[@id ='OrderId'])[2]")).getAttribute("value"));

		assertNotEquals("-1000", driver.findElement(By.id("OrderNet")).getText());

		/////////// Third TC (Insert order Net greater than order Total)  ///////////
		driver.navigate().back();
		assertEquals("https://dev.waffarad.com/Merchant/Orders", driver.getCurrentUrl());

		Thread.sleep(2000);

		driver.findElement(By.xpath("(//*[@title ='edit'])[5]")).click();

		Thread.sleep(2000);

		assertEquals("717666", driver.findElement(By.xpath("(//*[@id ='OrderId'])[2]")).getAttribute("value"));

		assertNotEquals("3000", driver.findElement(By.id("OrderNet")).getAttribute("value"));
	}

	@SuppressWarnings("deprecation")
	@Test
	public void TestCases_OrderTax() throws IOException, InterruptedException
	{
		test =extent.startTest("TestCases_OrderTax");

		driver = new ChromeDriver();

		driver.navigate().to("https://dev.waffarad.com/Merchant/Account/Login?ReturnURL=/");
		driver.manage().window().maximize();

		driver.findElement(By.id("UserName")).sendKeys("admin.merchant@waffarad.com");

		driver.findElement(By.id("Password")).sendKeys("M\"XwdU7]q)");

		driver.findElement(By.id("SubmitLogin")).click();

		WebElement List = driver.findElement(By.id("MerchantId"));
		Select option = new Select (List);
		option.selectByIndex(1); //Choice of Yashry

		driver.findElement(By.id("GotoMerchantBtn")).click();

		driver.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);

		driver.findElement(By.xpath("//*[@href='/Merchant/Orders']")).click();

		assertEquals("https://dev.waffarad.com/Merchant/Orders", driver.getCurrentUrl());

		/////////// First TC (Insert letters in column of order Tax) ///////////
		driver.findElement(By.xpath("(//*[@title ='edit'])[6]")).click();

		Thread.sleep(2000);

		assertEquals("717814", driver.findElement(By.xpath("(//*[@id ='OrderId'])[2]")).getAttribute("value"));

		assertNotEquals("jkjkjkjk", driver.findElement(By.id("OrderTax")).getAttribute("value"));

		/////////// Second TC (Insert negative number in column of order Tax)  ///////////
		driver.navigate().back() ;
		assertEquals("https://dev.waffarad.com/Merchant/Orders", driver.getCurrentUrl());

		Thread.sleep(2000);

		driver.findElement(By.xpath("(//*[@title ='edit'])[7]")).click();

		Thread.sleep(2000);

		assertEquals("718379", driver.findElement(By.xpath("(//*[@id ='OrderId'])[2]")).getAttribute("value"));

		assertNotEquals("-1000", driver.findElement(By.id("OrderTax")).getText());

		/////////// Third TC (Insert order Tax greater than order Total)  ///////////
		driver.navigate().back();
		assertEquals("https://dev.waffarad.com/Merchant/Orders", driver.getCurrentUrl());

		Thread.sleep(2000);

		driver.findElement(By.xpath("(//*[@title ='edit'])[8]")).click();

		Thread.sleep(2000);

		assertEquals("836050", driver.findElement(By.xpath("(//*[@id ='OrderId'])[2]")).getAttribute("value"));

		assertNotEquals("3000", driver.findElement(By.id("OrderTax")).getAttribute("value"));
	}

	@SuppressWarnings("deprecation")
	@Test
	public void TestCases_OrderShipping() throws IOException, InterruptedException
	{
		test =extent.startTest("TestCases_OrderShipping");

		driver = new ChromeDriver();

		driver.navigate().to("https://dev.waffarad.com/Merchant/Account/Login?ReturnURL=/");
		driver.manage().window().maximize();

		driver.findElement(By.id("UserName")).sendKeys("admin.merchant@waffarad.com");

		driver.findElement(By.id("Password")).sendKeys("M\"XwdU7]q)");

		driver.findElement(By.id("SubmitLogin")).click();

		WebElement List = driver.findElement(By.id("MerchantId"));
		Select option = new Select (List);
		option.selectByIndex(1); //Choice of Yashry

		driver.findElement(By.id("GotoMerchantBtn")).click();

		driver.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);

		driver.findElement(By.xpath("//*[@href='/Merchant/Orders']")).click();

		assertEquals("https://dev.waffarad.com/Merchant/Orders", driver.getCurrentUrl());

		/////////// First TC (Insert letters in column of order Shipping) ///////////
		driver.findElement(By.xpath("(//*[@title ='edit'])[9]")).click();

		Thread.sleep(2000);

		assertEquals("836288", driver.findElement(By.xpath("(//*[@id ='OrderId'])[2]")).getAttribute("value"));

		assertNotEquals("jkjkjkjk", driver.findElement(By.id("OrderShipping")).getAttribute("value"));

		/////////// Second TC (Insert negative number in column of order Shipping)  ///////////

		driver.navigate().back() ;
		assertEquals("https://dev.waffarad.com/Merchant/Orders", driver.getCurrentUrl());

		Thread.sleep(2000);

		driver.findElement(By.xpath("(//*[@title ='edit'])[10]")).click();

		Thread.sleep(2000);

		assertEquals("837492", driver.findElement(By.xpath("(//*[@id ='OrderId'])[2]")).getAttribute("value"));

		assertNotEquals("-1000", driver.findElement(By.id("OrderShipping")).getText());

		/////////// Third TC (Insert order Shipping greater than order Total)  ///////////
		driver.navigate().back();
		assertEquals("https://dev.waffarad.com/Merchant/Orders", driver.getCurrentUrl());

		Thread.sleep(2000);

		driver.findElement(By.xpath("(//*[@title ='edit'])[11]")).click();

		Thread.sleep(2000);

		assertEquals("838168", driver.findElement(By.xpath("(//*[@id ='OrderId'])[2]")).getAttribute("value"));

		assertNotEquals("3000", driver.findElement(By.id("OrderShipping")).getAttribute("value"));
	}

	@SuppressWarnings("deprecation")
	@Test
	public void TestCases_OrderDiscount() throws IOException, InterruptedException
	{
		test =extent.startTest("TestCases_OrderDiscount");

		driver = new ChromeDriver();

		driver.navigate().to("https://dev.waffarad.com/Merchant/Account/Login?ReturnURL=/");
		driver.manage().window().maximize();

		driver.findElement(By.id("UserName")).sendKeys("admin.merchant@waffarad.com");

		driver.findElement(By.id("Password")).sendKeys("M\"XwdU7]q)");

		driver.findElement(By.id("SubmitLogin")).click();

		WebElement List = driver.findElement(By.id("MerchantId"));
		Select option = new Select (List);
		option.selectByIndex(1); //Choice of Yashry

		driver.findElement(By.id("GotoMerchantBtn")).click();

		driver.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);

		driver.findElement(By.xpath("//*[@href='/Merchant/Orders']")).click();

		assertEquals("https://dev.waffarad.com/Merchant/Orders", driver.getCurrentUrl());

		/////////// First TC (Insert letters in column of order Discount) ///////////
		driver.findElement(By.xpath("(//*[@title ='edit'])[12]")).click();

		Thread.sleep(2000);

		assertEquals("838170", driver.findElement(By.xpath("(//*[@id ='OrderId'])[2]")).getAttribute("value"));

		assertNotEquals("jkjkjkjk", driver.findElement(By.id("OrderDiscounts")).getAttribute("value"));

		/////////// Second TC (Insert negative number in column of order Discount)  ///////////

		driver.navigate().back() ;
		assertEquals("https://dev.waffarad.com/Merchant/Orders", driver.getCurrentUrl());

		Thread.sleep(2000);

		driver.findElement(By.xpath("(//*[@title ='edit'])[13]")).click();

		Thread.sleep(2000);

		assertEquals("839703", driver.findElement(By.xpath("(//*[@id ='OrderId'])[2]")).getAttribute("value"));

		assertNotEquals("-1000", driver.findElement(By.id("OrderDiscounts")).getText());

		/////////// Third TC (Insert order Discount greater than order Total)  ///////////
		driver.navigate().back();
		assertEquals("https://dev.waffarad.com/Merchant/Orders", driver.getCurrentUrl());

		Thread.sleep(2000);

		driver.findElement(By.xpath("(//*[@title ='edit'])[14]")).click();

		Thread.sleep(2000);

		assertEquals("841624", driver.findElement(By.xpath("(//*[@id ='OrderId'])[2]")).getAttribute("value"));

		assertNotEquals("3000", driver.findElement(By.id("OrderDiscounts")).getAttribute("value"));
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void TestCases_Commision() throws IOException, InterruptedException
	{
		test =extent.startTest("TestCases_Commision");

		driver = new ChromeDriver();

		driver.navigate().to("https://dev.waffarad.com/Merchant/Account/Login?ReturnURL=/");
		driver.manage().window().maximize();

		driver.findElement(By.id("UserName")).sendKeys("admin.merchant@waffarad.com");

		driver.findElement(By.id("Password")).sendKeys("M\"XwdU7]q)");

		driver.findElement(By.id("SubmitLogin")).click();

		WebElement List = driver.findElement(By.id("MerchantId"));
		Select option = new Select (List);
		option.selectByIndex(1); //Choice of Yashry

		driver.findElement(By.id("GotoMerchantBtn")).click();

		driver.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);

		driver.findElement(By.xpath("//*[@href='/Merchant/Orders']")).click();

		assertEquals("https://dev.waffarad.com/Merchant/Orders", driver.getCurrentUrl());

		/////////// First TC (Insert letters in column of Commision) ///////////
		driver.findElement(By.xpath("(//*[@title ='edit'])[15]")).click();

		Thread.sleep(2000);

		assertEquals("841771", driver.findElement(By.xpath("(//*[@id ='OrderId'])[2]")).getAttribute("value"));

		assertNotEquals("90", driver.findElement(By.id("Commission")).getAttribute("value"));
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
		driver.quit();
	}
}