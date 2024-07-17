package manibabu.SeleniumFrameworkDesign;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import manibabu.Components.AbstractComponent;


public class ConformationPage extends AbstractComponent{

	WebDriver driver;
	
	
	@FindBy(css=".hero-primary")
	WebElement confirmationText;
	
	
	public ConformationPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	public Boolean verifyConfirmationOrder()
	{
		waitForWebElementToAppear(confirmationText);
		Boolean verify= confirmationText.getText().equals("THANKYOU FOR THE ORDER.");
		return verify;
	}

}
