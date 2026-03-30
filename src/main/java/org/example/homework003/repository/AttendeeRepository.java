package org.example.homework003.repository;

import jakarta.validation.constraints.*;
import lombok.NonNull;
import org.apache.ibatis.annotations.*;
import org.example.homework003.model.entity.Attendee;
import org.example.homework003.model.entity.Venue;
import org.example.homework003.model.request.AttendeeRequest;

import java.util.List;

@Mapper
public interface  AttendeeRepository {

    @Results(id = "mapping", value = {
            @Result(property = "attendeeId", column = "attendee_id"),
            @Result(property = "attendeeName", column = "attendee_name")
    })

    @Select("select * from attendees offset  #{offset} limit #{size}")
    List<Attendee> getAllAttendee(Integer offset, Integer size);

    @Select("select * from public.attendees where attendee_id = #{attendeeId} ")
    @ResultMap("mapping")
    Attendee getAttendeeById(Long attendeeId);

    @Select("insert into attendees values (default, attendee_name = #{r.attendeeName}, email = #{r.email}) returning * ")
    @ResultMap("mapping")
    Attendee saveAttendee(@Param("r")  AttendeeRequest request);

    @Select("update attendees set attendee_name = #{r.attendeeName}, email = #{r.email} where attendee_id = #{attendeeId} returning *")
    @ResultMap("mapping")
    Attendee updateAttendeeById(Long attendeeId, @Param("r") AttendeeRequest request);

    @Select("delete from attendees where attendee_id = #{attendeeId};")
    @ResultMap("mapping")
    Attendee deleteById(Long attendeeId);

    @Select("select exists(select 1 from attendees where attendee_name = #{attendeeName}) ")
    @ResultMap("mapping")
    boolean existsByAttendeeName(@NotBlank(message = "Attendee name cannot be blank")
                                 @NonNull @Size
            (min = 1, max = 50, message = "Attendee name must be between 1 and 50 characters") String attendeeName);



    @Select("select exists(select 1 from attendees where email = #{email})")
    boolean existsByAttendeeEmail(@NotNull @NotBlank(message = "Email must be valid format (bc@gmail.com)") @Email(message = "Invalid email format!")
                                  @Pattern(
            regexp = "^[a-zA-Z0-9]+@gmail\\.com$",
            message = "Email must be a valid format (abc@gmail.com)"
    ) String email);
}
