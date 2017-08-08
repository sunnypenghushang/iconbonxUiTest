package uiautoutils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidKeyCode;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import baseutils.EventLog;
import baseutils.ToolFunctions;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * 屏幕事件操作
 *@author penghong
 */
public abstract class Operate {

	

	/**
	 * 在某个元素内滑动
	 * @param element
	 * @param driver
	 */
	public static void swipeElement(AndroidElement element,AndroidDriver driver)
	{
		driver.swipe(element.getLocation().getX()+element.getSize().width, element.getLocation().getY()+element.getSize().height,element.getLocation().getX(), element.getLocation().getY(),300);
		
	}
	
	
	
/**
 * 通过getPageSource判断元素是否存在
 * @param element 
 * @param driver
 * @return
 */
	
	public static boolean assertExist(String element,AndroidDriver driver)
	{
		if(driver.getPageSource().contains(element))
			return true;
		else 
			return false;
			
	}
	
	
	
	/**
	 * 长按某个元素
	 * @param driver
	 * @param element
	 */
	public static void longPress(AndroidDriver driver,WebElement element)
	{
		TouchAction action=new TouchAction(driver);
		action.longPress(element).perform();
		
	}
	
	/**
	 * 获取界面所有元素
	 * @param driver
	 * @return
	 */
	public static List<AndroidElement> getAllElements(AndroidDriver driver)
	{  
		List<AndroidElement> list=driver.findElementsByXPath("//*");
	    for(int i=0;i<list.size();i++)
	    {   
	    	AndroidElement e=list.get(i);    		
	    	
	    }
		return list;
		
	}
	
	
	/**
	 * 返回当前界面元素的个数
	 * @param driver
	 * @return
	 */
	public static int elememtNums(AndroidDriver driver)
	{
		return driver.findElementsByXPath("//*").size();
	}
	
	
	/**
	 * 返回当前界面文本元素的个数
	 * @param driver
	 * @return
	 */
	public static int eleTextNums(AndroidDriver driver)
	{   
		int countbyname=0;
		List<AndroidElement> list=driver.findElementsByXPath("//*");
	    for(int i=0;i<list.size();i++)
	    {   
	    	AndroidElement e=list.get(i);
	    	if(!e.getText().equals(""))
	    		countbyname++;
	    }

		return countbyname;
	}
    
    
    

    /**
     * 相对座标点击
     */
    public static void clickbycoordinate(AndroidDriver driver,int x,int y){
    	int screenheight=driver.manage().window().getSize().height;
    	int screenwidth=driver.manage().window().getSize().width;
    	int x_click=x*screenwidth/1080;
    	int y_click=y*screenheight/1920;
    	driver.tap(1, x_click, y_click, 0);
    	 EventLog.logInfo("轻触坐标: x = " + x + ", y = " + y);
    	 newSleep(1);
   
    	//1080x1920
    }
    
    
    /**
     * 通过adb命令点击屏幕（相对座标）
     */
    public static void tapByadb(int x,int y)
    {   
    
    	String str;
    	int width = 0,height = 0;
		try {
			Process process=Runtime.getRuntime().exec("adb shell dumpsys window displays |grep init");
			BufferedReader br=new BufferedReader(new InputStreamReader(process.getInputStream()));
			while((str=br.readLine())!=null)
			{   
				if(ToolFunctions.getMatch(str, "init="))
				{   
					String x1=str.split(" ")[4].split("=")[1].split("x")[0];
					String y1=str.split(" ")[4].split("=")[1].split("x")[1];
					width=Integer.parseInt(x1);
					height=Integer.parseInt(y1);
					break;
				}

			}
			EventLog.logInfo("X为:"+width+"Y为："+height);
			br.close();
			int x_click=x*width/1080;
	    	int y_click=y*height/1920;
	    	Runtime.getRuntime().exec("adb shell input tap "+x_click+" "+y_click);
	    	newSleep(1);
		
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
    }
    
    public static void main(String[] args)
    {
    	
    }
    
    /**
     * 向上滑屏
     * @param driver
     * @param during
     */
  	public static void swipeToUp(AndroidDriver driver,String logtext)
  	{     
  		EventLog.logStep("[" + logtext + "] ");
  		  int width = driver.manage().window().getSize().width;
          int height = driver.manage().window().getSize().height;
         // driver.swipe(width / 2, height * 3 / 4, width / 2, height / 4, 3);
          try {
			Runtime.getRuntime().exec("adb shell input swipe "+width / 2+" "+height * 3 / 4+" "+width / 2+" "+ height / 4);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
          newSleep(1);
  		
  	}
  	
  /**
   * 向下滑屏
   * @param driver
   * @param during
   */
  	public static void swipeToDown(AndroidDriver driver,int logtext)
  	{   
  		EventLog.logStep("[" + logtext + "] ");
  		int width = driver.manage().window().getSize().width;
        int height = driver.manage().window().getSize().height;
        driver.swipe(width / 2, height / 4, width / 2, height * 3 / 4, 2);
  	}
  	
	/**
	 * override original method
	 * 按下指定的键,在原生方法执行时添加日志。
	 *
	 * @param keyEvent the element to be found.
	 * @param LogText  input log text.
	 */
	public static void sendKeyEvent(int keyEvent, String LogText,AndroidDriver driver) {
		EventLog.logStep("[" + LogText + "] ");
		driver.pressKeyCode(keyEvent);
		newSleep(1);
		
		
	}
	
	
	public static void newSleep(int p_time)
	{
		try
		{
			Thread.sleep(p_time * 1000);
		} catch (InterruptedException e)
		{

		}
	}
	


    
    
   

    /**
     * 如果目标元素不响应,可尝试重复点击。
     *
     * @param element       尝试点击的元素.
     * @param maxRetryTimes 重试次数
     * @return boolean
     */
    public boolean clickToRetry(WebElement element, int maxRetryTimes) {
        int retryTimes = 1;
        boolean unClickable = true;

        while (unClickable) {
            EventLog.logInfo("第" + retryTimes + "次点击......");
            try {
                click(element, "点击目标按钮");
                unClickable = false;
            } catch (Exception e) {
                e.printStackTrace();
                EventLog.logError(e.getStackTrace());
            }

            retryTimes++;

            if (retryTimes > maxRetryTimes)
                break;
        }

        return !unClickable;
    }

    
    /**
     * wait for the text present in timeout setting
     * 在指定时间内等待，直到控件出现在页面上。pageSource.contains方式判断
     *
     * @param timeoutInSeconds 设置等待时间,单位:秒.
     * @param TargetText       等待出现的文本,可以设置多个.
     * @return boolean
     */
   public static boolean waitForText(AndroidDriver driver,int timeoutInSeconds, String... TargetText) {
        EventLog.logStep("[Wait For Text : " + Arrays.toString(TargetText) + "] ");
        EventLog.logInfo("[Wait For Text : " + Arrays.toString(TargetText) + "] ");
        Boolean flag = false;
        String pageSource = null;
        long currentTime = System.currentTimeMillis();
        while (true) {
            try {
                Thread.sleep(3 * 1000);
                if (driver != null)
                    pageSource = driver.getPageSource();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (null != pageSource) {
                for (int i = 0; i < TargetText.length; i++) {
                    flag = pageSource.contains(TargetText[i]);
                }
            }

            if (System.currentTimeMillis() - currentTime >= timeoutInSeconds * 1000 || flag) {
                break;
            }
        }

        return flag;
    }

    /**
     * wait for the text present in timeout setting
     * 在指定时间内等待，直到文本消失在页面上。
     *
     * @param timeoutInSeconds 设置等待时间,单位:秒.
     * @param TargetText       等待消失的文本,可以设置多个.
     * @return boolean
     */
    public static void waitForTextDisappear(int timeoutInSeconds, String TargetText,AndroidDriver driver) {
        EventLog.logStep("[Wait For Text Disappear : " + TargetText + "] ");
        Boolean flag = true;
        long currentTime = System.currentTimeMillis();
        while (flag) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String pageSource = driver.getPageSource();

            
                flag = (pageSource.contains(TargetText));
            
            if (System.currentTimeMillis() - currentTime >= timeoutInSeconds * 1000 || !flag) {
                break;
            }
            
        }
      
    }

    /**
     * override original method
     * 模拟点击,在原生方法执行时添加日志。
     *
     * @param element the element to be found.
     * @param LogText input log text.
     */
    public static void click(final WebElement element, String LogText) {
        EventLog.logInfo("[点击 " + element.toString().substring(element.toString().indexOf("->")));
        element.click();
        newSleep(1);
    }

    /**
     * 输入字符
     *
     * @param element       输入框
     * @param LogText       备注
     * @param charSequences 要输入的字符
     */
    public static void sendKeys(WebElement element, String LogText, String charSequences) {
        EventLog.logInfo("[输入字符 " + element.toString().substring(element.toString().indexOf("->")));
        element.sendKeys(charSequences);
    }

    /**
     * override original method
     * 模拟清除,在原生方法执行时添加日志。
     *
     * @param element the element to be found.
     * @param LogText input log text.
     */
    public static void clear(final WebElement element, String LogText) {
        EventLog.logStep("[" + LogText + "] ");
        EventLog.logInfo("[清空数据 " + element.toString().substring(element.toString().indexOf("->")));
        element.clear();
    }
    
    
	/**
	 * 确认输入
	 */
	public static void submit(AndroidElement element,String LogText,AndroidDriver driver)
	{   
		EventLog.logStep("[" + LogText + "] ");
	    EventLog.logInfo("[输入后回车 " + element.toString().substring(element.toString().indexOf("->")));
	    driver.pressKeyCode(66);
		 
	}
	
	/**
	 * 点击确认或保存按扭
	 */
	public static void confirmButton(WebElement webElement,String LogText)
	{
		EventLog.logStep("[" + LogText + "] ");
	    EventLog.logInfo("[点击按扭 " + webElement.toString().substring(webElement.toString().indexOf("->")));
	    webElement.click();
	}

    /**
     * override original method
     * 获取文本,在原生方法执行时添加日志。
     *
     * @param element the element to be found.
     * @param LogText input log text.
     * @return String
     */
    public String getText(final WebElement element, String LogText) {
        EventLog.logStep("[" + LogText + "] ");
        EventLog.logInfo("[获取文本 " + element.toString().substring(element.toString().indexOf("->")));
        return element.getText();
    }

    /**
     * override original method
     * 获取属性,在原生方法执行时添加日志。
     *
     * @param element the element to be found.
     * @param LogText input log text.
     * @return String
     */
    public String getAttribute(final WebElement element, String attribute, String LogText) {
        EventLog.logStep("[" + LogText + "] ");
        EventLog.logInfo("[获取文本 " + element.toString().substring(element.toString().indexOf("->")));
        return element.getAttribute(attribute);
    }

    /**
     * override original method
     * 滚动到目标文本的位置,在原生方法执行时添加日志。
     *
     * @param targetText the target text to be scrollToExact.
     * @param LogText    input log text.
     */
    public void scroll_TargetText(String targetText, String LogText) {
        EventLog.logStep("[滑动屏幕定位目标文字:" + targetText + LogText + "] ");
    }


    /**
     * Js模拟滚动至目标文本的位置。
     *
     * @param TargetText the target text to be found.
     */
    public void scrollTo(String TargetText) {
        EventLog.logStep("[滑动页面以发现'" + TargetText + "'] ");
   //     ((AndroidDriver) driver).scrollTo(TargetText);
        
    }



    
    /**
     * 等待元素出现,通过name获取
     */
    
    public static void waitElementAppear(WebDriver driver,String text,int seconds)
    {   
    	boolean flag=true;
    	long start = System.currentTimeMillis();
    	while(flag&&((System.currentTimeMillis() - start) < seconds * 1000))
    	{
    		
    		try{
    		if(((AndroidElement) driver).findElementByAndroidUIAutomator("text(\""+text+"\")").isDisplayed())
    			flag=false;
    		}
    		catch(Exception e)
    		{
    			
    		}
    	}
    }




}

