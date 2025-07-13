package shaimaa.yelp.domain;

import lombok.*;

import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Place {
    private String id;
    private Photo[] photos;
    private String name;
    private String description;
    private Address address;
    private Location location;
    private String[] tags;

    private double rating;
    private int reviewCount;
    private String neighbourhood;
    private LocalTime openTime;
    private LocalTime closeTime;

    public Place(String id) {
        this.id = id;
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
