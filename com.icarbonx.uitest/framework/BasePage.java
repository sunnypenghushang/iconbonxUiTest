package framework;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import baseutils.EventLog;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;


/**
 * 页面类的编写采用Page Object模式，所有的Page均继承此类
 * @author penghong
 * @version 1.0
 */
public class BasePage {
	private static final int TIMEOUT = 20;             // 全局等待时长
	private AndroidDriver<AndroidElement> driver;
	private String udid;
	private String appPackage;
	private String appActivity;
	
	public BasePage(AndroidDriver<AndroidElement> driver)
	{   
		this.driver=driver;	
		PageFactory.initElements(driver, this);//初始化页面
		
	}


	
	
	
	

}
