package ca.gbc.bookingservice;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.MongoDBContainer;
import org.springframework.context.annotation.Import;

// @Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookingServiceApplicationTests {

    // This annotation is used in combination with TestContainers to automatically configure the connection to the Test MongoDBContainer
    @ServiceConnection
    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:latest");


    @LocalServerPort
    private Integer port;

    //  http://localhost:port/api/booking
    @BeforeEach
    void setup(){
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
    }

    static {
        mongoDBContainer.start();
    }

    @Test
    void createBookingTest(){

        String requestBody = """
                {
                   "userId" : "001",
                   "roomId" : "002",
                   "startTime" : "08:00",
                   "endTime" : "09:00",
                   "purpose" : "study"
                }
        """;

        // Mock a call to room-service
        RoomClientStub.stubRoomCall("001", true);

        // BDD - Behavioural Driven Development (Given, When, Then)
        // Rest Assured is used to perform HTTP requests and verify responses.
        // This test performs a POST request to the /api/product endpoint to create a new product.
        // Then it verifies that the response status is 201 (Created) and the response body contains the correct product details.
        RestAssured.given()
                .contentType("application/json")  //  Set the content type of the request to JSON
                .body(requestBody)  //  Pass the request body (the product data)
                .when()
                .post("/api/booking")  //  Perform the POST request to the /api/product endpoint
                .then()
                .log().all()  //  Log the response details
                .statusCode(201)  // Assert that the HTTP status code is 201 Created
                .body("id", Matchers.notNullValue())  //  Assert that the returned booking has a non-null ID
                .body("userId", Matchers.equalTo("001"))  //  Assert that the user's id matches
                .body("roomId", Matchers.equalTo("002"))  //  Assert that the room's id matches
                .body("startTime", Matchers.equalTo("08:00"))  //  Assert that the startTime is 8:00
                .body("endTime", Matchers.equalTo("09:00"))  //  Assert that the endTime is 9:00
                .body("purpose", Matchers.equalTo("study"));  //  Assert that the purpose matches
    }

    @Test
    void getAllBookingsTest(){

        String requestBody = """
                {
                   "userId" : "001",
                   "roomId" : "001",
                   "startTime" : "08:00",
                   "endTime" : "09:00",
                   "purpose" : "study"
                }
                """;


        // Mock a call to room-service
        RoomClientStub.stubRoomCall("001", true);

        // BDD - Behavioural Driven Development (Given, When, Then)
        RestAssured.given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post("/api/booking")
                .then()
                .log().all()
                .statusCode(201)
                .body("id", Matchers.notNullValue())  //  Assert that the returned booking has a non-null ID
                .body("userId", Matchers.equalTo("001"))  //  Assert that the user's id matches
                .body("roomId", Matchers.equalTo("001"))  //  Assert that the room's id matches
                .body("startTime", Matchers.equalTo("08:00"))  //  Assert that the startTime is 8:00
                .body("endTime", Matchers.equalTo("09:00"))  //  Assert that the endTime is 9:00
                .body("purpose", Matchers.equalTo("study"));  //  Assert that the purpose matches

        RestAssured.given()
                .contentType("application/json")
                .when()
                .get("/api/booking")
                .then()
                .log().all()
                .statusCode(200)
                .body("size()", Matchers.greaterThan(0))
                .body("[0].userId", Matchers.equalTo("001"))
                .body("[0].roomId", Matchers.equalTo("001"))
                .body("[0].startTime", Matchers.equalTo("08:00"))
                .body("[0].endTime", Matchers.equalTo("09:00"))
                .body("[0].purpose", Matchers.equalTo("study"));

    }

}
