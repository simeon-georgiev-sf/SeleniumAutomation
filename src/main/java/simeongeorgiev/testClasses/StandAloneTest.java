package SimeonTestingLearning.SeleniumFramework1;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

		
		@Test public void initialTest() {
		String productName = "ADIDAS ORIGINAL";
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		Actions a = new Actions(driver);

		driver.get("https://rahulshettyacademy.com/client/#/auth/login");
		//landingPage lPage = new landingPage(driver);
		driver.findElement(By.id("userEmail")).sendKeys("email@emails.com");
		driver.findElement(By.xpath("//input[@formcontrolname='userPassword']")).sendKeys("customPass!1");
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		
		//Wait for elements and collect into a list
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".mb-3")));
		List<WebElement> productList = driver.findElements(By.cssSelector(".mb-3"));
		
		//Filter list with stream to find product & add to cart
		WebElement filProd =productList.stream().filter
			(product->product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		filProd.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		//Wait for loading and go to cart
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();

		//Validate item added to cart
		List <WebElement> cartProd = driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean isPresent = cartProd.stream().anyMatch(prod->prod.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(isPresent);
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		/* Finalize order using Stream
		driver.findElement(By.xpath("//input[@placeholder]")).sendKeys("Bu");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		List <WebElement> countryList = driver.findElements(By.xpath("//button[contains(@class,'ta-item')]//span"));
		countryList.stream()
			.filter(s->s.getText().trim().equalsIgnoreCase("Bulgaria"))
			.findFirst()
			.ifPresent(WebElement::click);
		
		*/
		//
		a.sendKeys(driver.findElement(By.xpath("//input[@placeholder]")), "Bu").build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		driver.findElement(By.xpath("(//button[@type='button'])[2]")).click();
		driver.findElement(By.cssSelector(".action__submit")).click();
		String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
		driver.quit();
		
	}

}
