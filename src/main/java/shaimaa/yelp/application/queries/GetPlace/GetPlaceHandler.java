package shaimaa.yelp.application.queries.GetPlace;

import org.springframework.stereotype.Component;
import shaimaa.yelp.domain.Place;

import java.util.UUID;

@Component
public class GetPlaceHandler {

    public Place handle(UUID placeId) {
        // Here you would typically interact with a repository to fetch the place
        // For simplicity, we are just creating a new Place object

        return Place.builder()
//                .name(query.name())
//                .description("Sample description for " + query.name())
//                .tags(query.tags().toArray(new String[0]))
                .build();
    }
}
