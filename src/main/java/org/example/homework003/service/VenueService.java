package org.example.homework003.service;

import org.example.homework003.model.entity.Venue;
import org.example.homework003.model.request.VenueRequest;

import java.util.List;

public interface VenueService {
    List<Venue> getAllVenue(Integer page, Integer size);

    Venue getVenueById(Long instructorId);

    Venue saveVenue(VenueRequest request);

    Venue updateVenueById(Long venueId, VenueRequest request);

    Venue deleteById(Long venueId);
}
