package com.review01;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class CanvasTitle {
	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
		WebDriver driver = new ChromeDriver();
		driver.get("https://canvas.instructure.com/login/canvas");
		
		String title = driver.getTitle();
		System.out.println("title = " + title);

		String handle1 = driver.getWindowHandle();
		System.out.println("handle1 = " + handle1);

		Set<String> handleSet = driver.getWindowHandles();
		String handleFirst = handleSet.iterator().next();
		System.out.println("handle1 = " + handleFirst);

		driver.findElement(By.linkText("Facebook")).click();

		Thread.sleep(5000);
		String handle2 = "";

		Set<String> handles = driver.getWindowHandles();
		for (String handle : handles) {
			if (!handle.equals(handle1)) {
				handle2 = handle;
			}
		}
		
		System.out.println("handle2 = " + handle2);
		
		driver.switchTo().window(handle2);
		Thread.sleep(1000);
		driver.switchTo().window(handle1);
		Thread.sleep(1000);
		driver.switchTo().window(handle2);
		Thread.sleep(1000);
		driver.switchTo().window(handle1);
		Thread.sleep(1000);

		driver.close();			

	}
}
