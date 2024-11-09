package ca.gbc.roomservice.service;

import ca.gbc.roomservice.dto.RoomRequest;
import ca.gbc.roomservice.dto.RoomResponse;

import java.util.List;

public interface RoomService {
    List<RoomResponse> getAllRooms();
    RoomResponse createRoom(RoomRequest roomRequest);
    String updateRoom(String roomId, RoomRequest roomRequest);
    void deleteRoom(String roomId);
}