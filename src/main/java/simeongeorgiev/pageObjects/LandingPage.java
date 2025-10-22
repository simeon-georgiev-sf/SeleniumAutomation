package SimeonTestingLearning.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SimeonTestingLearning.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {
	
	WebDriver driver;
	public LandingPage(WebDriver driver) 
	{
		// This will initialize first in this class
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	
	// Another way is to use PageFactory instead of writing the driver object always
	// We're using initElements in the constructor for @FindBy to work using driver
	@FindBy(id="userEmail")
	WebElement usermail;
	@FindBy(xpath="//input[@formcontrolname='userPassword']")
	WebElement userPassword;
	@FindBy(id="login")
	WebElement login;
	
	/* Not able to use this and getText method
	public String getErrorMessage() {
		waitForWebElementToAppear(errorMsg);
		return errorMsg.getText();	

	}*/
	
	public String getErrorMessage() {
	    return waitAndGetText(By.cssSelector(".toast-error"));
	}

	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client/#/auth/login");

	}
	
	public ProductCatalogue loginApplication(String email, String password) {
		usermail.sendKeys(email);
		userPassword.sendKeys(password);
		login.click();
		ProductCatalogue pCatalogue = new ProductCatalogue(driver);
		return pCatalogue;
	}

}
