package shaimaa.yelp.infrastructure.repository;

import shaimaa.yelp.domain.Photo;
import shaimaa.yelp.domain.Place;
import shaimaa.yelp.domain.outport.PlaceRepository;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

//@Component
public class PlaceMockRepository implements PlaceRepository {
    private final Set<Place> places = new HashSet<>();

    PlaceMockRepository() {
        Photo[] photos = new Photo[] {
                Photo.builder().url("https://images.unsplash.com/photo-1517248135467-4c7edcad34c4?fm=jpg&q=60&w=3000&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8cmVzdGF1cmFudHxlbnwwfHwwfHx8MA%3D%3D").build(),
                Photo.builder().url("https://cdn.sortiraparis.com/images/80/100789/834071-too-restaurant-too-hotel-paris-photos-menu-entrees.jpg").build(),
                Photo.builder().url("https://media.istockphoto.com/id/1307190527/photo/happy-waiter-serving-food-to-group-of-friends-in-a-pub.jpg?s=612x612&w=0&k=20&c=EDqQ0oBcpFGV25p61vWUF5N-6lRJdbmZmQMe5kyuxyA=").build()
        };

        Place place1 = Place.builder()
                .id("1")
                .photos(photos)
                .name("Wolfnights - The Gourment Wrap")
                .rating(4.1)
                .reviewCount(53)
                .neighbourhood("Kps Bay")
                .openTime(LocalTime.of(12,0,0))
                .closeTime(LocalTime.of(0, 0, 0))
                .tags(new String[] {"Halal", "Mediterranean"})
                .build();

        Place place2 = Place.builder()
                .id("2")
                .photos(photos)
                .name("Bardolino Pizza")
                .rating(3.4)
                .reviewCount(79)
                .neighbourhood("East Harlem")
                .openTime(LocalTime.of(13,0,0))
                .closeTime(LocalTime.of(3, 0, 0))
                .tags(new String[] {"Pizza"})
                .build();

        Place place3 = Place.builder()
                .id("3")
                .photos(photos)
                .name("Valerie")
                .rating(4.3)
                .reviewCount(1000)
                .neighbourhood("Middletown West")
                .openTime(LocalTime.of(13,0,0))
                .closeTime(LocalTime.of(3, 0, 0))
                .tags(new String[] {"New American", "Cocktail Bars"})
                .build();

        places.add(place1);
        places.add(place2);
        places.add(place3);
    }

    @Override
    public Set<Place> getAll() {
        return this.places;
    }

    public Optional<Place> getOne(String placeId) {
        return this.places.stream().filter(p -> p.getId().equals(placeId)).findFirst();
    }

    @Override
    public Place save(Place place) {
        int id = places.stream().map(Place::getId).mapToInt(Integer::parseInt).max().orElse(0) + 1;
        place.setId(Integer.toString(id));
        places.add(place);
        return place;
    }
}
