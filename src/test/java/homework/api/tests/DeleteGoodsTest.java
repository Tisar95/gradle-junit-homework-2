package homework.api.tests;

import homework.api.GoodsApi;
import homework.api.GoodsDto;
import io.restassured.response.Response;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static homework.api.BasicApiAssert.assertThat;

@Tag("api")
public class DeleteGoodsTest {
    @Test
    void testDeleteSuccess200() {
        Response created = GoodsApi.addGoods(new GoodsDto("ItemForDelete_" + System.currentTimeMillis(), 5.0));
        int id = created.jsonPath().getInt("data.id");
        assertThat(GoodsApi.deleteGoods(id)).statusCodeIsEquals(200);
    }

    @Test
    void testDeleteNotFound404() {
        assertThat(GoodsApi.deleteGoods(999999)).statusCodeIsEquals(404);
    }
}