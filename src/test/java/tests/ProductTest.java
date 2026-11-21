package tests;

import base.BaseTest;
import pages.LoginPage;
import pages.InventoryPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProductTest extends BaseTest {

    @Test
    public void verifyAddProductToCart() {

        // Login
        LoginPage loginPage = new LoginPage(driver);

        loginPage.login("standard_user", "secret_sauce");

        // Add product
        InventoryPage inventoryPage =
                new InventoryPage(driver);

        inventoryPage.addBackpackToCart();

        // Verify cart badge
        String actualCount =
                inventoryPage.getCartBadgeCount();

        Assert.assertEquals(
                actualCount,
                "1",
                "Product was not added to cart!"
        );
    }

    @Test
    public void verifyRemoveProductFromCart() {

        // Login
        LoginPage loginPage = new LoginPage(driver);

        loginPage.login("standard_user", "secret_sauce");

        // Add product first
        InventoryPage inventoryPage =
                new InventoryPage(driver);

        inventoryPage.addBackpackToCart();

        // Remove product
        inventoryPage.removeBackpackFromCart();

        // Verify cart badge disappears
        Assert.assertFalse(
                inventoryPage.isCartBadgeDisplayed(),
                "Cart badge should disappear after removing product!"
        );
    }
}
