package org.example.homework003.service;

import org.example.homework003.model.entity.Attendee;
import org.example.homework003.model.entity.Event;
import org.example.homework003.model.request.EventRequest;

import java.util.List;

public interface EventService {
    List<Event> getAllEvent(Integer page, Integer size);

    Event getEventById(Long eventId);

    Event saveEvent(EventRequest request);

    Event updateEventById(Long eventId, EventRequest request);

    void deleteById(Long eventId);
}
