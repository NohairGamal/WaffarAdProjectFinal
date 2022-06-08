package Screens;

import java.io.File;
import java.io.IOException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import TestBase.testData;

public class snapShots extends testData
{
	public snapShots() throws IOException
	{
		super();
	}
	
	public static void TakeScreenShot(String name) throws IOException
	{
		File SrcFile= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		org.apache.commons.io.FileUtils.copyFile(SrcFile, new File("D:\\Projects\\WaffarAdd_Project\\Reports\\"+ name +".png"));
		//we put all screenshots in the same folder of reports to appear screenshots in report
	}
}
