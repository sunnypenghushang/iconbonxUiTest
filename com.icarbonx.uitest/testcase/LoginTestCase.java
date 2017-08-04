package testcase;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import baseutils.EventLog;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import page.LoginPage;

public class LoginTestCase extends BaseTestCase{
	AndroidDriver<AndroidElement> driver;

	LoginPage loginpage;
	
	
	@BeforeTest
	public void setUp()
	{
		driver=getConnectWithAppium(driver);
		loginpage=new LoginPage(driver);
		
	}
	
	@Test
	public void login()
	{   
		loginpage.login("13088884762", "8888");
	}
	
	
   @AfterTest
   public void tearUp()
   {
	   super.tearUp(driver);
   }

}
