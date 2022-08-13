package functionalTests.serviceClient;

import functionalTests.constants.Constants;
import functionalTests.constants.Endpoints;
import functionalTests.testDtos.LoginDto;

import static io.restassured.RestAssured.given;

public class ScaleUpClient {

    public String login(LoginDto body){
        return given()
                .body(body)
                .post(Endpoints.Login)
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getString(Constants.AccessToken);
    }

    public List<User> getUsers() {
        return given()
                .get(Endpoints.Users)
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getList("$", User.class);
    }
}
