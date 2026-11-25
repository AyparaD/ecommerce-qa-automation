package tests;

import org.testng.annotations.Listeners;
import utils.TestListener;
import base.BaseTest;
import pages.LoginPage;
import pages.InventoryPage;
import pages.CartPage;
import pages.CheckoutPage;

import org.testng.Assert;
import org.testng.annotations.Test;

@Listeners(TestListener.class)
public class CheckoutTest extends BaseTest {

    @Test
    public void verifySuccessfulCheckout() {

        // Step 1: Login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        System.out.println("Current URL: " + driver.getCurrentUrl());
        System.out.println("Page title: " + driver.getTitle());

        // Step 2: Add product
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.addBackpackToCart();

        // Step 3: Open cart
        CartPage cartPage = new CartPage(driver);
        cartPage.openCart();

        // Step 4: Proceed to checkout
        cartPage.clickCheckout();

        // Step 5: Fill checkout information
        CheckoutPage checkoutPage = new CheckoutPage(driver);

        checkoutPage.fillCheckoutInformation(
                "Aypara",
                "QA",
                "AZ1000"
        );

        // Step 6: Finish order
        checkoutPage.clickFinish();

        // Step 7: Verify confirmation
        String actualMessage =
                checkoutPage.getConfirmationMessage();

        Assert.assertEquals(
                actualMessage,
                "Thank you for your order!",
                "Order was not completed successfully!"
        );
    }

    @Test
    public void verifyCheckoutWithEmptyFirstName() {

        // Login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");


        // Add product
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.addBackpackToCart();

        // Open cart
        CartPage cartPage = new CartPage(driver);
        cartPage.openCart();
        cartPage.clickCheckout();

        // Leave first name empty
        CheckoutPage checkoutPage = new CheckoutPage(driver);

        checkoutPage.enterLastName("QA");
        checkoutPage.enterPostalCode("AZ1000");
        checkoutPage.clickContinue();

        // Verify error message
        String actualError = checkoutPage.getErrorMessage();

        Assert.assertEquals(
                actualError,
                "Error: First Name is required",
                "Expected validation message was not displayed!"
        );
    }
}
