import helper.CSVReader;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.CartPage;
import pages.HomePage;
import pages.ProductPage;
import pages.SearchPage;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.List;


public class BaseTest {
    private static WebDriver driver;
    protected static HomePage homePage;

    @BeforeClass
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", getFileFromResource("chromedriver.exe"));
        driver = new ChromeDriver();
        driver.get("https://www.kitapyurdu.com/");
        String title = driver.getTitle();
        String expectedTitle = "Kitapyurdu, Kitapla buluşmanın en kolay yolu";
        Assert.assertEquals(expectedTitle, title); // AnaSayfanın açıldığı sayfa title'ı üzerinden kontrol edilir
        homePage = new HomePage(driver);
        driver.manage().window().maximize(); // Chrome ekranı büyütülür
    }

    @Test
    public void testingScenario() throws InterruptedException {
        // Tum islemler arasina 1 saniye delay ekledim, rahat anlasilabilmesi icin
        CSVReader csvReader = new CSVReader();
        List<String> texts = csvReader.getSearchTexts(getFileFromResource("books.csv")); // CSV dosyası okunur
        homePage.fillSearchArea(texts.get(0)); // Aramaya csv dosyasındaki text yazılır
        Thread.sleep(1000);
        SearchPage searchPage = homePage.clickSearchButton(); //Arama butonuna basarak arama kutusuna girilmiş olan roman kelimesini aratır.
        Thread.sleep(1000);
        ProductPage productPage = searchPage.selectProduct(); // Rastgele kitap seçilir
        Thread.sleep(1000);
        productPage.clickAddToBasket(); // Sepete ekle buttonu tıklanır
        Thread.sleep(1000);
        String cartCount = productPage.checkCartCount(); // Sepetteki ürün sayısı değişkene atanır
        Thread.sleep(1000);
        if (!"0".equals(cartCount)) { // Ürünün sepete eklendiği kontrol edilir
            productPage.clickMyBasket();
            Thread.sleep(1000);
            CartPage cartPage = productPage.clickBasketPage(); // Sepet sayfasına gidilir
            Thread.sleep(1000);
            cartPage.increaseProductCount(cartCount); // Ürün miktarı arttırılır
            Thread.sleep(1000);
            cartPage.refreshProduct(); // Yenilenir
            Thread.sleep(1000);
            cartPage.deleteProduct(); // Ürün silinir
            Thread.sleep(1000);
            cartPage.checkBasketStatus(); // Sepet kontrol edilir
        }
    }

    @AfterClass
    public static void closeApp() {
        driver.quit(); // Sayfa kapatılır
    }

    private static String getFileFromResource(String fileName) { // Parametre olarak gönderilen dosya adını bulur
        URL resource = BaseTest.class.getResource(fileName);
        try {
            return Paths.get(resource.toURI()).toAbsolutePath().toString();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
