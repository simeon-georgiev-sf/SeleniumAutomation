package simeongeorgiev.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import simeongeorgiev.abstractComponents.AbstractComponent;

public class OrderCheckout extends AbstractComponent {

	WebDriver driver;

	public OrderCheckout(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// By cartItemList = By.cssSelector(".cartSection h3");

	@FindBy(css = ".cartSection h3")
	List<WebElement> cartItemList;
	@FindBy(css = ".totalRow button")
	WebElement checkoutEle;

	public Boolean validateItem(String productName) {
		Boolean isMatchingItem = cartItemList.stream().anyMatch(prod -> prod.getText().equalsIgnoreCase(productName));
		return isMatchingItem;
	}

	public CheckoutPage goToCheckout() {
		checkoutEle.click();
		return new CheckoutPage(driver);
	}

}
