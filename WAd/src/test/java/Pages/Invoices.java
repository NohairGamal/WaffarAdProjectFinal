package Pages;

import static org.testng.Assert.assertEquals;
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

public class Invoices
{
	WebDriver driver;

	@SuppressWarnings("deprecation")
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
		option.selectByIndex(1); //Choice of Yashry

		WebElement GoToMerchant_Button = driver.findElement(By.id("GotoMerchantBtn"));
		GoToMerchant_Button.click();
		
		driver.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);

		WebElement Invoices= driver.findElement(By.xpath("//*[@href='/Merchant/Invoice']"));
		Invoices.click();

		assertEquals("https://dev.waffarad.com/Merchant/Invoice", driver.getCurrentUrl());
		driver.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);
	}

	@Test
	public void Invoice_Details()
	{
		//test =extent.startTest("Invoice_Details");

		WebElement Invoices_Details_Button= driver.findElement(By.xpath("//*[@title='Invoice Details']"));
		Invoices_Details_Button.click();

		WebElement Field_InvoiceNumber= driver.findElement(By.xpath("//*[@for='InvoiceNumber']"));		
		assertEquals(Field_InvoiceNumber.getText(),"Invoice Number");  //This is a field in page of Invoice_Details
	}
	
	@Test
	public void Download_Invoice()
	{
		//test =extent.startTest("Download_Invoice");

		WebElement Download_Invoice= driver.findElement(By.xpath("//*[@title='Download Invoice']"));
		Download_Invoice.click();
	}
	
	@Test
	public void Pay_InvoiceWith_correctData_Buinsert_spacesInField_FirstName()
	{
	//	test =extent.startTest("Pay_InvoiceWith_correctData_Buinsert_spacesInField_FirstName");

		WebElement Pay_button= driver.findElement(By.xpath("//*[@title ='Pay Invoice']"));
		Pay_button.click();
		
		WebElement Lable_Firstname= driver.findElement(By.xpath("//*[@for='FirstName']"));
		assertEquals(Lable_Firstname.getText() , "FirstName");
	    
		WebElement Field_Firstname= driver.findElement(By.id("FirstName"));
		Field_Firstname.sendKeys("      ");
		
		WebElement Field_lastname= driver.findElement(By.id("LastName"));
		Field_lastname.sendKeys("Gamal");
		
		WebElement Field_Email = driver.findElement(By.id("Email"));
		Field_Email.sendKeys("gnohair@gmail.com");
		
		WebElement Field_Phone = driver.findElement(By.id("PhoneNumber"));
		Field_Phone.sendKeys("01087656565");
		
		WebElement Submit= driver.findElement(By.xpath("//*[@type='submit']"));
		Submit.click() ;
		
		WebElement Error= driver.findElement(By.xpath("//*[@data-valmsg-for='FirstName']"));
	    
	    assertEquals(Error.getText(),"First name is required.");
	}
	
	@Test
	public void Pay_InvoiceWith_correctData_Buinsert_SymbolsAndNumbersInField_FirstName()
	{
		//test =extent.startTest("Pay_InvoiceWith_correctData_Buinsert_SymbolsAndNumbersInField_FirstName");

		WebElement Pay_button= driver.findElement(By.xpath("//*[@title ='Pay Invoice']"));
		Pay_button.click();
		
		WebElement Lable_Firstname= driver.findElement(By.xpath("//*[@for='FirstName']"));
		assertEquals(Lable_Firstname.getText() , "FirstName");
	    
		WebElement Field_Firstname= driver.findElement(By.id("FirstName"));
		Field_Firstname.sendKeys("@#@#2323233");
		
		WebElement Field_lastname= driver.findElement(By.id("LastName"));
		Field_lastname.sendKeys("Gamal");
		
		WebElement Field_Email = driver.findElement(By.id("Email"));
		Field_Email.sendKeys("gnohair@gmail.com");
		
		WebElement Field_Phone = driver.findElement(By.id("PhoneNumber"));
		Field_Phone.sendKeys("01087656565");
		
		WebElement Submit= driver.findElement(By.xpath("//*[@type='submit']"));
		Submit.click();
		
		WebElement Error= driver.findElement(By.id("FirstName-error"));
	    
	    assertEquals(Error.getText(),"First name not valid must be characters only");
	}
	
	@Test
	public void Pay_InvoiceWith_correctData_Buinsert_SpacesInField_LastName()
	{
		//test =extent.startTest("Pay_InvoiceWith_correctData_Buinsert_SpacesInField_LastName");

		WebElement Pay_button= driver.findElement(By.xpath("//*[@title ='Pay Invoice']"));
		Pay_button.click();
		
		WebElement Lable_Firstname= driver.findElement(By.xpath("//*[@for='FirstName']"));
		assertEquals(Lable_Firstname.getText() , "FirstName");
	    
		WebElement Field_Firstname= driver.findElement(By.id("FirstName"));
		Field_Firstname.sendKeys("Nohair");
		
		WebElement Field_lastname= driver.findElement(By.id("LastName"));
		Field_lastname.sendKeys("    ");
		
		WebElement Field_Email = driver.findElement(By.id("Email"));
		Field_Email.sendKeys("gnohair@gmail.com");
		
		WebElement Field_Phone = driver.findElement(By.id("PhoneNumber"));
		Field_Phone.sendKeys("01087656565");
		
		WebElement Submit= driver.findElement(By.xpath("//*[@type='submit']"));
		Submit.click() ;
		
		WebElement Error= driver.findElement(By.xpath("//*[@data-valmsg-for='LastName']"));
	    
	    assertEquals(Error.getText(),"Last name is required.");
	}
	
	@Test
	public void Pay_InvoiceWith_correctData_Buinsert_SymbolsAndNumbersInField_LastName()
	{
//		test =extent.startTest("Pay_InvoiceWith_correctData_Buinsert_SymbolsAndNumbersInField_LastName");

		WebElement Pay_button= driver.findElement(By.xpath("//*[@title ='Pay Invoice']"));
		Pay_button.click();
		
		WebElement Lable_Firstname= driver.findElement(By.xpath("//*[@for='FirstName']"));
		assertEquals(Lable_Firstname.getText() , "FirstName");
	    
		WebElement Field_Firstname= driver.findElement(By.id("FirstName"));
		Field_Firstname.sendKeys("Nohair");
		
		WebElement Field_lastname= driver.findElement(By.id("LastName"));
		Field_lastname.sendKeys("@#@#2323");
		
		WebElement Field_Email = driver.findElement(By.id("Email"));
		Field_Email.sendKeys("gnohair@gmail.com");
		
		WebElement Field_Phone = driver.findElement(By.id("PhoneNumber"));
		Field_Phone.sendKeys("01087656565");
		
		WebElement Submit= driver.findElement(By.xpath("//*[@type='submit']"));
		Submit.click() ;
		
		WebElement Error= driver.findElement(By.id("LastName-error"));
	    
	    assertEquals(Error.getText(),"Last name not valid must be characters only");
	}

	@Test
	public void Pay_InvoiceWith_correctData_Buinsert_WrongFormat_inFieldEmail()
	{
		//test =extent.startTest("Pay_InvoiceWith_correctData_Buinsert_WrongFormat_inFieldEmail");

		WebElement Pay_button= driver.findElement(By.xpath("//*[@title ='Pay Invoice']"));
		Pay_button.click();
		
		WebElement Lable_Firstname= driver.findElement(By.xpath("//*[@for='FirstName']"));
		assertEquals(Lable_Firstname.getText() , "FirstName");
	    
		WebElement Field_Firstname= driver.findElement(By.id("FirstName"));
		Field_Firstname.sendKeys("Nohair");
		
		WebElement Field_lastname= driver.findElement(By.id("LastName"));
		Field_lastname.sendKeys("Gamal");
		
		WebElement Field_Email = driver.findElement(By.id("Email"));
		Field_Email.sendKeys("gnohai");
		
		WebElement Field_Phone = driver.findElement(By.id("PhoneNumber"));
		Field_Phone.sendKeys("01087656565");
		
		WebElement Submit= driver.findElement(By.xpath("//*[@type='submit']"));
		Submit.click() ;
		
		WebElement Error= driver.findElement(By.id("Email-error"));
	    
	    assertEquals(Error.getText(),"Email is invalid");
	}
	
	@Test
	public void Pay_InvoiceWith_correctData_Buinsert_LettersAndSymbols_InField_Phone()
	{
		//test =extent.startTest("Pay_InvoiceWith_correctData_Buinsert_LettersAndSymbols_InField_Phone");

		WebElement Pay_button= driver.findElement(By.xpath("//*[@title ='Pay Invoice']"));
		Pay_button.click();
		
		WebElement Lable_Firstname= driver.findElement(By.xpath("//*[@for='FirstName']"));
		assertEquals(Lable_Firstname.getText() , "FirstName");
	    
		WebElement Field_Firstname= driver.findElement(By.id("FirstName"));
		Field_Firstname.sendKeys("Nohair");
		
		WebElement Field_lastname= driver.findElement(By.id("LastName"));
		Field_lastname.sendKeys("Gamal");
		
		WebElement Field_Email = driver.findElement(By.id("Email"));
		Field_Email.sendKeys("gnohair@gmail.com");
		
		WebElement Field_Phone = driver.findElement(By.id("PhoneNumber"));
		Field_Phone.sendKeys("eeeeww@#@#@#@");
		
		WebElement Submit= driver.findElement(By.xpath("//*[@type='submit']"));
		Submit.click() ;
		
		WebElement Error= driver.findElement(By.id("PhoneNumber-error"));
	    
	    assertEquals(Error.getText(),"Phone number must be between 7 and 11 digits");
	}
	
	@Test
	public void Pay_InvoiceWith_correctData_Buinsert_Wronglimitation_InField_Phone()
	{
		//test =extent.startTest("Pay_InvoiceWith_correctData_Buinsert_Wronglimitation_InField_Phone");

		WebElement Pay_button= driver.findElement(By.xpath("//*[@title ='Pay Invoice']"));
		Pay_button.click();
		
		WebElement Lable_Firstname= driver.findElement(By.xpath("//*[@for='FirstName']"));
		assertEquals(Lable_Firstname.getText() , "FirstName");
	    
		WebElement Field_Firstname= driver.findElement(By.id("FirstName"));
		Field_Firstname.sendKeys("Nohair");
		
		WebElement Field_lastname= driver.findElement(By.id("LastName"));
		Field_lastname.sendKeys("Gamal");
		
		WebElement Field_Email = driver.findElement(By.id("Email"));
		Field_Email.sendKeys("gnohair@gmail.com");
		
		WebElement Field_Phone = driver.findElement(By.id("PhoneNumber"));
		Field_Phone.sendKeys("010");
		
		WebElement Submit= driver.findElement(By.xpath("//*[@type='submit']"));
		Submit.click() ;
		
		WebElement Error= driver.findElement(By.id("PhoneNumber-error"));
	    
	    assertEquals(Error.getText(),"Phone number must be between 7 and 11 digits");
	}
	
	@Test
	public void Pay_InvoiceWith_correctData_AndChoose_CreditCard()
	{
	//	test =extent.startTest("Pay_InvoiceWith_correctData_AndChoose_CreditCard");

		WebElement Pay_button= driver.findElement(By.xpath("//*[@title ='Pay Invoice']"));
		Pay_button.click();
		
		WebElement Lable_Firstname= driver.findElement(By.xpath("//*[@for='FirstName']"));
		assertEquals(Lable_Firstname.getText() , "FirstName");
	    
		WebElement Field_Firstname= driver.findElement(By.id("FirstName"));
		Field_Firstname.sendKeys("Nohair");
		
		WebElement Field_lastname= driver.findElement(By.id("LastName"));
		Field_lastname.sendKeys("Gamal");
		
		WebElement Field_Email = driver.findElement(By.id("Email"));
		Field_Email.sendKeys("gnohair@gmail.com");
		
		WebElement Field_Phone = driver.findElement(By.id("PhoneNumber"));
		Field_Phone.sendKeys("01099898989");
		
		WebElement Payment_Type_List = driver.findElement(By.id("PaymentType"));
		Select option = new Select (Payment_Type_List);
		option.selectByIndex(2);
		
		WebElement Submit= driver.findElement(By.xpath("//*[@type='submit']"));
		Submit.click();
	}
	
	@AfterMethod
	public void quit()
	{
		driver.quit();	
	}

}
