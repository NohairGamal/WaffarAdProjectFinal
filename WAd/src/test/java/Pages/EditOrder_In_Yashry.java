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

public class EditOrder_In_Yashry 
{
	WebDriver driver;
	@SuppressWarnings("deprecation")
	@BeforeMethod
	public void open_URL() throws InterruptedException
	{
		WebDriverManager.chromedriver().setup();
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
	public void CheckThat_someFields_Disabled()
	{
		//test = extent.startTest("CheckThat_someFields_Disabled");

		assertFalse(driver.findElement(By.xpath("(//*[@id ='OrderId'])[2]")).isEnabled()); //Check that Order ID is disabled

		assertFalse(driver.findElement(By.id("OrderNet")).isEnabled()) ; //Check that Order Net is disabled

		assertFalse(driver.findElement(By.id("OrderTax")).isEnabled()) ; //Check that Order Tax is disabled

		assertFalse(driver.findElement(By.id("Commission")).isEnabled()) ; //Check that Order Commission is disabled

		assertFalse(driver.findElement(By.id("NumberOfCoupons")).isEnabled()) ;	//Check that NumberOfCoupons is disabled
	} 

	// ------------------- Test cases for OrderTotal (3 test cases) ------------------- //
	@Test
	public void ValidData_WithInsert_NegativeNumber_In_OrderTotal() throws InterruptedException  //Data don't save
	{
		//test = extent.startTest("ValidData_WithInsert_NegativeNumber_In_OrderTotal");

		WebElement OrderID = driver.findElement(By.xpath("(//*[@id ='OrderId'])[2]"));
		assertEquals("714688", OrderID.getAttribute("value"));

		//		WebElement OrderTotal = driver.findElement(By.id("OrderTotal"));
		//		OrderTotal.clear();
		//		OrderTotal.sendKeys("-99");
		//
		//		WebElement Order_Shipping = driver.findElement(By.id("OrderShipping"));
		//		Order_Shipping.clear();
		//		Order_Shipping.sendKeys("30");
		//
		//		WebElement Order_Discount = driver.findElement(By.id("OrderDiscounts"));
		//		Order_Discount.clear();
		//		Order_Discount.sendKeys("40");
		//
		//		WebElement Save = driver.findElement(By.xpath("//*[@type ='submit']"));
		//		Save.click();
		//
		//		Thread.sleep(3000);
		//
		//		driver.navigate().back() ;
		//		assertEquals("https://dev.waffarad.com/Merchant/Orders", driver.getCurrentUrl());
		//
		//		Thread.sleep(3000);
		//
		//		WebElement Edit_Icon = driver.findElement(By.xpath("(//*[@title ='edit'])[1]"));
		//		Edit_Icon.click();
		//
		//		Thread.sleep(3000);
		//
		//		WebElement OrderID2 = driver.findElement(By.xpath("(//*[@id ='OrderId'])[2]"));
		//		assertEquals("713982", OrderID2.getAttribute("value"));
		//
		//		WebElement OrderTotal2 = driver.findElement(By.id("OrderTotal"));
		//		assertNotEquals("-99", OrderTotal2.getAttribute("value"));
	}

	@Test
	public void ValidData_Without_InsertData_In_OrderTotal() throws InterruptedException  //Data don't save
	{
		//test = extent.startTest("ValidData_Without_InsertData_In_OrderTotal");

		WebElement OrderID = driver.findElement(By.xpath("(//*[@id ='OrderId'])[1]"));
		assertEquals("714688", OrderID.getAttribute("value"));

		WebElement OrderTotal = driver.findElement(By.id("OrderTotal"));
		OrderTotal.clear();
		OrderTotal.sendKeys("   ");

		WebElement Order_Shipping = driver.findElement(By.id("OrderShipping"));
		Order_Shipping.clear();
		Order_Shipping.sendKeys("30");

		WebElement Order_Discount = driver.findElement(By.id("OrderDiscounts"));
		Order_Discount.clear();
		Order_Discount.sendKeys("40");

		WebElement Save = driver.findElement(By.xpath("//*[@type ='submit']"));
		Save.click();

		Thread.sleep(3000);

		driver.navigate().back() ;
		assertEquals("https://dev.waffarad.com/Merchant/Orders", driver.getCurrentUrl());

		Thread.sleep(3000);

		WebElement Edit_Icon = driver.findElement(By.xpath("(//*[@title ='edit'])[2]"));
		Edit_Icon.click();

		Thread.sleep(3000);

		WebElement OrderID2 = driver.findElement(By.xpath("(//*[@id ='OrderId'])[2]"));
		assertEquals("713982", OrderID2.getAttribute("value"));

		WebElement OrderTotal2 = driver.findElement(By.id("OrderTotal"));
		assertNotEquals("   ", OrderTotal2.getAttribute("value"));
	}

	@Test
	public void ValidData_With_Insert_Zero_In_OrderTotal() throws InterruptedException  //Data don't save
	{ 
		//test = extent.startTest("ValidData_With_Insert_Zero_In_OrderTotal");

		WebElement OrderID = driver.findElement(By.xpath("(//*[@id ='OrderId'])[1]"));
		assertEquals("714688", OrderID.getAttribute("value"));

		WebElement OrderTotal = driver.findElement(By.id("OrderTotal"));
		OrderTotal.clear();
		OrderTotal.sendKeys("0");

		WebElement Order_Shipping = driver.findElement(By.id("OrderShipping"));
		Order_Shipping.clear();
		Order_Shipping.sendKeys("30");

		WebElement Order_Discount = driver.findElement(By.id("OrderDiscounts"));
		Order_Discount.clear();
		Order_Discount.sendKeys("40");

		WebElement Save = driver.findElement(By.xpath("//*[@type ='submit']"));
		Save.click();

		Thread.sleep(3000);

		driver.navigate().back() ;
		assertEquals("https://dev.waffarad.com/Merchant/Orders", driver.getCurrentUrl());

		Thread.sleep(3000);

		WebElement Edit_Icon = driver.findElement(By.xpath("(//*[@title ='edit'])[1]"));
		Edit_Icon.click();

		Thread.sleep(3000);

		WebElement OrderID2 = driver.findElement(By.xpath("(//*[@id ='OrderId'])[1]"));
		assertEquals("713982", OrderID2.getAttribute("value"));

		WebElement OrderTotal2 = driver.findElement(By.id("OrderTotal"));
		assertNotEquals("0", OrderTotal2.getAttribute("value"));
	}

	// ------------------- Test cases for OrderShipping (3 test cases) ------------------- //
	@Test
	public void ValidData_WithInsert_NegativeNumber_In_OrderShipping() throws InterruptedException  //Data don't save
	{
		//test = extent.startTest("ValidData_WithInsert_NegativeNumber_In_OrderShipping");

		WebElement OrderID = driver.findElement(By.xpath("(//*[@id ='OrderId'])[1]"));
		assertEquals("714688", OrderID.getAttribute("value"));

		WebElement OrderTotal = driver.findElement(By.id("OrderTotal"));
		OrderTotal.clear();
		OrderTotal.sendKeys("2000");

		WebElement Order_Shipping = driver.findElement(By.id("OrderShipping"));
		Order_Shipping.clear();
		Order_Shipping.sendKeys("-30");

		WebElement Order_Discount = driver.findElement(By.id("OrderDiscounts"));
		Order_Discount.clear();
		Order_Discount.sendKeys("40");

		WebElement Save = driver.findElement(By.xpath("//*[@type ='submit']"));
		Save.click();

		Thread.sleep(3000);

		driver.navigate().back() ;
		assertEquals("https://dev.waffarad.com/Merchant/Orders", driver.getCurrentUrl());

		Thread.sleep(3000);

		WebElement Edit_Icon = driver.findElement(By.xpath("(//*[@title ='edit'])[1]"));
		Edit_Icon.click();

		Thread.sleep(3000);

		WebElement OrderID2 = driver.findElement(By.xpath("(//*[@id ='OrderId'])[1]"));
		assertEquals("713982", OrderID2.getAttribute("value"));

		WebElement Order_Shipping2 = driver.findElement(By.id("OrderShipping"));
		assertNotEquals("-30", Order_Shipping2.getAttribute("value"));
	}

	@Test
	public void ValidData_Without_InsertData_In_OrderShipping() throws InterruptedException  //Data don't save
	{
		//	test = extent.startTest("ValidData_Without_InsertData_In_OrderShipping");

		WebElement OrderID = driver.findElement(By.xpath("(//*[@id ='OrderId'])[1]"));
		assertEquals("714688", OrderID.getAttribute("value"));

		WebElement OrderTotal = driver.findElement(By.id("OrderTotal"));
		OrderTotal.clear();
		OrderTotal.sendKeys("2000");

		WebElement Order_Shipping = driver.findElement(By.id("OrderShipping"));
		Order_Shipping.clear();
		Order_Shipping.sendKeys("  ");

		WebElement Order_Discount = driver.findElement(By.id("OrderDiscounts"));
		Order_Discount.clear();
		Order_Discount.sendKeys("40");

		WebElement Save = driver.findElement(By.xpath("//*[@type ='submit']"));
		Save.click();

		Thread.sleep(3000);

		driver.navigate().back() ;
		assertEquals("https://dev.waffarad.com/Merchant/Orders", driver.getCurrentUrl());

		Thread.sleep(3000);

		WebElement Edit_Icon = driver.findElement(By.xpath("(//*[@title ='edit'])[1]"));
		Edit_Icon.click();

		Thread.sleep(3000);

		WebElement OrderID2 = driver.findElement(By.xpath("(//*[@id ='OrderId'])[1]"));
		assertEquals("713982", OrderID2.getAttribute("value"));

		WebElement Order_Shipping2 = driver.findElement(By.id("OrderShipping"));
		assertNotEquals("   ", Order_Shipping2.getAttribute("value"));
	}

	@Test
	public void ValidData_With_Insert_Zero_In_OrderShipping() throws InterruptedException  //Data don't save
	{ 
		//	test = extent.startTest("ValidData_With_Insert_Zero_In_OrderShipping");

		WebElement OrderID = driver.findElement(By.xpath("(//*[@id ='OrderId'])[1]"));
		assertEquals("714688", OrderID.getAttribute("value"));

		WebElement OrderTotal = driver.findElement(By.id("OrderTotal"));
		OrderTotal.clear();
		OrderTotal.sendKeys("2000");

		WebElement Order_Shipping = driver.findElement(By.id("OrderShipping"));
		Order_Shipping.clear();
		Order_Shipping.sendKeys("0");

		WebElement Order_Discount = driver.findElement(By.id("OrderDiscounts"));
		Order_Discount.clear();
		Order_Discount.sendKeys("40");

		WebElement Save = driver.findElement(By.xpath("//*[@type ='submit']"));
		Save.click();

		Thread.sleep(3000);

		driver.navigate().back() ;
		assertEquals("https://dev.waffarad.com/Merchant/Orders", driver.getCurrentUrl());

		Thread.sleep(3000);

		WebElement Edit_Icon = driver.findElement(By.xpath("(//*[@title ='edit'])[1]"));
		Edit_Icon.click();

		Thread.sleep(3000);

		WebElement OrderID2 = driver.findElement(By.xpath("(//*[@id ='OrderId'])[1]"));
		assertEquals("714688", OrderID2.getAttribute("value"));

		WebElement Order_Shipping2 = driver.findElement(By.id("OrderShipping"));
		assertEquals("0", Order_Shipping2.getAttribute("value"));
	}

	@Test
	public void ValidData_WithInsert_OrderShipping_GreaterThan_OrderTotal() throws InterruptedException //Data don't save
	{ 
		//	test = extent.startTest("ValidData_WithInsert_OrderShipping_GreaterThan_OrderTotal");

		WebElement OrderID = driver.findElement(By.xpath("(//*[@id ='OrderId'])[1]"));
		assertEquals("714688", OrderID.getAttribute("value"));

		WebElement OrderTotal = driver.findElement(By.id("OrderTotal"));
		OrderTotal.clear();
		OrderTotal.sendKeys("2000");

		WebElement Order_Shipping = driver.findElement(By.id("OrderShipping"));
		Order_Shipping.clear();
		Order_Shipping.sendKeys("3000");

		WebElement Order_Discount = driver.findElement(By.id("OrderDiscounts"));
		Order_Discount.clear();
		Order_Discount.sendKeys("40");

		WebElement Save = driver.findElement(By.xpath("//*[@type ='submit']"));
		Save.click();

		Thread.sleep(3000);

		driver.navigate().back() ;
		assertEquals("https://dev.waffarad.com/Merchant/Orders", driver.getCurrentUrl());

		Thread.sleep(3000);

		WebElement Edit_Icon = driver.findElement(By.xpath("(//*[@title ='edit'])[1]"));
		Edit_Icon.click();

		Thread.sleep(3000);

		WebElement OrderID2 = driver.findElement(By.xpath("(//*[@id ='OrderId'])[1]"));
		assertEquals("714688", OrderID2.getAttribute("value"));

		WebElement Order_Shipping2 = driver.findElement(By.id("OrderShipping"));
		assertNotEquals("3000", Order_Shipping2.getAttribute("value"));
	}

	// ------------------- Test cases for OrderDiscount (3 test cases) ------------------- //
	@Test
	public void ValidData_WithInsert_NegativeNumber_In_OrderDiscount() throws InterruptedException  //Data don't save
	{
		//test = extent.startTest("ValidData_WithInsert_NegativeNumber_In_OrderDiscount");

		WebElement OrderID = driver.findElement(By.xpath("(//*[@id ='OrderId'])[1]"));
		assertEquals("714688", OrderID.getAttribute("value"));

		WebElement OrderTotal = driver.findElement(By.id("OrderTotal"));
		OrderTotal.clear();
		OrderTotal.sendKeys("2000");

		WebElement Order_Shipping = driver.findElement(By.id("OrderShipping"));
		Order_Shipping.clear();
		Order_Shipping.sendKeys("40");

		WebElement Order_Discount = driver.findElement(By.id("OrderDiscounts"));
		Order_Discount.clear();
		Order_Discount.sendKeys("-30");

		WebElement Save = driver.findElement(By.xpath("//*[@type ='submit']"));
		Save.click();

		Thread.sleep(3000);

		driver.navigate().back() ;
		assertEquals("https://dev.waffarad.com/Merchant/Orders", driver.getCurrentUrl());

		Thread.sleep(3000);

		WebElement Edit_Icon = driver.findElement(By.xpath("(//*[@title ='edit'])[1]"));
		Edit_Icon.click();

		Thread.sleep(3000);

		WebElement OrderID2 = driver.findElement(By.xpath("(//*[@id ='OrderId'])[1]"));
		assertEquals("714688", OrderID2.getAttribute("value"));

		WebElement Order_Discount2 = driver.findElement(By.id("OrderDiscounts"));
		assertNotEquals("-30", Order_Discount2.getAttribute("value"));
	}

	@Test
	public void ValidData_Without_InsertData_In_OrderDiscount() throws InterruptedException  //Data don't save
	{
		//	test = extent.startTest("ValidData_Without_InsertData_In_OrderDiscount");

		WebElement OrderID = driver.findElement(By.xpath("(//*[@id ='OrderId'])[1]"));
		assertEquals("714688", OrderID.getAttribute("value"));

		WebElement OrderTotal = driver.findElement(By.id("OrderTotal"));
		OrderTotal.clear();
		OrderTotal.sendKeys("2000");

		WebElement Order_Shipping = driver.findElement(By.id("OrderShipping"));
		Order_Shipping.clear();
		Order_Shipping.sendKeys("40");

		WebElement Order_Discount = driver.findElement(By.id("OrderDiscounts"));
		Order_Discount.clear();
		Order_Discount.sendKeys("  ");

		WebElement Save = driver.findElement(By.xpath("//*[@type ='submit']"));
		Save.click();

		Thread.sleep(3000);

		driver.navigate().back() ;
		assertEquals("https://dev.waffarad.com/Merchant/Orders", driver.getCurrentUrl());

		Thread.sleep(3000);

		WebElement Edit_Icon = driver.findElement(By.xpath("(//*[@title ='edit'])[1]"));
		Edit_Icon.click();

		Thread.sleep(3000);

		WebElement OrderID2 = driver.findElement(By.xpath("(//*[@id ='OrderId'])[1]"));
		assertEquals("714688", OrderID2.getAttribute("value"));

		WebElement Order_Discount2 = driver.findElement(By.id("OrderDiscounts"));
		assertNotEquals("   ", Order_Discount2.getAttribute("value"));
	}

	@Test
	public void ValidData_With_Insert_Zero_In_OrderDiscount() throws InterruptedException  //Data don't save
	{ 
		//	test = extent.startTest("ValidData_With_Insert_Zero_In_OrderDiscount");

		WebElement OrderID = driver.findElement(By.xpath("(//*[@id ='OrderId'])[1]"));
		assertEquals("714688", OrderID.getAttribute("value"));

		WebElement OrderTotal = driver.findElement(By.id("OrderTotal"));
		OrderTotal.clear();
		OrderTotal.sendKeys("2000");

		WebElement Order_Shipping = driver.findElement(By.id("OrderShipping"));
		Order_Shipping.clear();
		Order_Shipping.sendKeys("40");

		WebElement Order_Discount = driver.findElement(By.id("OrderDiscounts"));
		Order_Discount.clear();
		Order_Discount.sendKeys("0");

		WebElement Save = driver.findElement(By.xpath("//*[@type ='submit']"));
		Save.click();

		Thread.sleep(3000);

		driver.navigate().back() ;
		assertEquals("https://dev.waffarad.com/Merchant/Orders", driver.getCurrentUrl());

		Thread.sleep(3000);

		WebElement Edit_Icon = driver.findElement(By.xpath("(//*[@title ='edit'])[1]"));
		Edit_Icon.click();

		Thread.sleep(3000);

		WebElement OrderID2 = driver.findElement(By.xpath("(//*[@id ='OrderId'])[1]"));
		assertEquals("714688", OrderID2.getAttribute("value"));

		WebElement Order_Discount2 = driver.findElement(By.id("OrderDiscounts"));
		assertEquals("0", Order_Discount2.getAttribute("value"));
	}

	@Test
	public void ValidData_WithInsert_OrderDiscount_GreaterThan_OrderTotal() throws InterruptedException //Data don't save
	{ 
		//test = extent.startTest("ValidData_WithInsert_OrderDiscount_GreaterThan_OrderTotal");

		WebElement OrderID = driver.findElement(By.xpath("(//*[@id ='OrderId'])[1]"));
		assertEquals("714688", OrderID.getAttribute("value"));

		WebElement OrderTotal = driver.findElement(By.id("OrderTotal"));
		OrderTotal.clear();
		OrderTotal.sendKeys("2000");

		WebElement Order_Shipping = driver.findElement(By.id("OrderShipping"));
		Order_Shipping.clear();
		Order_Shipping.sendKeys("40");

		WebElement Order_Discount = driver.findElement(By.id("OrderDiscounts"));
		Order_Discount.clear();
		Order_Discount.sendKeys("3000");

		WebElement Save = driver.findElement(By.xpath("//*[@type ='submit']"));
		Save.click();

		Thread.sleep(3000);

		driver.navigate().back() ;
		assertEquals("https://dev.waffarad.com/Merchant/Orders", driver.getCurrentUrl());

		Thread.sleep(3000);

		WebElement Edit_Icon = driver.findElement(By.xpath("(//*[@title ='edit'])[1]"));
		Edit_Icon.click();

		Thread.sleep(3000);

		WebElement OrderID2 = driver.findElement(By.xpath("(//*[@id ='OrderId'])[1]"));
		assertEquals("714688", OrderID2.getAttribute("value"));

		WebElement Order_Discount2 = driver.findElement(By.id("OrderDiscounts"));
		assertEquals("3000", Order_Discount2.getAttribute("value"));
	}

	/*
	@Test
	public void ValidData_WithNotNewUSer_And_NoCoupouns() throws InterruptedException
	{ 
		test = extent.startTest("ValidData_WithNotNewUSer_And_NoCoupouns");

		WebElement OrderID = driver.findElement(By.xpath("(//*[@id ='OrderId'])[1]"));
		assertEquals("713982", OrderID.getAttribute("value"));

		WebElement OrderTotal = driver.findElement(By.id("OrderTotal"));
		OrderTotal.clear();
		OrderTotal.sendKeys("2000");

		WebElement Order_Shipping = driver.findElement(By.id("OrderShipping"));
		Order_Shipping.clear();
		Order_Shipping.sendKeys("200");

		WebElement Order_Discount = driver.findElement(By.id("OrderDiscounts"));
		Order_Discount.clear();
		Order_Discount.sendKeys("70");

		driver.findElement(By.id("CustomerCanBeCreated")).click(); //To make check box of is New User un selected

		WebElement Save = driver.findElement(By.xpath("//*[@type ='submit']"));
		Save.click();

		Thread.sleep(3000);

		driver.navigate().back();
		assertEquals("https://dev.waffarad.com/Merchant/Orders", driver.getCurrentUrl());

		Thread.sleep(3000);

		WebElement Edit_Icon = driver.findElement(By.xpath("(//*[@title ='edit'])[1]"));
		Edit_Icon.click();

		Thread.sleep(3000);

		WebElement OrderID2 = driver.findElement(By.xpath("(//*[@id ='OrderId'])[1]"));
		assertEquals("713982", OrderID2.getAttribute("value"));

		//Check that order tax calculated correctly
		assertEquals("221.05", driver.findElement(By.id("OrderTax")).getAttribute("value"));

		//Check that order Net calculated correctly
		assertEquals("1578.95", driver.findElement(By.id("OrderNet")).getAttribute("value"));

		assertFalse(driver.findElement(By.id("CustomerCanBeCreated")).isSelected()); //If Check box of is New User is not checked
		assertEquals("0" , driver.findElement(By.id("NumberOfCoupons")).getAttribute("value")); //If Number of coupons is Zero

		assertEquals("110.5" , driver.findElement(By.id("Commission")).getAttribute("value"));//Check value of commission

		//		driver.findElement(By.id("CustomerCanBeCreated")).click(); //To make check box of is New User checked
		//
		//		driver.findElement(By.xpath("//*[@type ='submit']")).click();
		//
		//		Thread.sleep(3000);
		//
		//		driver.navigate().back();
		//		assertEquals("https://dev.waffarad.com/Merchant/Orders", driver.getCurrentUrl());
		//
		//		Thread.sleep(3000);
		//
		//		driver.findElement(By.xpath("(//*[@title ='edit'])[1]")).click();
		//
		//		Thread.sleep(3000);
		//		
		//		//Check that we open correct order
		//		assertEquals("713982", driver.findElement(By.xpath("(//*[@id ='OrderId'])[1]")).getAttribute("value"));
		//		
		//		//Check that order tax calculated correctly
		//		assertEquals("221.05",  driver.findElement(By.id("OrderTax")).getAttribute("value"));
		//		
		//		//Check that order Net calculated correctly
		//		assertEquals("1578.95", driver.findElement(By.id("OrderNet")).getAttribute("value"));
		//		
		//		assertTrue(driver.findElement(By.id("CustomerCanBeCreated")).isSelected()); //Check that Check box of is New User is checked
		//		
		//		assertEquals("0" , driver.findElement(By.id("NumberOfCoupons")).getAttribute("value")); //If Number of coupons is Zero
		//		
		//		//Check that order commission calculated correctly
		//		assertEquals("189.47" , driver.findElement(By.id("Commission")).getAttribute("value")); 
		//		//Commission is Net*12%
	}*/

	@AfterMethod
	public void quit()
	{
		driver.close();
	}
}