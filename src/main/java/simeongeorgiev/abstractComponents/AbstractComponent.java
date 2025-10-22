package SimeonTestingLearning.AbstractComponents;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import SimeonTestingLearning.pageObjects.OrderCheckout;
import SimeonTestingLearning.pageObjects.OrderPage;

public class AbstractComponent {
	
	WebDriver driver;
	@FindBy(xpath = "//button[@routerlink='/dashboard/cart']")
	WebElement cartBtn;
	
	@FindBy(xpath = "//button[@routerlink='/dashboard/myorders']")
	WebElement orderHeaderBtn;


	public AbstractComponent(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	/* Not able to use this and .getText() method
	public void waitForWebElementToAppear(By locator) {
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	} */
	
	public String waitAndGetText(By locator) {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	    WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	    return element.getText();
	}
	
	public void waitForElementToAppear(By findBy) {
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(7));
	wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(findBy));
	}

	public void waitForElementToDisAppear(By locator) {
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(7));
	wait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}
	
	public OrderCheckout goToCart() {
		cartBtn.click();
		OrderCheckout checkout = new OrderCheckout(driver);
		return checkout;
	}
	
	public OrderPage goToOrdersPage() {
		orderHeaderBtn.click();
		OrderPage orderPage = new OrderPage(driver);
		return orderPage;
	}
}
