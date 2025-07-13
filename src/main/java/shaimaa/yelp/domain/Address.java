package shaimaa.yelp.domain;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Address {
    private String street;
    private String city;
    private String state;
    private String country;

    public static Address create(
            String street,
            String city,
            String state,
            String country
    ) {
        return Address.builder()
                .street(street)
                .city(city)
                .state(state)
                .country(country)
                .build();
    }
}
