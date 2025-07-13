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
}
