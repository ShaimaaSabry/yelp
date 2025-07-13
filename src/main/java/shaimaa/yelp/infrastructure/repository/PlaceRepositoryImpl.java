//package com.shaimaa.yelp.repository;
//
//import com.shaimaa.yelp.domain.Place;
//import com.shaimaa.yelp.domain.outport.PlaceRepository;
//
//import java.util.Optional;
//import java.util.Set;
//import java.util.UUID;
//import java.util.stream.Collectors;
//
////@Component
//public class PlaceRepositoryImpl implements PlaceRepository {
//    private final PlaceSqlRepository placeSqlRepository;
//
//    PlaceRepositoryImpl(
//            final PlaceSqlRepository placeSqlRepository
//    ) {
//        this.placeSqlRepository = placeSqlRepository;
//    }
//
//    @Override
//    public Set<Place> getAll() {
//        Set<PlaceEntity> places = this.placeSqlRepository.findAll();
//
//        return places
//                .stream()
//                .map(p -> p.to())
//                .collect(Collectors.toSet());
//    }
//
//    @Override
//    public Optional<Place> getOne(String placeId) {
//        return Optional.empty();
//    }
//
//    @Override
//    public Place save(Place place) {
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
//    }
//}
