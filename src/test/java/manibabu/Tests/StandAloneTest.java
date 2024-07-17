package manibabu.Tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;
import junit.framework.Assert;
import manibabu.SeleniumFrameworkDesign.LandingPage;
import manibabu.SeleniumFrameworkDesign.ProductCatalogue;

public class StandAloneTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1000));
		
		
		
		
		LandingPage login = new LandingPage(driver);
		login.goTo(); 
		login.loginApplication("practice.selenium@gmail.com", "Manibabu@373");
		/*driver.findElement(By.id("userEmail")).sendKeys("practice.selenium@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Manibabu@373");
		driver.findElement(By.id("login")).click();*/  
		
		/*List<WebElement> products = driver.findElements(By.cssSelector(
				"div[class=\"col-lg-4 col-md-6 col-sm-10 offset-md-0 offset-sm-1 mb-3 ng-star-inserted\"]"));*/
		ProductCatalogue prodcat= new  ProductCatalogue(driver);
	//	List<WebElement> products= prodcat.productsList;
		
		
		
		
		
		/*
		 * for(WebElement product:products) { String value= product.getText(); String[]
		 * val= value.split(" "); String productname= val[0]; if
		 * (productname.contains("ZARA")) {
		 * product.findElement(By.cssSelector("button[class=\"btn w-10 rounded\"]")).
		 * click(); }
		 * 
		 * }
		 */
		// here applying filter to search the product text/name and used findfirst
		// method if any product found name ZARA we can select 1st one if not found any
		// return null
		
		
		
		String productname= "ZARA COAT 3";
		/*WebElement prod = products.stream().filter(
				product -> product.findElement(By.xpath("//b[text()=\"ZARA COAT 3\"]")).getText().equals(productname))
				.findFirst().orElse(null);*/
		//prodcat.getProductByName("ZARA COAT 3");
		
		
		//prod.findElement(By.cssSelector("button[class=\"btn w-10 rounded\"]")).click();
		prodcat.productToCart(productname);
		WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(20));
		/*wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[id=\"toast-container\"]")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));*/
		//driver.findElement(By.cssSelector("button[routerlink=\"/dashboard/cart\"]")).click();
		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cart h3"));
		Boolean match= cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productname));
		Assert.assertTrue(match);
		WebElement checkout =driver.findElement(By.cssSelector(".totalRow button"));
		wait.until(ExpectedConditions.elementToBeClickable(checkout));
		Actions actions = new Actions(driver);
		actions.moveToElement(checkout).click().perform();
		checkout.click();
		driver.findElement(By.xpath("//input[@placeholder=\"Select Country\"]")).sendKeys("ind");
		List<WebElement> countryList = driver.findElements(By.xpath("//span[@class=\"ng-star-inserted\"]"));
		WebElement co= countryList.stream().filter(country->country.getText().equalsIgnoreCase("India")).findAny().orElse(null);
		co.click();
		WebElement placeorder = driver.findElement(By.cssSelector("a[class*=\"action__submit\"]"));
        actions.moveToElement(placeorder).build().perform();
        placeorder.click();
       wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".toast-container")));
       String ordertext=  driver.findElement(By.cssSelector("label[class=\"ng-star-inserted\"]")).getText();
       System.out.println(ordertext);
       char[] ch = new char[ordertext.length()];
       for(int i=0;i<ordertext.length();i++)
       {
    	   if(ordertext.charAt(i)=='|' || ordertext.charAt(i)==' ')
    	   {
    		   continue;
    	   }
    	   else
    		   ch[i]=ordertext.charAt(i);
    		   
    	   
       }
      System.out.println(toString(ch).trim()); 
      
     
    
        
        
        
	}

	private static String toString(char[] ch) {
		// TODO Auto-generated method stub
		String string= new String(ch);
		return string;
	}
}
