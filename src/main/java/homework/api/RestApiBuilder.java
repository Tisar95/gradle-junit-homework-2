package homework.api;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class RestApiBuilder {
    public static RequestSpecification getBaseSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(Endpoints.BASE_URI)
                .setBasePath(Endpoints.BASE_PATH)
                .setContentType(ContentType.JSON)
                .setAuth(RestAssured.preemptive().basic("admin", "secret123"))
                .log(io.restassured.filter.log.LogDetail.ALL)
                .build();
    }
}