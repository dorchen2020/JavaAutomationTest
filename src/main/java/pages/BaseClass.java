package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseClass {

	private static JavascriptExecutor jse;
	private static WebDriverWait wait;

	public BaseClass(WebDriver driver) {
		jse=(JavascriptExecutor)driver;
		wait = new WebDriverWait(driver, 40);
	}
	
	// scroll to the bottom or top of the page
	public static void Scroll(String bottomOrTop) {
		switch (bottomOrTop) {
		case "bottom":	 jse.executeScript("window.scrollTo(0,document.body.scrollHeight)");	break;
		case "top":		 jse.executeScript("window.scrollTo(document.body.scrollHeight, 0)");	break;
		default: 		 System.out.println("Error, scroll func get \"bottom\" or \"top\"");	break;
		}
	}
	// wait and return partial URL
	public static boolean isUrlContain(String partialUrl) {
		return wait.until(ExpectedConditions.urlContains(partialUrl));
	}

	// get xpath/id/link/css locator from WebElement
	public static String webEleToLocator(WebElement e) {
		String str = e.toString(); 
		String[] listString= {}; 
		if(str.contains("xpath")) 
			listString = str.split("xpath:"); 
		else if(str.contains("id")) 
			listString = str.split("id:");
		else if(str.contains("link text")) 
			listString = str.split("link text:");
		
		// print WebElement (css locator) pattern can contains: 
		// 'By.cssSelector: ...'    OR   css selector: ...]
		else if(str.contains("css selector") || str.contains("cssSelector")) // can be two options. 
			listString = str.split("elector:"); 
		String last = listString[1].trim(); 
		return last.substring(0, last.length()-1); 
	}
}
