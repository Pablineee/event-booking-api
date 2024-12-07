package ca.gbc.approvalservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service", url = "http://user-service:9003", path = "api/user")
public interface UserClient {
    @GetMapping("/is-staff/{userId}")
    boolean isStaff(@PathVariable("userId") Long userId);
}
