package ca.gbc.bookingservice.service;

import ca.gbc.bookingservice.client.RoomClient;
import ca.gbc.bookingservice.dto.BookingRequest;
import ca.gbc.bookingservice.dto.BookingResponse;
import ca.gbc.bookingservice.model.Booking;
import ca.gbc.bookingservice.repository.BookingRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final MongoTemplate mongoTemplate;
    @Autowired
    private RoomClient roomClient;

    @Override
    public BookingResponse createBooking(BookingRequest bookingRequest) {

        boolean isRoomAvailable = roomClient.roomAvailable(Long.parseLong(bookingRequest.roomId()));

        if (!isRoomAvailable) {
            throw new IllegalStateException("Room is not available for booking.");
        }

        Booking booking = Booking.builder()
                .userId(bookingRequest.userId())
                .roomId(bookingRequest.roomId())
                .date(bookingRequest.date())
                .startTime(bookingRequest.startTime())
                .endTime(bookingRequest.endTime())
                .purpose(bookingRequest.purpose())
                .build();

        Booking savedBooking = bookingRepository.save(booking);

        roomClient.makeUnavailable(Long.valueOf(bookingRequest.roomId()));

        return BookingResponse.builder()
                .bookingId(savedBooking.getId())
                .userId(savedBooking.getUserId())
                .roomId(savedBooking.getRoomId())
                .date(savedBooking.getDate())
                .startTime(savedBooking.getStartTime())
                .endTime(savedBooking.getEndTime())
                .purpose(savedBooking.getPurpose())
                .build();
    }

    @Override
    public List<BookingResponse> getAllBookings() {
        List<Booking> bookings = bookingRepository.findAll();

        return bookings.stream()
                .map(booking -> BookingResponse.builder()
                        .bookingId(booking.getId())
                        .userId(booking.getUserId())
                        .roomId(booking.getRoomId())
                        .date(booking.getDate())
                        .startTime(booking.getStartTime())
                        .endTime(booking.getEndTime())
                        .purpose(booking.getPurpose())
                        .build())
                .collect(Collectors.toList());
    }


    @Override
    public String updateBooking(String id, BookingRequest bookingRequest) {

        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        Booking booking = mongoTemplate.findOne(query, Booking.class);

        if (booking == null) {
            throw new RuntimeException("Booking with ID " + id + " not found");
        }

        booking.setUserId(bookingRequest.userId());
        booking.setRoomId(bookingRequest.roomId());
        booking.setDate(bookingRequest.date());
        booking.setStartTime(bookingRequest.startTime());
        booking.setEndTime(bookingRequest.endTime());
        booking.setPurpose(bookingRequest.purpose());

        bookingRepository.save(booking);

        return "Booking with ID " + id + " has been updated";
    }


    @Override
    public void deleteBooking(String id) {

        log.debug("Deleting a booking with ID {}", id);
        bookingRepository.deleteById(id);

    }
}
