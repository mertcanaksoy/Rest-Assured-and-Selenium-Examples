package functionalTests.tests.baseClasses;

import functionalTests.serviceClient.ScaleUpClient;
import functionalTests.testDtos.LoginDto;
import functionalTests.testDtos.testDtoFactories.TestDtoFactory;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import org.assertj.core.api.SoftAssertions;
import org.junit.After;
import org.junit.Before;

public abstract class BaseTest {

    protected SoftAssertions softAssert;
    protected TestDtoFactory factory;
    protected ScaleUpClient client;

    @Before
    public void setup() {
        softAssert = new SoftAssertions();
        factory = new TestDtoFactory();
        client = new ScaleUpClient();

        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        RestAssured.baseURI = Endpoints.BaseURL;

        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .build();

        String accessToken = login();

        RestAssured.requestSpecification.auth().oauth2(accessToken);
    }

    @After
    public void teardown() {
        softAssert.assertAll();
    }

    private String login() {
        LoginDto loginDto = factory.createLoginDtoWithValidCredentials();
        return client.login(loginDto);
    }
}