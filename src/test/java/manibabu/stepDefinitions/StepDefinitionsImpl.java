package manibabu.stepDefinitions;

import java.io.IOException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;
import manibabu.SeleniumFrameworkDesign.CartPage;
import manibabu.SeleniumFrameworkDesign.CheckOutPage;
import manibabu.SeleniumFrameworkDesign.ConformationPage;
import manibabu.SeleniumFrameworkDesign.LandingPage;
import manibabu.SeleniumFrameworkDesign.ProductCatalogue;
import manibabu.TestComponents.BaseTest;

public class StepDefinitionsImpl extends BaseTest{
	
	LandingPage landingPage;
	ProductCatalogue prodcat;
	CheckOutPage chPage;
	ConformationPage cnfPage;
	
	@Given("i landed on the Ecommerce Page")
	public void i_landed_on_the_Ecommerce_Page() throws IOException
	{
		landingPage= launchApplication();
	}
	@Given ("^login with useremail (.+) password (.+)$")
	public void login_with_useremail_password(String username, String password)
	{
		prodcat = landingPage.loginApplication(username,password );
	}
	@When("^add the  product (.+) to Cart$")
	public void add_the_product_Cart(String productName)
	{
		CartPage crtpage = prodcat.goToCart();
		Boolean match = crtpage.verifyProduct(productName);
		Assert.assertTrue(match);
		chPage = crtpage.checkout();
	}
	@When("^checkout the (.+) and submit the order$")
	public void checkout_the_and_submit_the_order(String productName)
	{
		String country = "India";
		chPage.providePaymentDetails(country);
		cnfPage = chPage.submitOrder();
		
	}
	@Then("{string} message is displayed on the confirmation page")
	public void message_is_displayed_on_the_confirmation_page(String message)
	{
		ConformationPage cnfPage = chPage.submitOrder();
		Boolean verify = cnfPage.verifyConfirmationOrder();
		Assert.assertTrue(verify);
	}
	
}
