package ca.gbc.userservice;

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
class UserServiceApplicationTests {

    @Container
    static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:15-alpine")
            .withDatabaseName("user-service")
            .withUsername("admin")
            .withPassword("password")
            .withInitScript("init-user.sql");

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
    void shouldCreateNewUser() {
        String createUserRequestJson = """
            {
                "name": "George Davis",
                "email": "davis.g@user.ca",
                "role": "part-time",
                "userType": "student"
            }
            """;

        RestAssured.given()
                .contentType("application/json")
                .body(createUserRequestJson)
                .when()
                .post("/api/user")
                .then()
                .statusCode(201);
    }

    @Test
    void shouldUpdateExistingUser() {
        String createUserRequestJson = """
            {
                "name": "George Davis",
                "email": "davis.g@user.ca",
                "role": "full-time",
                "userType": "student"
            }
            """;

        RestAssured.given()
                .contentType("application/json")
                .body(createUserRequestJson)
                .when()
                .put("/api/user/1")
                .then()
                .statusCode(204);
    }

    @Test
    void shouldDeleteExistingUser() {

        RestAssured.given()
                .contentType("application/json")
                .when()
                .delete("/api/user/3")
                .then()
                .statusCode(204);
    }

    @Test
    void shouldGetAllExistingUsers() {
        RestAssured.given()
                .contentType("application/json")
                .when()
                .get("/api/user")
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test
    void shouldGetUserById() {
        int userId = 2;

        RestAssured.given()
                .contentType("application/json")
                .when()
                .get("/api/user/" + userId)
                .then()
                .statusCode(200);
    }

}
