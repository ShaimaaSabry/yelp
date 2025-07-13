package shaimaa.yelp.domain.outport;

import shaimaa.yelp.domain.Place;

import java.util.Optional;
import java.util.Set;

public interface PlaceRepository {
    Set<Place> getAll();

    Optional<Place> getOne(String placeId);

    Place save(Place place);
}
