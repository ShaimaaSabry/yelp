package shaimaa.yelp.presentation.api.v1.place.GetPlace;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import shaimaa.yelp.application.commands.CreatePlace.CreatePlaceCommand;
import shaimaa.yelp.domain.Place;
import shaimaa.yelp.presentation.api.v1.place.dto.AddressDto;
import shaimaa.yelp.presentation.api.v1.place.dto.LocationDto;

import java.time.LocalTime;
import java.util.Collections;
import java.util.Set;
import java.util.UUID;

@Builder
public record PlaceItemDto (
    UUID id,
//    private PhotoResponse[] photos;
    String name,
    String description,
    AddressDto address,
    LocationDto location,
    Set<String> tags,

    double distance,
    double rating,
    int reviewCount,
    String neighbourhood,
    String openClosedStatus,
    LocalTime openClosedTime
) {
    public static PlaceItemDto of(Place place) {
        if (place == null) {
            return PlaceItemDto.builder()
                    .address(AddressDto.builder().build())
                    .location(LocationDto.builder().build())
                    .tags(Collections.emptySet())
                    .build();
        }

        return PlaceItemDto.builder()
                .id(place.getId())
                .name(place.getName())
                .description(place.getDescription())
                .address(AddressDto.of(place.getAddress()))
                .location(LocationDto.of(place.getLocation()))
                .tags(place.getTags())
                .build();
    }
}
