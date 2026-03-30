package org.example.homework003.controller;

import lombok.RequiredArgsConstructor;
import org.example.homework003.model.entity.Attendee;
import org.example.homework003.model.entity.Event;
import org.example.homework003.model.request.AttendeeRequest;
import org.example.homework003.model.request.EventRequest;
import org.example.homework003.model.response.ApiResponse;
import org.example.homework003.service.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/api/v1/events")
@RequiredArgsConstructor
public class EventController {
    private final EventService service;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Event>>> getAllAttendee(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "5") Integer size) {
        ApiResponse<List<Event>> apiResponse = ApiResponse.<List<Event>>builder()
                .success(true)
                .status(HttpStatus.OK)
                .message("Event fetched successfully")
                .payload(service.getAllEvent(page, size))
                .timestamp(Instant.now())
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/{event-id}")
    public ResponseEntity<ApiResponse<Event>> getAttendeeById(@PathVariable("event-id") Long eventId) {

        ApiResponse<Event> apiResponse ;
        Event checkId = service.getEventById(eventId);

        if(!(checkId == null)) {
            apiResponse = ApiResponse.<Event>builder()
                    .success(true)
                    .status(HttpStatus.OK)
                    .message("Event fetched successfully")
                    .payload(service.getEventById(eventId))
                    .timestamp(Instant.now())
                    .build();
            return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
        } else {
            apiResponse = ApiResponse.<Event>builder()
                    .success(false)
                    .status(HttpStatus.NOT_FOUND)
                    .message("Event fetched failed")
                    .timestamp(Instant.now())
                    .build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Event>> saveEvent(@RequestBody EventRequest request) {
        ApiResponse<Event> apiResponse = ApiResponse.<Event>builder()
                .success(true)
                .status(HttpStatus.CREATED)
                .message("Event created successfully")
                .payload(service.saveEvent(request))
                .timestamp(Instant.now())
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    @PutMapping("/{event-id}")
    public ResponseEntity<ApiResponse<Event>> updateEventById(@PathVariable("event-id") Long eventId, @RequestBody EventRequest request) {
        ApiResponse<Event> apiResponse;
        Event checkId = service.updateEventById(eventId, request);

        if(!(checkId == null)) {
            apiResponse = ApiResponse.<Event>builder()
                    .success(true)
                    .status(HttpStatus.OK)
                    .message("Event updated successfully")
                    .payload(service.updateEventById(eventId, request))
                    .timestamp(Instant.now())
                    .build();
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } else {
            apiResponse = ApiResponse.<Event>builder()
                    .success(false)
                    .status(HttpStatus.NOT_FOUND)
                    .message("Event updated failed")
                    .timestamp(Instant.now())
                    .build();
            return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{event-id}")
    public ResponseEntity<ApiResponse<Event>> deleteById(@PathVariable("event-id") Long eventId) {

        Event checkId = service.getEventById(eventId);

        ApiResponse<Event> apiResponse;

        if(!(checkId == null)) {
            service.deleteById(eventId);
            apiResponse = ApiResponse.<Event>builder()
                    .success(true)
                    .status(HttpStatus.OK)
                    .message("Event deleted successfully")
                    .timestamp(Instant.now())
                    .build();
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        }
        else {
            apiResponse = ApiResponse.<Event>builder()
                    .success(false)
                    .status(HttpStatus.NOT_FOUND)
                    .message("Event failed successfully")
                    .timestamp(Instant.now())
                    .build();
            return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
        }
    }

}
