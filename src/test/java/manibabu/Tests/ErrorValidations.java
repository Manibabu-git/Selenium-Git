package manibabu.Tests;

import org.testng.annotations.Test;



import junit.framework.Assert;
import manibabu.SeleniumFrameworkDesign.OrderViewPage;
import manibabu.SeleniumFrameworkDesign.ProductCatalogue;
import manibabu.TestComponents.BaseTest;
import manibabu.TestComponents.Retry;

public class ErrorValidations extends BaseTest {
	
	@Test(groups={"ErrorHandling"},retryAnalyzer=Retry.class)
	public void LoginErrorMessage()
	{
		
		landingPage.loginApplication("practicxxcxcce.selenium@gmail.com", "Manibabu@373");
		String errorMessage= landingPage.ErrorMessageValidation();
		System.out.println(errorMessage);
	    Assert.assertEquals("Incorrect email or password.", errorMessage);
		
	}
	@Test
	public void errorProductMessage()
	{
		String productname = "ZARA COAT 3";
		ProductCatalogue prodcat = landingPage.loginApplication("practice.selenium@gmail.com", "Manibabu@373");
	    prodcat.goToOrders();
		OrderViewPage viewPage = prodcat.goToViewPage();
		String orderId = viewPage.clickView(productname);
		Assert.assertEquals(orderId, productname);
	}

}
