package org.example;

import org.example.DTO.UserDataDto;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.rootPath;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

public class SerializationDeserializationTest {

    @Test
    public void testUserObject() {
        UserDataDto userData = given()
                .get("https://reqres.in/api/users/1")
                .then().log().body()
                .statusCode(200)
                .extract().jsonPath().getObject("data", UserDataDto.class);
    }

    @Test
    public void testUserObjectList() {
        List<UserDataDto> userDataList = given()
                .get("https://reqres.in/api/users/")
                .then()
                .statusCode(200)
                .extract().jsonPath().getList("data", UserDataDto.class);
    }

    @Test
    public void postRequestObject() {
        UserDataDto body = new UserDataDto();
        body.setFirstName("Emma");
        body.setLastName("Wong");

        given()
                .body(body)
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(201)
                .extract().jsonPath().getObject("data", UserDataDto.class);
        assertThat(body.getFirstName(), containsString("Emma"));
        assertThat(body.getLastName(), containsString("Wong"));
    }

}


