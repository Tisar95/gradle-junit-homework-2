package homework.api;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class GoodsApi {
    public static Response getList() {
        return given().spec(RestApiBuilder.getBaseSpec()).when().get(Endpoints.LIST);
    }

    public static Response addGoods(GoodsDto goods) {
        return given().spec(RestApiBuilder.getBaseSpec()).body(goods).when().post(Endpoints.ADD);
    }

    public static Response getGoods(int id) {
        return given().spec(RestApiBuilder.getBaseSpec()).pathParam("id", id).when().get(Endpoints.BY_ID);
    }

    public static Response deleteGoods(int id) {
        return given().spec(RestApiBuilder.getBaseSpec()).pathParam("id", id).when().delete(Endpoints.BY_ID);
    }

    public static Response patchGoods(int id, GoodsDto goods) {
        return given().spec(RestApiBuilder.getBaseSpec()).pathParam("id", id).body(goods).when().patch(Endpoints.BY_ID);
    }
}