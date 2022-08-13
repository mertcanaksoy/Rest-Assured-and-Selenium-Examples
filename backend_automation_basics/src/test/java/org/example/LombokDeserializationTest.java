package org.example;

import org.example.DTO.UserWithLombok;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

public class LombokDeserializationTest {

    @Test
    public void testUserObject() {
        UserWithLombok userData = given()
                .get("https://reqres.in/api/users/1")
                .then().log().body()
                .statusCode(200)
                .extract().jsonPath().getObject("data", UserWithLombok.class);
    }

    @Test
    public void testUserObjectList() {
        List<UserWithLombok> userDataList = given()
                .get("https://reqres.in/api/users/")
                .then()
                .statusCode(200)
                .extract().jsonPath().getList("data", UserWithLombok.class);
    }

    @Test
    public void postRequestObject() {
        UserWithLombok body = new UserWithLombok();
        body.setFirstName("Emma");
        body.setLastName("Wong");

        given()
                .body(body)
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(201)
                .extract().jsonPath().getObject("data", UserWithLombok.class);
        assertThat(body.getFirstName(), containsString("Emma"));
        assertThat(body.getLastName(), containsString("Wong"));
    }

}


