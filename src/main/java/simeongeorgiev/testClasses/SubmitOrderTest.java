package simeongeorgiev.testClasses;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import simeongeorgiev.testComponents.BaseTest;
import simeongeorgiev.pageObjects.CheckoutPage;
import simeongeorgiev.pageObjects.ConfirmationPage;
import simeongeorgiev.pageObjects.OrderCheckout;
import simeongeorgiev.pageObjects.OrderPage;
import simeongeorgiev.pageObjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest {

	String productName = "ADIDAS ORIGINAL";
	String testGit = "This is a test for Git";

	@Test(dataProvider = "getData", groups="Purchase")
	public void submitOrder(HashMap<String, String> input) throws IOException {

		// Use login method to add values and click login
		ProductCatalogue pCatalogue = lPage.loginApplication(input.get("email"), (input.get("password")));

		// Wait, find element, add to and go to cart
		pCatalogue.getProductList();
		pCatalogue.addProductToCart(input.get("product"));
		OrderCheckout checkout = pCatalogue.goToCart();

		// Validate item and go to checkout
		Boolean match = checkout.validateItem(input.get("product"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = checkout.goToCheckout();
		checkoutPage.countrySelect("Bu");

		// Submit and validate order number
		ConfirmationPage confirmPage = checkoutPage.submitOrder();
		String confirmMessage = confirmPage.getOrderMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));

}

	@Test(dependsOnMethods = { "submitOrder" })
	public void OrderHistoryTest() {

		ProductCatalogue pCatalogue = lPage.loginApplication("email@emails.com", "customPass!1");
		OrderPage orderPage = pCatalogue.goToOrdersPage();
		Assert.assertTrue(orderPage.verifyOrderDisplay(productName));
	}

	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String, String>> data = (List<HashMap<String, String>>) getJsonDataToMap(
				System.getProperty("user.dir") + "//src//main//java//SimeonTestingLearning//data//PurchaseOrder.json");
		return new Object[][] { { data.get(0) }, { data.get(1) } };
	}
	

}