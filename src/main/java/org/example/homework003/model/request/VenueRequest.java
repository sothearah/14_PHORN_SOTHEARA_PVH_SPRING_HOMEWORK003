package org.example.homework003.model.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VenueRequest {
    @NotNull
    @NotBlank(message = "Venue name cannot be blank!")
    @Size(min = 1, max = 50, message = "Venue name must not exceed 50 characters")

    private String venueName;
    @NotNull
    @NotBlank(message = "Location cannot be blank!")
    @Size(min = 1, max = 50, message = "Location must not exceed 50 characters")
    private String location;
}
