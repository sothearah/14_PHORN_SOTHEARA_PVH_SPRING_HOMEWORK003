package org.example.homework003.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.homework003.exception.DuplicateUserException;
import org.example.homework003.exception.NotFoundException;
import org.example.homework003.model.entity.Venue;
import org.example.homework003.model.request.VenueRequest;
import org.example.homework003.repository.VenueRepository;
import org.example.homework003.service.VenueService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VenueImpl implements VenueService {
    private final VenueRepository repository;

    @Override
    public List<Venue> getAllVenue(Integer page, Integer size) {
        Integer offset = size * (page - 1);
        return repository.getAllVenue(offset, size);
    }

    @Override
    public Venue getVenueById(Long venueId) {
        Venue v = repository.getVenueById(venueId);
        if (v == null ) {
            throw new NotFoundException("Venue id " + venueId + " not found");
        }
        return repository.getVenueById(venueId);
    }

    @Override
    public Venue saveVenue(VenueRequest request) {

        if (repository.existsVenueName(request.getVenueName())) {
            throw new DuplicateUserException("Venue name already exists");
        }

        return repository.saveVenue(request);
    }

    @Override
    public Venue updateVenueById(Long venueId, VenueRequest request) {

        Venue v = repository.getVenueById(venueId);
        if (v == null ) {
            throw new NotFoundException("Venue id " + venueId + " not found");
        }
        if (repository.existsVenueName(request.getVenueName())) {
            throw new DuplicateUserException("Venue name is already exists");
        }

        return repository.updateVenueById(venueId, request);
    }

    @Override
    public Venue deleteById(Long venueId) {

        Venue v = repository.getVenueById(venueId);
        if (v == null ) {
            throw new NotFoundException("Venue  id " + venueId + " not found");
        }

        return repository.deleteById(venueId);
    }
}
