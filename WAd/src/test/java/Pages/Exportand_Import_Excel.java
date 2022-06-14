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
import io.github.bonigarcia.wdm.WebDriverManager;

public class Exportand_Import_Excel 
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
	public void ExportExcel_And_BulkUpdate() throws IOException
	{
		driver.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);

//		test =extent.startTest("ExportExcel_And_BulkUpdate");

		WebElement orders = driver.findElement(By.xpath("//*[@href='/Merchant/Orders']"));
		orders.click();

		assertEquals("https://dev.waffarad.com/Merchant/Orders", driver.getCurrentUrl()) ;

		WebElement Export_to_Excel = driver.findElement(By.xpath("//*[@href='/merchant/orders/GetOrdersXlsSheet']"));
		Export_to_Excel.click();
	}

	@SuppressWarnings("deprecation")
	@Test
	public void Click_UploadFile_Without_chooseFile() throws IOException
	{
		driver.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);

		//test =extent.startTest("Click_UploadFile_Without_chooseFile");

		WebElement orders = driver.findElement(By.xpath("//*[@href='/Merchant/Orders']"));
		orders.click();

		WebElement Upload = driver.findElement(By.xpath("//*[@id='modalBtn']"));
		Upload.click();

		WebElement UploadFile_button = driver.findElement(By.xpath("//*[@type='submit']"));
		UploadFile_button.click();

		WebElement error = driver.findElement(By.id("FileRequired"));
		assertEquals("Please select file", error.getText()) ;
	}

	@SuppressWarnings("deprecation")
	@Test
	public void Upload_wrong_file_type() throws IOException //Upload wrong file type
	{
		driver.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);

		//test =extent.startTest("Upload_wrong_file_type");

		WebElement orders = driver.findElement(By.xpath("//*[@href='/Merchant/Orders']"));
		orders.click();

		assertEquals("https://dev.waffarad.com/Merchant/Orders", driver.getCurrentUrl()) ;

		WebElement Upload = driver.findElement(By.xpath("//*[@id='modalBtn']"));
		Upload.click();

		driver.findElement(By.xpath("//*[@id='OrdersFile']")).sendKeys("C:\\Users\\n.jamal\\Downloads\\Nohair_Gamal.pdf");

		WebElement UploadFile_button = driver.findElement(By.xpath("//*[@type='submit']"));
		UploadFile_button.click();

		WebElement Error = driver.findElement(By.xpath("//*[@role='alert'][2]"));
		Error.click();
		assertEquals("Warning!:  Wrong File Type!.", Error.getText()) ;
	}

	@AfterMethod
	public void quit(ITestResult result)
	{
		driver.quit();	
	}
}