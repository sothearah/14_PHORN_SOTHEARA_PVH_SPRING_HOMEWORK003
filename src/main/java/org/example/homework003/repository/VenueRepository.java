package org.example.homework003.repository;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.apache.ibatis.annotations.*;
import org.example.homework003.model.entity.Venue;
import org.example.homework003.model.request.VenueRequest;

import java.util.List;

@Mapper
public interface VenueRepository {

    @Results(id = "mapping", value = {
            @Result(property = "venueId", column = "venue_id"),
            @Result(property = "venueName", column = "venue_name")
    })

    @Select("select * from venues offset #{offset} limit #{size}")
    List<Venue> getAllVenue(Integer offset, Integer size);

    @Select("select * from venues where venues.venue_id = #{venueId};")
    @ResultMap("mapping")
    Venue getVenueById(Long venueId);

    @Select("insert into venues values (default, #{r.venueName}, #{r.location}) returning *;")
    @ResultMap("mapping")
    Venue saveVenue(@Param("r") VenueRequest request);


    @Select("update venues set venue_name = #{r.venueName}, location = #{r.location} where venue_id = #{venueId} returning *;")
    @ResultMap("mapping")
    Venue updateVenueById(Long venueId, @Param("r") VenueRequest request);

    @Select("delete from venues where venues.venue_id = #{venue};")
    @ResultMap("mapping")
    Venue deleteById(Long venueId);

    @Select("""
           select exists ( select 1 from venues where venue_name = #{venueName})
    """)
    @ResultMap("mapping")
    boolean existsVenueName(
            @NotNull @NotBlank
                    (message = "Venue name cannot be blank")
            @Size
                    (min = 1, max = 50, message = "Venue name must not exceed 50 characters")
            String venueName);
}
