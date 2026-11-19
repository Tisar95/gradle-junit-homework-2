package homework.api;

import io.restassured.response.Response;
import org.assertj.core.api.AbstractAssert;

public class GoodsApiAssert extends AbstractAssert<GoodsApiAssert, Response> {

    public GoodsApiAssert(Response actual) {
        super(actual, GoodsApiAssert.class);
    }

    public static GoodsApiAssert assertThat(Response response) {
        return new GoodsApiAssert(response);
    }

    public GoodsApiAssert statusCodeIsEquals(int expected) {
        BasicApiAssert.assertThat(actual).statusCodeIsEquals(expected);
        return this;
    }

    public GoodsApiAssert isCreatedSuccessfully() {
        BasicApiAssert.assertThat(actual)
                .statusCodeIsEquals(200)
                .bodyFieldIsExists("data.id")
                .bodyFieldIsExists("message");
        return this;
    }
}