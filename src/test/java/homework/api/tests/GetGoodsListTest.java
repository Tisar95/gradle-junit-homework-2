package homework.api.tests;

import homework.api.GoodsApi;
import io.restassured.response.Response;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static homework.api.BasicApiAssert.assertThat;
import static io.restassured.RestAssured.given;
import static homework.api.RestApiBuilder.getBaseSpec;

@Tag("api")
public class GetGoodsListTest {
    @Test
    void testListSuccess200() {
        Response response = given()
                .spec(getBaseSpec())
                .queryParam("page", 0)
                .queryParam("size", 10)
                .when()
                .get("/list");
        assertThat(response).statusCodeIsEquals(200);
    }
}