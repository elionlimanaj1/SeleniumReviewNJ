package com.review02;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.utils.CommonMethods;
import com.utils.Constants;

public class EmployeeDetails extends CommonMethods {
	public static void main(String[] args) throws InterruptedException {
		CommonMethods.setUp("chrome", Constants.HRMS_URL);

		WebElement userNameTextBox = driver.findElement(By.id("txtUsername"));
		System.out.println("userNameTextBox type is ->" + userNameTextBox.getAttribute("type"));
		System.out.println("userNameTextBox name is ->" + userNameTextBox.getAttribute("name"));
		userNameTextBox.sendKeys("admin");
		driver.findElement(By.id("txtPassword")).sendKeys("Hum@nhrm123");

		driver.findElement(By.id("btnLogin")).submit();

		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("menu_pim_viewPimModule")));
		driver.findElement(By.id("menu_pim_viewPimModule")).click();

		wait.until(ExpectedConditions.elementToBeClickable(By.id("menu_pim_viewEmployeeList")));
		driver.findElement(By.xpath("//a[@id='menu_pim_viewEmployeeList']")).click();

		List<WebElement> rows = driver.findElements(By.xpath("//table[@id='resultTable']/tbody/tr"));
		System.out.println(rows.size());

		String expectedText = "2451"; //please make sure it is an existing Employee ID
		for (int i = 1; i <= rows.size(); i++) {
//			System.out.println(i + " -> " + rows.get(i - 1).getText());
			if (rows.get(i - 1).getText().contains(expectedText)) {
				System.out.println("I found it in row " + i);
				Thread.sleep(1000);
				WebElement link = driver.findElement(By.xpath("//table[@id='resultTable']/tbody/tr[" + i + "]/td[3]/a"));
				wait.until(ExpectedConditions.elementToBeClickable(link));
				link.click();
				break;
			}
		}

		WebElement edit_save = driver.findElement(By.id("btnSave"));
		edit_save.click();
		// select female
		driver.findElement(By.xpath("//input[@id='personal_optGender_2']")).click();
		// select single
		WebElement dropdown = driver.findElement(By.name("personal[cmbMarital]"));
		Select select = new Select(dropdown);
		select.selectByVisibleText("Single");
		// click on date of birth
		driver.findElement(By.id("personal_DOB")).click();
		// select the month
		WebElement monthDD = driver.findElement(By.className("ui-datepicker-month"));
		Select month = new Select(monthDD);
		month.selectByValue("4");
		// select the year
		WebElement yearDD = driver.findElement(By.className("ui-datepicker-year"));
		Select year = new Select(yearDD);
		year.selectByVisibleText("1997");
		// find all the cells inside calendar
		List<WebElement> cells = driver.findElements(By.xpath("//div[@id='ui-datepicker-div']/table/tbody/tr/td"));
		Iterator<WebElement> iterator = cells.iterator();
		while (iterator.hasNext()) {
			WebElement cell = iterator.next();
			if (cell.getText().equals("13")) {
				cell.click();
				break;
			}
		}
		// save the details
		edit_save.click();

		WebElement form = driver.findElement(By.xpath("//form[@id='frmEmpPersonalDetails']"));
		TakesScreenshot ts = (TakesScreenshot) form;
		File file = ts.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(file, new File("screenshots/HRMS/SaveEmployeeForm.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		ts = (TakesScreenshot) driver;
		File fullPage = ts.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(fullPage, new File("screenshots/HRMS/SaveEmployeeDetails.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		driver.close();
	}
}