package homework.api.tests;

import homework.api.Endpoints;
import homework.api.GoodsApi;
import homework.api.GoodsDto;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import java.util.List;
import static homework.api.RestApiBuilder.getBaseSpec;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;

@Tag("api")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LectureTasksTest {

    @Test
    @Order(1)
    void testListWithGivenWhenThen() {
        given().spec(getBaseSpec())
                .when().get(Endpoints.LIST)
                .then().statusCode(200)
                .body("goods", notNullValue()); // Проверяем, что массив goods существует, он может быть не пустым
    }

    @Test
    @Order(2)
    void testListWithRequestSpecification() {
        Response response = GoodsApi.getList();
        assertThat(response.statusCode()).as("Status code should be 200").isEqualTo(200);
        List<Object> list = response.jsonPath().getList("goods");
        assertThat(list).as("Response body should contain goods").isNotNull();
    }

    @Test
    @Order(3)
    void testCreateAndCheckViaRestAssured() {
        String uniqueName = "Item_" + System.currentTimeMillis();
        GoodsApi.addGoods(new GoodsDto(uniqueName, 1500.0)).then().statusCode(200);

        GoodsApi.getList().then().statusCode(200)
                .body("goods.find { it.name == '" + uniqueName + "' }.name", equalTo(uniqueName));
    }

    @Test
    @Order(4)
    void testCreateAndCheckViaAssertJ() {
        String uniqueName = "Item_" + System.currentTimeMillis();
        GoodsApi.addGoods(new GoodsDto(uniqueName, 2000.0)).then().statusCode(200);

        Response listResponse = GoodsApi.getList();
        List<String> names = listResponse.jsonPath().getList("goods.name");
        assertThat(names).as("Created item should be in the list").contains(uniqueName);
    }
}