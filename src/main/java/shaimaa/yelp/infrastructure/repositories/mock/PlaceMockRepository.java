package shaimaa.yelp.infrastructure.repositories.mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import shaimaa.yelp.domain.Address;
import shaimaa.yelp.domain.Location;
import shaimaa.yelp.domain.Photo;
import shaimaa.yelp.domain.Place;
import shaimaa.yelp.application.contracts.PlaceRepository;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Component
public class PlaceMockRepository implements PlaceRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(PlaceMockRepository.class);

    private final Set<Place> places = new HashSet<>();

    PlaceMockRepository() {
        Photo[] photos = new Photo[] {
                Photo.builder().url("https://images.unsplash.com/photo-1517248135467-4c7edcad34c4?fm=jpg&q=60&w=3000&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8cmVzdGF1cmFudHxlbnwwfHwwfHx8MA%3D%3D").build(),
                Photo.builder().url("https://cdn.sortiraparis.com/images/80/100789/834071-too-restaurant-too-hotel-paris-photos-menu-entrees.jpg").build(),
                Photo.builder().url("https://media.istockphoto.com/id/1307190527/photo/happy-waiter-serving-food-to-group-of-friends-in-a-pub.jpg?s=612x612&w=0&k=20&c=EDqQ0oBcpFGV25p61vWUF5N-6lRJdbmZmQMe5kyuxyA=").build()
        };

        Place place1 = Place.builder()
                .id(UUID.randomUUID())
                .photos(photos)
                .name("East Village Pizza")
                .description("This East Village spot offers crispy thin-crust pepperoni and buffalo chicken slices.")
                .tags(Set.of("Pizza", "Italian"))
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
                .rating(3.4)
                .reviewCount(79)
                .neighbourhood("East Harlem")
                .openTime(LocalTime.of(13,0,0))
                .closeTime(LocalTime.of(3, 0, 0))
                .build();


        Place place2 = Place.builder()
                .id(UUID.randomUUID())
                .photos(photos)
                .name("Bardolino Pizza")
                .rating(3.4)
                .reviewCount(79)
                .neighbourhood("East Harlem")
                .openTime(LocalTime.of(13,0,0))
                .closeTime(LocalTime.of(3, 0, 0))
                .tags(Set.of("Pizza"))
                .build();

        Place place3 = Place.builder()
                .id(UUID.randomUUID())
                .photos(photos)
                .name("Wolfnights - The Gourment Wrap")
                .rating(4.1)
                .reviewCount(53)
                .neighbourhood("Kps Bay")
                .openTime(LocalTime.of(12,0,0))
                .closeTime(LocalTime.of(0, 0, 0))
                .tags(Set.of("Halal", "Mediterranean"))
                .build();

        Place place4 = Place.builder()
                .id(UUID.randomUUID())
                .photos(photos)
                .name("Valerie")
                .rating(4.3)
                .reviewCount(1000)
                .neighbourhood("Middletown West")
                .openTime(LocalTime.of(13,0,0))
                .closeTime(LocalTime.of(3, 0, 0))
                .tags(Set.of("New American", "Cocktail Bars"))
                .build();

        places.add(place1);
        places.add(place2);
        places.add(place3);
        places.add(place4);
    }

    public Optional<Place> getPlace(String placeId) {
        return this.places.stream().filter(p -> p.getId().equals(placeId)).findFirst();
    }

    @Override
    public Place createPlace(Place place) {
        place = Place.of(
                UUID.randomUUID(),
                place.getName(),
                place.getDescription(),
                place.getAddress(),
                place.getLocation(),
                place.getTags()
        );
        LOGGER.debug("Creating place: {}", place);
        places.add(place);
        return place;
    }

    @Override
    public List<Place> search(String query, Location location, double radius) {
        return List.of();
    }
}
