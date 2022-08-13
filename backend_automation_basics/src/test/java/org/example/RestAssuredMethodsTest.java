package org.example;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.is;

public class RestAssuredMethodsTest {

    @Test
    public void getRequestTest() {
        String sample_body = given()
                .get("https://jsonplaceholder.typicode.com/posts")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .asString();

        assertThat(sample_body, containsString("qui est esse"));
        System.out.print(sample_body);
    }

    @Test
    public void getRequestWithParameterTest() {
        int parameter = 100;
        String sample_body = given()
                .get("https://jsonplaceholder.typicode.com/posts/"+parameter)
                .then()
                .statusCode(200)
                .extract()
                .body()
                .asString();

        assertThat(sample_body, containsString("cupiditate quo est"));
        System.out.print(sample_body);
    }

    @Test
    public void postRequestTest() {
        String sample_body = "{\n"
                + "    \"userId\": 11,\n"
                + "    \"id\": 101,\n"
                + "    \"title\": \"POST operation\",\n"
                + "    \"body\": \"Tested a POST Operation\"\n"
                + "}";

        given()
                .body(sample_body)
                .post("https://jsonplaceholder.typicode.com/posts")
                .then()
                .statusCode(201);
        System.out.print(sample_body);
    }

    @Test
    public void putRequestTest() {
        String sample_body = "{\n"
                + "    \"userId\": 11,\n"
                + "    \"id\": 101,\n"
                + "    \"title\": \"PUT operation\",\n"
                + "    \"body\": \"Tested a PUT Operation\"\n"
                + "}";

        given()
                .body(sample_body)
                .put("https://jsonplaceholder.typicode.com/posts/100")
                .then()
                .statusCode(200);
    }

    @Test
    public void deleteRequestTest() {
        given()
                .delete("https://jsonplaceholder.typicode.com/posts/100")
                .then()
                .statusCode(anyOf(is(200),is(204), is(202)));
                // Delete method returns 200 Code. But sometimes return code 204 or 202
    }
}
