package com.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class CommonMethods {
	public static final String url = "http://166.62.36.207/humanresources/symfony/web/index.php/auth/login\\n";
//	WebDriver driver = null;
//
//	public static void setUp(String browser, String url) {
//
//		if (browser.equals("chrome")) {
//			System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
//			driver = new ChromeDriver();
//		} else {
//			System.out.println("Driver not supported!");
//		}
//
//		driver.get("https://canvas.instructure.com/login/canvas");
//
//	}

	public static WebDriver createDriver(String browser) {
		WebDriver driver = null;

		if (browser.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
			driver = new ChromeDriver();
		} else {
			System.out.println("Driver not supported!");
		}

		driver.get(url);
		return driver;
	}

}
