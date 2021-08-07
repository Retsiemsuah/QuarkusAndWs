package de.scheffler;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class bowlingTest {

    @Test
     void testHelloEndpoint() {
        Response r = given()
          .when().get("/hello");
//          .then()
//             .statusCode(200)
//             .body(is("Hello RESTEasy"));
        System.out.println(r.body().print());
    }

}