package test.java.ca.gbc.eventservice.stub;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;

public class UserClientStub {

    public static void stubUserCall(String userId, Integer attendees){

        stubFor(get(urlEqualTo("/api/user?userId=" + userId + "&attendees=" + attendees))
                .willReturn(aResponse()
                        .withStatus(200)  // The status code to expect from inventory-service
                        .withHeader("Content-Type", "application/json")
                        .withBody("true"))
        );
    }

}
