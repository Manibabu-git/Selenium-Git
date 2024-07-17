package manibabu.SeleniumFrameworkDesign;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import manibabu.Components.AbstractComponent;

public class ProductCatalogue extends AbstractComponent{
	
	WebDriver driver;
	public ProductCatalogue(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);// this method provides driver to the FindBy annotations
		
	}
	@FindBy(css=".mb-3")// here driver scope will be activate once you should use initElements method in the constructor
	List<WebElement> productsList;
	By findBy = By.cssSelector(".mb-3");
	By toaster = By.cssSelector("div[id='toast-container']");
	
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
	
	
	
	public List<WebElement> getProductList()
	{
		waitForElementToAppear(findBy);
		return productsList;
	}
	public WebElement getProductByName(String productName)
	{
		waitForElementToDisappear(spinner);
		WebElement prod = productsList.stream().filter(product->product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		System.out.println(prod.getText());
		return prod;
	}
	public void productToCart(String productname)
	{
		WebElement prod = getProductByName(productname);
		
		WebElement pd= prod.findElement(By.cssSelector("button[class='btn w-10 rounded']"));
		waitForWebElementToAppear(pd);
		pd.click();
		System.out.println("nothing");
		waitForElementToAppear(toaster);
		waitForElementToDisappear(spinner);
		
	}
		
}