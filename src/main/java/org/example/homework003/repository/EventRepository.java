package org.example.homework003.repository;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.One;
import org.example.homework003.model.entity.Event;
import org.example.homework003.model.request.EventRequest;

import java.util.List;

@Mapper
public interface EventRepository {


    @Results(id = "mapping", value = {
            @Result(property = "eventId", column = "event_id"),
            @Result(property = "eventName", column = "event_Name"),
            @Result(property = "eventDate", column = "event_Date"),
            @Result(
                    property = "venue", column = "venue_id",
                    one = @One(select = "org.example.homework003.repository.VenueRepository.getVenueById")
            ),
            @Result(
                    property = "attendee", column = "event_id",
                    many = @Many(select = "org.example.homework003.repository.AttendeeRepository.getAttendeeById")
            )
    }
    )

    @Select("select * from events offset #{offset} limit #{size} ")
    List<Event> getAllEvent(Integer offset, Integer size);

    @Select("select * from events where event_id = #{eventId}")
    @ResultMap("mapping")
    Event getEventById(Long eventId);


    @Select("insert into events  values (default, #{eventName}, #{eventDate}, #{venueId}) returning *")
    @ResultMap("mapping")
    Event saveEvent( EventRequest request);


    @Select("update events set event_name = #{r.eventName}, event_date = #{r.eventDate}, venue_id = #{r.venueId} where event_id = #{eventId}")
    @ResultMap("mapping")
    Event updateEventById(Long eventId, @Param("r") EventRequest request);

    @Select("delete from events where event_id = #{eventId}")
    @ResultMap("mapping")
    void deleteById(Long eventId);
}
