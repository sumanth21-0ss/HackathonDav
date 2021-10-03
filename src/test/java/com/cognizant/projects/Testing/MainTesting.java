package com.cognizant.projects.Testing;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.cognizant.projects.Hackathon.Implement;
import com.cognizant.projects.Hackathon.ImplementUI;

import junit.framework.Assert;

public class MainTesting {
	public static WebDriver driver;
	ExtentHtmlReporter htmlreporter;
	ExtentReports extent;
	ImplementUI implement = new ImplementUI();

	@Parameters({"OS","browser"})
	@BeforeSuite
	public void setDriver(String OS,String Browser) throws InterruptedException {
		htmlreporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/Reports/testReport.html");
		extent = new ExtentReports();
		driver = Implement.createDriver();
		implement.maximizeBrowser();
		extent.attachReporter(htmlreporter);
		extent.setSystemInfo("OS", OS);
		extent.setSystemInfo("Browser", Browser);
		extent.setSystemInfo("TEAM NO", "03");
		extent.setSystemInfo("Site to test", "UrbanLadder");
		htmlreporter.config().setDocumentTitle("Extent Report");
		htmlreporter.config().setDocumentTitle("Tests for UrbanLadder");
		htmlreporter.config().setReportName("Testing UrbanLadder");
		htmlreporter.config().setTheme(Theme.STANDARD);
	}

	@BeforeTest
	public void startTest() {
		htmlreporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "//test-output/ExtentTest.html");
	}

	@Test(priority = 1)
	public void showBookShelves() throws InterruptedException {
		extent.attachReporter(htmlreporter);
		ExtentTest test1 = extent.createTest("BookShelfs Testing", "To validate if the Bookshelves is showing or Not");
		test1.info("This will show the BookShelves section");
		implement.openBookShelvesPage();
		test1.pass("Passed");
	}

	@Test(priority = 2)
	public void getFirstThreeBookshelves() throws InterruptedException, FileNotFoundException, IOException {
		extent.attachReporter(htmlreporter);
		ExtentTest test1 = extent.createTest("Bookshelves Testing",
				"To validate if the first 3  Bookshelves is showing or Not");
		test1.info("This will check the functionality to get the first 3 bookshelves");
		implement.setPrice();
		implement.closePopup();
		implement.setStorageType("open");
		implement.excludeOutOfStock();
		implement.timeWait();
		implement.selectFirstthreeBookSelves();
		test1.pass("Passed");
	}

	@Test(priority = 3)
	public void getGiftCards() throws InterruptedException, IOException {
		extent.attachReporter(htmlreporter);
		ExtentTest test1 = extent.createTest("Gift Cards Section testing",
				"To validate if the gift cards is showing or Not");
		test1.info("This will check the functionality to open the gift card");
		implement.GiftCards();
		test1.pass("Passed");
	}

	@Test(priority = 4, dependsOnMethods = "getGiftCards")
	public void getListOfSubmenuItems() throws InterruptedException, FileNotFoundException, IOException {
		extent.attachReporter(htmlreporter);
		ExtentTest test1 = extent.createTest("Testing phase",
				"To validate if the sub menu items in collections is showing or Not");
		test1.info("This will check the functionality to get the sub menu in collections");
		implement.subMenuItemsInCollections();
		test1.pass("Passed");
	}

	@Test(priority = 5)
	public void emailEnterdisWrong() throws IOException {
		extent.attachReporter(htmlreporter);
		ExtentTest test1 = extent.createTest("Test", "this is a test case to show email is entered wrong");
		test1.info("This is a failed test for email");
		String screenShotpath = System.getProperty("user.dir")+"\\Screenshots\\errorMessage.png";
		test1.log(Status.PASS, "Test case"+test1.addScreenCaptureFromPath(screenShotpath));
		Assert.assertTrue(true);

	}

	@AfterSuite
	public void closeBrowser() throws InterruptedException {
		extent.flush();
		implement.timeWait();
		implement.closeBrowser();
	}

}