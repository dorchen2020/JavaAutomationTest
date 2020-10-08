package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import resources.ReadJsonFile;

public class ContactUs {

	private WebDriverWait wait;
	private HomePage hp;
	private Select select;
	private Actions action;
			
	@FindBy(id = "comp-k9yni85iinlineContent") 
	WebElement popupDiv;

	@FindBy(id = "comp-kcbuwor8input")
	WebElement nameInput;

	@FindBy(id = "comp-kcbuworbinput")
	WebElement emailInput;

	@FindBy(id = "comp-kcbuwordinput")
	WebElement companyInput;

	@FindBy(id = "comp-kcbuworfinput")
	WebElement titleInput;

	@FindBy(id = "comp-kcbuworhinput")
	WebElement countryInput;

	@FindBy(id = "comp-kcbuwori4collection")
	WebElement inquiryTypeSelect;

	@FindBy(id = "comp-kcbuworn3textarea")
	WebElement messageInput;

	@FindBy(id = "comp-kcbuworslink")
	WebElement sendBTN;

	@FindBy(xpath = "//div[@id='comp-kcbuworq1'][@style='width: 245px; pointer-events: none;']//span[contains(text(), 'Thanks')]")
	WebElement confirmSubmitText; // this xpath without visibility hidden style.


	public ContactUs(WebDriver driver) {
		wait = new WebDriverWait(driver, 40);
		PageFactory.initElements(driver, this);
		hp = new HomePage(driver);
		action = new Actions(driver);
	}

	public boolean IsNavigateToContactUs() {
		hp.ContactUsLink();
		return BaseClass.isUrlContain("minutemedia");
	}

	public boolean InsertDetails() {
		hp.ContactUsLink();
	
		String[] jsonArr = ReadJsonFile.getData();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(BaseClass.webEleToLocator(popupDiv))));	
		action.sendKeys(Keys.ESCAPE).build().perform(); // handle with pop up 
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(BaseClass.webEleToLocator(nameInput))));
		nameInput.sendKeys(jsonArr[0]);
		emailInput.sendKeys(jsonArr[1]);
		companyInput.sendKeys(jsonArr[2]);
		
		BaseClass.Scroll("bottom");
		// action.keyDown(Keys.CONTROL).sendKeys(Keys.END).build().perform(); 
		// CONTROL+END cannot use when there is a focus on input element, need to "stand" on the page.
		
		titleInput.sendKeys(jsonArr[3]);
		countryInput.sendKeys(jsonArr[4]);
		select=new Select(inquiryTypeSelect);
		select.selectByVisibleText(jsonArr[5]);
		messageInput.sendKeys(jsonArr[6]);
		wait.until(ExpectedConditions.elementToBeClickable(By.id(BaseClass.webEleToLocator(sendBTN)))).click();
				
		// confirmation text of submit
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(BaseClass.webEleToLocator(confirmSubmitText)))); 
		return true;			
	}
}

