package ca.gbc.roomservice.service;

import ca.gbc.roomservice.dto.RoomRequest;
import ca.gbc.roomservice.dto.RoomResponse;
import ca.gbc.roomservice.model.Room;
import ca.gbc.roomservice.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    @Override
    public List<RoomResponse> getAllRooms() {
        List<Room> rooms = roomRepository.findAll();
        return rooms.stream()
                .map( room -> RoomResponse.builder()
                        .id(room.getId())
                        .roomName(room.getRoomName())
                        .features(room.getFeatures())
                        .capacity(room.getCapacity())
                        .available(room.getAvailable())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public RoomResponse getRoom(String roomId) {
        Room room = roomRepository.findById(Long.parseLong(roomId))
                .orElseThrow(() -> new RuntimeException("Room with ID " + roomId + " not found"));

        return RoomResponse.builder()
                .id(room.getId())
                .roomName(room.getRoomName())
                .capacity(room.getCapacity())
                .features(room.getFeatures())
                .available(room.getAvailable())
                .build();
    }


    @Override
    public RoomResponse createRoom(RoomRequest roomRequest) {
        Room room = Room.builder()
                .roomName(roomRequest.roomName())
                .capacity(roomRequest.capacity())
                .features(roomRequest.features())
                .available(roomRequest.available())
                .build();

        Room savedRoom = roomRepository.save(room);

        return RoomResponse.builder()
                .id(savedRoom.getId())
                .roomName(savedRoom.getRoomName())
                .capacity(savedRoom.getCapacity())
                .features(savedRoom.getFeatures())
                .available(savedRoom.getAvailable())
                .build();
    }

    @Override
    public String updateRoom(String roomId, RoomRequest roomRequest) {
        Room room = roomRepository.findById(Long.parseLong(roomId))
                .orElseThrow(() -> new RuntimeException("Room with ID " + roomId + " not found"));

        room.setRoomName(roomRequest.roomName());
        room.setCapacity(roomRequest.capacity());
        room.setFeatures(roomRequest.features());
        room.setAvailable(roomRequest.available());

        Room updatedRoom = roomRepository.save(room);
        return String.valueOf(updatedRoom.getId());
    }

    @Override
    public void makeUnavailable(String roomId, RoomResponse roomResponse) {
        Room room = roomRepository.findById(Long.parseLong(roomId))
                .orElseThrow(() -> new RuntimeException("Room with ID " + roomId + " not found"));

        room.setAvailable(false);

        roomRepository.save(room);
    }

    @Override
    public void makeAvailable(String roomId, RoomResponse roomResponse) {
        Room room = roomRepository.findById(Long.parseLong(roomId))
                .orElseThrow(() -> new RuntimeException("Room with ID " + roomId + " not found"));

        room.setAvailable(true);

        roomRepository.save(room);
    }

    @Override
    public void deleteRoom(String roomId) {
        Room room = roomRepository.findById(Long.parseLong(roomId))
                .orElseThrow(() -> new RuntimeException("Room with ID " + roomId + " not found"));

        roomRepository.delete(room);
    }
}
