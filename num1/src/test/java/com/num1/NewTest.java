package com.num1;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import java.io.File;
import java.net.URL;
import java.util.List;
import org.junit.After;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
public class NewTest {
	private AndroidDriver<AndroidElement> driver;

	@BeforeTest
	public void setUp() throws Exception {
		// set up appium

		File classpathRoot = new File(System.getProperty("user.dir"));
		File appDir = new File(classpathRoot, "apps");

		File app = new File(appDir, "ContactManager.apk");
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("deviceName", "HUAWEI");
		capabilities.setCapability("platformVersion", "7.0");
		capabilities.setCapability("app", app.getAbsolutePath());
		capabilities.setCapability("appPackage",
				"com.example.android.contactmanager");
		capabilities.setCapability("appActivity", ".ContactManager");
		driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"),
				capabilities);
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}

	@Test
	public void addContact() {
		WebElement el = driver.findElement(By.name("Add Contact"));
		el.click();
		List<AndroidElement> textFieldsList = driver
				.findElementsByClassName("android.widget.EditText");
		textFieldsList.get(0).sendKeys("Some Name");
		textFieldsList.get(2).sendKeys("Some@example.com");
		driver.swipe(100, 500, 100, 100, 2);
		driver.findElementByName("Save").click();
	}
}
