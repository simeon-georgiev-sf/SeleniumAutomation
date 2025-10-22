package simeongeorgiev.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import simeongeorgiev.abstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent {

	WebDriver driver;
	public CheckoutPage (WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy (xpath = "//input[@placeholder]")
	private WebElement country;
	By result = By.cssSelector(".ta-results");
	@FindBy (xpath = "(//button[@type='button'])[2]")
	private	WebElement countryOrder;
	@FindBy (css = ".action__submit")
	private WebElement submitBtn;

	public void countrySelect(String countryName) {
		Actions a = new Actions(driver);
		a.sendKeys(country, countryName).build().perform();
		waitForElementToAppear(result);
		countryOrder.click();

	}

	public ConfirmationPage submitOrder() {
		submitBtn.click();
		return new ConfirmationPage(driver);
	}

}
