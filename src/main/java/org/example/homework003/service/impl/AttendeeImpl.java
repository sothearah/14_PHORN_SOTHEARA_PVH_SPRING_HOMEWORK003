package org.example.homework003.service.impl;

import io.swagger.v3.oas.models.info.Contact;
import lombok.RequiredArgsConstructor;
import org.example.homework003.exception.DuplicateUserException;
import org.example.homework003.exception.NotFoundException;
import org.example.homework003.model.entity.Attendee;
import org.example.homework003.model.entity.Venue;
import org.example.homework003.model.request.AttendeeRequest;
import org.example.homework003.repository.AttendeeRepository;
import org.example.homework003.service.AttendeeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AttendeeImpl implements AttendeeService {

    private final AttendeeRepository repository;

    @Override
    public List<Attendee> getAllAttendee(Integer page, Integer size) {
        Integer offset = size * (page - 1);
        return repository.getAllAttendee(offset, size);
    }

    @Override
    public Attendee getAttendeeById(Long attendeeId) {

        Attendee a = repository.getAttendeeById(attendeeId);

        if (a == null ) {
            throw new NotFoundException("Attendee id " + attendeeId + " not found");
        }
        return a;
    }

    @Override
    public Attendee saveAttendee(AttendeeRequest request) {

        Contact attendeeRequest;
        if (repository.existsByAttendeeName(request.getAttendeeName())) {
            throw new DuplicateUserException("Attendee name already exists");
        }
        if (repository.existsByAttendeeEmail(request.getEmail())) {
            throw new DuplicateUserException("Attendee email already exists");
        }
        
        return repository.saveAttendee(request);
    }

    @Override
    public Attendee updateAttendeeById(Long attendeeId, AttendeeRequest request) {

        Attendee a = repository.getAttendeeById(attendeeId);
        if (a == null) {
            throw new NotFoundException("Attendee id " + attendeeId + " not found");
        }
        if (repository.existsByAttendeeName(request.getAttendeeName())){
            throw new DuplicateUserException("Attendee name already exists");
        }

        return repository.updateAttendeeById(attendeeId, request);
    }

    @Override
    public Attendee deleteById(Long attendeeId) {

        Attendee attendee = repository.deleteById(attendeeId);
        if (attendee == null ) {
            throw new NotFoundException("Attendee id " + attendeeId + " not found");
        }
        return repository.deleteById(attendeeId);
        
    }
}
