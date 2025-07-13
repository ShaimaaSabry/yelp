package shaimaa.yelp.infrastructure.repositories.sql;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import shaimaa.yelp.application.contracts.PlaceRepository;
import shaimaa.yelp.domain.Location;
import shaimaa.yelp.domain.Place;

import java.util.List;
import java.util.Optional;

@Primary
@Component
public class PlaceSqlRepositoryImpl implements PlaceRepository {
    private final PlaceJpaRepository placeJpaRepository;

    public PlaceSqlRepositoryImpl(
            final PlaceJpaRepository placeJpaRepository
    ) {
        this.placeJpaRepository = placeJpaRepository;
    }

    @Override
    public List<Place> search(String query, Location location, double radius) {
        List<PlaceEntity> placeEntities = placeJpaRepository.searchByTextAndLocation(
                query, location.getLatitude(), location.getLatitude(), radius
        );

        return placeEntities
                .stream()
                .map(PlaceEntity::to)
                .toList();
    }

    @Override
    public Optional<Place> getPlace(String placeId) {
        return Optional.empty();
    }

    @Override
    public Place createPlace(Place place) {
        return null;
//        System.out.println("heree1 " + place.getName());
//        PlaceEntity placeEntity = PlaceEntity.from(place);
//
//        if (placeEntity.getId() == null) {
//            UUID uuid = UUID.randomUUID();
//            placeEntity.setId(uuid.toString());
//        }
//
//        this.placeSqlRepository.save(placeEntity);
//
//        return placeEntity.to();
    }
}
