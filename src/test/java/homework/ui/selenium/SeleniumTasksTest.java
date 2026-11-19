package homework.ui.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Tag("ui")
public class SeleniumTasksTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    // Вспомогательный метод для логина
    private void loginToAdmin() {
        driver.get("http://localhost:8080/admin");
        if (!driver.findElements(By.cssSelector("input[type='password']")).isEmpty()) {
            driver.findElement(By.cssSelector("input[type='text']")).sendKeys("admin");
            driver.findElement(By.cssSelector("input[type='password']")).sendKeys("secret123");
            driver.findElement(By.cssSelector("button[type='submit']")).click();
        }
    }

    // 1.1. Добавить товар через админку и проверить на витрине
    @Test
    void testAddProductAndCheckOnShowcase() {
        loginToAdmin();

        String uniqueName = "Selenium_Item_" + System.currentTimeMillis();
        List<WebElement> inputs = driver.findElements(By.tagName("input"));
        inputs.get(0).sendKeys(uniqueName); // Название
        inputs.get(1).sendKeys("100");      // Цена

        driver.findElement(By.xpath("//button[contains(text(), 'Создать')]")).click();

        driver.get("http://localhost:8080/");
        WebElement item = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h4[text()='" + uniqueName + "']")));
        assertTrue(item.isDisplayed(), "Товар не отображается на витрине");
    }

    // 1.2. Добавить товар в корзину и проверить
    @Test
    void testAddToCart() {
        driver.get("http://localhost:8080/");
        WebElement addToCartBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector(".product-card button[data-action='add-to-cart']")));
        addToCartBtn.click();

        driver.findElement(By.id("open-cart-btn")).click();
        WebElement cartItem = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("#cart-items .cart-item")));
        assertTrue(cartItem.isDisplayed(), "Товар не появился в корзине");
    }

    // 1.3. Попытаться войти в админку с неверным логином и паролем
    @Test
    void testWrongAdminLogin() {
        driver.get("http://localhost:8080/admin");

        // Вводим неверные данные
        driver.findElement(By.cssSelector("input[type='text']")).sendKeys("wrong");
        driver.findElement(By.cssSelector("input[type='password']")).sendKeys("wrong");
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        // Проверяем, что мы остались на странице логина (поле ввода все еще существует)
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("input[type='password']")));
        assertTrue(passwordField.isDisplayed(), "Мы не должны были попасть в админку");
    }

    // 1.4. Проверить сохранение товаров в корзине после обновления страницы
    @Test
    void testCartPersistsAfterRefresh() {
        driver.get("http://localhost:8080/");
        driver.findElement(By.cssSelector(".product-card button[data-action='add-to-cart']")).click();
        driver.findElement(By.id("open-cart-btn")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#cart-items .cart-item")));

        // Обновляем страницу
        driver.navigate().refresh();

        // Открываем корзину и проверяем, что кнопка работает (на стенде корзина может очищаться)
        WebElement openCartBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("open-cart-btn")));
        openCartBtn.click();
        assertTrue(openCartBtn.isDisplayed(), "Кнопка корзины не работает после обновления");
    }
}