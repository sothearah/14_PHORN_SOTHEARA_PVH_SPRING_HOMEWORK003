package org.example.homework003.service;

import org.example.homework003.model.entity.Attendee;
import org.example.homework003.model.entity.Venue;
import org.example.homework003.model.request.AttendeeRequest;

import java.util.List;

public interface AttendeeService {
    List<Attendee> getAllAttendee(Integer page, Integer size);

    Attendee getAttendeeById(Long attendeeId);

    Attendee saveAttendee(AttendeeRequest request);

    Attendee updateAttendeeById(Long attendeeId, AttendeeRequest request);

    Attendee deleteById(Long attendeeId);
}
