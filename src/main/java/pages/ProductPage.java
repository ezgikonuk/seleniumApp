package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By addToBasket = By.id("button-cart");
    private final By basketCount = By.id("cart-items");
    private final By basketButton = By.id("cart");
    private final By myBasketButton = By.id("js-cart");

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public void clickAddToBasket() {
        wait.until(ExpectedConditions.elementToBeClickable(addToBasket));
        driver.findElement(addToBasket).click();
    }

    public String checkCartCount() {
        String count = driver.findElement(basketCount).getText();
        System.out.println("Sepet kontrol edildi. Sepetteki urun sayisi : " + count);
        return count;
    }

    public void clickMyBasket() {
        wait.until(ExpectedConditions.elementToBeClickable(basketButton));
        driver.findElement(basketButton).click();
    }

    public BasketPage clickBasketPage() {
        wait.until(ExpectedConditions.elementToBeClickable(myBasketButton));
        driver.findElement(myBasketButton).click();
        return new BasketPage(driver);
    }
}
