package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static helpers.CustomApiListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.http.ContentType.JSON;

public class RegSpec {
    public static RequestSpecification regRequestSpec = with()
            .basePath("/api/register")
            .filter(withCustomTemplates())
            .log().uri()
            .log().body()
            .contentType(JSON);
    public static ResponseSpecification regResponseSpec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .log(LogDetail.STATUS)
            .log(LogDetail.BODY)
            .build();
}
