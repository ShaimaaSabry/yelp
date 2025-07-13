package shaimaa.yelp.application.commands.CreatePlace;

import lombok.Builder;

import java.util.Set;

@Builder
public record CreatePlaceCommand(
        String name,
        String description,
        Address address,
        Location location,
        Set<String> tags
) {
    @Builder
    public record Address (
        String street,
        String city,
        String state,
        String country
    ) {}

    @Builder
    public record Location (
        double latitude,
        double longitude
    ) {}
}
