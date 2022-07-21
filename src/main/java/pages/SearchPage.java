package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class SearchPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By selectProduct = By.className("pr-img-link");

    public SearchPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public ProductPage selectProduct() throws InterruptedException {
        // Sayfadaki tum kitaplarin sayisi alinir ve random bir sayi generate edilir. Liste icerisindeki sayiyla eslesen kitaba tiklanir
        wait.until(ExpectedConditions.elementToBeClickable(selectProduct));
        List<WebElement> elements = driver.findElements(selectProduct);
        Random random = new Random();
        WebElement webElement = elements.get(random.nextInt(elements.size() - 1));
        Actions actions = new Actions(driver);
        actions.moveToElement(webElement);
        actions.perform();
        Thread.sleep(1000);
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
        webElement.click();
        return new ProductPage(driver);
    }
}
