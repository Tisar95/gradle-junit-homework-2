package homework.api.tests;

import homework.api.GoodsApi;
import homework.api.GoodsDto;
import io.restassured.response.Response;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static homework.api.BasicApiAssert.assertThat;

@Tag("api")
public class PatchGoodsTest {
    @Test
    void testPatchSuccess200() {
        Response created = GoodsApi.addGoods(new GoodsDto("ItemForPatch_" + System.currentTimeMillis(), 5.0));
        int id = created.jsonPath().getInt("data.id");
        assertThat(GoodsApi.patchGoods(id, new GoodsDto("UpdatedItem_" + System.currentTimeMillis(), 15.0))).statusCodeIsEquals(200);
    }

    @Test
    void testPatchBadRequest400() {
        String existingName = "BusyName_" + System.currentTimeMillis();
        GoodsApi.addGoods(new GoodsDto(existingName, 10.0));

        Response second = GoodsApi.addGoods(new GoodsDto("SecondItem_" + System.currentTimeMillis(), 20.0));
        int secondId = second.jsonPath().getInt("data.id");

        assertThat(GoodsApi.patchGoods(secondId, new GoodsDto(existingName, 25.0))).statusCodeIsEquals(400);
    }

    @Test
    void testPatchNotFound404() {
        assertThat(GoodsApi.patchGoods(999999, new GoodsDto("GhostItem", 1.0))).statusCodeIsEquals(404);
    }
}