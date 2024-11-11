package ca.gbc.eventservice;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.MongoDBContainer;


// @Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EventServiceApplicationTests {

    @ServiceConnection
    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:latest");


    @LocalServerPort
    private Integer port;

    //  http://localhost:port/api/event
    @BeforeEach
    void setup(){
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
    }

    static {
        mongoDBContainer.start();
    }


    @Test
    void createEventTest() {

        String requestBody = """
                {
                   "eventName" : "Study Hall",
                   "eventType" : "study",
                   "expectedAttendees" : "2",
                   "organizerId" : "001",
                   "status" : "pending"
                }
        """;

        // Mock a call to user-service
        UserClientStub.stubUserCall("001", 5);



        // BDD - Behavioural Driven Development (Given, When, Then)
        // Rest Assured is used to perform HTTP requests and verify responses.
        // This test performs a POST request to the /api/product endpoint to create a new product.
        // Then it verifies that the response status is 201 (Created) and the response body contains the correct product details.
        RestAssured.given()
                .contentType("application/json")  //  Set the content type of the request to JSON
                .body(requestBody)  //  Pass the request body (the product data)
                .when()
                .post("/api/event")  //  Perform the POST request to the /api/product endpoint
                .then()
                .log().all()  //  Log the response details
                .statusCode(201)  // Assert that the HTTP status code is 201 Created
                .body("id", Matchers.notNullValue())  //  Assert that the returned event has a non-null ID
                .body("eventName", Matchers.equalTo("Study Hall"))  //  Assert that the event's name matches
                .body("eventType", Matchers.equalTo("study"))  //  Assert that the event's type matches
                .body("organizerId", Matchers.equalTo("001"))  //  Assert that the organizer's id matches
                .body("status", Matchers.equalTo("pending")); //  Assert that the status matches


    }

    @Test
    void getAllProductsTest(){

        String requestBody = """
                {
                   "eventName" : "Study Hall",
                   "eventType" : "study",
                   "expectedAttendees" : "2",
                   "organizerId" : "001",
                   "status" : "pending"
                }
                """;
        // BDD - Behavioural Driven Development (Given, When, Then)
        RestAssured.given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post("/api/event")
                .then()
                .log().all()
                .statusCode(201)
                .body("id", Matchers.notNullValue())  //  Assert that the returned event has a non-null ID
                .body("eventName", Matchers.equalTo("Study Hall"))  //  Assert that the event's name matches
                .body("eventType", Matchers.equalTo("study"))  //  Assert that the event's type matches
                .body("organizerId", Matchers.equalTo("001"))  //  Assert that the organizer's id matches
                .body("status", Matchers.equalTo("pending")); //  Assert that the status matches

        RestAssured.given()
                .contentType("application/json")
                .when()
                .get("/api/event")
                .then()
                .log().all()
                .statusCode(200)
                .body("size()", Matchers.greaterThan(0))
                .body("[0].eventName", Matchers.equalTo("Study Hall"))
                .body("[0].eventType", Matchers.equalTo("study"))
                .body("[0].organizerId", Matchers.equalTo("001"))
                .body("[0].status", Matchers.equalTo("pending"));

    }

}
