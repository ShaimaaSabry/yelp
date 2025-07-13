package shaimaa.yelp.application.commands.CreatePlace;

import shaimaa.yelp.domain.Location;
import shaimaa.yelp.domain.Place;

public class CreatePlaceHandler {

    public Place handle(CreatePlaceCommand command) {
        // Here you would typically interact with a repository to save the place
        // For simplicity, we are just creating a new Place object

        return Place.builder()
                .name(command.name())
                .description(command.description())
                .tags(command.tags())
                .build();
    }
}
