package manibabu.SeleniumFrameworkDesign;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import manibabu.Components.AbstractComponent;

public class OrderViewPage extends AbstractComponent{
	
	WebDriver driver;
	
	@FindBy(xpath="//tbody/tr/td[2]")
	List<WebElement> orderedProductList;
	
	@FindBy(xpath="//div[text()=\" ZARA COAT 3 \"]")
	WebElement orderId;
	
	public OrderViewPage (WebDriver driver)
	{
	    super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	public String clickView(String productname)
	{
		WebElement productFound = orderedProductList.stream().filter(orproduct->orproduct.getText().equalsIgnoreCase(productname)).findAny().orElse(null);
	    productFound.findElement(By.xpath("//button[text()='View']")).click();
		return orderId.getText();
		
	}

}
