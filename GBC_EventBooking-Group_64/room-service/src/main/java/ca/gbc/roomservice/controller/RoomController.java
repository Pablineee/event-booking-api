package ca.gbc.roomservice.controller;


import ca.gbc.roomservice.dto.RoomRequest;
import ca.gbc.roomservice.dto.RoomResponse;
import ca.gbc.roomservice.model.Room;
import ca.gbc.roomservice.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/room")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<RoomResponse> getAllRooms() {
        return roomService.getAllRooms();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{roomId}")
    public RoomResponse getRoom(@PathVariable String roomId) {
        return roomService.getRoom(roomId);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/unavailable/{roomId}")
    public void makeRoomUnavailable(@PathVariable String roomId) {
        RoomResponse roomResponse = roomService.getRoom(roomId);
        roomService.makeUnavailable(roomId, roomResponse);
    }

    @GetMapping("/available/{roomId}")
    @ResponseStatus(HttpStatus.OK)
    public boolean isRoomAvailable(@PathVariable Long roomId) {
        RoomResponse roomResponse = roomService.getRoom(String.valueOf(roomId));
        return roomResponse.available();
    }


    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/available/{roomId}")
    public void makeRoomAvailable(@PathVariable String roomId) {
        RoomResponse roomResponse = roomService.getRoom(roomId);
        roomService.makeAvailable(roomId, roomResponse);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<RoomResponse> createRoom(@RequestBody RoomRequest roomRequest) {
        RoomResponse createdRoom = roomService.createRoom(roomRequest);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/room" + createdRoom.id());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .headers(headers)
                .contentType(MediaType.APPLICATION_JSON)
                .body(createdRoom);
    }

    @PutMapping("/{roomId}")
    // @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> updateRoom(@PathVariable("roomId") String roomId,
                                           @RequestBody RoomRequest roomRequest) {

        String updatedRoomId = roomService.updateRoom(roomId, roomRequest);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "api/room/" + updatedRoomId);

        return new ResponseEntity<>(headers, HttpStatus.NO_CONTENT);

    }

    @DeleteMapping("/{roomId}")
    public ResponseEntity<?> deleteRoom(@PathVariable("roomId") String roomId) {

        roomService.deleteRoom(roomId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
