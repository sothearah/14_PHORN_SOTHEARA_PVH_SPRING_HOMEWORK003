package org.example.homework003.controller;

import lombok.RequiredArgsConstructor;
import org.example.homework003.model.entity.Venue;
import org.example.homework003.model.request.VenueRequest;
import org.example.homework003.model.response.ApiResponse;
import org.example.homework003.service.VenueService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("api/v1/venues")
@RequiredArgsConstructor
public class VenueController {
    private final VenueService service;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Venue>>> getAllInstructor(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "5") Integer size) {
        ApiResponse<List<Venue>> apiResponse = ApiResponse.<List<Venue>>builder()
                .success(true)
                .status(HttpStatus.OK)
                .message("Instructors fetched successfully")
                .payload(service.getAllVenue(page, size))
                .timestamp(Instant.now())
                .build();

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);

    }

    @GetMapping("/{venue-id}")
    public ResponseEntity<ApiResponse<Venue>> getVenueById(@PathVariable("venue-id") Long venueId) {

        ApiResponse<Venue> apiResponse = ApiResponse.<Venue>builder()
                .timestamp(Instant.now())
                .message("Retrieved venue successfully")
                .status(HttpStatus.OK)
                .payload(service.getVenueById(venueId))
                .build();

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<ApiResponse<Venue>> saveInstructor(@RequestBody VenueRequest request) {
        ApiResponse<Venue> apiResponse = ApiResponse.<Venue>builder()
                .success(true)
                .status(HttpStatus.CREATED)
                .message("Instructor created successfully")
                .payload(service.saveVenue(request))
                .timestamp(Instant.now())
                .build();

        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    @PutMapping("/{venue-id}")
    public ResponseEntity<ApiResponse<Venue>> updateVenueById(@PathVariable("venue-id") Long venueId, @RequestBody VenueRequest request) {

        ApiResponse<Venue> apiResponse = ApiResponse.<Venue>builder()
                .timestamp(Instant.now())
                .message("Created venue successfully")
                .status(HttpStatus.CREATED)
                .payload(service.updateVenueById(venueId, request))
                .build();

        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);

    }

    @DeleteMapping("/{venue-id}")
    public ResponseEntity<ApiResponse<Venue>> deleteById(@PathVariable("venue-id") Long venueId) {

        ApiResponse<Venue> apiResponse = ApiResponse.<Venue>builder()
                .timestamp(Instant.now())
                .message("Delete venue successfully")
                .status(HttpStatus.OK)
                .payload(service.deleteById(venueId))
                .build();

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

}
