package Pages;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import TestBase.TestData;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Cookies extends TestData

{
	WebDriver driver;

	@BeforeMethod
	public void open_URL()
	{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();	
	}

	@Test
	public void Cookies_footcourt() throws InterruptedException
	{
		//test =extent.startTest("Cookies_footcourt");

		driver.navigate().to("https://www.footcourt-eg.com/?afftoken=31180&afsrc=waffarad&af_id=a7293ce1-a2d3-40e8-8cb5-2e1fb8b86953");
		Thread.sleep(2000);

		driver.manage().deleteAllCookies() ;

		Thread.sleep(2000);

		driver.navigate().to("https://www.footcourt-eg.com/?afftoken=31180&afsrc=waffarad&af_id=a7293ce1-a2d3-40e8-8cb5-2e1fb8b86953");
		Thread.sleep(2000);

		assertEquals("waffarad", driver.manage().getCookieNamed("afsrc").getValue()) ;
		assertEquals("31180", driver.manage().getCookieNamed("afftoken").getValue()) ;
		assertEquals("a7293ce1-a2d3-40e8-8cb5-2e1fb8b86953", driver.manage().getCookieNamed("af_id").getValue()) ;
	}

	@Test
	public void Cookies_msou() throws InterruptedException
	{
		//test =extent.startTest("Cookies_msou");

		driver.navigate().to("https://www.msou.com/?afftoken=31180&afsrc=waffarad&af_id=a7293ce1-a2d3-40e8-8cb5-2e1fb8b86953");

		Thread.sleep(2000);

		driver.manage().deleteAllCookies() ;

		Thread.sleep(2000);

		driver.navigate().to("https://www.msou.com/?afftoken=31180&afsrc=waffarad&af_id=a7293ce1-a2d3-40e8-8cb5-2e1fb8b86953");
		Thread.sleep(2000);

		assertEquals("waffarad", driver.manage().getCookieNamed("afsrc").getValue()) ;
		assertEquals("31180", driver.manage().getCookieNamed("afftoken").getValue()) ;
		assertEquals("a7293ce1-a2d3-40e8-8cb5-2e1fb8b86953", driver.manage().getCookieNamed("af_id").getValue()) ;
	}

	@Test
	public void Cookies_magmasport() throws InterruptedException
	{
		//test =extent.startTest("Cookies_magmasport");

		driver.navigate().to("https://magmasportswear.com/?afftoken=31180&afsrc=waffarad&af_id=a7293ce1-a2d3-40e8-8cb5-2e1fb8b86953");	

		Thread.sleep(2000);

		driver.manage().deleteAllCookies() ;

		Thread.sleep(2000);

		driver.navigate().to("https://magmasportswear.com/?afftoken=31180&afsrc=waffarad&af_id=a7293ce1-a2d3-40e8-8cb5-2e1fb8b86953");	
		Thread.sleep(2000);

		assertEquals("waffarad", driver.manage().getCookieNamed("afsrc").getValue()) ;
		assertEquals("31180", driver.manage().getCookieNamed("afftoken").getValue()) ;
		assertEquals("a7293ce1-a2d3-40e8-8cb5-2e1fb8b86953", driver.manage().getCookieNamed("af_id").getValue()) ;
	}

	@Test
	public void Cookies_melouk() throws InterruptedException
	{
		//test =extent.startTest("Cookies_melouk");

		driver.navigate().to("https://www.melouk.site/?afftoken=31180&afsrc=waffarad&af_id=a7293ce1-a2d3-40e8-8cb5-2e1fb8b86953");	

		Thread.sleep(2000);

		driver.manage().deleteAllCookies() ;

		Thread.sleep(2000);

		driver.navigate().to("https://www.melouk.site/?afftoken=31180&afsrc=waffarad&af_id=a7293ce1-a2d3-40e8-8cb5-2e1fb8b86953");	
		Thread.sleep(2000);

		assertEquals("waffarad", driver.manage().getCookieNamed("afsrc").getValue()) ;
		assertEquals("31180", driver.manage().getCookieNamed("afftoken").getValue()) ;
		assertEquals("a7293ce1-a2d3-40e8-8cb5-2e1fb8b86953", driver.manage().getCookieNamed("af_id").getValue()) ;
	}

	@Test
	public void Cookies_Entertainer() throws InterruptedException
	{
		//test =extent.startTest("Cookies_Entertainer");

		driver.navigate().to("https://entertaineregypt.com/?afftoken=31180&afsrc=waffarad&af_id=a7293ce1-a2d3-40e8-8cb5-2e1fb8b86953");	

		Thread.sleep(2000);

		driver.manage().deleteAllCookies() ;

		Thread.sleep(2000);

		driver.navigate().to("https://entertaineregypt.com/?afftoken=31180&afsrc=waffarad&af_id=a7293ce1-a2d3-40e8-8cb5-2e1fb8b86953");	
		Thread.sleep(2000);

		assertEquals("waffarad", driver.manage().getCookieNamed("afsrc").getValue()) ;
		assertEquals("31180", driver.manage().getCookieNamed("afftoken").getValue()) ;
		assertEquals("a7293ce1-a2d3-40e8-8cb5-2e1fb8b86953", driver.manage().getCookieNamed("af_id").getValue()) ;
	}

	@Test
	public void Cookies_olife() throws InterruptedException
	{
		//test =extent.startTest("Cookies_olife");

		driver.navigate().to("https://olifemena.com/?afftoken=31180&afsrc=waffarad&af_id=a7293ce1-a2d3-40e8-8cb5-2e1fb8b86953");	

		Thread.sleep(2000);

		driver.manage().deleteAllCookies() ;

		Thread.sleep(2000);

		driver.navigate().to("https://olifemena.com/?afftoken=31180&afsrc=waffarad&af_id=a7293ce1-a2d3-40e8-8cb5-2e1fb8b86953");	
		Thread.sleep(2000);

		assertEquals("waffarad", driver.manage().getCookieNamed("afsrc").getValue()) ;
		assertEquals("31180", driver.manage().getCookieNamed("afftoken").getValue()) ;
		assertEquals("a7293ce1-a2d3-40e8-8cb5-2e1fb8b86953", driver.manage().getCookieNamed("af_id").getValue()) ;
	}

	@Test
	public void Cookies_Auditechnology() throws InterruptedException
	{
		//test =extent.startTest("Cookies_Auditechnology");

		driver.navigate().to("https://audioteceg.com/?afftoken=31180&afsrc=waffarad&af_id=a7293ce1-a2d3-40e8-8cb5-2e1fb8b86953");	

		Thread.sleep(2000);

		driver.manage().deleteAllCookies() ;

		Thread.sleep(2000);

		driver.navigate().to("https://audioteceg.com/?afftoken=31180&afsrc=waffarad&af_id=a7293ce1-a2d3-40e8-8cb5-2e1fb8b86953");	
		Thread.sleep(2000);

		assertEquals("waffarad", driver.manage().getCookieNamed("afsrc").getValue()) ;
		assertEquals("31180", driver.manage().getCookieNamed("afftoken").getValue()) ;
		assertEquals("a7293ce1-a2d3-40e8-8cb5-2e1fb8b86953", driver.manage().getCookieNamed("af_id").getValue()) ;
	}

	@Test
	public void Cookies_alsharifbeaute() throws InterruptedException
	{
		//test =extent.startTest("Cookies_alsharifbeaute");

		driver.navigate().to("https://alsharifbeaute.com/?afftoken=31180&afsrc=waffarad&af_id=a7293ce1-a2d3-40e8-8cb5-2e1fb8b86953");
		Thread.sleep(2000);

		driver.manage().deleteAllCookies() ;

		Thread.sleep(2000);

		driver.navigate().to("https://alsharifbeaute.com/?afftoken=31180&afsrc=waffarad&af_id=a7293ce1-a2d3-40e8-8cb5-2e1fb8b86953");
		Thread.sleep(2000);

		assertEquals("waffarad", driver.manage().getCookieNamed("afsrc").getValue()) ;
		assertEquals("31180", driver.manage().getCookieNamed("afftoken").getValue()) ;
		assertEquals("a7293ce1-a2d3-40e8-8cb5-2e1fb8b86953", driver.manage().getCookieNamed("af_id").getValue()) ;
	}
	@Test
	public void Cookies_Carina() throws InterruptedException
	{
		//test =extent.startTest("Cookies_Carina");

		driver.navigate().to("https://carinawear.com/?afftoken=31180&afsrc=waffarad&af_id=a7293ce1-a2d3-40e8-8cb5-2e1fb8b86953");
		Thread.sleep(2000);

		driver.manage().deleteAllCookies();

		Thread.sleep(2000);

		driver.navigate().to("https://carinawear.com/?afftoken=31180&afsrc=waffarad&af_id=a7293ce1-a2d3-40e8-8cb5-2e1fb8b86953");
		Thread.sleep(2000);

		assertNull(driver.manage().getCookieNamed("afsrc")) ;
		assertNull(driver.manage().getCookieNamed("afftoken")) ;
		assertNull(driver.manage().getCookieNamed("af_id")) ;
	}

	@Test
	public void Cookies_cdejewelry() throws InterruptedException
	{
		//test =extent.startTest("Cookies_cdejewelry");

		driver.navigate().to("https://cdejewelry-egypt.com/?afftoken=31180&afsrc=waffarad&af_id=a7293ce1-a2d3-40e8-8cb5-2e1fb8b86953");
		Thread.sleep(2000);

		driver.manage().deleteAllCookies();

		Thread.sleep(2000);

		driver.navigate().to("https://cdejewelry-egypt.com/?afftoken=31180&afsrc=waffarad&af_id=a7293ce1-a2d3-40e8-8cb5-2e1fb8b86953");
		Thread.sleep(2000);

		assertNull(driver.manage().getCookieNamed("afsrc")) ;
		assertNull(driver.manage().getCookieNamed("afftoken")) ;
		assertNull(driver.manage().getCookieNamed("af_id")) ;
	}

	@AfterMethod
	public void quit()
	{
		driver.quit();	
	} 

}
