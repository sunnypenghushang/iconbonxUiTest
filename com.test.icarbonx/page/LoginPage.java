package page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import framework.BasePage;
import uiautoutils.Operate;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class LoginPage extends BasePage{


	public LoginPage(AndroidDriver<AndroidElement> driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	@FindBy(id="com.icarbonx.meum:id/etPhone")
	private WebElement accountEdit;
	
	@FindBy(id="com.icarbonx.meum:id/etVerify")
	private WebElement passwordEdit;
	
	@FindBy(id="com.icarbonx.meum:id/btn_login")
	private WebElement loginBtn;
	
	
    public void login(String phone,String password)
    {    

    	Operate.sendKeys(accountEdit, "输入账号", phone);
    	Operate.sendKeys(passwordEdit, "输入密码", password);
    	Operate.click(loginBtn, "点击登录");
    
    }
		
	

}
