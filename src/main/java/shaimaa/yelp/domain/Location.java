package shaimaa.yelp.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class Location {
    double latitude;
    double longitude;
}
