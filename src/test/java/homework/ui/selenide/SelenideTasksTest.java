package homework.ui.selenide;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import homework.api.GoodsApi;
import homework.api.GoodsDto;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

@Tag("ui")
public class SelenideTasksTest {

    private Integer createdApiId = null;

    @BeforeAll
    static void setUp() {
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 5000;
    }

    @BeforeEach
    void openPage() {
        clearBrowserCookies();
        open("http://localhost:8080/");
    }

    @AfterEach
    void tearDown() {
        if (createdApiId != null) {
            GoodsApi.deleteGoods(createdApiId);
            createdApiId = null;
        }
    }

    private void loginToAdmin(String user, String pass) {
        open("/admin");
        if ($("input[type='password']").exists()) {
            $("input[type='text']").setValue(user);
            $("input[type='password']").setValue(pass);
            $("button[type='submit']").click();
        }
    }

    // 2.1 / 3.3 (Админка: добавление)
    @Test
    void testAddProductInAdmin() {
        loginToAdmin("admin", "secret123");
        $("body").shouldHave(text("SmartShop Admin"));

        String uniqueName = "Selenide_Item_" + System.currentTimeMillis();

        $$("input").get(0).setValue(uniqueName);
        $$("input").get(1).setValue("150");
        $(byText("Создать")).click();

        // Проверяем, что в таблице админки появился input с нашим названием
        $(byValue(uniqueName)).should(exist);
    }

    // 2.2. Добавить товар в корзину и проверить
    @Test
    void testAddToCart() {
        $(".product-card").shouldBe(visible);
        $(".product-card button[data-action='add-to-cart']").click();
        $("#open-cart-btn").click();
        $("#cart-items .cart-item").shouldBe(visible);
    }

    // 2.3. Попытаться войти с неверным паролем
    @Test
    void testWrongAdminLogin() {
        loginToAdmin("wrong", "wrong");
        $("input[type='password']").shouldBe(visible);
    }

    // 2.4. Сохранение корзины после обновления
    @Test
    void testCartPersistsAfterRefresh() {
        $(".product-card button[data-action='add-to-cart']").click();
        $("#open-cart-btn").click();
        $("#cart-items .cart-item").shouldBe(visible);

        refresh();

        $("#open-cart-btn").click();
        $("#open-cart-btn").shouldBe(visible);
    }

    // 2.5. Корзина > 300 руб и проверка JS Alert
    @Test
    void testCartMoreThan300AndAlert() {
        $$(".product-card button[data-action='add-to-cart']").get(0).click();
        $$(".product-card button[data-action='add-to-cart']").get(1).click();
        $$(".product-card button[data-action='add-to-cart']").get(2).click();

        $("#open-cart-btn").click();
        $(byText("Оформить заказ")).click();
        confirm();
    }

    // 3.1. Добавить 3 единицы товара
    @Test
    void testOrderThreeItemsUnder300() {
        for (int i = 0; i < 3; i++) {
            $(".product-card button[data-action='add-to-cart']").click();
        }
        $("#open-cart-btn").click();
        $(byText("Оформить заказ")).click();
        $("#open-cart-btn").shouldBe(visible);
    }

    // 3.2. Несколько разных товаров и проверка суммы
    @Test
    void testMultipleItemsTotalPrice() {
        $(".product-card button[data-action='add-to-cart']").click();
        $$(".product-card button[data-action='add-to-cart']").get(1).click();

        $("#open-cart-btn").click();
        $$("#cart-items .cart-item").shouldHave(size(2));
    }

    // 3.4. Редактирование товара в админке + API
    @Test
    void testEditProductInAdmin() {
        String apiName = "Api_Item_" + System.currentTimeMillis();
        Response response = GoodsApi.addGoods(new GoodsDto(apiName, 100.0));
        createdApiId = response.jsonPath().getInt("data.id");

        loginToAdmin("admin", "secret123");
        $("body").shouldHave(text("SmartShop Admin"));

        // Находим строку с нашим товаром
        SelenideElement row = $(byValue(apiName)).ancestor("tr");

        // Меняем название
        String newName = "Updated_" + System.currentTimeMillis();
        row.$$("input").get(0).setValue(newName);

        // Сохраняем
        row.find(byText("Сохранить")).click();

        // Проверяем на витрине, что карточка с новым именем существует в списке
        open("/");
        $$(".product-card").findBy(text(newName)).shouldBe(visible);
    }
}