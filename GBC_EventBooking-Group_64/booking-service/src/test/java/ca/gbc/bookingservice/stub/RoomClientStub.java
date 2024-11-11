package test.java.ca.gbc.bookingservice.stub;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;

public class RoomClientStub {

    public static void stubUserCall(String roomId){

        stubFor(get(urlEqualTo("/api/room?roomId=" + roomId)))
                .willReturn(aResponse()
                        .withStatus(200)  // The status code to expect from room-service
                        .withHeader("Content-Type", "application/json")
                        .withBody("true")
        );
    }

}
