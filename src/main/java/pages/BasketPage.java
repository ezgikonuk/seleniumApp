package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasketPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    // Burada elementlere id verilmedigi icin elementlerin diger ozellikleri kullanildi
    private final By increaseProductButton = By.xpath("//input[@name='quantity']");
    private final By refreshButton = By.xpath("//i[@title='Güncelle']");
    private final By deleteProductButton = By.xpath("//i[@title='Kaldır']");
    private final By basketStatus = By.xpath("//div[@class='content']");
    String expectedBasketStatus = "Sepetinizdeki ürünleri görmek için üye girişi yapmanız gerekmektedir.";

    public BasketPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(50));
    }

    public void increaseProductCount(String number){
        int newCount = Integer.parseInt(number);
        newCount++;
        String result = String.valueOf(newCount);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(increaseProductButton));
        driver.findElement(increaseProductButton).click();
        driver.findElement(increaseProductButton).clear();
        driver.findElement(increaseProductButton).sendKeys(result);
        System.out.println("Adet sayisi arttirildi. " + result);
    }

    public void refreshProduct(){
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(refreshButton));
        driver.findElement(refreshButton).click();
        System.out.println("Urun yenilendi.");
    }

    public void deleteProduct(){
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(deleteProductButton));
        driver.findElement(deleteProductButton).click();
        System.out.println("Urun sepetten silindi.");
    }

    public void checkBasketStatus(){
        wait.until(ExpectedConditions.presenceOfElementLocated(basketStatus));
        Assert.assertTrue(expectedBasketStatus.contains(driver.findElement(basketStatus).getText()));
        System.out.println("Sepet bos");
    }
}
