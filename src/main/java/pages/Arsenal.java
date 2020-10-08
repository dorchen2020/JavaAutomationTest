package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Arsenal {

	private WebDriverWait wait;
	private HomePage hp;

	@FindBy(xpath = "//a[.//*[text()='Arsenal']]")
	WebElement arsenalLink;
	
	@FindBy(css = "img.feedpage-article__thumbnail") // xpath: //main//section/ul/li[./div/a/img]
	WebElement postsCoversList;
	

	public Arsenal(WebDriver driver) {
		wait = new WebDriverWait(driver, 40);
		PageFactory.initElements(driver, this);
		hp = new HomePage(driver);
	}
	
	public boolean IsNavigateToArsenal() {
		hp.PremierHover();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(BaseClass.webEleToLocator(arsenalLink)))).click();
		return BaseClass.isUrlContain("teams/arsenal");
	}

	public boolean EachPostsHaveCover() {
		hp.PremierHover();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(BaseClass.webEleToLocator(arsenalLink)))).click();
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(
				BaseClass.webEleToLocator(postsCoversList)))).size()==15;
	}
}
