package systemutils;

import org.openqa.selenium.By;

import baseutils.EventLog;
import io.appium.java_client.android.AndroidDriver;
import uiautoutils.Operate;

public class SystemTool {
	
	/**
	 * 删除通知栏信息(小米手机)
	 */
		public static void clearNotification(AndroidDriver driver)
		{
			driver.openNotifications();
			driver.findElement(By.id("com.android.systemui:id/clear_all_button")).click();
		    Operate.sendKeyEvent(4,"返回",driver);
		}
		
	    /**
	     * 设置手机的网络连接状态,可以开关蓝牙、wifi、数据流量。
	     *
	     * @param LogText      input log text.
	     * @param airplaneMode airplaneMode switch.
	     * @param wifi         wifi switch.
	     * @param data         data switch.
	     */
	    public void setNetworkConnection(String LogText, Boolean airplaneMode, Boolean wifi, Boolean data) {
	        EventLog.logStep("[" + LogText + "] ");
	     //   ((AndroidDriver) driver).setNetworkConnection(new NetworkConnectionSetting(airplaneMode, wifi, data));
	        
	    }
	    
	    /**
	     * override original method
	     * 锁屏指定时间后解锁,在原生方法执行时添加日志。
	     *
	     * @param LogText          input log text.
	     * @param timeoutInSeconds time for waiting, unit: second.
	     */
	    public void lockScreen(String LogText, AndroidDriver driver) {
	        EventLog.logStep("[" + LogText + "] ");
	       
	    }

		

}
