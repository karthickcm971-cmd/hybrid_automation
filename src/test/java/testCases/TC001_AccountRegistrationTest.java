package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;

public class TC001_AccountRegistrationTest extends BaseClass{
	
		@Test (groups = {"Regression","Master"})
		public void verifyAccountRegestration()
		{
			logger.info("**********Starting TC001_AccountRegistrationTest**********");
			try
			{
			 HomePage hp = new HomePage(driver);
			 hp.clickMyAccount();
			 logger.info(".......Clicked myAccount.....");
			 hp.clickRegister();
			 logger.info(".......Clicked Registration......");
			 
			 AccountRegistrationPage reg = new AccountRegistrationPage(driver);
			 logger.info("......Providing Regestration Detail........");
             reg.enterFirstName(randomString(7));
			 reg.enterLastName(randomString(5));
			 reg.enterEmail(randomString(5)+"@gmail.com");
			 reg.enterTelephone(randomNumber(10));
			 String passwd= randomAlphaNumeric(10);
             reg.enterPassword(passwd);
             reg.enterConfirmPassword(passwd);
             reg.enableChkBoxAgree();
             reg.clickContinue();
             logger.info("Validating expected msg....");
             String confMsg = reg.getConformationMessage();
             if(confMsg.equals("Your Account Has Been Created!"))
             {
            	 Assert.assertTrue(true);
             }
             else
             {
            	 logger.error("....Test failed....");
 				logger.debug("...Debug logs....");
 				Assert.assertTrue(false);
             }
             //Assert.assertEquals(confMsg, "Your Account Has Been Created!", "Test failed..");
			}
			catch(Exception e)
			{
				Assert.fail();
			}
			logger.info(".....Finished TC001_AccountRegistrationTest.......");
		}
		
		
}
