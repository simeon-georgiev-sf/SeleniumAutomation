package SimeonTestingLearning.stepDefinitions;

import java.io.IOException;

import org.testng.Assert;

import SimeonTestingLearning.TestComponents.BaseTest;
import SimeonTestingLearning.pageObjects.CheckoutPage;
import SimeonTestingLearning.pageObjects.ConfirmationPage;
import SimeonTestingLearning.pageObjects.LandingPage;
import SimeonTestingLearning.pageObjects.OrderCheckout;
import SimeonTestingLearning.pageObjects.ProductCatalogue;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImpl extends BaseTest {
	public LandingPage landingpage;
	public ProductCatalogue pCatalogue;
	public ConfirmationPage confirmPage;

	@Given("I landed on the ecommerce page")
	public void I_landend_on_the_ecommerce_page() throws IOException {
		landingpage = launchApplication();

	}

	@Given("^Logged in with username (.+) and password (.+)$")
	public void logged_in_username_and_password(String name, String password) {
		pCatalogue = lPage.loginApplication(name, password);

	}

	@When("^I add product (.+) to cart$")
	public void add_product_to_cart(String productName) {
		pCatalogue.getProductList();
		pCatalogue.addProductToCart(productName);
	}

	@When("^Checkout and verify (.+) and submit order$")
	public void checkout_submit_order(String productName) {
		OrderCheckout checkout = pCatalogue.goToCart();
		Boolean match = checkout.validateItem(productName);
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = checkout.goToCheckout();
		checkoutPage.countrySelect("Bu");
		confirmPage = checkoutPage.submitOrder();
	}

	@Then("{string} is displayed on the confirmation page")
	public void message_displayed_confirmation_page(String string) {
		String confirmMessage = confirmPage.getOrderMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
		driver.close();
	}
	
	@Then("{string} is displayed on the login page")
	public void error_message_is_displayed(String string) {
		Assert.assertEquals(string, lPage.getErrorMessage());
		driver.close();
	}

}
