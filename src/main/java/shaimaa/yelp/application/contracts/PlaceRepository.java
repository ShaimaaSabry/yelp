package shaimaa.yelp.application.contracts;

import com.fasterxml.jackson.core.JsonProcessingException;
import shaimaa.yelp.domain.Location;
import shaimaa.yelp.domain.Place;

import java.util.List;
import java.util.Optional;

public interface PlaceRepository {
    List<Place> search(String query, Location location, double radius) throws JsonProcessingException;
    Optional<Place> getPlace(String placeId);
    Place createPlace(Place place);
}
