package ca.gbc.eventservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "user-service", url = "http://user-service:8080", path = "api/user/")
public interface UserClient {

    @GetMapping( "is-student/{userId}")
    boolean isStudent(@PathVariable Long userId);

}
