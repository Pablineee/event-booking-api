package ca.gbc.roomservice.service;

import ca.gbc.roomservice.dto.RoomRequest;
import ca.gbc.roomservice.dto.RoomResponse;

import java.util.List;

public interface RoomService {
    List<RoomResponse> getAllRooms();
    RoomResponse getRoom(String roomId);
    RoomResponse createRoom(RoomRequest roomRequest);
    String updateRoom(String roomId, RoomRequest roomRequest);
    void makeUnavailable(String roomId, RoomResponse roomResponse);
    void makeAvailable(String roomId, RoomResponse roomResponse);
    void deleteRoom(String roomId);
}