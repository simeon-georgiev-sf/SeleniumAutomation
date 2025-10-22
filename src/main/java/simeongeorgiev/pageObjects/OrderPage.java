package simeongeorgiev.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import simeongeorgiev.abstractComponents.AbstractComponent;

public class OrderPage extends AbstractComponent {

	WebDriver driver;

	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//tr/td[2]")
	List<WebElement> orderList;


	public Boolean verifyOrderDisplay(String productName) {
		Boolean isMatchingItem = orderList.stream().anyMatch(prod -> prod.getText().equalsIgnoreCase(productName));
		return isMatchingItem;
	}

}
