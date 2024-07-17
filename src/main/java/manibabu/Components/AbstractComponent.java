package manibabu.Components;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;
import manibabu.SeleniumFrameworkDesign.CartPage;
import manibabu.SeleniumFrameworkDesign.OrderPage;
import manibabu.SeleniumFrameworkDesign.OrderViewPage;

public class AbstractComponent {
	
	WebDriver driver;
	WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(5));
	
	
	
	public AbstractComponent(WebDriver driver) {

		
         this.driver=driver;
         PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="button[routerlink='/dashboard/cart']")
	WebElement cartButton;
	
	@FindBy(css="button[routerlink='/dashboard/myorders']")
	WebElement orderButton;
	
	public CartPage goToCart()
	{
		moveToElement(cartButton);
		
		wait.until(ExpectedConditions.visibilityOf(cartButton));
		System.out.println(cartButton.getText());
		cartButton.click();
		CartPage crtpage= new CartPage(driver);
		return crtpage;
	}
	public OrderPage goToOrders()
	{
		
		orderButton.click();
		OrderPage orderPage= new OrderPage(driver);
		return orderPage;
	}
	public OrderViewPage goToViewPage()
	{
		OrderViewPage view= new OrderViewPage(driver);
		return view;
		
	}
	
	public void waitForElementToAppear(By findBy)
	{
		WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	public void waitForElementToDisappear(WebElement spinner)
	{
		WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.invisibilityOf(spinner));
	}
	public void moveToElement(WebElement ele)
	{
		Actions actions= new Actions(driver);
		actions.moveToElement(ele).build().perform();;
	}
	public void waitForWebElementToAppear(WebElement webElement)
	{
		WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(webElement));
	}

}
