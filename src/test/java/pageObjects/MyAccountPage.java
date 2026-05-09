package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends basePage {
	
	//constructor
	
	public MyAccountPage (WebDriver driver)
	{
		super (driver);
	}
	
	// LOCATOR
	
	@FindBy(xpath ="//h2[normalize-space()='My Account']")  // My Account page Heading 
	WebElement msgMyAccount;
	
	@FindBy(xpath ="//a[@class='list-group-item'][normalize-space()='Logout']")
	WebElement linkLogout;
	
	//ACTION METHOD
	
	public boolean isMyAccountPageExists()
	{
			try
			{
				return msgMyAccount.isDisplayed();
			}
			catch(Exception e)
			{
				return false;
			}
	}
	public void clickLogout()
	{
		linkLogout.click();
	}
	
	

}
