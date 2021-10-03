package com.cognizant.projects.Hackathon;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.cognizant.projects.Utilities.AccessProperties;
import com.cognizant.projects.Utilities.FileOperations;

//import bsh.ParseException;

public class ImplementUI extends Implement {
	FileOperations fileOP = new FileOperations();

	/***********************************
	 * Method to open BookShelves Page
	 ************************************/
	public void openBookShelvesPage() throws InterruptedException {
		driver.get("https://www.urbanladder.com/");
		WebElement living = driver.findElement(By.xpath(AccessProperties.getXpathForLivingButtonOnNavBar()));
		mouseHoverActions(driver, living);
		timeWait();
		WebElement bookshelves = driver.findElement(By.xpath(AccessProperties.getXpathforBookshelvesOption()));
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click()", bookshelves);
	}

	/***********************************
	 * Method to set storage Type
	 ************************************/
	public void setStorageType(String SelectedstorageType) throws InterruptedException {
		WebElement storage = driver.findElement(By.cssSelector(AccessProperties.getCssSelectorToSetStorageType()));
		mouseHoverActions(driver, storage);
		if (SelectedstorageType.equalsIgnoreCase("open")) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			WebElement open = driver.findElement(By.id("filters_storage_type_Open"));
			js.executeScript("arguments[0].click()", open);
		}
	}

	/***********************************
	 * Method to set the prices range
	 ************************************/
	public void setPrice() throws InterruptedException {
		Thread.sleep(2000);
		WebElement setPriceElement = driver.findElement(By.xpath(AccessProperties.getXpathToSetPrice()));
		sliderAction(setPriceElement);
	}

	/***********************************
	 * Method to press exclude out of stock
	 ************************************/

	public void excludeOutOfStock() throws InterruptedException {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		WebElement outOfStock = driver.findElement(By.xpath("//input[@id=\"filters_availability_In_Stock_Only\"]"));
		jse.executeScript("arguments[0].click()", outOfStock);
	}

	/***********************************
	 * Method to Retrieve and print first three BookShelve's names and prices
	 * 
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws FileNotFoundException
	 ************************************/
	public void selectFirstthreeBookSelves() throws FileNotFoundException, InterruptedException, IOException {

		List<WebElement> nameElement = driver.findElements(By.xpath("//span[@class='name']"));
		List<WebElement> priceElement = driver.findElements(By.xpath("//div[@class='price-number']/span"));

		String[] names = new String[3];
		String[] prices = new String[3];
		String pri = null;

		System.out.println("\nHi!! Got the items You want!\n");

		for (int i = 0; i < 3; i++) {
			names[i] = nameElement.get(i).getText();
			prices[i] = priceElement.get(i).getText();
			pri = convert(prices[i]);
			// System.out.print(names[i]+" "+prices[i]);
			System.out.println("Item" + (i + 1) + ": " + names[i] + " " + " and Price: " + pri);
			System.out.println();
		}
		Thread.sleep(2000);
		fileOP.readItemsIntoExcelSheet(names, prices);
		Thread.sleep(2000);
	}

	/***********************************
	 * Method send a gift card to a person
	 ************************************/

	public void GiftCards() throws IOException, InterruptedException {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		WebElement giftCards = driver.findElement(By.xpath("//*[@id=\"header\"]/section/div/ul[2]/li[3]/a"));
		//giftCards.click();
		jse.executeScript("arguments[0].click()", giftCards);
		Thread.sleep(2000);
		jse.executeScript("window.scrollBy(0,800)");
		Thread.sleep(2000);
		System.out.println("gt cards");
		////*[@id="app-container"]/div/main/section/section[1]/ul/li[3]/div/div/button
		WebElement birthday = driver
				.findElement(By.xpath("//*[@id=\"app-container\"]/div/main/section/section[1]/ul/li[3]"));
		mouseHoverActions(driver, birthday);
		//System.out.print("gvhgv");
		WebElement choose = driver.findElement(
				By.xpath("//*[@id=\"app-container\"]/div/main/section/section[1]/ul/li[3]/div/div/button"));
		jse.executeScript("arguments[0].click()", choose);
		WebElement customize = driver.findElement(
				By.xpath("//*[@id=\"app-container\"]/div/main/section/section[2]/div/section[2]/div[1]/button[1]"));
		jse.executeScript("arguments[0].click()", customize);
		WebElement next = driver
				.findElement(By.xpath("//*[@id=\"app-container\"]/div/main/section/section[2]/div/section[2]/button"));
		jse.executeScript("arguments[0].click()", next);
		jse.executeScript("window.scrollBy(0,900)");
		timeWait();
		WebElement toName = driver.findElement(By.xpath("//*[@id=\"ip_4036288348\"]"));
		toName.sendKeys(AccessProperties.ReciversName());
		Thread.sleep(2000);
		WebElement toMail = driver.findElement(By.xpath("//*[@id=\"ip_137656023\"]"));
		toMail.sendKeys(AccessProperties.ReciversEmail());
		Thread.sleep(2000);
		WebElement fromName = driver.findElement(By.xpath("//*[@id=\"ip_1082986083\"]"));
		fromName.sendKeys(AccessProperties.SendersName());
		Thread.sleep(2000);
		WebElement fromMail = driver.findElement(By.xpath("//*[@id=\"ip_4081352456\"]"));
		fromMail.sendKeys(AccessProperties.SendersEmail());
		Thread.sleep(2000);
		WebElement fromMob = driver.findElement(By.xpath("//*[@id=\"ip_2121573464\"]"));
		fromMob.sendKeys(AccessProperties.SendersPhoneNumber());
		//timeWait();
		Thread.sleep(5000);
		/*WebElement confirm = driver.findElement(By.xpath("//button[@type='submit']"));
		jse.executeScript("arguments[0].click();", confirm);*/
		
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		//timeWait();
		//Thread.sleep(1000);
		takeScreenShot("errorMessage");
		Thread.sleep(2000);
		System.out.println("gt cards2");
		jse.executeScript("window.scrollTo(0,0)");
	} 

	/**********************************
	 * Method to Retrieve the List of Mattresses Types
	 * 
	 * @throws IOException
	 * @throws ParseException
	 * @throws FileNotFoundException
	 ***************************************/
	public void subMenuItemsInCollections() throws InterruptedException, FileNotFoundException, IOException {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollTo(0,0)");
		Actions act = new Actions(driver);
		WebElement matt = driver.findElement(By.xpath("//*[@id=\"app-container\"]/div/header/div[2]/nav/ul/li[10]/h4"));
		////*[@id="app-container"]/div/header/div[2]/nav/ul/li[8]/h4
		act.moveToElement(matt).build().perform();
		////*[@id="default"]
		timeWait();
		List<WebElement> mlist = driver.findElements(
				By.xpath("//a[contains(text(),'Best Sellers')]/parent::*/following-sibling::ul/li"));
																												// _1KFQA
		String[] prodList = new String[30];
		System.out.println(mlist.size() + " size");
		for (WebElement li : mlist) {
			System.out.println(li.getText());
		}

		for (int i = 0; i < mlist.size(); i++) {
			prodList[i] = mlist.get(i).getText();
		}

		fileOP.readCollectionsIntoExcelSheet(prodList);

	}

	/***************************************************
	 * Main method
	 *****************************************************************/
	public static void main(String[] args) throws IOException, InterruptedException {
		ImplementUI imui = new ImplementUI();

		imui.createDriver();
		imui.displayWelcomeMessage();
		imui.maximizeBrowser();
		imui.closePopup();
		String page = "Startpage";
		imui.takeScreenShot(page);
		imui.openBookShelvesPage();
		imui.setPrice();
		imui.setStorageType("open");
		imui.excludeOutOfStock();
		imui.selectFirstthreeBookSelves();
		imui.GiftCards();
		imui.subMenuItemsInCollections();
		imui.closeBrowser();
	}

}
