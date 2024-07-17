package manibabu.SeleniumFrameworkDesign;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import manibabu.Components.AbstractComponent;

public class CartPage extends AbstractComponent {
	
	WebDriver driver;
	
	
	@FindBy(css=".cart h3")
	List<WebElement> products;
	
	@FindBy(css=".totalRow button")
	WebElement checkout;
	
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	public Boolean verifyProduct(String productname)
	{
		List<WebElement> l=products;
		for(WebElement el:l)
		{
			System.out.println(el.getText());
		}
		Boolean match= products.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productname));
		return match;
	}
	public CheckOutPage checkout()
	{

		moveToElement(checkout);
		checkout.click();
		CheckOutPage chPage= new CheckOutPage(driver);
		return chPage;
	}

}
