package homework.api.tests;

import homework.api.GoodsApi;
import homework.api.GoodsDto;
import io.restassured.response.Response;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static homework.api.BasicApiAssert.assertThat;

@Tag("api")
public class GetGoodsByIdTest {
    @Test
    void testGetSuccess200() {
        Response created = GoodsApi.addGoods(new GoodsDto("ItemForGet_" + System.currentTimeMillis(), 5.0));
        int id = created.jsonPath().getInt("data.id");
        assertThat(GoodsApi.getGoods(id)).statusCodeIsEquals(200);
    }

    @Test
    void testGetNotFound500() {
        assertThat(GoodsApi.getGoods(999999)).statusCodeIsEquals(500);
    }
}