package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static helpers.CustomApiListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.http.ContentType.JSON;

public class DelayedSpec {
    public static RequestSpecification delayedRequestSpec = with()
            .basePath("/api/users?delay=3")
            .filter(withCustomTemplates())
            .log().uri()
            .log().body()
            .contentType(JSON);
    public static ResponseSpecification delayedResponseSpec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .log(LogDetail.STATUS)
            .log(LogDetail.BODY)
            .build();
}
