package shaimaa.yelp.presentation.api.v1.place.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import shaimaa.yelp.application.commands.CreatePlace.CreatePlaceCommand;
import shaimaa.yelp.domain.Address;
import shaimaa.yelp.presentation.api.v1.place.CreatePlace.CreatePlaceRequest;

@Builder
public record AddressDto(
        @NotBlank
        String street,

        @NotBlank
        String city,

        @NotBlank
        String state,

        @NotBlank
        String country
) {
    public static AddressDto of(Address address) {
        if(address == null) {
            return AddressDto.builder().build();
        }

        return AddressDto.builder()
                .street(address.getStreet())
                .city(address.getCity())
                .state(address.getState())
                .country(address.getCountry())
                .build();
    }

    public CreatePlaceCommand.Address to() {
        return CreatePlaceCommand.Address.builder()
                .street(this.street)
                .city(this.city)
                .state(this.state)
                .country(this.country)
                .build();
    }
}
