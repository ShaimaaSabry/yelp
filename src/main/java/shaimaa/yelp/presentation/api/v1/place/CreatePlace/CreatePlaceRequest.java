package shaimaa.yelp.presentation.api.v1.place.CreatePlace;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import shaimaa.yelp.application.commands.CreatePlace.CreatePlaceCommand;
import shaimaa.yelp.presentation.api.v1.place.dto.AddressDto;
import shaimaa.yelp.presentation.api.v1.place.dto.LocationDto;

import java.util.Set;

@Builder
public record CreatePlaceRequest(
        @NotBlank
        @Size(max = 50)
        String name,

        @NotBlank
        @Size(max = 100)
        String description,

        @NotNull
        @Valid
        AddressDto address,

        @NotNull
        @Valid
        LocationDto location,

        @NotNull
        @Size(min = 0, max = 5)
        Set<String> tags
) {
    public CreatePlaceCommand toCommand() {
        return CreatePlaceCommand.builder()
                .name(this.name)
                .description(this.description)
                .address(this.address.to())
                .location(this.location.to())
                .tags(this.tags)
                .build();
    }
}
