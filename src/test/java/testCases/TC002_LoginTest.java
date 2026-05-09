package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;

public class TC002_LoginTest extends BaseClass {
	
	@Test (groups = {"Sanity","Master"})
	public void verifyLoginTest()
	{
		logger.info(" *********** Stating TC002_LoginTest ************ ");
		
		try
		{
		//Home Page
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		//Login Page
		LoginPage Lp = new LoginPage(driver);
		Lp.EnterEmail(p.getProperty("email"));
		Lp.EnterPassword(p.getProperty("password"));
		Lp.clickLogin();
		
		//My Account page
		MyAccountPage Acc = new MyAccountPage(driver);
		boolean targetPage= Acc.isMyAccountPageExists();
		
	    Assert.assertTrue(targetPage);
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		logger.info("************ Finished TC002_LoginTest ************** ");
	}
	
	

}
