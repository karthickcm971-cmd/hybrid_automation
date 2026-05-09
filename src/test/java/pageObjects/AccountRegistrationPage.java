package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage  extends basePage {
	
	//CONSTRUCTOR
	public AccountRegistrationPage(WebDriver driver)
	{
		super(driver);
	}
	
	//LOCATOR
	@FindBy(xpath="//input[@name='firstname']")
	WebElement txtFirstName;
	
	@FindBy(xpath="//input[@name='lastname']")
	WebElement txtLastName;
	
	@FindBy(xpath="//input[@id='input-email']")
	WebElement txtEmail;

	@FindBy(xpath="//input[@id='input-telephone']")
	WebElement txtphone;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement txtPwd;
	
	@FindBy(xpath="//input[@id='input-confirm']")
	WebElement txtConfirmPwd;
	
	@FindBy(xpath="//input[@name='agree']")
	WebElement cboxAgree;
	
	@FindBy(xpath="//input[@value='Continue']")
	WebElement btnContinue;
	
	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement msgConformation;
	
	// ACTIONS METHOD
	public void enterFirstName(String fname)
	{
		txtFirstName.sendKeys(fname);
	}
	public void enterLastName(String Lname)
	{
		txtLastName.sendKeys(Lname);
	}
	public void enterEmail(String Email)
	{
		txtEmail.sendKeys(Email);
	}
	public void enterTelephone(String Ph)
	{
		txtphone.sendKeys(Ph);
	}
	public void enterPassword(String pwd)
	{
		txtPwd.sendKeys(pwd);
	}
	public void enterConfirmPassword(String Cpwd)
	{
		txtConfirmPwd.sendKeys(Cpwd);
	}
	public void enableChkBoxAgree()
	{
		cboxAgree.click();
	}
	public void clickContinue()
	{
		// btnContinue.submit();
		
		 btnContinue.click();
		
		// Actions act = new Actions(driver);
		// act.moveToElement(btnContinue).click().perform();
		
		//JavascriptExecutor js = (JavascriptExecutor)driver;
		//js.executeScript("arguments[0].click()",btnContinue );
	}
	public String getConformationMessage() 
	{
		try 
		{
		 return msgConformation.getText();
		}
		catch(Exception e)
		{
			return e.getMessage();
		}
	}
	
	
}
