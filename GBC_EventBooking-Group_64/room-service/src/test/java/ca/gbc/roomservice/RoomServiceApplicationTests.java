package ca.gbc.roomservice;

import ca.gbc.orderservice.stub.InventoryClientStub;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;

// @Import(TestcontainersConfiguration.class)
// Tells SpringBoot to look for a main configuration class {@SpringBootApplication}
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RoomServiceApplicationTests {

    // This annotation is used in combination with TestContainers to automatically configure the connection to the Test MongoDBContainer
    @ServiceConnection
    static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:15-alpine");


    @LocalServerPort
    private Integer port;

    //  http://localhost:port/api/product
    @BeforeEach
    void setup(){
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
    }

    static {
        postgreSQLContainer.start();
    }

    @Test
    void shouldCreateRoom(){

        String createRoomJson = """
                {
                   "id" : "002",
                   "roomName" : "B002",
                   "capacity" : "5",
                   "features" : "whiteboard",
                   "available" : "true"
                }
        """;



        // BDD - Behavioural Driven Development (Given, When, Then)
        // Rest Assured is used to perform HTTP requests and verify responses.
        // This test performs a POST request to the /api/product endpoint to create a new product.
        // Then it verifies that the response status is 201 (Created) and the response body contains the correct product details.
        var responseBodyString = RestAssured.given()
                .contentType("application/json")  //  Set the content type of the request to JSON
                .body(submitOrderJson)  //  Pass the request body (the product data)
                .when()
                .post("/api/room")  //  Perform the POST request to the /api/product endpoint
                .then()
                .log().all()  //  Log the response details
                .statusCode(201)  // Assert that the HTTP status code is 201 Created
                .extract()
                .body().asString();

        assertThat(responseBodyString, Matchers.is("Room Created Successfully"));

    }

}