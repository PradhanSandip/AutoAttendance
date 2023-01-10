package application;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Attendance {
	public Attendance(String link) {
				
				//location of webdriver
				System.setProperty("webdriver.chrome.driver",".\\src\\");
				//chorme options
				ChromeOptions options = new ChromeOptions();
				options.addArguments("");
				//manage webdriver binary
				WebDriverManager.chromedriver().setup();
				//create new chrome driver
				WebDriver driver = new ChromeDriver(options=options);
				System.out.println(link);
		        //open edgehill website
				driver.get(link);
				WebElement login = driver.findElement(By.className("submit"));
//				login.click();

		        
	}
}
