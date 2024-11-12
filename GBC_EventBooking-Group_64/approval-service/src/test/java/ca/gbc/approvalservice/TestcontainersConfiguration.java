package ca.gbc.approvalservice;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.wait.strategy.Wait;

@TestConfiguration(proxyBeanMethods = false)
@EnableJpaRepositories(basePackages = "ca.gbc.approvalservice.repository")
@EntityScan(basePackages = "ca.gbc.approvalservice.model")
class TestcontainersConfiguration {

    @Bean
    @ServiceConnection
    public PostgreSQLContainer<?> postgresContainer() {
        return new PostgreSQLContainer<>("postgres:15-alpine")
                .withDatabaseName("approval-service")
                .withUsername("admin")
                .withPassword("password")
                .waitingFor(Wait.forListeningPort());
    }

}
