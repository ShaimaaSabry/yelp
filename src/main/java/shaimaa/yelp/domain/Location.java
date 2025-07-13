package shaimaa.yelp.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Builder
@Getter
public class Location {
    double latitude;
    double longitude;

    public static Location create(double latitude, double longitude) {
        return Location.builder()
                .latitude(latitude)
                .longitude(longitude)
                .build();
    }
}
