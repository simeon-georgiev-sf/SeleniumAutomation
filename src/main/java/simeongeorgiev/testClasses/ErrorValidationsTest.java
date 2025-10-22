package simeongeorgiev.testClasses;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import simeongeorgiev.pageObjects.OrderCheckout;
import simeongeorgiev.pageObjects.ProductCatalogue;
import simeongeorgiev.testComponents.BaseTest;
import simeongeorgiev.testComponents.Retry;

public class ErrorValidationsTest extends BaseTest {

	@Test(retryAnalyzer=Retry.class, groups="ErrorHandling")
	public void loginErrorValidation() throws IOException {

		// Use login method to add values and click login
		lPage.loginApplication("emailss@emails.com", "customPass!1");
		Assert.assertEquals("Incorrect email or password.", lPage.getErrorMessage());
	}

	@Test
	public void productErrorValidation() throws IOException {
		String productName = "ADIDAS ORIGINAL";

		ProductCatalogue pCatalogue = lPage.loginApplication("simeon@tester.com", "customPass@2");

		pCatalogue.getProductList();
		pCatalogue.addProductToCart(productName);
		OrderCheckout checkout = pCatalogue.goToCart();

		Boolean match = checkout.validateItem("Zara Coat 3");
		Assert.assertFalse(match);

	}

}
