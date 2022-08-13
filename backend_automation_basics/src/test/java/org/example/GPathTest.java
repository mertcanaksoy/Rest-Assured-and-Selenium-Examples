package org.example;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class GPathTest {

    @Test
    public void extractAndCheckMultipleValues() {
        given().
                when().
                get("http://ergast.com/api/f1/2016/drivers.json").
                then().
                assertThat().
                body("MRData.DriverTable.Drivers.driverId",hasItems("alonso","button"));
    }

    @Test
    public void extractDriverCode() {
        JsonPath jsonPath = given()
                .get("http://ergast.com/api/f1/2016/drivers.json")
                .then()
                .statusCode(200)
                .extract().jsonPath();

        String driver_code = jsonPath.setRootPath("MRData.DriverTable.Drivers")
                .getString("find { d -> d.driverId == 'alonso' }.code");

        assertThat(driver_code, equalTo("ALO"));
    }

    @Test
    public void extractDriverNationality() {
        JsonPath jsonPath = given()
                .get("http://ergast.com/api/f1/2016/drivers.json")
                .then()
                .statusCode(200)
                .extract().jsonPath()
                .setRootPath("MRData.DriverTable.Drivers");

        String driver_nationality = jsonPath.getString("find { d -> d.driverId == 'alonso' }.nationality");

        assertThat(driver_nationality, equalTo("Spanish"));
    }

    @Test
    public void extractAllDriversAndCheckSomeOfThem() {
        JsonPath jsonPath = given()
                .get("http://ergast.com/api/f1/2016/drivers.json")
                .then()
                .statusCode(200)
                .extract().jsonPath()
                .setRootPath("MRData.DriverTable.Drivers");

        String all_drivers = jsonPath.getString("driverId");
        System.out.println(all_drivers);
        assertThat(all_drivers, containsString("alonso"));
        assertThat(all_drivers, containsString("raikkonen"));
        assertThat(all_drivers, containsString("vettel"));
        assertThat(all_drivers, containsString("hamilton"));
    }
}
