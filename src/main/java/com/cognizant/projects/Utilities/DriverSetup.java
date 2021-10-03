package com.cognizant.projects.Utilities;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

//import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverSetup {

	private static WebDriver driver;
	public static String userDir = System.getProperty("user.dir");

	public static void launchChrome() {

		System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\Drivers\\chromedriver.exe");
		//WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
	}

	public static void launchFirefox() {
		System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")+"\\Drivers\\geckodriver.exe");
		FirefoxOptions options = new FirefoxOptions();
		options.setProfile(new FirefoxProfile());
		options.addPreference("dom.webnotifications.enabled", true);
		driver = new FirefoxDriver(options);
	}

	public static WebDriver getWebDriver() {
		//System.out.println("Enter Web Driver");
		//Scanner in=new Scanner(System.in);
		//String userChoice = in.nextLine();
		//if (userChoice.equalsIgnoreCase("chrome")) {
			//launchChrome();
		//} else{
		//	{
				launchFirefox();
			//}
	//	}
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		return driver;
	}

}
