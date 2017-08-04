package testcase;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.pattern.LogEvent;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import baseutils.EventLog;
import baseutils.ToolFunctions;
import framework.BasePage;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class BaseTestCase {
	
	
	
	private static final int TIMEOUT = 20;             // 全局等待时长
	private String udid;
	private String appPackage;
	private String appActivity;

	/*
	 * 与appium服务器建立连接
	 */
	public AndroidDriver<AndroidElement> getConnectWithAppium(AndroidDriver<AndroidElement> driver)
	{
		
		setTestInfo();
		EventLog.logInfo("连接appium服务器");
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("deviceName", "Android Emulator");	
		capabilities.setCapability("device", "Selendroid");//测试H5页页
		capabilities.setCapability("udid",udid);	
		capabilities.setCapability("appPackage", appPackage);
		capabilities.setCapability("appActivity", appActivity);
		capabilities.setCapability("unicodeKeyboard", "True");  
		capabilities.setCapability("resetKeyboard", "True"); 
		capabilities.setCapability("sessionOverride", true); // 每次启动时覆盖session，否则第二次后运行会报错不能新建session
		capabilities.setCapability("setWebContentsDebuggingEnabled", "True");	
		capabilities.setCapability("noSign", "True");
		capabilities.setCapability("noReset", true);//实现app不是每次都安装
		try {
			driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.manage().timeouts().implicitlyWait(TIMEOUT, TimeUnit.SECONDS);//全局等待20秒
		
        return driver;
	}
	

	public void tearUp(AndroidDriver<AndroidElement> driver)
	{
		driver.quit();
		ToolFunctions.setinputmethod();
	}
	
	
	
	/*
	 * 获取测试环境相关数据
	 */
	public void setTestInfo()
	{   
		try{
		Properties prop=new Properties();
		InputStream in=BasePage.class.getClassLoader().getResourceAsStream("testinfo.properties");
		prop.load(in);
		udid=prop.getProperty("udid");
		appPackage=prop.getProperty("appPackage");
		appActivity=prop.getProperty("appActivity");
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		
	}
	

}
