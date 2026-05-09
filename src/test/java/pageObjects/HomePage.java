package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends basePage{

	//CONSTRUCTOR
	public HomePage(WebDriver driver)
	{
		super (driver);
	}
	//LOCATORS
	@FindBy(xpath="//span[normalize-space()='My Account']")
	WebElement linkMyAccount;
	
	@FindBy(xpath="//a[text()='Register']")
	WebElement linkRegister;
	
	@FindBy(xpath ="//a[normalize-space()='Login']")
	WebElement linkLogin;

	//ACTION METHODS
	public void clickMyAccount()
	{
		linkMyAccount.click();
	}
	public void clickRegister()
	{
		linkRegister.click();
	}
	public void clickLogin()
	{
		linkLogin.click();
	}
}
