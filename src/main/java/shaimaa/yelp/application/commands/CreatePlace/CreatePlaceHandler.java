package shaimaa.yelp.application.commands.CreatePlace;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import shaimaa.yelp.application.contracts.PlaceRepository;
import shaimaa.yelp.domain.Address;
import shaimaa.yelp.domain.Location;
import shaimaa.yelp.domain.Place;

import java.util.Set;

@Component
public class CreatePlaceHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(CreatePlaceHandler.class);

    private final PlaceRepository placeRepository;

    public CreatePlaceHandler(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    public Place handle(CreatePlaceCommand command) {
        Place place = Place.create(
                command.name(),
                command.description(),
                Address.create(
                        command.address().street(),
                        command.address().city(),
                        command.address().state(),
                        command.address().country()
                ),
                Location.create(command.location().latitude(), command.location().longitude()),
                command.tags()
        );

        place = placeRepository.createPlace(place);
        LOGGER.debug("Place created: {}", place);

        return place;
    }
}
