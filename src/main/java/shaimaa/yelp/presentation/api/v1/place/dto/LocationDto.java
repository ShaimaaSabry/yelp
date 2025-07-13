package shaimaa.yelp.presentation.api.v1.place.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import shaimaa.yelp.application.commands.CreatePlace.CreatePlaceCommand;
import shaimaa.yelp.domain.Location;


@Builder
public record LocationDto (
    @NotNull
    Double latitude,

    @NotNull
    Double longitude
) {

    public static LocationDto of(Location location) {
        return new LocationDto(
                location.getLatitude(),
                location.getLongitude()
        );
    }

    public CreatePlaceCommand.Location to() {
        return CreatePlaceCommand.Location.builder()
                .latitude(this.latitude)
                .longitude(this.longitude)
                .build();
    }
}
