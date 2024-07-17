package manibabu.Tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import junit.framework.Assert;
import manibabu.SeleniumFrameworkDesign.CartPage;
import manibabu.SeleniumFrameworkDesign.CheckOutPage;
import manibabu.SeleniumFrameworkDesign.ConformationPage;
import manibabu.SeleniumFrameworkDesign.OrderPage;
import manibabu.SeleniumFrameworkDesign.ProductCatalogue;
import manibabu.SeleniumFrameworkDesign.OrderViewPage;
import manibabu.TestComponents.BaseTest;

public class SubmitOrder extends BaseTest {

//	@Test(dataProvider="getData",groups="Product")
//	public void submitOrder(String useremail, String password, String productname) throws IOException {
//
//		
//		ProductCatalogue prodcat = landingPage.loginApplication(useremail,password );
//		prodcat.productToCart(productname);
//		CartPage crtpage = prodcat.goToCart();
//		Boolean match = crtpage.verifyProduct(productname);
//		Assert.assertTrue(match);
//		CheckOutPage chPage = crtpage.checkout();
//		String country = "India";
//		chPage.providePaymentDetails(country);
//		ConformationPage cnfPage = chPage.submitOrder();
//		Boolean verify = cnfPage.verifyConfirmationOrder();
//		Assert.assertTrue(verify);
//
//	}
	@Test(dataProvider="getData",groups="Product")
	public void submitOrder(HashMap<String,String> input) throws IOException {

		
		ProductCatalogue prodcat = landingPage.loginApplication(input.get("email"), input.get("password") );
		prodcat.productToCart(input.get("productname"));
		CartPage crtpage = prodcat.goToCart();
		Boolean match = crtpage.verifyProduct(input.get("productname"));
		Assert.assertTrue(match);
		CheckOutPage chPage = crtpage.checkout();
		String country = "India";
		chPage.providePaymentDetails(country);
		ConformationPage cnfPage = chPage.submitOrder();
		Boolean verify = cnfPage.verifyConfirmationOrder();
		Assert.assertTrue(verify);

	}
	@Test(dependsOnMethods = { "submitOrder" })
	public void OrderedProduct() {
		String productname = "ZARA COAT 3";
		ProductCatalogue prodcat = landingPage.loginApplication("practice.selenium@gmail.com", "Manibabu@373");
		OrderPage orderPage = prodcat.goToOrders();
		Boolean match = orderPage.verifyProduct(productname);
		Assert.assertTrue(match);

	}

	@Test(dependsOnMethods= {"OrderedProduct"})
	public void OrderView() {
		String productname = "ZARA COAT 3";
		ProductCatalogue prodcat = landingPage.loginApplication("practice.selenium@gmail.com", "Manibabu@373");
		prodcat.goToOrders();
		OrderViewPage viewPage = prodcat.goToViewPage();
		String orderId = viewPage.clickView(productname);
		Assert.assertEquals(orderId, productname);

	}
//	@DataProvider
//	public Object[][] getData()
//	{
//		Object[][] obj= new Object[][] {{"practice.selenium@gmail.com","Manibabu@373","ZARA COAT 3"},{"mani.uppu@gmail.com","Manibabu@373","ADIDAS ORIGINAL"}};
//		return obj;
//	}
	@DataProvider
	public Object[][] getData() throws IOException
	{
		
		
		List<HashMap<String,String>> data= getJsonToMap(System.getProperty("user.dir")+"//src//test//java//manibabu//data//PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
		
//		HashMap<String, String> map= new HashMap<String, String>();
//		map.put("email", "practice.selenium@gmail.com");
//		map.put("password", "Manibabu@373");
//		map.put("productname", "ZARA COAT 3");
//		HashMap<String, String> maptwo= new HashMap<String, String>();
//		maptwo.put("email", "mani.uppu@gmail.com");
//		maptwo.put("password", "Manibabu@373");
//		maptwo.put("productname", "ADIDAS ORIGINAL");
//		return new Object[][] {{map},{maptwo}};
	}
	
}
