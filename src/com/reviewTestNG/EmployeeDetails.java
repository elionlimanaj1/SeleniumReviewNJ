package com.reviewTestNG;

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
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.utils.CommonMethods;
import com.utils.Constants;

public class EmployeeDetails extends CommonMethods {

	@BeforeClass
	public void beforeClass() {
		CommonMethods.setUp("chrome", Constants.HRMS_URL);

		WebElement userNameTextBox = driver.findElement(By.id("txtUsername"));
		userNameTextBox.sendKeys("admin");
		driver.findElement(By.id("txtPassword")).sendKeys("Hum@nhrm123");

		driver.findElement(By.id("btnLogin")).submit();
	}

	@AfterClass
	public void afterClass() throws InterruptedException {
		Thread.sleep(2000);
		driver.quit();
	}

	@BeforeMethod
	public void beforeMethod() {
		driver.navigate().to("http://166.62.36.207/humanresources/symfony/web/index.php/dashboard");
	}

	@DataProvider
	public Object[] getIds() {
		Object[] data = { "3398", "3399" };
		return data;
	}

	@Test(dataProvider = "getIds", enabled = false)
	public void savePersonalDetails(String Id) throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("menu_pim_viewPimModule")));
		driver.findElement(By.id("menu_pim_viewPimModule")).click();

		wait.until(ExpectedConditions.elementToBeClickable(By.id("menu_pim_viewEmployeeList")));
		driver.findElement(By.xpath("//a[@id='menu_pim_viewEmployeeList']")).click();

		List<WebElement> rows = driver.findElements(By.xpath("//table[@id='resultTable']/tbody/tr"));

		for (int i = 1; i <= rows.size(); i++) {
			if (rows.get(i - 1).getText().contains(Id)) {
				WebElement link = driver
						.findElement(By.xpath("//table[@id='resultTable']/tbody/tr[" + i + "]/td[3]/a"));
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

	}

	@Test(dataProvider = "getIdAndData")
	public void savePersonalDetailsWithData(String Id, String gender, String status, String yearSel, String monthSel,
			String daySel) throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("menu_pim_viewPimModule")));
		driver.findElement(By.id("menu_pim_viewPimModule")).click();

		wait.until(ExpectedConditions.elementToBeClickable(By.id("menu_pim_viewEmployeeList")));
		driver.findElement(By.xpath("//a[@id='menu_pim_viewEmployeeList']")).click();

		List<WebElement> rows = driver.findElements(By.xpath("//table[@id='resultTable']/tbody/tr"));

		for (int i = 1; i <= rows.size(); i++) {
			if (rows.get(i - 1).getText().contains(Id)) {
				Thread.sleep(1000);
				WebElement link = driver
						.findElement(By.xpath("//table[@id='resultTable']/tbody/tr[" + i + "]/td[3]/a"));
				wait.until(ExpectedConditions.elementToBeClickable(link));
				link.click();
				break;
			}
		}

		Thread.sleep(500);
		WebElement edit_save = driver.findElement(By.id("btnSave"));
		edit_save.click();
		// select gender
		if (gender.equals("F")) {
			driver.findElement(By.xpath("//input[@id='personal_optGender_2']")).click();
		} else if (gender.equals("M")) {
			driver.findElement(By.xpath("//input[@id='personal_optGender_1']")).click();
		}
		// select single
		WebElement dropdown = driver.findElement(By.name("personal[cmbMarital]"));
		Select select = new Select(dropdown);
		select.selectByVisibleText(status);
		// click on date of birth
		driver.findElement(By.id("personal_DOB")).click();
		// select the month
		WebElement monthDD = driver.findElement(By.className("ui-datepicker-month"));
		Select month = new Select(monthDD);
		month.selectByValue(monthSel);
		// select the year
		WebElement yearDD = driver.findElement(By.className("ui-datepicker-year"));
		Select year = new Select(yearDD);
		year.selectByVisibleText(yearSel);
		// find all the cells inside calendar
		List<WebElement> cells = driver.findElements(By.xpath("//div[@id='ui-datepicker-div']/table/tbody/tr/td"));
		Iterator<WebElement> iterator = cells.iterator();
		while (iterator.hasNext()) {
			WebElement cell = iterator.next();
			if (cell.getText().equals(daySel)) {
				cell.click();
				break;
			}
		}
		// save the details
		edit_save.click();

		TakesScreenshot ts = (TakesScreenshot) driver;
		File fullPage = ts.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(fullPage, new File("screenshots/HRMS/SaveEmployeeDetails" + Id + ".jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		// check that Id matches
		WebElement empId = driver.findElement(By.id("personal_txtEmployeeId"));
		Assert.assertEquals(empId.getAttribute("value"), Id);

		// check that correct gender is selected
		WebElement maleOption = driver.findElement(By.xpath("//input[@id='personal_optGender_1']"));
		WebElement femaleOption = driver.findElement(By.xpath("//input[@id='personal_optGender_2']"));

		if (gender.equals("M")) {
			Assert.assertTrue(maleOption.isSelected());
		} else if (gender.equals("F")) {
			Assert.assertTrue(femaleOption.isSelected());
		}

	}

	@DataProvider
	public Object[][] getIdAndData() {
		Object[][] data = { { "3422", "F", "Single", "1975", "4", "18" }, { "3478", "M", "Married", "1990", "9", "25" },
				{ "3466", "M", "Single", "2010", "10", "25" } };
		return data;
	}

}
