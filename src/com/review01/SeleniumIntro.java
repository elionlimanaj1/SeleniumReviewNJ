package com.review01;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumIntro {
	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
		WebDriver driver = new ChromeDriver();
		driver.get("http://166.62.36.207/humanresources/symfony/web/index.php/auth/login\\n");
		Thread.sleep(1000);
		
//		driver.findElement(By.id("txtUsername"));
		
		WebElement txtUsername = driver.findElement(By.id("txtUsername"));
		txtUsername.sendKeys("admin");
		
		Thread.sleep(1000);
		
		driver.findElement(By.name("txtPassword")).sendKeys("Hum@nhrm123");
		Thread.sleep(1000);
		
		List<WebElement> elementList = driver.findElements(By.xpath("//input[@id='btnLogin' and @class='button']"));
		WebElement button = elementList.get(0);
		button.click();
		
		//driver.findElements(By.xpath("//input[@id='btnLogin']")).get(0).click();
		
		Thread.sleep(2000);
		
		driver.close();
	}
}
