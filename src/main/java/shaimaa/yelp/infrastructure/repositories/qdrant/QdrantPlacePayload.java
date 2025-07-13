package shaimaa.yelp.infrastructure.repositories.qdrant;

import shaimaa.yelp.domain.Address;
import shaimaa.yelp.domain.Location;
import shaimaa.yelp.domain.Place;

import java.util.List;
import java.util.Set;

public record QdrantPlacePayload(
        String name,
        String description,
        QdrantAddressPayload address,
        QdrantLocationPayload location,
        Set<String> tags
) {
    record QdrantAddressPayload(
        String street,
        String city,
        String state,
        String country
    ) {
        Address toDomain() {
            return Address.create(
                    street,
                    city,
                    state,
                    country
            );
        }
    }

    record QdrantLocationPayload(
        double latitude,
        double longitude
    ) {
        Location toDomain() {
            return Location.create(latitude, longitude);
        }
    }

    static QdrantPlacePayload from(Place place) {
        return new QdrantPlacePayload(
                place.getName(),
                place.getDescription(),
                new QdrantAddressPayload(
                        place.getAddress().getStreet(),
                        place.getAddress().getCity(),
                        place.getAddress().getState(),
                        place.getAddress().getCountry()
                ),
                new QdrantLocationPayload(
                        place.getLocation().getLatitude(),
                        place.getLocation().getLongitude()
                ),
                place.getTags()
        );
    }
}
