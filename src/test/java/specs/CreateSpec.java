package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static helpers.CustomApiListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

public class CreateSpec {
    public static RequestSpecification createRequestSpec = with()
            .basePath("/api/users")
            .filter(withCustomTemplates())
            .log().uri()
            .log().body()
            .contentType(JSON);

    public static ResponseSpecification createResponseSpec = new ResponseSpecBuilder()
            .expectStatusCode(201)
            .log(LogDetail.BODY)
            .log(LogDetail.STATUS)
            .expectBody("name", is("morpheus"))
            .build();
}
