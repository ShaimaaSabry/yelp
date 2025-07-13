package shaimaa.yelp.application.queries.GetPlaces;

import org.springframework.stereotype.Component;
import shaimaa.yelp.domain.Address;
import shaimaa.yelp.domain.Location;
import shaimaa.yelp.domain.Place;

import java.util.List;

@Component
public class GetPlacesHandler {

    public List<Place> handle(GetPlacesQuery query) {
        // Here you would typically interact with a repository to fetch the places
        // For simplicity, we are just creating a new Place object

        return List.of(
            Place.builder()
                .id("1")
                .name("East Village Pizza")
                .description("This East Village spot offers crispy thin-crust pepperoni and buffalo chicken slices.")
                .address(
                    Address.builder()
                        .street("145 1st Ave")
                        .city("East Village")
                        .state("New York")
                        .country("USA")
                        .build()
                )
                .location(
                    Location.builder()
                        .latitude(40.7279243469238f)
                        .longitude(-73.9851531982422f)
                        .build()
                )
                .tags(new String[]{"Pizza"})
                .build()
        );
    }
}
