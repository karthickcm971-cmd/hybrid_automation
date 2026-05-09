package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import utilities.DataProviders;

/*
   Data is valid - login success -Test passed
   Data is valid - Login failed -Test failed
   
   Data is invalid -login success - Test failed
   Data is invalid - login failed - Test passed
*/
public class TC003_LoginDDT extends BaseClass {
	
	@Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class ,groups = "DataDriven")
	public void verify_LoginDDT( String email, String pwd ,String exp)
	{
		     
		    logger.info("******************Starting TC003_LoginDDT************************************");
		    try
		    {
		    	//Home Page
				HomePage hp = new HomePage(driver);
				hp.clickMyAccount();
				hp.clickLogin();
				
				//Login Page
				LoginPage Lp = new LoginPage(driver);
				Lp.EnterEmail(email);
				Lp.EnterPassword(pwd);
				Lp.clickLogin();
				
				//My Account page
				MyAccountPage Acc = new MyAccountPage(driver);
				boolean targetPage= Acc.isMyAccountPageExists();
					
					/*
					   Data is valid - login success -Test passed
					                        - Login failed -Test failed
					   
					   Data is invalid -login success - Test failed
					                          - login failed - Test passed
					*/
					
					if(exp.equalsIgnoreCase("valid"))
					{
						if(targetPage==true)
						{
							Acc.clickLogout();  // before assert we have to click logout
							Assert.assertTrue(true);
						}
						else
						{
							Assert.assertTrue(false);
						}
					}
					
					if(exp.equalsIgnoreCase("invalid"))
					{
						if(targetPage==true)
						{
							Acc.clickLogout();
							Assert.assertTrue(false);
						}
						else
						{
							Assert.assertTrue(true);
						}
					}
		    }
		    catch(Exception e)
		    {
		    	 e.printStackTrace(); 
		    	 Assert.fail(e.getMessage());
		    }
			
			logger.info("**********************Finished TC003_LoginDDT***********************");
	}

}
