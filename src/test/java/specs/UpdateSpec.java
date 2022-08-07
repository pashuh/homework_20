package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static helpers.CustomApiListener.withCustomTemplates;
import static io.restassured.RestAssured.*;
import static io.restassured.http.ContentType.JSON;

public class UpdateSpec {
    public static RequestSpecification updateRequestSpec = with()
            .basePath("/api/users/2")
            .filter(withCustomTemplates())
            .log().uri()
            .log().body()
            .contentType(JSON);
    public static ResponseSpecification updateResponseSpec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .log(LogDetail.STATUS)
            .log(LogDetail.BODY)
            .build();
}
