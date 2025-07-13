package shaimaa.yelp.domain;

import lombok.*;

import java.time.LocalTime;
import java.util.Set;
import java.util.UUID;

@Getter
@AllArgsConstructor
@Builder
@ToString
public class Place {
    private UUID id;
    private Photo[] photos;
    private String name;
    private String description;
    private Address address;
    private Location location;
    private Set<String> tags;

    private double rating;
    private int reviewCount;
    private String neighbourhood;
    private LocalTime openTime;
    private LocalTime closeTime;

    public static Place create(
            String name,
            String description,
            Address address,
            Location location,
            Set<String> tags
    ) {
        return Place.builder()
                .name(name)
                .description(description)
                .address(address)
                .location(location)
                .tags(tags)
                .build();
    }

    public static Place of(
            UUID id,
            String name,
            String description,
            Address address,
            Location location,
            Set<String> tags
    ) {
        return Place.builder()
                .id(id)
                .name(name)
                .description(description)
                .address(address)
                .location(location)
                .tags(tags)
                .build();
    }

    public double getDistance() {
        return 4.2;
    }

    public String getOpenClosedStatus() {
        return "Closed";
    }

    public LocalTime getOpenClosedUntilTime() {
        return LocalTime.of(3,0,0);
    }
}
