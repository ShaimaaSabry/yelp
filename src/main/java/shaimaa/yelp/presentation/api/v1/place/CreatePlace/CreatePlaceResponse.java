package shaimaa.yelp.presentation.api.v1.place.CreatePlace;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import shaimaa.yelp.domain.Place;
import shaimaa.yelp.presentation.api.v1.PhotoResponse;
import shaimaa.yelp.presentation.api.v1.place.dto.AddressDto;
import shaimaa.yelp.presentation.api.v1.place.dto.LocationDto;

//import javax.validation.Valid;
//import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.Size;
import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import java.util.UUID;

@Builder
public record CreatePlaceResponse (
    UUID id,

    String name,

    String description,

    AddressDto address,

    LocationDto location,

    Set<String> tags
) {

    public static CreatePlaceResponse of(Place place) {
        if (place == null) {
            return CreatePlaceResponse.builder()
                    .address(AddressDto.builder().build())
                    .location(LocationDto.builder().build())
                    .tags(Collections.emptySet())
                    .build();
        }

        return CreatePlaceResponse.builder()
                .id(place.getId())
                .name(place.getName())
                .description(place.getDescription())
                .address(AddressDto.of(place.getAddress()))
                .location(LocationDto.of(place.getLocation()))
                .tags(place.getTags() != null ? place.getTags() : Collections.emptySet())
                .build();
    }
}