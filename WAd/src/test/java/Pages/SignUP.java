package Pages;

import static org.testng.Assert.assertEquals;
import java.io.IOException;
import org.apache.commons.lang.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SignUP 
{
	WebDriver driver;
	@BeforeMethod
	public void openURL()
	{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.navigate().to("https://dev.waffarad.com/Merchant/Account/Login?ReturnURL=/");
		driver.manage().window().maximize();
		WebElement Create_account_button = driver.findElement(By.id("register-btn"));
		Create_account_button.click();
	}
	@Test
	public void SignUP_ValidData() throws IOException
	{
	//	test = extent.startTest("SignUP_ValidData");

		WebElement MerchantName = driver.findElement(By.id("MerchantName"));
		MerchantName.clear();
		MerchantName.sendKeys("Nohair");

		WebElement MerchantURl = driver.findElement(By.id("MerchantURl"));
		MerchantURl.clear();
		MerchantURl.sendKeys("https://www.example@example.com");

		WebElement MerchantDescription = driver.findElement(By.id("MerchantDescription"));
		MerchantDescription.clear();
		MerchantDescription.sendKeys("Description");

		String GenerateString = RandomStringUtils.randomAlphabetic(8); //These 2 lines to generate random mail 
		String mailRandom = GenerateString + "@gmail.com";

		WebElement Email = driver.findElement(By.id("Email"));
		Email.clear();
		Email.sendKeys(mailRandom);

		WebElement MobileNumber = driver.findElement(By.id("MobileNumber"));
		MobileNumber.clear();
		MobileNumber.sendKeys("01001889892");

		WebElement Password = driver.findElement(By.id("password-show1"));
		Password.clear();
		Password.sendKeys("88888888");

		WebElement Show_Password = driver.findElement(By.id("ChangeEYE1"));	//To show password after insert it
		Show_Password.click();

		WebElement ConfirmPassword = driver.findElement(By.name("ConfirmPassword"));
		ConfirmPassword.clear();
		ConfirmPassword.sendKeys("88888888");

		WebElement List_Currency = driver.findElement(By.id("Currency"));
		Select select_options = new Select(List_Currency);
		select_options.selectByValue("USD");

		WebElement SubmitRegister = driver.findElement(By.id("SubmitRegister"));	
		SubmitRegister.click();

		assertEquals("https://dev.waffarad.com/Merchant", driver.getCurrentUrl()) ;

		driver.findElement(By.linkText("User")).click();

		driver.findElement(By.linkText("Log Out")).click();
		assertEquals(driver.getCurrentUrl(), "https://dev.waffarad.com/Merchant/Account/Login");
	}

	@Test
	public void SignUP_ValidData_WithInsert_any_data_In_Description() throws IOException //All fields correct but insert any data field Description
	{
		//test = extent.startTest("SignUP_ValidData_WithInsert_any_data_In_Description");
		WebElement MerchantName = driver.findElement(By.id("MerchantName"));
		MerchantName.clear();
		MerchantName.sendKeys("Nohair");

		WebElement MerchantURl = driver.findElement(By.id("MerchantURl"));
		MerchantURl.clear();
		MerchantURl.sendKeys("https://www.example@example.com");

		WebElement MerchantDescription = driver.findElement(By.id("MerchantDescription"));	
		MerchantDescription.clear();
		MerchantDescription.sendKeys("@!@@@30893092");
		
		String GenerateString = RandomStringUtils.randomAlphabetic(8); //These 2 lines to generate random mail 
		String mailRandom = GenerateString + "@gmail.com";

		WebElement Email = driver.findElement(By.id("Email"));
		Email.clear();
		Email.sendKeys(mailRandom);
		
		WebElement MobileNumber = driver.findElement(By.id("MobileNumber"));
		MobileNumber.clear();
		MobileNumber.sendKeys("01001889892");

		WebElement Password = driver.findElement(By.id("password-show1"));
		Password.clear();
		Password.sendKeys("88888888");

		WebElement Show_Password = driver.findElement(By.id("ChangeEYE1"));	//To show password after insert it
		Show_Password.click();

		WebElement ConfirmPassword = driver.findElement(By.name("ConfirmPassword"));
		ConfirmPassword.clear();
		ConfirmPassword.sendKeys("88888888");

		WebElement List_Currency = driver.findElement(By.id("Currency"));	
		Select select_options = new Select(List_Currency);
		select_options.selectByValue("USD");

		WebElement SubmitRegister = driver.findElement(By.id("SubmitRegister"));	
		SubmitRegister.click();

		assertEquals("https://dev.waffarad.com/Merchant", driver.getCurrentUrl()) ;
	}
	@Test
	public void SignUP_CorrectData_Butinsert_numberWhichExistBefore_in_phone() throws IOException //All fields correct but insert number which exist before in field phone
	{
		//test = extent.startTest("SignUP_CorrectData_Butinsert_numberWhichExistBefore_in_phone");

		WebElement MerchantName = driver.findElement(By.id("MerchantName"));
		MerchantName.clear();
		MerchantName.sendKeys("Nohair");

		WebElement MerchantURl = driver.findElement(By.id("MerchantURl"));
		MerchantURl.clear();
		MerchantURl.sendKeys("https://www.example@example.com");

		WebElement MerchantDescription = driver.findElement(By.id("MerchantDescription"));	
		MerchantDescription.clear();
		MerchantDescription.sendKeys("Description");

		String GenerateString = RandomStringUtils.randomAlphabetic(8); //These 2 lines to generate random mail 
		String mailRandom = GenerateString + "@gmail.com";

		WebElement Email = driver.findElement(By.id("Email"));
		Email.clear();
		Email.sendKeys(mailRandom);
		
		WebElement MobileNumber = driver.findElement(By.id("MobileNumber"));
		MobileNumber.clear();
		MobileNumber.sendKeys("01001889892");

		WebElement Password = driver.findElement(By.id("password-show1"));
		Password.clear();
		Password.sendKeys("88888888");

		WebElement Show_Password = driver.findElement(By.id("ChangeEYE1"));	//To show password after insert it
		Show_Password.click();

		WebElement ConfirmPassword = driver.findElement(By.name("ConfirmPassword"));	
		ConfirmPassword.clear();
		ConfirmPassword.sendKeys("88888888");

		WebElement List_Currency = driver.findElement(By.id("Currency"));	
		Select select_options = new Select(List_Currency);
		select_options.selectByValue("USD");

		WebElement SubmitRegister = driver.findElement(By.id("SubmitRegister"));	
		SubmitRegister.click();

		assertEquals("https://dev.waffarad.com/Merchant", driver.getCurrentUrl()) ;
	}
	@Test
	public void SignUP_All_fields_empty() throws IOException //All fields empty
	{
		//test = extent.startTest("SignUP_All_fields_empty");

		driver.findElement(By.id("MerchantName")).clear();

		driver.findElement(By.id("MerchantURl")).clear();

		driver.findElement(By.id("MerchantDescription")).clear();

		driver.findElement(By.id("Email")).clear();

		driver.findElement(By.id("MobileNumber")).clear();

		driver.findElement(By.id("password-show1")).clear();

		driver.findElement(By.name("ConfirmPassword")).clear();

		driver.findElement(By.id("SubmitRegister")).click();

		WebElement Error = driver.findElement(By.id("MerchantName-error"));
		Assert.assertEquals("Name is required", Error.getText());
	}
	@Test
	public void SignUP_CorrectData_Butinsert_spaces_only_in_name() throws IOException //All fields correct but insert spaces only in field name
	{
	//	test = extent.startTest("SignUP_CorrectData_Butinsert_spaces_only_in_name");

		WebElement MerchantName = driver.findElement(By.id("MerchantName"));
		MerchantName.clear();
		MerchantName.sendKeys("   ");

		WebElement MerchantURl = driver.findElement(By.id("MerchantURl"));
		MerchantURl.clear();
		MerchantURl.sendKeys("https://www.example@example.com");

		WebElement MerchantDescription = driver.findElement(By.id("MerchantDescription"));
		MerchantDescription.clear();
		MerchantDescription.sendKeys("Description");

		String GenerateString = RandomStringUtils.randomAlphabetic(8); //These 2 lines to generate random mail
		String mailRandom = GenerateString + "@gmail.com";

		WebElement Email = driver.findElement(By.id("Email"));
		Email.clear();
		Email.sendKeys(mailRandom);

		WebElement MobileNumber = driver.findElement(By.id("MobileNumber"));
		MobileNumber.clear();
		MobileNumber.sendKeys("01001889892");

		WebElement Password = driver.findElement(By.id("password-show1"));
		Password.clear();
		Password.sendKeys("88888888");

		WebElement ConfirmPassword = driver.findElement(By.name("ConfirmPassword"));
		ConfirmPassword.clear();
		ConfirmPassword.sendKeys("88888888");

		WebElement List_Currency = driver.findElement(By.id("Currency"));
		Select select_options = new Select(List_Currency);
		select_options.selectByValue("USD");

		WebElement SubmitRegister = driver.findElement(By.id("SubmitRegister"));
		SubmitRegister.click();

		WebElement Error = driver.findElement(By.xpath("//*[@data-valmsg-for= 'MerchantName']"));
		String ExpectedMessage = "Name is required";
		Assert.assertEquals(ExpectedMessage, Error.getText());
	}

	@Test
	public void SignUP_CorrectData_Butinsert_NumbersandSymbols_in_name() throws IOException//All fields correct but insert insert Numbers and symbols in field name
	{
	//	test = extent.startTest("SignUP_CorrectData_Butinsert_NumbersandSymbols_in_name");

		WebElement MerchantName = driver.findElement(By.id("MerchantName"));
		MerchantName.clear();
		MerchantName.sendKeys("32090933@#@#@#@#209");

		WebElement MerchantURl = driver.findElement(By.id("MerchantURl"));
		MerchantURl.clear();
		MerchantURl.sendKeys("https://www.example@example.com");

		WebElement MerchantDescription = driver.findElement(By.id("MerchantDescription"));	
		MerchantDescription.clear();
		MerchantDescription.sendKeys("Description");

		String GenerateString = RandomStringUtils.randomAlphabetic(8); //These 2 lines to generate random mail 
		String mailRandom = GenerateString + "@gmail.com";

		WebElement Email = driver.findElement(By.id("Email"));
		Email.clear();
		Email.sendKeys(mailRandom);

		WebElement MobileNumber = driver.findElement(By.id("MobileNumber"));
		MobileNumber.clear();
		MobileNumber.sendKeys("01001889892");

		WebElement Password = driver.findElement(By.id("password-show1"));
		Password.clear();
		Password.sendKeys("88888888");

		WebElement ConfirmPassword = driver.findElement(By.name("ConfirmPassword"));
		ConfirmPassword.clear();
		ConfirmPassword.sendKeys("88888888");

		WebElement List_Currency = driver.findElement(By.id("Currency"));
		Select select_options = new Select(List_Currency);
		select_options.selectByValue("USD");

		driver.findElement(By.id("SubmitRegister")).click();

		WebElement Error = driver.findElement(By.id("MerchantName-error"));
		Assert.assertEquals("Name must be characters only",Error.getText()) ;
	}
	@Test
	public void SignUP_CorrectData_Butinsert_Wrong_Format_in_URL() throws IOException //All fields correct but insert Wrong Format in URL
	{
		//test = extent.startTest("SignUP_CorrectData_Butinsert_Wrong_Format_in_URL");

		WebElement MerchantName = driver.findElement(By.id("MerchantName"));
		MerchantName.clear();
		MerchantName.sendKeys("Nohair");

		WebElement MerchantURl = driver.findElement(By.id("MerchantURl"));
		MerchantURl.clear();
		MerchantURl.sendKeys("http");

		WebElement MerchantDescription = driver.findElement(By.id("MerchantDescription"));	
		MerchantDescription.clear();
		MerchantDescription.sendKeys("Description");

		String GenerateString = RandomStringUtils.randomAlphabetic(8); //These 2 lines to generate random mail 
		String mailRandom = GenerateString + "@gmail.com";

		WebElement Email = driver.findElement(By.id("Email"));
		Email.clear();
		Email.sendKeys(mailRandom);
		
		WebElement MobileNumber = driver.findElement(By.id("MobileNumber"));
		MobileNumber.clear();
		MobileNumber.sendKeys("01001889892");

		WebElement Password = driver.findElement(By.id("password-show1"));
		Password.clear();
		Password.sendKeys("88888888");

		WebElement ConfirmPassword = driver.findElement(By.name("ConfirmPassword"));
		ConfirmPassword.clear();
		ConfirmPassword.sendKeys("88888888");

		WebElement List_Currency = driver.findElement(By.id("Currency"));
		Select select_options = new Select(List_Currency);
		select_options.selectByValue("USD");

		driver.findElement(By.id("SubmitRegister")).click();

		WebElement Error = driver.findElement(By.id("MerchantURl-error"));
		Assert.assertEquals("Url must be like https://www.example@example.com", Error.getText()) ;
	}

	@Test
	public void SignUP_CorrectData_Butinsert_Wrong_Format_in_Mail() throws IOException //All fields correct but insert Wrong Format in Mail
	{
		//test = extent.startTest("SignUP_CorrectData_Butinsert_Wrong_Format_in_Mail");

		WebElement MerchantName = driver.findElement(By.id("MerchantName"));
		MerchantName.clear();
		MerchantName.sendKeys("Nohair");

		WebElement MerchantURl = driver.findElement(By.id("MerchantURl"));
		MerchantURl.clear();
		MerchantURl.sendKeys("https://www.example@example.com");

		WebElement MerchantDescription = driver.findElement(By.id("MerchantDescription"));	
		MerchantDescription.clear();
		MerchantDescription.sendKeys("Description");

		WebElement Email = driver.findElement(By.id("Email"));
		Email.clear();
		Email.sendKeys("jkjkjk");
		
		WebElement MobileNumber = driver.findElement(By.id("MobileNumber"));
		MobileNumber.clear();
		MobileNumber.sendKeys("01001889892");

		WebElement Password = driver.findElement(By.id("password-show1"));
		Password.clear();
		Password.sendKeys("88888888");

		WebElement ConfirmPassword = driver.findElement(By.name("ConfirmPassword"));
		ConfirmPassword.clear();
		ConfirmPassword.sendKeys("88888888");

		WebElement List_Currency = driver.findElement(By.id("Currency"));
		Select select_options = new Select(List_Currency);
		select_options.selectByValue("USD");

		driver.findElement(By.id("SubmitRegister")).click();

		WebElement Error = driver.findElement(By.id("Email-error"));
		Assert.assertEquals("Email is invalid", Error.getText()) ;
	}

	@Test
	public void SignUP_CorrectData_Butinsert_email_exist_before_in_Mail() throws IOException //All fields correct but insert email exist before in Mail
	{
	//	test = extent.startTest("SignUP_CorrectData_Butinsert_email_exist_before_in_Mail");

		WebElement MerchantName = driver.findElement(By.id("MerchantName"));
		MerchantName.clear();
		MerchantName.sendKeys("Nohair");

		WebElement MerchantURl = driver.findElement(By.id("MerchantURl"));
		MerchantURl.clear();
		MerchantURl.sendKeys("https://www.example@example.com");

		WebElement MerchantDescription = driver.findElement(By.id("MerchantDescription"));	
		MerchantDescription.clear();
		MerchantDescription.sendKeys("Description");

		WebElement Email = driver.findElement(By.id("Email"));
		Email.clear();
		Email.sendKeys("admin.merchant@waffarad.com");

		WebElement MobileNumber = driver.findElement(By.id("MobileNumber"));
		MobileNumber.clear();
		MobileNumber.sendKeys("01001889892");

		WebElement Password = driver.findElement(By.id("password-show1"));
		Password.clear();
		Password.sendKeys("88888888");

		WebElement ConfirmPassword = driver.findElement(By.name("ConfirmPassword"));
		ConfirmPassword.clear();
		ConfirmPassword.sendKeys("88888888");

		WebElement List_Currency = driver.findElement(By.id("Currency"));
		Select select_options = new Select(List_Currency);
		select_options.selectByValue("USD");

		driver.findElement(By.id("SubmitRegister")).click();

		Assert.assertEquals("https://dev.waffarad.com/Merchant/Account/Register", driver.getCurrentUrl()) ;
	}

	@Test
	public void SignUP_CorrectData_Butinsert_lettersandsymbols_in_phone() throws IOException//All fields correct but insert letters and symbols in phone
	{
		//test = extent.startTest("SignUP_CorrectData_Butinsert_lettersandsymbols_in_phone");

		WebElement MerchantName = driver.findElement(By.id("MerchantName"));
		MerchantName.clear();
		MerchantName.sendKeys("Nohair");

		WebElement MerchantURl = driver.findElement(By.id("MerchantURl"));
		MerchantURl.clear();
		MerchantURl.sendKeys("https://www.example@example.com");

		WebElement MerchantDescription = driver.findElement(By.id("MerchantDescription"));	
		MerchantDescription.clear();
		MerchantDescription.sendKeys("Description");

		String GenerateString = RandomStringUtils.randomAlphabetic(8); //These 2 lines to generate random mail 
		String mailRandom = GenerateString + "@gmail.com";

		WebElement Email = driver.findElement(By.id("Email"));
		Email.clear();
		Email.sendKeys(mailRandom);
		
		WebElement MobileNumber = driver.findElement(By.id("MobileNumber"));
		MobileNumber.clear();
		MobileNumber.sendKeys("nodjjkjks@@!@@!@@");

		WebElement Password = driver.findElement(By.id("password-show1"));
		Password.clear();
		Password.sendKeys("88888888");

		WebElement ConfirmPassword = driver.findElement(By.name("ConfirmPassword"));
		ConfirmPassword.clear();
		ConfirmPassword.sendKeys("88888888");

		WebElement List_Currency = driver.findElement(By.id("Currency"));
		Select select_options = new Select(List_Currency);
		select_options.selectByValue("USD");

		driver.findElement(By.id("SubmitRegister")).click();

		WebElement Error = driver.findElement(By.id("MobileNumber-error"));
		Assert.assertEquals("Mobile number must be between 7 and 11 digits", Error.getText()) ;
	}

	@Test
	public void SignUP_CorrectData_Butinsert_value_less_than_minimum_limitation_in_phone() throws IOException
	//All fields correct but insert value less than minimum limitation in phone
	{
	//	test = extent.startTest("SignUP_CorrectData_Butinsert_value_less_than_minimum_limitation_in_phone");

		WebElement MerchantName = driver.findElement(By.id("MerchantName"));
		MerchantName.clear();
		MerchantName.sendKeys("Nohair");

		WebElement MerchantURl = driver.findElement(By.id("MerchantURl"));
		MerchantURl.clear();
		MerchantURl.sendKeys("https://www.example@example.com");

		WebElement MerchantDescription = driver.findElement(By.id("MerchantDescription"));	
		MerchantDescription.clear();
		MerchantDescription.sendKeys("Description");

		String GenerateString = RandomStringUtils.randomAlphabetic(8); //These 2 lines to generate random mail 
		String mailRandom = GenerateString + "@gmail.com";

		WebElement Email = driver.findElement(By.id("Email"));
		Email.clear();
		Email.sendKeys(mailRandom);

		WebElement MobileNumber = driver.findElement(By.id("MobileNumber"));
		MobileNumber.clear();
		MobileNumber.sendKeys("010");

		WebElement Password = driver.findElement(By.id("password-show1"));
		Password.clear();
		Password.sendKeys("88888888");

		WebElement ConfirmPassword = driver.findElement(By.name("ConfirmPassword"));
		ConfirmPassword.clear();
		ConfirmPassword.sendKeys("88888888");

		WebElement List_Currency = driver.findElement(By.id("Currency"));
		Select select_options = new Select(List_Currency);
		select_options.selectByValue("USD");

		driver.findElement(By.id("SubmitRegister")).click();

		WebElement Error = driver.findElement(By.id("MobileNumber-error"));
		Assert.assertEquals("Mobile number must be between 7 and 11 digits", Error.getText()) ;
	}

	@Test
	public void SignUP_CorrectData_Butinsert_value_Greater_than_maximum_limitation_in_phone() throws IOException
	//All fields correct but insert value Greater than maximum limitation in phone
	{
		//test = extent.startTest("SignUP_CorrectData_Butinsert_value_Greater_than_maximum_limitation_in_phone");

		WebElement MerchantName = driver.findElement(By.id("MerchantName"));
		MerchantName.clear();
		MerchantName.sendKeys("Nohair");

		WebElement MerchantURl = driver.findElement(By.id("MerchantURl"));
		MerchantURl.clear();
		MerchantURl.sendKeys("https://www.example@example.com");

		WebElement MerchantDescription = driver.findElement(By.id("MerchantDescription"));	
		MerchantDescription.clear();
		MerchantDescription.sendKeys("Description");

		String GenerateString = RandomStringUtils.randomAlphabetic(8); //These 2 lines to generate random mail 
		String mailRandom = GenerateString + "@gmail.com";

		WebElement Email = driver.findElement(By.id("Email"));
		Email.clear();
		Email.sendKeys(mailRandom);
		
		WebElement MobileNumber = driver.findElement(By.id("MobileNumber"));
		MobileNumber.clear();
		MobileNumber.sendKeys("010221232350934309304930493049");

		WebElement Password = driver.findElement(By.id("password-show1"));
		Password.clear();
		Password.sendKeys("88888888");

		WebElement ConfirmPassword = driver.findElement(By.name("ConfirmPassword"));
		ConfirmPassword.clear();
		ConfirmPassword.sendKeys("88888888");

		WebElement List_Currency = driver.findElement(By.id("Currency"));
		Select select_options = new Select(List_Currency);
		select_options.selectByValue("USD");

		driver.findElement(By.id("SubmitRegister")).click();

		WebElement Error = driver.findElement(By.id("MobileNumber-error"));
		Assert.assertEquals("Mobile number must be between 7 and 11 digits", Error.getText()) ;
	}

	@Test
	public void SignUP_CorrectData_Butinsert_value_less_than_minimum_limitation_in_password() throws IOException
	//All fields correct but insert value less than minimum limitation in password
	{
		//test = extent.startTest("SignUP_CorrectData_Butinsert_value_less_than_minimum_limitation_in_password");

		WebElement MerchantName = driver.findElement(By.id("MerchantName"));
		MerchantName.clear();
		MerchantName.sendKeys("Nohair");

		WebElement MerchantURl = driver.findElement(By.id("MerchantURl"));
		MerchantURl.clear();
		MerchantURl.sendKeys("https://www.example@example.com");

		WebElement MerchantDescription = driver.findElement(By.id("MerchantDescription"));	
		MerchantDescription.clear();
		MerchantDescription.sendKeys("Description");

		String GenerateString = RandomStringUtils.randomAlphabetic(8);//These 2 lines to generate random mail 
		String mailRandom = GenerateString + "@gmail.com";

		WebElement Email = driver.findElement(By.id("Email"));
		Email.clear();
		Email.sendKeys(mailRandom);

		WebElement MobileNumber = driver.findElement(By.id("MobileNumber"));
		MobileNumber.clear();
		MobileNumber.sendKeys("01022123235");

		WebElement Password = driver.findElement(By.id("password-show1"));
		Password.clear();
		Password.sendKeys("88");

		WebElement ConfirmPassword = driver.findElement(By.name("ConfirmPassword"));
		ConfirmPassword.clear();
		ConfirmPassword.sendKeys("88");

		WebElement List_Currency = driver.findElement(By.id("Currency"));
		Select select_options = new Select(List_Currency);
		select_options.selectByValue("USD");

		driver.findElement(By.id("SubmitRegister")).click();

		WebElement Error = driver.findElement(By.id("password-show1-error"));
		Assert.assertEquals("Password must be at least 8 characters", Error.getText()) ;
	}

	@Test
	public void SignUP_CorrectData_Butinsert_value_Greater_than_maximum_limitation_in_password() throws IOException
	//All fields correct but insert value Greater than maximum limitation in password
	{
		//test = extent.startTest("SignUP_CorrectData_Butinsert_value_Greater_than_maximum_limitation_in_password");

		WebElement MerchantName = driver.findElement(By.id("MerchantName"));
		MerchantName.clear();
		MerchantName.sendKeys("Nohair");

		WebElement MerchantURl = driver.findElement(By.id("MerchantURl"));
		MerchantURl.clear();
		MerchantURl.sendKeys("https://www.example@example.com");

		WebElement MerchantDescription = driver.findElement(By.id("MerchantDescription"));	
		MerchantDescription.clear();
		MerchantDescription.sendKeys("Description");

		String GenerateString = RandomStringUtils.randomAlphabetic(8); //These 2 lines to generate random mail 
		String mailRandom = GenerateString + "@gmail.com";

		WebElement Email = driver.findElement(By.id("Email"));
		Email.clear();
		Email.sendKeys(mailRandom);

		WebElement MobileNumber = driver.findElement(By.id("MobileNumber"));
		MobileNumber.clear();
		MobileNumber.sendKeys("01022123235");

		WebElement Password = driver.findElement(By.id("password-show1"));
		Password.clear();
		Password.sendKeys("123456789123456789128898"); //value which greater than 20

		WebElement ConfirmPassword = driver.findElement(By.name("ConfirmPassword"));
		ConfirmPassword.clear();
		ConfirmPassword.sendKeys("123456789123456789128898");

		WebElement List_Currency = driver.findElement(By.id("Currency"));
		Select select_options = new Select(List_Currency);
		select_options.selectByValue("USD");

		driver.findElement(By.id("SubmitRegister")).click();

		WebElement Error = driver.findElement(By.id("password-show1-error"));
		Assert.assertEquals("Password must be at max 20 characters", Error.getText()) ;
	}

	@Test
	public void SignUP_CorrectData_Butinsert_differentValuesin_password_and_confirmpassword() throws IOException
	//All fields correct but insert different Values in password and confirmpassword
	{
		//test = extent.startTest("SignUP_CorrectData_Butinsert_diffrentValuesin_password_and_confirmpassword");

		WebElement MerchantName = driver.findElement(By.id("MerchantName"));
		MerchantName.clear();
		MerchantName.sendKeys("Nohair");

		WebElement MerchantURl = driver.findElement(By.id("MerchantURl"));
		MerchantURl.clear();
		MerchantURl.sendKeys("https://www.example@example.com");

		WebElement MerchantDescription = driver.findElement(By.id("MerchantDescription"));	
		MerchantDescription.clear();
		MerchantDescription.sendKeys("Description");
		
		String GenerateString = RandomStringUtils.randomAlphabetic(8); //These 2 lines to generate random mail 
		String mailRandom = GenerateString + "@gmail.com";

		WebElement Email = driver.findElement(By.id("Email"));
		Email.clear();
		Email.sendKeys(mailRandom);

		WebElement MobileNumber = driver.findElement(By.id("MobileNumber"));
		MobileNumber.clear();
		MobileNumber.sendKeys("01022123235");

		WebElement Password = driver.findElement(By.id("password-show1"));
		Password.clear();
		Password.sendKeys("99999999");

		WebElement ConfirmPassword = driver.findElement(By.name("ConfirmPassword"));
		ConfirmPassword.clear();
		ConfirmPassword.sendKeys("88888888");

		WebElement List_Currency = driver.findElement(By.id("Currency"));
		Select select_options = new Select(List_Currency);
		select_options.selectByValue("USD");

		driver.findElement(By.id("SubmitRegister")).click();

		WebElement Error = driver.findElement(By.id("password-show2-error"));
		Assert.assertEquals("The password and confirm password do not match.", Error.getText()) ;
	}
	
	@Test
	public void BackButton() throws IOException //All fields correct but insert number which exist before in field phone
	{
		//test = extent.startTest("BackButton");
		
		WebElement BackButton = driver.findElement(By.linkText("Back"));
		BackButton.click();
		
		assertEquals("https://dev.waffarad.com/Merchant/Account/Login", driver.getCurrentUrl()) ;
	}
	
	@Test
	public void SignUP_Check_System_TerminateSpacesFrom_Mail() throws IOException
	{
		//test = extent.startTest("SignUP_Check_System_TerminateSpacesFrom_Mail");

		WebElement MerchantName = driver.findElement(By.id("MerchantName"));
		MerchantName.clear();
		MerchantName.sendKeys("Nohair");

		WebElement MerchantURl = driver.findElement(By.id("MerchantURl"));
		MerchantURl.clear();
		MerchantURl.sendKeys("https://www.example@example.com");

		WebElement MerchantDescription = driver.findElement(By.id("MerchantDescription"));
		MerchantDescription.clear();
		MerchantDescription.sendKeys("Description");

		WebElement Email = driver.findElement(By.id("Email"));
		Email.clear();
		Email.sendKeys("      Ter4@gmail.com     ");

		WebElement MobileNumber = driver.findElement(By.id("MobileNumber"));
		MobileNumber.clear();
		MobileNumber.sendKeys("01001889892");

		WebElement Password = driver.findElement(By.id("password-show1"));
		Password.clear();
		Password.sendKeys("88888888");

		WebElement Show_Password = driver.findElement(By.id("ChangeEYE1"));	//To show password after insert it
		Show_Password.click();

		WebElement ConfirmPassword = driver.findElement(By.name("ConfirmPassword"));
		ConfirmPassword.clear();
		ConfirmPassword.sendKeys("88888888");

		WebElement List_Currency = driver.findElement(By.id("Currency"));
		Select select_options = new Select(List_Currency);
		select_options.selectByValue("USD");

		WebElement SubmitRegister = driver.findElement(By.id("SubmitRegister"));	
		SubmitRegister.click();

		assertEquals("https://dev.waffarad.com/Merchant/Account/Register", driver.getCurrentUrl());
	}

	@AfterMethod
	public void quit()
	{
		driver.close();
	}
}
