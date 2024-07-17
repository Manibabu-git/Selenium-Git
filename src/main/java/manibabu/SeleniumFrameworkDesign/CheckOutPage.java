package manibabu.SeleniumFrameworkDesign;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


import manibabu.Components.AbstractComponent;

public class CheckOutPage extends AbstractComponent{
	WebDriver driver;

	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		
	}
	
	@FindBy(css="input[placeholder=\"Select Country\"]")
	WebElement countryvalue;
	@FindBy(css=".list-group-item")
	List<WebElement> countryList;
	
	@FindBy(css=".action__submit")
	WebElement placeOrder;
	
	By toaster=By.id("toast-container");
	
	public void providePaymentDetails(String country)
	{
		
		countryvalue.sendKeys(country);
		WebElement selectCountry= countryList.stream().filter(coun->coun.getText().equalsIgnoreCase(country)).findAny().orElse(null);
		System.out.println(selectCountry.getText());
		moveToElement(selectCountry);
		selectCountry.click();
		
		
	}
	public ConformationPage submitOrder()
	{
		moveToElement(placeOrder);
		waitForWebElementToAppear(placeOrder);
		placeOrder.click();
		waitForElementToAppear(toaster);
		ConformationPage cnfPage= new ConformationPage(driver);
		return cnfPage;
		
	}

}
