package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends basePage {
	
	// CONSTRUCTOR
	public LoginPage (WebDriver driver)
	{
		super (driver);
	}
	
	// LOCATOR
	
	@FindBy(xpath = "//input[@id='input-email']")
	WebElement txtEmail;
	
	@FindBy(xpath ="//input[@id='input-password']")
	WebElement txtPassword;
	
	@FindBy(xpath = "//input[@value='Login']")
	WebElement linkLogin1;
	
	// ACTION METHODS
	
	public void EnterEmail( String email )
	{
		txtEmail.sendKeys(email);
	}
	
	public void EnterPassword ( String pwd )
	{
		txtPassword.sendKeys(pwd);
	}
	public void clickLogin ()
	{
		linkLogin1.click();
	}
	

}
