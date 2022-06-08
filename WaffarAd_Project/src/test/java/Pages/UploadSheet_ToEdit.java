package Pages;

import static org.testng.Assert.assertEquals;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import TestBase.testData;
import io.github.bonigarcia.wdm.WebDriverManager;

public class UploadSheet_ToEdit extends testData
{
	WebDriver driver;

	@BeforeMethod
	public void open_URL()
	{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.navigate().to("https://dev.waffarad.com/Merchant/Account/Login?ReturnURL=/");
		driver.manage().window().maximize() ;

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

		driver.navigate().back();
		assertEquals("https://dev.waffarad.com/Merchant/Orders", driver.getCurrentUrl());

		Thread.sleep(3000);
		
		System.out.println("ok1");
		
		WebElement orders2 = driver.findElement(By.xpath("//*[@href='/Merchant/Orders']"));
		orders2.click();

		assertEquals("https://dev.waffarad.com/Merchant/Orders", driver.getCurrentUrl()) ;
		
		System.out.println("ok2");
	}

	@Test
	public void TestCases_OrderTotal()
	{		
		WebElement OrdersTable = driver.findElement(By.xpath("//*[@id='OrdersTable']")); //To locate table

		System.out.println("ok3");	

//		List RowList = OrdersTable.findElements(By.tagName("tr"));  //To locate Rows
//
//		System.out.println("ok4");
//
//		int RowListCount = RowList.size();
//		System.out.println("No of Rows are :"+ RowListCount);		
	}
}