package com.shaimaa.yelp.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.hamcrest.Matchers.*;

class PlaceApiIT {

//    @Autowired
    ObjectMapper objectMapper = new ObjectMapper();

//    @BeforeEach
//    public void init() {
//        RestAssured.baseURI = "http://localhost";
//        RestAssured.port = 8080;
//    }

    @Test
    void whenGelAll_thenReturnPlaces() {
        PlaceDto placeRequest = this.createPlace();
        Wrapper<PlaceDto> requestBody = new Wrapper<>(placeRequest);

        Map<String, Object> response = RestAssured
                .given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post("places")
                .then()
                .log().status().log().body()
                .extract()
                .response()
                .path("data");

//        Map<String, Object> place1 = response.getBody().path("data");
//        PlaceDto r= response.as(PlaceDto.class);

        RestAssured
                .given()
                .when()
                .get("places")
                .then()
                .log().status().log().body()
                .statusCode(200)
//                .body("data[0].id", equalTo(response.))
                .body("data[0].name", equalTo(placeRequest.getName()));
//                .body("data", arrayContainingInAnyOrder(placeRequest));
    }

    @Test
    void whenPostRequest_thenCreatePlace() {
        // GIVEN
        PlaceDto placeRequest = this.createPlace();
        Wrapper<PlaceDto> requestBody = new Wrapper<>(placeRequest);

        // WHEN THEN
        RestAssured
                .given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post("places")
                .then()
                .log().status().log().body()
                .statusCode(201)
                .body("data.id", notNullValue())
                .body("data.name", equalTo(placeRequest.getName()));
    }

    private PlaceDto createPlace() {
        return PlaceDto.builder()
                .name("shaimaa")
                .location(
                        new LocationDto(1.0,1.0)
                )
                .build();
    }
}
