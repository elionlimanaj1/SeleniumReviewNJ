package com.review01;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.utils.CommonMethods;

public class GetDriverExample extends CommonMethods{

	public static final String url = "http://166.62.36.207/humanresources/symfony/web/index.php/auth/login\\n";

	public static void main(String[] args) throws InterruptedException {

		CommonMethods.setUp("chrome", url);

		WebElement txtUsername = driver.findElement(By.id("txtUsername"));
		txtUsername.sendKeys("admin");

		driver.findElement(By.name("txtPassword")).sendKeys("Hum@nhrm123");
		Thread.sleep(1000);

		driver.findElements(By.xpath("//input[@id='btnLogin']")).get(0).submit();

		Thread.sleep(1000);

		driver.findElement(By.xpath("//a[@id='menu_pim_viewPimModule']")).click();
		Thread.sleep(2000);

		driver.findElement(By.xpath("//a[@id='menu_pim_addEmployee']")).click();
		Thread.sleep(2000);

		WebElement checkbox = driver.findElement(By.id("chkLogin"));
		boolean isChecked = checkbox.isSelected();
		if (!isChecked) {
			checkbox.click();
		}
		
		Thread.sleep(2000);
		
		WebElement dropdown = driver.findElement(By.xpath("//select[@id='status']"));
		Select sel = new Select(dropdown);
		sel.selectByIndex(1);
		
		Thread.sleep(2000);

		driver.quit();
	}

}
