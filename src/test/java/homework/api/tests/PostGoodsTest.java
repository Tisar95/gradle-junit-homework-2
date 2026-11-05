package homework.api.tests;

import homework.api.GoodsApi;
import homework.api.GoodsDto;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static homework.api.GoodsApiAssert.assertThat;

@Tag("api")
public class PostGoodsTest {
    @Test
    void testCreateSuccess200() {
        assertThat(GoodsApi.addGoods(new GoodsDto("Item_" + System.currentTimeMillis(), 10.5))).isCreatedSuccessfully();
    }

    @Test
    void testCreateBadRequest400() {
        assertThat(GoodsApi.addGoods(new GoodsDto("", 10.5))).statusCodeIsEquals(400);
    }
}