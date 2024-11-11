package ca.gbc.roomservice;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
class RoomServiceApplicationTests {

    @Container
    static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:15-alpine")
            .withDatabaseName("room-service")
            .withUsername("admin")
            .withPassword("password")
            .withInitScript("init-room.sql");

    @DynamicPropertySource
    static void registerPgProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
        registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
    }

    @LocalServerPort
    private Integer port;

    @BeforeEach
    void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
    }

    @Test
    void shouldCreateNewRoom() {
        String createRoomRequestJson = """
            {
                "roomName": "Conference Room 2B",
                "capacity": 30,
                "features": "whiteboard,projector",
                "available": true
            }
            """;

        RestAssured.given()
                .contentType("application/json")
                .body(createRoomRequestJson)
                .when()
                .post("/api/room")
                .then()
                .statusCode(201);
    }

    @Test
    void shouldUpdateExistingRoom() {
        String createRoomRequestJson = """
            {
                "roomName": "Conference Room 1A",
                "capacity": 30,
                "features": "whiteboard,projector,computers",
                "available": true
            }
            """;

        RestAssured.given()
                .contentType("application/json")
                .body(createRoomRequestJson)
                .when()
                .put("/api/room/1")
                .then()
                .statusCode(204);
    }

    @Test
    void shouldDeleteExistingRoom() {

        RestAssured.given()
                .contentType("application/json")
                .when()
                .delete("/api/room/3")
                .then()
                .statusCode(204);
    }

    @Test
    void shouldGetAllExistingRooms() {
        RestAssured.given()
                .contentType("application/json")
                .when()
                .get("/api/room")
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test
    void shouldGetRoomById() {
        int roomId = 2;

        RestAssured.given()
                .contentType("application/json")
                .when()
                .get("/api/room/" + roomId)
                .then()
                .statusCode(200)
                .body("capacity", greaterThan(0));
    }

}
