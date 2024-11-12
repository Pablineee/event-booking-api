package ca.gbc.approvalservice;

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
class ApprovalServiceApplicationTests {

//    @Container
//    static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:15-alpine")
//            .withDatabaseName("approval-service")
//            .withUsername("admin")
//            .withPassword("password")
//            .withInitScript("init-approval.sql");
//
//    @DynamicPropertySource
//    static void registerPgProperties(DynamicPropertyRegistry registry) {
//        registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
//        registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
//        registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
//    }
//
//    @LocalServerPort
//    private Integer port;
//
//    @BeforeEach
//    void setup() {
//        RestAssured.baseURI = "http://localhost";
//        RestAssured.port = port;
//    }
//
//    @Test
//    void shouldCreateNewApproval() {
//        String createApprovalRequestJson = """
//            {
//                "eventId": "3hgv4jhg3j4hg3j4gjhh3",
//                "staffId": "2",
//                "status": "approved"
//            }
//            """;
//
//        RestAssured.given()
//                .contentType("application/json")
//                .body(createApprovalRequestJson)
//                .when()
//                .post("/api/approval/process")
//                .then()
//                .statusCode(201);
//    }
//
//    @Test
//    void shouldUpdateExistingApproval() {
//        String createApprovalRequestJson = """
//            {
//                "eventId": "3hgv4jhg3j4hg3j4gjhh3",
//                "staffId": "2",
//                "status": "denied"
//            }
//            """;
//
//        RestAssured.given()
//                .contentType("application/json")
//                .body(createApprovalRequestJson)
//                .when()
//                .put("/api/approval/1")
//                .then()
//                .statusCode(204);
//    }
//
//    @Test
//    void shouldDeleteExistingApproval() {
//
//        RestAssured.given()
//                .contentType("application/json")
//                .when()
//                .delete("/api/approval/3")
//                .then()
//                .statusCode(204);
//    }
//
//    @Test
//    void shouldGetAllExistingApprovals() {
//        RestAssured.given()
//                .contentType("application/json")
//                .when()
//                .get("/api/approval")
//                .then()
//                .statusCode(200)
//                .body("size()", greaterThan(0));
//    }

}
