package pages;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InventoryPage {

    private WebDriver driver;

    // Locators
    private By addBackpackButton =
            By.id("add-to-cart-sauce-labs-backpack");

    private By removeBackpackButton =
            By.id("remove-sauce-labs-backpack");

    private By shoppingCartBadge =
            By.className("shopping_cart_badge");

    // Constructor
    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }

    // Actions
    public void addBackpackToCart() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.elementToBeClickable(addBackpackButton)).click();
    }

    public void removeBackpackFromCart() {
        driver.findElement(removeBackpackButton).click();
    }

    // Validation methods
    public String getCartBadgeCount() {
        return driver.findElement(shoppingCartBadge).getText();
    }

    public boolean isCartBadgeDisplayed() {
        return driver.findElements(shoppingCartBadge).size() > 0;
    }
}

