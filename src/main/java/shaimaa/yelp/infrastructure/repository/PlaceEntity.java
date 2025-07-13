//package com.shaimaa.yelp.repository;
//
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import com.shaimaa.yelp.domain.Location;
//import com.shaimaa.yelp.domain.Place;
//
//import javax.persistence.*;
//import java.util.Set;
//
//@Data
//@NoArgsConstructor
//@Entity
//@Table(name = "places")
//public class PlaceEntity {
//    @Id
//    private String id;
//
//    private String name;
//
//    private String description;
//
//    private double latitude;
//
//    private double longitude;
//
//    @OneToMany(mappedBy = "place")
//    private Set<PhotoEntity> photos;
//
//    PlaceEntity(String id) {
//        this.id = id;
//    }
//
//    static PlaceEntity from(Place place) {
//        PlaceEntity placeEntity = new PlaceEntity();
//        placeEntity.name = place.getName();
//        placeEntity.description = place.getDescription();
//        if (place.getLocation() != null) {
//            placeEntity.latitude = place.getLocation().getLatitude();
//            placeEntity.longitude = place.getLocation().getLongitude();
//        }
//        return placeEntity;
//    }
//
//    Place to() {
//        Place place = new Place();
//        place.setId(this.id);
//        place.setName(this.name);
//        place.setDescription(this.description);
//        place.setLocation(new Location(
//                this.latitude,
//                this.longitude
//        ));
////        if (this.photos != null) {
////            place.setPhotos(
////                    this.photos.stream().map(p -> p.to()).collect(Collectors.toSet())
////            );
////        }
//        return place;
//    }
//}
