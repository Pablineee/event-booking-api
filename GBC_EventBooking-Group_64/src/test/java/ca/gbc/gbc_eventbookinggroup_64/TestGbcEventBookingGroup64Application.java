package ca.gbc.gbc_eventbookinggroup_64;

import org.springframework.boot.SpringApplication;

public class TestGbcEventBookingGroup64Application {

    public static void main(String[] args) {
        SpringApplication.from(GbcEventBookingGroup64Application::main).with(TestcontainersConfiguration.class).run(args);
    }

}
