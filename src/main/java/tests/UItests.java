package tests;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.Arsenal;
import pages.BaseClass;
import pages.ContactUs;
import pages.HomePage;

/*	
	Maven project using Java, TestNG and Selenium, Automated UI Tests script + API Tests. 
	In this project I demonstrate some of my knowledge and abilities in automation. 
	All tests can be run from testng.xml
	SUT: 90min site - https://www.90min.com
*/

public class UItests {

	//props
	private HomePage hp;
	private Arsenal arsenal;
	private ContactUs contactUs;
	private WebDriver driver;
	
	@BeforeMethod
	public void prepareEachTest() {
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		hp = new HomePage(driver);
		arsenal = new Arsenal(driver);
		contactUs = new ContactUs(driver);
		new BaseClass(driver);
		hp.GoTo();
	}
	@AfterMethod
	public void AfterEachTest() {
		driver.quit();
	}


	@Test // test 1 - check if header menu links displayed.
	public void headerMenuCheck() {
		Assert.assertTrue(hp.HeaderMenuCheck(),"the Header is not valid");
	}

	@Test // test 2 - check if there is 21 links while hover on "Premier League".
	public void is21Links() {
		Assert.assertTrue(hp.Is21Links(),"there is no 21 links.");
	}

	@Test // test 3 - change Language to Spanish and verify it. 
	public void isSpanish() {
		Assert.assertTrue(hp.IsSpanish(),"the site is not spanish like expected.");
	}

	@Test // test 4 - validate each link has a suitable icon
	public void isSuitableLinksToIcons() {
		Assert.assertTrue(hp.isSuitableLinksToIcons(),"Links is not suite to icons");
	}

	@Test // test 5 - verify "Premier League" Becomes Red while hover.
	public void isPremierBecomesRed() {
		Assert.assertTrue(hp.IsPremierBecomesRed(),"premier title is not becomes red.");
	}

	@Test // test 6 - navigate to "Arsenal" page.
	public void isNavigateToArsenal() {
		Assert.assertTrue(arsenal.IsNavigateToArsenal(),"navigate to Arsenal Page failed.");
	}

	@Test // test 7 - navigate to "Arsenal" page and validate all the posts have a valid cover image.
	public void eachPostsHaveCover() {
		Assert.assertTrue(arsenal.EachPostsHaveCover(),"validate all the posts have a valid cover image failed");
	}

	@Test // test 8 - navigate to "Contact Us" page.
	public void isNavigateToContactUs() {
		Assert.assertTrue(contactUs.IsNavigateToContactUs(),"navigate to Contact Us page failed.");
	}

	@Test // test 9 - navigate to "Contact Us" page and insert data from JSON file.
	public void contactUsInsertDetails() {
		Assert.assertTrue(contactUs.InsertDetails(), "insert details failed.");
	}
}
