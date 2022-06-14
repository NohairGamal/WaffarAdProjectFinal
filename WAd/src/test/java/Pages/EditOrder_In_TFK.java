package Pages;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotEquals;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class EditOrder_In_TFK 
{
	WebDriver driver;

	@SuppressWarnings("deprecation")
	@BeforeMethod
	public void open_URL() throws InterruptedException
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

		assertEquals("https://dev.waffarad.com/Merchant/home/ChooseMerchant", driver.getCurrentUrl());

		WebElement Title_Choose_Merchant = driver.findElement(By.xpath("//*[@for='MerchantId']"));
		assertEquals(Title_Choose_Merchant.getText(), "Choose Merchant");

		WebElement Choose_Merchant_List = driver.findElement(By.id("MerchantId"));
		Select option = new Select (Choose_Merchant_List);
		option.selectByIndex(2); //TFK

		WebElement GoToMerchant_Button = driver.findElement(By.id("GotoMerchantBtn"));
		GoToMerchant_Button.click();

		driver.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);

		WebElement orders = driver.findElement(By.xpath("//*[@href='/Merchant/Orders']"));
		orders.click();

		assertEquals("https://dev.waffarad.com/Merchant/Orders", driver.getCurrentUrl());

		Thread.sleep(3000);

		WebElement Edit_Icon = driver.findElement(By.xpath("(//*[@title ='edit'])[1]"));
		Edit_Icon.click();
		Thread.sleep(3000);

		WebElement PageTitle = driver.findElement(By.className("panel-title"));
		assertEquals("Edit Order", PageTitle.getText());
	}

	@Test
	public void CheckThat_OrderID_Disabled()
	{
	//	test = extent.startTest("CheckThat_OrderID_Disabled");

		WebElement OrderID = driver.findElement(By.xpath("(//*[@id ='OrderId'])[2]"));
		assertEquals("3712293699749", OrderID.getAttribute("value"));
		assertFalse(OrderID.isEnabled());
	}
	// ------------------- Test cases for OrderTotal (3 test cases) ------------------- //
	@Test
	public void ValidData_WithInsert_NegativeNumber_In_OrderTotal()  //Data don't save
	{
		//test = extent.startTest("ValidData_WithInsert_NegativeNumber_In_OrderTotal");

		WebElement OrderID = driver.findElement(By.xpath("(//*[@id ='OrderId'])[2]"));
		assertEquals("3712293699749", OrderID.getAttribute("value"));

		WebElement OrderTotal = driver.findElement(By.id("OrderTotal"));
		OrderTotal.clear();
		OrderTotal.sendKeys("-99");

		WebElement Order_Net = driver.findElement(By.id("OrderNet"));
		Order_Net.clear();
		Order_Net.sendKeys("500");

		WebElement Order_Tax = driver.findElement(By.id("OrderTax"));
		Order_Tax.clear();
		Order_Tax.sendKeys("20");

		WebElement Order_Shipping = driver.findElement(By.id("OrderShipping"));
		Order_Shipping.clear();
		Order_Shipping.sendKeys("30");

		WebElement Order_Discount = driver.findElement(By.id("OrderDiscounts"));
		Order_Discount.clear();
		Order_Discount.sendKeys("40");

		WebElement Order_Commission = driver.findElement(By.id("Commission"));
		Order_Commission.clear();
		Order_Commission.sendKeys("50");

		WebElement Save = driver.findElement(By.xpath("//*[@type ='submit']"));
		Save.click();

		WebElement Error = driver.findElement(By.id("OrderTotal-error"));
		assertEquals("Order Total can't be empty or less than 0 And can't be more than 10000000000000.", Error.getText());
	}

	@Test
	public void ValidData_Without_InsertData_In_OrderTotal()  //Data don't save
	{
		//test = extent.startTest("ValidData_Without_InsertData_In_OrderTotal");

		WebElement OrderID = driver.findElement(By.xpath("(//*[@id ='OrderId'])[2]"));
		assertEquals("3712293699749", OrderID.getAttribute("value"));

		WebElement OrderTotal = driver.findElement(By.id("OrderTotal"));
		OrderTotal.clear();
		OrderTotal.sendKeys("   ");

		WebElement Order_Net = driver.findElement(By.id("OrderNet"));
		Order_Net.clear();
		Order_Net.sendKeys("500");

		WebElement Order_Tax = driver.findElement(By.id("OrderTax"));
		Order_Tax.clear();
		Order_Tax.sendKeys("20");

		WebElement Order_Shipping = driver.findElement(By.id("OrderShipping"));
		Order_Shipping.clear();
		Order_Shipping.sendKeys("30");

		WebElement Order_Discount = driver.findElement(By.id("OrderDiscounts"));
		Order_Discount.clear();
		Order_Discount.sendKeys("40");

		WebElement Order_Commission = driver.findElement(By.id("Commission"));
		Order_Commission.clear();
		Order_Commission.sendKeys("50");

		WebElement Save = driver.findElement(By.xpath("//*[@type ='submit']"));
		Save.click();

		WebElement Error = driver.findElement(By.id("OrderTotal-error"));
		assertEquals("'Order Total' must not be empty.", Error.getText());
	}

	@Test
	public void ValidData_With_Insert_Zero_In_OrderTotal() throws InterruptedException  //Data don't save
	{ 
		//test = extent.startTest("ValidData_With_Insert_Zero_In_OrderTotal");

		WebElement OrderID = driver.findElement(By.xpath("(//*[@id ='OrderId'])[2]"));
		assertEquals("3712293699749", OrderID.getAttribute("value"));

		WebElement OrderTotal = driver.findElement(By.id("OrderTotal"));
		OrderTotal.clear();
		OrderTotal.sendKeys("0");

		WebElement Order_Net = driver.findElement(By.id("OrderNet"));
		Order_Net.clear();
		Order_Net.sendKeys("500");

		WebElement Order_Tax = driver.findElement(By.id("OrderTax"));
		Order_Tax.clear();
		Order_Tax.sendKeys("20");

		WebElement Order_Shipping = driver.findElement(By.id("OrderShipping"));
		Order_Shipping.clear();
		Order_Shipping.sendKeys("30");

		WebElement Order_Discount = driver.findElement(By.id("OrderDiscounts"));
		Order_Discount.clear();
		Order_Discount.sendKeys("40");

		WebElement Order_Commission = driver.findElement(By.id("Commission"));
		Order_Commission.clear();
		Order_Commission.sendKeys("50");

		WebElement Save = driver.findElement(By.xpath("//*[@type ='submit']"));
		Save.click();

		Thread.sleep(3000);

		driver.navigate().back() ;
		assertEquals("https://dev.waffarad.com/Merchant/Orders", driver.getCurrentUrl());

		Thread.sleep(3000);

		WebElement Edit_Icon = driver.findElement(By.xpath("(//*[@title ='edit'])[1]"));
		Edit_Icon.click();

		Thread.sleep(3000);

		WebElement OrderID2 = driver.findElement(By.xpath("(//*[@id ='OrderId'])[2]"));
		assertEquals("3712293699749", OrderID2.getAttribute("value"));

		WebElement OrderTotal2 = driver.findElement(By.id("OrderTotal"));
		assertNotEquals("0", OrderTotal2.getAttribute("value"));
	}

	// ------------------- Test cases for OrderNet (4 test cases) ------------------- //
	@Test
	public void ValidData_WithInsert_NegativeNumber_In_OrderNet()  //Data don't save
	{
		//test = extent.startTest("ValidData_WithInsert_NegativeNumber_In_OrderNet");

		WebElement OrderID = driver.findElement(By.xpath("(//*[@id ='OrderId'])[2]"));
		assertEquals("3712293699749", OrderID.getAttribute("value"));

		WebElement OrderTotal = driver.findElement(By.id("OrderTotal"));
		OrderTotal.clear();
		OrderTotal.sendKeys("1000");

		WebElement Order_Net = driver.findElement(By.id("OrderNet"));
		Order_Net.clear();
		Order_Net.sendKeys("-20");

		WebElement Order_Tax = driver.findElement(By.id("OrderTax"));
		Order_Tax.clear();
		Order_Tax.sendKeys("20");

		WebElement Order_Shipping = driver.findElement(By.id("OrderShipping"));
		Order_Shipping.clear();
		Order_Shipping.sendKeys("30");

		WebElement Order_Discount = driver.findElement(By.id("OrderDiscounts"));
		Order_Discount.clear();
		Order_Discount.sendKeys("40");

		WebElement Order_Commission = driver.findElement(By.id("Commission"));
		Order_Commission.clear();
		Order_Commission.sendKeys("50");

		WebElement Save = driver.findElement(By.xpath("//*[@type ='submit']"));
		Save.click();

		WebElement Error = driver.findElement(By.id("OrderNet-error"));
		assertEquals("Order Net can't be empty or less than 0 And can't be more than 10000000000000..", Error.getText());
	}

	@Test
	public void ValidData_WithInsert_Zero_In_OrderNet() throws InterruptedException //Data saves normally
	{
		//test = extent.startTest("ValidData_WithInsert_Zero_In_OrderNet");

		WebElement OrderID = driver.findElement(By.xpath("(//*[@id ='OrderId'])[2]"));
		assertEquals("3712293699749", OrderID.getAttribute("value"));

		WebElement OrderTotal = driver.findElement(By.id("OrderTotal"));
		OrderTotal.clear();
		OrderTotal.sendKeys("1000");

		WebElement Order_Net = driver.findElement(By.id("OrderNet"));
		Order_Net.clear();
		Order_Net.sendKeys("0");

		WebElement Order_Tax = driver.findElement(By.id("OrderTax"));
		Order_Tax.clear();
		Order_Tax.sendKeys("20");

		WebElement Order_Shipping = driver.findElement(By.id("OrderShipping"));
		Order_Shipping.clear();
		Order_Shipping.sendKeys("30");

		WebElement Order_Discount = driver.findElement(By.id("OrderDiscounts"));
		Order_Discount.clear();
		Order_Discount.sendKeys("40");

		WebElement Order_Commission = driver.findElement(By.id("Commission"));
		Order_Commission.clear();
		Order_Commission.sendKeys("50");

		WebElement Save = driver.findElement(By.xpath("//*[@type ='submit']"));
		Save.click();

		Thread.sleep(3000);

		driver.navigate().back();
		assertEquals("https://dev.waffarad.com/Merchant/Orders", driver.getCurrentUrl());

		Thread.sleep(3000);

		WebElement Edit_Icon = driver.findElement(By.xpath("(//*[@title ='edit'])[1]"));
		Edit_Icon.click();

		Thread.sleep(3000);

		WebElement OrderID2 = driver.findElement(By.xpath("(//*[@id ='OrderId'])[2]"));
		assertEquals("3712293699749", OrderID2.getAttribute("value"));

		WebElement Order_Net2= driver.findElement(By.id("OrderNet"));
		assertEquals("0", Order_Net2.getAttribute("value"));
	}

	@Test
	public void ValidData_WithInsert_OrderNet_GreaterThan_OrderTotal() throws InterruptedException //Data don't save
	{
		//test = extent.startTest("ValidData_WithInsert_OrderNet_GreaterThan_OrderTotal");

		WebElement OrderID = driver.findElement(By.xpath("(//*[@id ='OrderId'])[2]"));
		assertEquals("3712293699749", OrderID.getAttribute("value"));

		WebElement OrderTotal = driver.findElement(By.id("OrderTotal"));
		OrderTotal.clear();
		OrderTotal.sendKeys("1000");

		WebElement Order_Net = driver.findElement(By.id("OrderNet"));
		Order_Net.clear();
		Order_Net.sendKeys("3000");

		WebElement Order_Tax = driver.findElement(By.id("OrderTax"));
		Order_Tax.clear();
		Order_Tax.sendKeys("20");

		WebElement Order_Shipping = driver.findElement(By.id("OrderShipping"));
		Order_Shipping.clear();
		Order_Shipping.sendKeys("30");

		WebElement Order_Discount = driver.findElement(By.id("OrderDiscounts"));
		Order_Discount.clear();
		Order_Discount.sendKeys("40");

		WebElement Order_Commission = driver.findElement(By.id("Commission"));
		Order_Commission.clear();
		Order_Commission.sendKeys("50");

		WebElement Save = driver.findElement(By.xpath("//*[@type ='submit']"));
		Save.click();

		Thread.sleep(3000);

		driver.navigate().back() ;
		assertEquals("https://dev.waffarad.com/Merchant/Orders", driver.getCurrentUrl());

		Thread.sleep(3000);

		WebElement Edit_Icon = driver.findElement(By.xpath("(//*[@title ='edit'])[1]"));
		Edit_Icon.click();

		Thread.sleep(3000);

		WebElement OrderID2 = driver.findElement(By.xpath("(//*[@id ='OrderId'])[2]"));
		assertEquals("3712293699749", OrderID2.getAttribute("value"));

		WebElement Order_Net2= driver.findElement(By.xpath("//*[@id ='OrderNet']"));
		assertNotEquals("3000" ,Order_Net2.getAttribute("value")) ;
	}

	@Test
	public void ValidData_Without_insertDataIN_OrderNet() //Data don't save
	{
	//	test = extent.startTest("ValidData_Without_insertDataIN_OrderNet");

		WebElement OrderID = driver.findElement(By.xpath("(//*[@id ='OrderId'])[2]"));
		assertEquals("3712293699749", OrderID.getAttribute("value"));

		WebElement OrderTotal = driver.findElement(By.id("OrderTotal"));
		OrderTotal.clear();
		OrderTotal.sendKeys("1000");

		WebElement Order_Net = driver.findElement(By.id("OrderNet"));
		Order_Net.clear();
		Order_Net.sendKeys("");

		WebElement Order_Tax = driver.findElement(By.id("OrderTax"));
		Order_Tax.clear();
		Order_Tax.sendKeys("20");

		WebElement Order_Shipping = driver.findElement(By.id("OrderShipping"));
		Order_Shipping.clear();
		Order_Shipping.sendKeys("30");

		WebElement Order_Discount = driver.findElement(By.id("OrderDiscounts"));
		Order_Discount.clear();
		Order_Discount.sendKeys("40");

		WebElement Order_Commission = driver.findElement(By.id("Commission"));
		Order_Commission.clear();
		Order_Commission.sendKeys("50");

		WebElement Save = driver.findElement(By.xpath("//*[@type ='submit']"));
		Save.click();

		WebElement Error = driver.findElement(By.id("OrderNet-error"));
		assertEquals("The Order Net field is required.", Error.getText());
	}

	// ------------------- Test cases for OrderTax (5 test cases) ------------------- //
	@Test
	public void ValidData_WithInsert_NegativeNumber_In_OrderTax() throws InterruptedException  //Data don't save
	{
	//	test = extent.startTest("ValidData_WithInsert_NegativeNumber_In_OrderTax");

		WebElement OrderID = driver.findElement(By.xpath("(//*[@id ='OrderId'])[2]"));
		assertEquals("3712293699749", OrderID.getAttribute("value"));

		WebElement OrderTotal = driver.findElement(By.id("OrderTotal"));
		OrderTotal.clear();
		OrderTotal.sendKeys("1000");

		WebElement Order_Net = driver.findElement(By.id("OrderNet"));
		Order_Net.clear();
		Order_Net.sendKeys("500");

		WebElement Order_Tax = driver.findElement(By.id("OrderTax"));
		Order_Tax.clear();
		Order_Tax.sendKeys("-20");

		WebElement Order_Shipping = driver.findElement(By.id("OrderShipping"));
		Order_Shipping.clear();
		Order_Shipping.sendKeys("30");

		WebElement Order_Discount = driver.findElement(By.id("OrderDiscounts"));
		Order_Discount.clear();
		Order_Discount.sendKeys("40");

		WebElement Order_Commission = driver.findElement(By.id("Commission"));
		Order_Commission.clear();
		Order_Commission.sendKeys("50");

		WebElement Save = driver.findElement(By.xpath("//*[@type ='submit']"));
		Save.click();

		WebElement Error = driver.findElement(By.id("OrderTax-error"));
		assertEquals("Order Tax can't be empty or less than 0.", Error.getText());
	}

	@Test
	public void ValidData_WithInsert_Zero_In_OrderTax() throws InterruptedException  //Data save normally
	{
	//	test = extent.startTest("ValidData_WithInsert_Zero_In_OrderTax");
		
		WebElement OrderID = driver.findElement(By.xpath("(//*[@id ='OrderId'])[2]"));
		assertEquals("3712293699749", OrderID.getAttribute("value"));

		WebElement OrderTotal = driver.findElement(By.id("OrderTotal"));
		OrderTotal.clear();
		OrderTotal.sendKeys("1000");

		WebElement Order_Net = driver.findElement(By.id("OrderNet"));
		Order_Net.clear();
		Order_Net.sendKeys("500");

		WebElement Order_Tax = driver.findElement(By.id("OrderTax"));
		Order_Tax.clear();
		Order_Tax.sendKeys("0");

		WebElement Order_Shipping = driver.findElement(By.id("OrderShipping"));
		Order_Shipping.clear();
		Order_Shipping.sendKeys("30");

		WebElement Order_Discount = driver.findElement(By.id("OrderDiscounts"));
		Order_Discount.clear();
		Order_Discount.sendKeys("40");

		WebElement Order_Commission = driver.findElement(By.id("Commission"));
		Order_Commission.clear();
		Order_Commission.sendKeys("50");

		WebElement Save = driver.findElement(By.xpath("//*[@type ='submit']"));
		Save.click();
		Thread.sleep(3000);

		driver.navigate().back() ;
		assertEquals("https://dev.waffarad.com/Merchant/Orders", driver.getCurrentUrl());

		Thread.sleep(3000);

		WebElement Edit_Icon = driver.findElement(By.xpath("(//*[@title ='edit'])[1]"));
		Edit_Icon.click();
		Thread.sleep(3000);

		WebElement OrderID2 = driver.findElement(By.xpath("(//*[@id ='OrderId'])[2]"));
		assertEquals("3712293699749", OrderID2.getAttribute("value"));

		WebElement Order_Tax2 = driver.findElement(By.id("OrderTax"));
		assertEquals("0", Order_Tax2.getAttribute("value"));
	}

	@Test
	public void ValidData_WithInsert_OrderTax_GreaterThan_OrderTotal() throws InterruptedException  //Data don't save
	{
	//	test = extent.startTest("ValidData_WithInsert_OrderTax_GreaterThan_OrderTotal");
		WebElement OrderTotal = driver.findElement(By.id("OrderTotal"));
		OrderTotal.clear();
		OrderTotal.sendKeys("1000");

		WebElement Order_Net = driver.findElement(By.id("OrderNet"));
		Order_Net.clear();
		Order_Net.sendKeys("500");

		WebElement Order_Tax = driver.findElement(By.id("OrderTax"));
		Order_Tax.clear();
		Order_Tax.sendKeys("3000");

		WebElement Order_Shipping = driver.findElement(By.id("OrderShipping"));
		Order_Shipping.clear();
		Order_Shipping.sendKeys("30");

		WebElement Order_Discount = driver.findElement(By.id("OrderDiscounts"));
		Order_Discount.clear();
		Order_Discount.sendKeys("40");

		WebElement Order_Commission = driver.findElement(By.id("Commission"));
		Order_Commission.clear();
		Order_Commission.sendKeys("50");

		WebElement Save = driver.findElement(By.xpath("//*[@type ='submit']"));
		Save.click();
		Thread.sleep(3000);

		driver.navigate().back() ;
		assertEquals("https://dev.waffarad.com/Merchant/Orders", driver.getCurrentUrl());

		Thread.sleep(3000);

		WebElement Edit_Icon = driver.findElement(By.xpath("(//*[@title ='edit'])[1]"));
		Edit_Icon.click();
		Thread.sleep(3000);

		WebElement OrderID2 = driver.findElement(By.xpath("(//*[@id ='OrderId'])[2]"));
		assertEquals("3712293699749", OrderID2.getAttribute("value"));

		WebElement Order_Tax2 = driver.findElement(By.id("OrderTax"));
		assertNotEquals("3000" ,Order_Tax2.getAttribute("value")) ;
	}
	@Test
	public void ValidData_WithInsert_OrderTax_GreaterThan_OrderNet() throws InterruptedException  //Data save normally
	{
//		test = extent.startTest("ValidData_WithInsert_OrderTax_GreaterThan_OrderNet");

		WebElement OrderID = driver.findElement(By.xpath("(//*[@id ='OrderId'])[2]"));
		assertEquals("3712293699749", OrderID.getAttribute("value"));

		WebElement OrderTotal = driver.findElement(By.id("OrderTotal"));
		OrderTotal.clear();
		OrderTotal.sendKeys("1000");

		WebElement Order_Net = driver.findElement(By.id("OrderNet"));
		Order_Net.clear();
		Order_Net.sendKeys("500");

		WebElement Order_Tax = driver.findElement(By.id("OrderTax"));
		Order_Tax.clear();
		Order_Tax.sendKeys("600");

		WebElement Order_Shipping = driver.findElement(By.id("OrderShipping"));
		Order_Shipping.clear();
		Order_Shipping.sendKeys("30");

		WebElement Order_Discount = driver.findElement(By.id("OrderDiscounts"));
		Order_Discount.clear();
		Order_Discount.sendKeys("40");

		WebElement Order_Commission = driver.findElement(By.id("Commission"));
		Order_Commission.clear();
		Order_Commission.sendKeys("50");

		WebElement Save = driver.findElement(By.xpath("//*[@type ='submit']"));
		Save.click();
		Thread.sleep(3000);

		driver.navigate().back() ;
		assertEquals("https://dev.waffarad.com/Merchant/Orders", driver.getCurrentUrl());

		Thread.sleep(3000);

		WebElement Edit_Icon = driver.findElement(By.xpath("(//*[@title ='edit'])[1]"));
		Edit_Icon.click();

		Thread.sleep(3000);

		WebElement OrderID2 = driver.findElement(By.xpath("(//*[@id ='OrderId'])[2]"));
		assertEquals("3712293699749", OrderID2.getAttribute("value"));

		WebElement Order_Tax2 = driver.findElement(By.id("OrderTax"));
		assertEquals("600" ,Order_Tax2.getAttribute("value")) ;
	}

	@Test
	public void ValidData_Without_insertDataIN_OrderTax() //Data don't save
	{
	//	test = extent.startTest("ValidData_Without_insertDataIN_OrderTax");

		WebElement OrderID = driver.findElement(By.xpath("(//*[@id ='OrderId'])[2]"));
		assertEquals("3712293699749", OrderID.getAttribute("value"));

		WebElement OrderTotal = driver.findElement(By.id("OrderTotal"));
		OrderTotal.clear();
		OrderTotal.sendKeys("1000");

		WebElement Order_Net = driver.findElement(By.id("OrderNet"));
		Order_Net.clear();
		Order_Net.sendKeys("500");

		WebElement Order_Tax = driver.findElement(By.id("OrderTax"));
		Order_Tax.clear();
		Order_Tax.sendKeys("");

		WebElement Order_Shipping = driver.findElement(By.id("OrderShipping"));
		Order_Shipping.clear();
		Order_Shipping.sendKeys("30");

		WebElement Order_Discount = driver.findElement(By.id("OrderDiscounts"));
		Order_Discount.clear();
		Order_Discount.sendKeys("40");

		WebElement Order_Commission = driver.findElement(By.id("Commission"));
		Order_Commission.clear();
		Order_Commission.sendKeys("50");

		WebElement Save = driver.findElement(By.xpath("//*[@type ='submit']"));
		Save.click();

		WebElement Error = driver.findElement(By.id("OrderTax-error"));
		assertEquals("The Order Tax field is required.", Error.getText());
	}

	//	------------------- Test cases for OrderShipping (5 test cases) ------------------- //
	@Test
	public void ValidData_WithInsert_NegativeNumber_In_OrderShipping()  //Data don't save
	{
		//test = extent.startTest("ValidData_WithInsert_NegativeNumber_In_OrderShipping");

		WebElement OrderID = driver.findElement(By.xpath("(//*[@id ='OrderId'])[2]"));
		assertEquals("3712293699749", OrderID.getAttribute("value"));

		WebElement OrderTotal = driver.findElement(By.id("OrderTotal"));
		OrderTotal.clear();
		OrderTotal.sendKeys("1000");

		WebElement Order_Net = driver.findElement(By.id("OrderNet"));
		Order_Net.clear();
		Order_Net.sendKeys("500");

		WebElement Order_Tax = driver.findElement(By.id("OrderTax"));
		Order_Tax.clear();
		Order_Tax.sendKeys("20");

		WebElement Order_Shipping = driver.findElement(By.id("OrderShipping"));
		Order_Shipping.clear();
		Order_Shipping.sendKeys("-30");

		WebElement Order_Discount = driver.findElement(By.id("OrderDiscounts"));
		Order_Discount.clear();
		Order_Discount.sendKeys("40");

		WebElement Order_Commission = driver.findElement(By.id("Commission"));
		Order_Commission.clear();
		Order_Commission.sendKeys("50");

		WebElement Save = driver.findElement(By.xpath("//*[@type ='submit']"));
		Save.click();

		WebElement Error = driver.findElement(By.id("OrderShipping-error"));
		assertEquals("Order Shipping can't be empty or less than 0.", Error.getText());
	}

	@Test
	public void ValidData_WithInsert_Zero_In_OrderShipping() throws InterruptedException  //Data save normally
	{
		//	test = extent.startTest("ValidData_WithInsert_Zero_In_OrderShipping");

		WebElement OrderID = driver.findElement(By.xpath("(//*[@id ='OrderId'])[2]"));
		assertEquals("3712293699749", OrderID.getAttribute("value"));

		WebElement OrderTotal = driver.findElement(By.id("OrderTotal"));
		OrderTotal.clear();
		OrderTotal.sendKeys("1000");

		WebElement Order_Net = driver.findElement(By.id("OrderNet"));
		Order_Net.clear();
		Order_Net.sendKeys("500");

		WebElement Order_Tax = driver.findElement(By.id("OrderTax"));
		Order_Tax.clear();
		Order_Tax.sendKeys("20");

		WebElement Order_Shipping = driver.findElement(By.id("OrderShipping"));
		Order_Shipping.clear();
		Order_Shipping.sendKeys("0");

		WebElement Order_Discount = driver.findElement(By.id("OrderDiscounts"));
		Order_Discount.clear();
		Order_Discount.sendKeys("40");

		WebElement Order_Commission = driver.findElement(By.id("Commission"));
		Order_Commission.clear();
		Order_Commission.sendKeys("50");

		WebElement Save = driver.findElement(By.xpath("//*[@type ='submit']"));
		Save.click();

		Thread.sleep(3000);

		driver.navigate().back() ;
		assertEquals("https://dev.waffarad.com/Merchant/Orders", driver.getCurrentUrl());

		Thread.sleep(3000);

		WebElement Edit_Icon = driver.findElement(By.xpath("(//*[@title ='edit'])[1]"));
		Edit_Icon.click();
		Thread.sleep(3000);


		WebElement OrderID2 = driver.findElement(By.xpath("(//*[@id ='OrderId'])[2]"));
		assertEquals("3712293699749", OrderID2.getAttribute("value"));

		WebElement Order_Shipping2 = driver.findElement(By.id("OrderShipping"));
		assertEquals("0", Order_Shipping2.getAttribute("value"));
	}

	@Test
	public void ValidData_WithInsert_OrderShipping_GreaterThan_OrderTotal() throws InterruptedException  //Data don't save
	{
		//test = extent.startTest("ValidData_WithInsert_OrderShipping_GreaterThan_OrderTotal");

		WebElement OrderID = driver.findElement(By.xpath("(//*[@id ='OrderId'])[2]"));
		assertEquals("3712293699749", OrderID.getAttribute("value"));

		WebElement OrderTotal = driver.findElement(By.id("OrderTotal"));
		OrderTotal.clear();
		OrderTotal.sendKeys("1000");

		WebElement Order_Net = driver.findElement(By.id("OrderNet"));
		Order_Net.clear();
		Order_Net.sendKeys("500");

		WebElement Order_Tax = driver.findElement(By.id("OrderTax"));
		Order_Tax.clear();
		Order_Tax.sendKeys("30");

		WebElement Order_Shipping = driver.findElement(By.id("OrderShipping"));
		Order_Shipping.clear();
		Order_Shipping.sendKeys("3000");

		WebElement Order_Discount = driver.findElement(By.id("OrderDiscounts"));
		Order_Discount.clear();
		Order_Discount.sendKeys("40");

		WebElement Order_Commission = driver.findElement(By.id("Commission"));
		Order_Commission.clear();
		Order_Commission.sendKeys("50");

		WebElement Save = driver.findElement(By.xpath("//*[@type ='submit']"));
		Save.click();
		Thread.sleep(3000);

		driver.navigate().back() ;
		assertEquals("https://dev.waffarad.com/Merchant/Orders", driver.getCurrentUrl());

		Thread.sleep(3000);

		WebElement Edit_Icon = driver.findElement(By.xpath("(//*[@title ='edit'])[1]"));
		Edit_Icon.click();
		Thread.sleep(3000);

		WebElement OrderID2 = driver.findElement(By.xpath("(//*[@id ='OrderId'])[2]"));
		assertEquals("3712293699749", OrderID2.getAttribute("value"));

		WebElement Order_Shipping2 = driver.findElement(By.id("OrderShipping"));
		assertNotEquals("3000" , Order_Shipping2.getAttribute("value"));
	}

	@Test
	public void ValidData_WithInsert_OrderShipping_GreaterThan_OrderNet() throws InterruptedException  //Data save normally
	{
		//test = extent.startTest("ValidData_WithInsert_OrderShipping_GreaterThan_OrderNet");

		WebElement OrderID = driver.findElement(By.xpath("(//*[@id ='OrderId'])[2]"));
		assertEquals("3712293699749", OrderID.getAttribute("value"));

		WebElement OrderTotal = driver.findElement(By.id("OrderTotal"));
		OrderTotal.clear();
		OrderTotal.sendKeys("1000");

		WebElement Order_Net = driver.findElement(By.id("OrderNet"));
		Order_Net.clear();
		Order_Net.sendKeys("500");

		WebElement Order_Tax = driver.findElement(By.id("OrderTax"));
		Order_Tax.clear();
		Order_Tax.sendKeys("30");

		WebElement Order_Shipping = driver.findElement(By.id("OrderShipping"));
		Order_Shipping.clear();
		Order_Shipping.sendKeys("600");

		WebElement Order_Discount = driver.findElement(By.id("OrderDiscounts"));
		Order_Discount.clear();
		Order_Discount.sendKeys("40");

		WebElement Order_Commission = driver.findElement(By.id("Commission"));
		Order_Commission.clear();
		Order_Commission.sendKeys("50");

		WebElement Save = driver.findElement(By.xpath("//*[@type ='submit']"));
		Save.click();

		Thread.sleep(3000);

		driver.navigate().back();
		assertEquals("https://dev.waffarad.com/Merchant/Orders", driver.getCurrentUrl());

		Thread.sleep(3000);

		WebElement Edit_Icon = driver.findElement(By.xpath("(//*[@title ='edit'])[1]"));
		Edit_Icon.click();
		Thread.sleep(3000);

		WebElement OrderID2 = driver.findElement(By.xpath("(//*[@id ='OrderId'])[2]"));
		assertEquals("3712293699749", OrderID2.getAttribute("value"));

		WebElement Order_Shipping2 = driver.findElement(By.id("OrderShipping"));
		assertEquals("600" ,Order_Shipping2.getAttribute("value")) ;
	}

	@Test
	public void ValidData_Without_insertDataIN_OrderShipping() //Data don't save
	{
		//	test = extent.startTest("ValidData_Without_insertDataIN_OrderShipping");

		WebElement OrderID = driver.findElement(By.xpath("(//*[@id ='OrderId'])[2]"));
		assertEquals("3712293699749", OrderID.getAttribute("value"));

		WebElement OrderTotal = driver.findElement(By.id("OrderTotal"));
		OrderTotal.clear();
		OrderTotal.sendKeys("1000");

		WebElement Order_Net = driver.findElement(By.id("OrderNet"));
		Order_Net.clear();
		Order_Net.sendKeys("500");

		WebElement Order_Tax = driver.findElement(By.id("OrderTax"));
		Order_Tax.clear();
		Order_Tax.sendKeys("30");

		WebElement Order_Shipping = driver.findElement(By.id("OrderShipping"));
		Order_Shipping.clear();
		Order_Shipping.sendKeys("");

		WebElement Order_Discount = driver.findElement(By.id("OrderDiscounts"));
		Order_Discount.clear();
		Order_Discount.sendKeys("40");

		WebElement Order_Commission = driver.findElement(By.id("Commission"));
		Order_Commission.clear();
		Order_Commission.sendKeys("50");

		WebElement Save = driver.findElement(By.xpath("//*[@type ='submit']"));
		Save.click();

		WebElement Error = driver.findElement(By.id("OrderShipping-error"));
		assertEquals("The Order Shipping field is required.", Error.getText());
	}

	// ------------------- Test cases for OrderDiscount (5 test cases) ------------------- //
	@Test
	public void ValidData_WithInsert_NegativeNumber_In_OrderDiscount()  //Data don't save
	{
		//test = extent.startTest("ValidData_WithInsert_NegativeNumber_In_OrderDiscount");

		WebElement OrderID = driver.findElement(By.xpath("(//*[@id ='OrderId'])[2]"));
		assertEquals("3712293699749", OrderID.getAttribute("value"));

		WebElement OrderTotal = driver.findElement(By.id("OrderTotal"));
		OrderTotal.clear();
		OrderTotal.sendKeys("1000");

		WebElement Order_Net = driver.findElement(By.id("OrderNet"));
		Order_Net.clear();
		Order_Net.sendKeys("500");

		WebElement Order_Tax = driver.findElement(By.id("OrderTax"));
		Order_Tax.clear();
		Order_Tax.sendKeys("20");

		WebElement Order_Shipping = driver.findElement(By.id("OrderShipping"));
		Order_Shipping.clear();
		Order_Shipping.sendKeys("30");

		WebElement Order_Discounts = driver.findElement(By.id("OrderDiscounts"));
		Order_Discounts.clear();
		Order_Discounts.sendKeys("-40");

		WebElement Order_Commission = driver.findElement(By.id("Commission"));
		Order_Commission.clear();
		Order_Commission.sendKeys("50");

		WebElement Save = driver.findElement(By.xpath("//*[@type ='submit']"));
		Save.click();

		WebElement Error = driver.findElement(By.id("OrderDiscounts-error"));
		assertEquals("Order Discounts can't be empty or less than 0.", Error.getText());
	}

	@Test
	public void ValidData_WithInsert_Zero_In_OrderDiscount() throws InterruptedException  //Data save normally
	{
		//	test = extent.startTest("ValidData_WithInsert_Zero_In_OrderDiscount");

		WebElement OrderTotal = driver.findElement(By.id("OrderTotal"));
		OrderTotal.clear();
		OrderTotal.sendKeys("1000");

		WebElement Order_Net = driver.findElement(By.id("OrderNet"));
		Order_Net.clear();
		Order_Net.sendKeys("500");

		WebElement Order_Tax = driver.findElement(By.id("OrderTax"));
		Order_Tax.clear();
		Order_Tax.sendKeys("20");

		WebElement Order_Shipping = driver.findElement(By.id("OrderShipping"));
		Order_Shipping.clear();
		Order_Shipping.sendKeys("30");

		WebElement Order_Discount = driver.findElement(By.id("OrderDiscounts"));
		Order_Discount.clear();
		Order_Discount.sendKeys("0");

		WebElement Order_Commission = driver.findElement(By.id("Commission"));
		Order_Commission.clear();
		Order_Commission.sendKeys("50");

		WebElement Save = driver.findElement(By.xpath("//*[@type ='submit']"));
		Save.click();
		Thread.sleep(3000);

		driver.navigate().back() ;
		assertEquals("https://dev.waffarad.com/Merchant/Orders", driver.getCurrentUrl());
		Thread.sleep(3000);

		WebElement Edit_Icon = driver.findElement(By.xpath("(//*[@title ='edit'])[1]"));
		Edit_Icon.click();
		Thread.sleep(3000);

		WebElement OrderID2 = driver.findElement(By.xpath("(//*[@id ='OrderId'])[2]"));
		assertEquals("3712293699749", OrderID2.getAttribute("value"));

		WebElement Order_Discount2 = driver.findElement(By.id("OrderDiscounts"));
		assertEquals("0", Order_Discount2.getAttribute("value"));
	}

	@Test
	public void ValidData_Without_insertDataIN_OrderDiscount() //Data don't save
	{
		//		test = extent.startTest("ValidData_Without_insertDataIN_OrderDiscount");

		WebElement OrderID = driver.findElement(By.xpath("(//*[@id ='OrderId'])[2]"));
		assertEquals("3712293699749", OrderID.getAttribute("value"));

		WebElement OrderTotal = driver.findElement(By.id("OrderTotal"));
		OrderTotal.clear();
		OrderTotal.sendKeys("1000");

		WebElement Order_Net = driver.findElement(By.id("OrderNet"));
		Order_Net.clear();
		Order_Net.sendKeys("500");

		WebElement Order_Tax = driver.findElement(By.id("OrderTax"));
		Order_Tax.clear();
		Order_Tax.sendKeys("20");

		WebElement Order_Shipping = driver.findElement(By.id("OrderShipping"));
		Order_Shipping.clear();
		Order_Shipping.sendKeys("30");

		WebElement Order_Discount = driver.findElement(By.id("OrderDiscounts"));
		Order_Discount.clear();
		Order_Discount.sendKeys("");

		WebElement Order_Commission = driver.findElement(By.id("Commission"));
		Order_Commission.clear();
		Order_Commission.sendKeys("50");

		WebElement Save = driver.findElement(By.xpath("//*[@type ='submit']"));
		Save.click();

		WebElement Error = driver.findElement(By.id("OrderDiscounts-error"));
		assertEquals("The Order Discounts field is required.", Error.getText());
	}

	@Test
	public void ValidData_WithInsert_OrderDiscount_GreaterThan_OrderTotal() throws InterruptedException  //Data don't save
	{
		//test = extent.startTest("ValidData_WithInsert_OrderDiscount_GreaterThan_OrderTotal");

		WebElement OrderID = driver.findElement(By.xpath("(//*[@id ='OrderId'])[2]"));
		assertEquals("3712293699749", OrderID.getAttribute("value"));

		WebElement OrderTotal = driver.findElement(By.id("OrderTotal"));
		OrderTotal.clear();
		OrderTotal.sendKeys("1000");

		WebElement Order_Net = driver.findElement(By.id("OrderNet"));
		Order_Net.clear();
		Order_Net.sendKeys("500");

		WebElement Order_Tax = driver.findElement(By.id("OrderTax"));
		Order_Tax.clear();
		Order_Tax.sendKeys("20");

		WebElement Order_Shipping = driver.findElement(By.id("OrderShipping"));
		Order_Shipping.clear();
		Order_Shipping.sendKeys("30");

		WebElement Order_Discount = driver.findElement(By.id("OrderDiscounts"));
		Order_Discount.clear();
		Order_Discount.sendKeys("3000");

		WebElement Order_Commission = driver.findElement(By.id("Commission"));
		Order_Commission.clear();
		Order_Commission.sendKeys("50");

		WebElement Save = driver.findElement(By.xpath("//*[@type ='submit']"));
		Save.click();
		Thread.sleep(3000);

		driver.navigate().back() ;
		assertEquals("https://dev.waffarad.com/Merchant/Orders", driver.getCurrentUrl());
		Thread.sleep(3000);

		WebElement Edit_Icon = driver.findElement(By.xpath("(//*[@title ='edit'])[1]"));
		Edit_Icon.click();
		Thread.sleep(3000);

		WebElement OrderID2 = driver.findElement(By.xpath("(//*[@id ='OrderId'])[2]"));
		assertEquals("3712293699749", OrderID2.getAttribute("value"));

		WebElement Order_Discount2 = driver.findElement(By.id("OrderDiscounts"));
		assertNotEquals("3000" , Order_Discount2.getAttribute("value"));
	}

	@Test
	public void ValidData_WithInsert_OrderDiscount_GreaterThan_OrderNet() throws InterruptedException  //Data save normally
	{
		//test = extent.startTest("ValidData_WithInsert_OrderDiscount_GreaterThan_OrderNet");

		WebElement OrderID = driver.findElement(By.xpath("(//*[@id ='OrderId'])[2]"));
		assertEquals("3712293699749", OrderID.getAttribute("value"));

		WebElement OrderTotal = driver.findElement(By.id("OrderTotal"));
		OrderTotal.clear();
		OrderTotal.sendKeys("1000");

		WebElement Order_Net = driver.findElement(By.id("OrderNet"));
		Order_Net.clear();
		Order_Net.sendKeys("500");

		WebElement Order_Tax = driver.findElement(By.id("OrderTax"));
		Order_Tax.clear();
		Order_Tax.sendKeys("20");

		WebElement Order_Shipping = driver.findElement(By.id("OrderShipping"));
		Order_Shipping.clear();
		Order_Shipping.sendKeys("30");

		WebElement Order_Discount = driver.findElement(By.id("OrderDiscounts"));
		Order_Discount.clear();
		Order_Discount.sendKeys("600");

		WebElement Order_Commission = driver.findElement(By.id("Commission"));
		Order_Commission.clear();
		Order_Commission.sendKeys("50");

		WebElement Save = driver.findElement(By.xpath("//*[@type ='submit']"));
		Save.click();
		Thread.sleep(3000);

		driver.navigate().back() ;
		assertEquals("https://dev.waffarad.com/Merchant/Orders", driver.getCurrentUrl());
		Thread.sleep(3000);

		WebElement Edit_Icon = driver.findElement(By.xpath("(//*[@title ='edit'])[1]"));
		Edit_Icon.click();
		Thread.sleep(3000);

		WebElement OrderID2 = driver.findElement(By.xpath("(//*[@id ='OrderId'])[2]"));
		assertEquals("3712293699749", OrderID2.getAttribute("value"));

		WebElement Order_Discount2 = driver.findElement(By.id("OrderDiscounts"));
		assertEquals("600" ,Order_Discount2.getAttribute("value")) ;
	}

	// ------------------- Test cases for Order Commission (5 test cases) ------------------- //
	@Test
	public void ValidData_WithInsert_NegativeNumber_In_OrderCommission()  //Data don't save
	{
		//	test = extent.startTest("ValidData_WithInsert_NegativeNumber_In_OrderCommission");

		WebElement OrderID = driver.findElement(By.xpath("(//*[@id ='OrderId'])[2]"));
		assertEquals("3712293699749", OrderID.getAttribute("value"));

		WebElement OrderTotal = driver.findElement(By.id("OrderTotal"));
		OrderTotal.clear();
		OrderTotal.sendKeys("1000");

		WebElement Order_Net = driver.findElement(By.id("OrderNet"));
		Order_Net.clear();
		Order_Net.sendKeys("500");

		WebElement Order_Tax = driver.findElement(By.id("OrderTax"));
		Order_Tax.clear();
		Order_Tax.sendKeys("20");

		WebElement Order_Shipping = driver.findElement(By.id("OrderShipping"));
		Order_Shipping.clear();
		Order_Shipping.sendKeys("30");

		WebElement Order_Discounts = driver.findElement(By.id("OrderDiscounts"));
		Order_Discounts.clear();
		Order_Discounts.sendKeys("40");

		WebElement Order_Commission = driver.findElement(By.id("Commission"));
		Order_Commission.clear();
		Order_Commission.sendKeys("-50");

		WebElement Save = driver.findElement(By.xpath("//*[@type ='submit']"));
		Save.click();

		WebElement Error = driver.findElement(By.id("Commission-error"));
		assertEquals("Commission can't be empty or less than 0.", Error.getText());
	}

	@Test
	public void ValidData_WithInsert_Zero_In_OrderCommission() throws InterruptedException  //Data save normally
	{
		//	test = extent.startTest("ValidData_WithInsert_Zero_In_OrderCommission");

		WebElement OrderTotal = driver.findElement(By.id("OrderTotal"));
		OrderTotal.clear();
		OrderTotal.sendKeys("1000");

		WebElement Order_Net = driver.findElement(By.id("OrderNet"));
		Order_Net.clear();
		Order_Net.sendKeys("500");

		WebElement Order_Tax = driver.findElement(By.id("OrderTax"));
		Order_Tax.clear();
		Order_Tax.sendKeys("20");

		WebElement Order_Shipping = driver.findElement(By.id("OrderShipping"));
		Order_Shipping.clear();
		Order_Shipping.sendKeys("30");

		WebElement Order_Discount = driver.findElement(By.id("OrderDiscounts"));
		Order_Discount.clear();
		Order_Discount.sendKeys("0");

		WebElement Order_Commission = driver.findElement(By.id("Commission"));
		Order_Commission.clear();
		Order_Commission.sendKeys("0");

		WebElement Save = driver.findElement(By.xpath("//*[@type ='submit']"));
		Save.click();
		Thread.sleep(3000);

		driver.navigate().back() ;
		assertEquals("https://dev.waffarad.com/Merchant/Orders", driver.getCurrentUrl());
		Thread.sleep(3000);

		WebElement Edit_Icon = driver.findElement(By.xpath("(//*[@title ='edit'])[1]"));
		Edit_Icon.click();
		Thread.sleep(3000);

		WebElement OrderID2 = driver.findElement(By.xpath("(//*[@id ='OrderId'])[2]"));
		assertEquals("3712293699749", OrderID2.getAttribute("value"));

		WebElement Order_Commission2 = driver.findElement(By.id("Commission"));
		assertEquals("0", Order_Commission2.getAttribute("value"));
	}

	@Test
	public void ValidData_Without_insertDataIN_OrderCommission() //Data don't save
	{
		//	test = extent.startTest("ValidData_Without_insertDataIN_OrderCommission");

		WebElement OrderID = driver.findElement(By.xpath("(//*[@id ='OrderId'])[2]"));
		assertEquals("3712293699749", OrderID.getAttribute("value"));

		WebElement OrderTotal = driver.findElement(By.id("OrderTotal"));
		OrderTotal.clear();
		OrderTotal.sendKeys("1000");

		WebElement Order_Net = driver.findElement(By.id("OrderNet"));
		Order_Net.clear();
		Order_Net.sendKeys("500");

		WebElement Order_Tax = driver.findElement(By.id("OrderTax"));
		Order_Tax.clear();
		Order_Tax.sendKeys("20");

		WebElement Order_Shipping = driver.findElement(By.id("OrderShipping"));
		Order_Shipping.clear();
		Order_Shipping.sendKeys("30");

		WebElement Order_Discount = driver.findElement(By.id("OrderDiscounts"));
		Order_Discount.clear();
		Order_Discount.sendKeys("40");

		WebElement Order_Commission = driver.findElement(By.id("Commission"));
		Order_Commission.clear();
		Order_Commission.sendKeys("");

		WebElement Save = driver.findElement(By.xpath("//*[@type ='submit']"));
		Save.click();

		WebElement Error = driver.findElement(By.id("Commission-error"));
		assertEquals("The Order Commission field is required.", Error.getText());
	}

	@Test
	public void ValidData_WithInsert_OrderCommission_GreaterThan_OrderTotal() throws InterruptedException  //Data don't save
	{
		//test = extent.startTest("ValidData_WithInsert_OrderCommission_GreaterThan_OrderTotal");

		WebElement OrderTotal = driver.findElement(By.id("OrderTotal"));
		OrderTotal.clear();
		OrderTotal.sendKeys("1000");

		WebElement Order_Net = driver.findElement(By.id("OrderNet"));
		Order_Net.clear();
		Order_Net.sendKeys("500");

		WebElement Order_Tax = driver.findElement(By.id("OrderTax"));
		Order_Tax.clear();
		Order_Tax.sendKeys("20");

		WebElement Order_Shipping = driver.findElement(By.id("OrderShipping"));
		Order_Shipping.clear();
		Order_Shipping.sendKeys("30");

		WebElement Order_Discount = driver.findElement(By.id("OrderDiscounts"));
		Order_Discount.clear();
		Order_Discount.sendKeys("40");

		WebElement Order_Commission = driver.findElement(By.id("Commission"));
		Order_Commission.clear();
		Order_Commission.sendKeys("3000");

		WebElement Save = driver.findElement(By.xpath("//*[@type ='submit']"));
		Save.click();
		Thread.sleep(3000);

		driver.navigate().back();
		assertEquals("https://dev.waffarad.com/Merchant/Orders", driver.getCurrentUrl());
		Thread.sleep(3000);

		WebElement Edit_Icon = driver.findElement(By.xpath("(//*[@title ='edit'])[1]"));
		Edit_Icon.click();
		Thread.sleep(3000);

		WebElement OrderID2 = driver.findElement(By.xpath("(//*[@id ='OrderId'])[2]"));
		assertEquals("3712293699749", OrderID2.getAttribute("value"));

		WebElement Order_Commission2 = driver.findElement(By.id("Commission"));
		assertNotEquals("3000" , Order_Commission2.getAttribute("value"));
	}

	@Test
	public void ValidData_WithInsert_OrderCommission_GreaterThan_OrderNet() throws InterruptedException  //Data save normally
	{
		//test = extent.startTest("ValidData_WithInsert_OrderCommission_GreaterThan_OrderNet");

		WebElement OrderTotal = driver.findElement(By.id("OrderTotal"));
		OrderTotal.clear();
		OrderTotal.sendKeys("1000");

		WebElement Order_Net = driver.findElement(By.id("OrderNet"));
		Order_Net.clear();
		Order_Net.sendKeys("500");

		WebElement Order_Tax = driver.findElement(By.id("OrderTax"));
		Order_Tax.clear();
		Order_Tax.sendKeys("20");

		WebElement Order_Shipping = driver.findElement(By.id("OrderShipping"));
		Order_Shipping.clear();
		Order_Shipping.sendKeys("30");

		WebElement Order_Discount = driver.findElement(By.id("OrderDiscounts"));
		Order_Discount.clear();
		Order_Discount.sendKeys("40");

		WebElement Order_Commission = driver.findElement(By.id("Commission"));
		Order_Commission.clear();
		Order_Commission.sendKeys("600");

		WebElement Save = driver.findElement(By.xpath("//*[@type ='submit']"));
		Save.click();
		Thread.sleep(3000);

		driver.navigate().back() ;
		assertEquals("https://dev.waffarad.com/Merchant/Orders", driver.getCurrentUrl());
		Thread.sleep(3000);

		WebElement Edit_Icon = driver.findElement(By.xpath("(//*[@title ='edit'])[1]"));
		Edit_Icon.click();
		Thread.sleep(3000);

		WebElement OrderID2 = driver.findElement(By.xpath("(//*[@id ='OrderId'])[2]"));
		assertEquals("3712293699749", OrderID2.getAttribute("value"));

		WebElement Order_Commission2 = driver.findElement(By.id("Commission"));
		assertEquals("600" , Order_Commission2.getAttribute("value")) ;
	}

	@AfterMethod
	public void quit()
	{
		driver.close();
	}
}