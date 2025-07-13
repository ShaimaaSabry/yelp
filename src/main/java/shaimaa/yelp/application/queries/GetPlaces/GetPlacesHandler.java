package shaimaa.yelp.application.queries.GetPlaces;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Component;
import shaimaa.yelp.application.contracts.PlaceRepository;
import shaimaa.yelp.domain.Address;
import shaimaa.yelp.domain.Location;
import shaimaa.yelp.domain.Place;

import java.util.List;
import java.util.Set;

@Component
public class GetPlacesHandler {
    private static final double DEFAULT_RADIUS_IN_METERS = 5000.0;

    private final PlaceRepository placeRepository;

    GetPlacesHandler(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    public List<Place> handle(GetPlacesQuery query) throws JsonProcessingException {
        return placeRepository.search(
                query.query(),
                Location.create(query.location().latitude(), query.location().longitude()),
                DEFAULT_RADIUS_IN_METERS
        );
    }
}
