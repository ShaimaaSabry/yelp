//package com.shaimaa.yelp.repository;
//
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import com.shaimaa.yelp.domain.Location;
//import com.shaimaa.yelp.domain.Photo;
//import com.shaimaa.yelp.domain.Place;
//
//import javax.persistence.*;
//
//@NoArgsConstructor
//@Getter
//@Setter
//@Entity
//@Table(name = "photos")
//public class PhotoEntity {
//    @Id
//    private String id;
//
//    @ManyToOne
//    @JoinColumn(name = "place_id")
//    private PlaceEntity place;
//
//    @Column(name = "s3_path")
//    private String s3Path;
//
//    static PhotoEntity from (Photo photo) {
//        PhotoEntity photoEntity = new PhotoEntity();
//        photoEntity.place = new PlaceEntity(photo.getPlace().getId());
//        photoEntity.s3Path = photo.getS3Path();
//        return photoEntity;
//    }
//
//    Photo to() {
//        Photo photo = new Photo();
//        photo.setId(this.id);
//        Place place = new Place();
//        place.setId(this.getPlace().getId());
//        place.setName(this.getPlace().getName());
//        place.setDescription(this.getPlace().getDescription());
//        place.setLocation(new Location(this.getPlace().getLatitude(), this.getPlace().getLongitude()));
//        photo.setPlace(place);
//        photo.setS3Path(this.s3Path);
//        return photo;
//    }
//}
