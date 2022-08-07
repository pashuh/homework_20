package tests;

import io.restassured.RestAssured;
import lombok.UserData;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pojo.UserPojo;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static specs.CreateSpec.*;
import static specs.DelayedSpec.delayedRequestSpec;
import static specs.DelayedSpec.delayedResponseSpec;
import static specs.RegSpec.*;
import static specs.UpdateSpec.updateRequestSpec;
import static specs.UpdateSpec.updateResponseSpec;
import static specs.loginSpec.loginRequestSpec;
import static specs.loginSpec.loginResponseSpec;

public class ReqresTests {

    @BeforeAll
    static void setUp() {
        RestAssured.baseURI = "https://reqres.in";
    }

    @Test
    void createTest() {
        UserPojo body = new UserPojo();
        body.setName("morpheus");
        body.setJob("leader");
        UserPojo response = given()
                .spec(createRequestSpec)
                .body(body)
                .when()
                .post()
                .then()
                .spec(createResponseSpec)
                .extract()
                .as(UserPojo.class);
        assertEquals(response.getName(), ("morpheus"));
    }

    @Test
    void registerSuccessfulTest() {
        UserData body = new UserData();
        body.setEmail("eve.holt@reqres.in");
        body.setPassword("pistol");
        UserData response = given()
                .spec(regRequestSpec)
                .body(body)
                .when()
                .post()
                .then()
                .spec(regResponseSpec)
                .body("id", is(4))
                .extract()
                .as(UserData.class);
        assertThat(response.getToken(), notNullValue());
    }

    @Test
    void updateTest() {
        UserPojo body = new UserPojo();
        body.setName("morpheus");
        body.setJob("zion resident");
        UserData response = given()
                .spec(updateRequestSpec)
                .body(body)
                .when()
                .put()
                .then()
                .spec(updateResponseSpec)
                .extract()
                .as(UserData.class);
        assertEquals(response.getJob(), "zion resident");
    }

    @Test
    void loginTest() {
        UserData body = new UserData();
        body.setEmail("eve.holt@reqres.in");
        body.setPassword("cityslicka");
        UserPojo response = given()
                .spec(loginRequestSpec)
                .body(body)
                .when()
                .post()
                .then()
                .spec(loginResponseSpec)
                .extract()
                .as(UserPojo.class);
        assertThat(response.getToken(), notNullValue());
    }

    @Test
    void delayedTest() {
        UserPojo response = given()
                .spec(delayedRequestSpec)
                .when()
                .get()
                .then()
                .spec(delayedResponseSpec)
                .extract()
                .as(UserPojo.class);
        assertEquals(response.getTotal(), 12);
    }
}
