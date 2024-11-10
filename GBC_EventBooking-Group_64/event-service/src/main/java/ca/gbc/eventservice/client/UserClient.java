package ca.gbc.eventserviceservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "user", url = "${user.service.url}")
public interface UserClient {

    @RequestMapping(method = RequestMethod.GET, value = "/api/user")
    boolean isStudent(@RequestParam String userId);

}
