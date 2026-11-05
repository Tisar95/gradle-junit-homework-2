package homework.api;

import io.restassured.response.Response;
import org.assertj.core.api.AbstractAssert;

public class BasicApiAssert extends AbstractAssert<BasicApiAssert, Response> {

    public BasicApiAssert(Response actual) {
        super(actual, BasicApiAssert.class);
    }

    public static BasicApiAssert assertThat(Response response) {
        return new BasicApiAssert(response);
    }

    public BasicApiAssert statusCodeIsEquals(int expected) {
        isNotNull();
        if (actual.statusCode() != expected) {
            failWithMessage("Expected status code %d, but was %d. Server response: %s", expected, actual.statusCode(), actual.body().asString());
        }
        return this;
    }

    public BasicApiAssert bodyFieldIsExists(String path) {
        isNotNull();
        if (actual.jsonPath().get(path) == null) {
            failWithMessage("Expected field '%s' to exist, but it does not", path);
        }
        return this;
    }
}