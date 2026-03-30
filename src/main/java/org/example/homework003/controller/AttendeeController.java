package org.example.homework003.controller;


import lombok.RequiredArgsConstructor;
import org.example.homework003.model.entity.Attendee;
import org.example.homework003.model.entity.Venue;
import org.example.homework003.model.request.AttendeeRequest;
import org.example.homework003.model.request.VenueRequest;
import org.example.homework003.model.response.ApiResponse;
import org.example.homework003.service.AttendeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("api/v1/attendees")
@RequiredArgsConstructor
public class AttendeeController {
    private final AttendeeService service;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Attendee>>> getAllAttendee(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "5") Integer size) {
        ApiResponse<List<Attendee>> apiResponse = ApiResponse.<List<Attendee>>builder()
                .success(true)
                .status(HttpStatus.OK)
                .message("Instructors fetched successfully")
                .payload(service.getAllAttendee(page, size))
                .timestamp(Instant.now())
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/{attendee-id}")
    public ResponseEntity<ApiResponse<Attendee>> getAttendeeById(@PathVariable("attendee-id") Long attendeeId) {

        ApiResponse<Attendee> apiResponse = ApiResponse.<Attendee>builder()
                .timestamp(Instant.now())
                .message("Retrieved Attendee successfully")
                .status(HttpStatus.OK)
                .payload(service.getAttendeeById(attendeeId))
                .build();

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<ApiResponse<Attendee>> saveAttendee(@RequestBody AttendeeRequest request) {
        ApiResponse<Attendee> apiResponse = ApiResponse.<Attendee>builder()
                .success(true)
                .status(HttpStatus.CREATED)
                .message("Attendee created successfully")
                .payload(service.saveAttendee(request))
                .timestamp(Instant.now())
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    @PutMapping("/{attendee-id}")
    public ResponseEntity<ApiResponse<Attendee>> updateAttendeeById(@PathVariable("attendee-id") Long attendeeId, @RequestBody AttendeeRequest request) {

        ApiResponse<Attendee> apiResponse = ApiResponse.<Attendee>builder()
                .success(true)
                .status(HttpStatus.CREATED)
                .message("Attendee updated successfully")
                .payload(service.updateAttendeeById(attendeeId, request))
                .timestamp(Instant.now())
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);

    }

    @DeleteMapping("/{attendee-id}")
    public ResponseEntity<ApiResponse<Attendee>> deleteById(@PathVariable("attendee-id") Long attendeeId) {

        ApiResponse<Attendee> apiResponse = ApiResponse.<Attendee>builder()
                .success(true)
                .status(HttpStatus.CREATED)
                .message("Attendee updated successfully")
                .payload(service.deleteById(attendeeId))
                .timestamp(Instant.now())
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);

    }

}
