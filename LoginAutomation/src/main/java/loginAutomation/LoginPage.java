package loginAutomation;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.baseUtilites.BaseUtility;

public class LoginPage{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		

		System.out.println("Starts");
		
		BaseUtility b1 = new BaseUtility();
		WebDriver driver = b1.startup("ch", "https://online.actitime.com/swaydande/login.do");
		
		WebElement userName = driver.findElement(By.id("username"));
		WebElement psd = driver.findElement(By.cssSelector(".textField.pwdfield"));
		
		WebElement loginBtn = driver.findElement(By.id("loginButton"));
		
		userName.sendKeys("waydandesneha80@gmail.com");
		psd.sendKeys("dpdv3eEQ");
		
		loginBtn.click();
		
		List<WebElement> profileBtn = 
				driver.findElements(By.xpath("//button[@data-testid='popup_menu_button_profile']"));
		profileBtn.get(0).click();
		
		List<WebElement> logoutBtn = driver.findElements(By.xpath("//div[contains(text(),'Logout')]"));
		logoutBtn.get(0).click();
		
		System.out.println("Ends");
		
	}

}