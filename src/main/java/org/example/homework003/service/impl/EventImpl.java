package org.example.homework003.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.homework003.model.entity.Attendee;
import org.example.homework003.model.entity.Event;
import org.example.homework003.model.request.EventRequest;
import org.example.homework003.repository.EventRepository;
import org.example.homework003.service.EventService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventImpl implements EventService {

    private final EventRepository repository;

    @Override
    public List<Event> getAllEvent(Integer page, Integer size) {
        Integer offset = size * (page - 1);
        return repository.getAllEvent(offset, size);
    }

    @Override
    public Event getEventById(Long eventId) {
        return repository.getEventById(eventId);
    }

    @Override
    public Event saveEvent(EventRequest request) {
        return repository.saveEvent(request);
    }

    @Override
    public Event updateEventById(Long eventId, EventRequest request) {
        return repository.updateEventById(eventId, request);
    }

    @Override
    public void deleteById(Long eventId) {
        repository.deleteById(eventId);
    }
}
