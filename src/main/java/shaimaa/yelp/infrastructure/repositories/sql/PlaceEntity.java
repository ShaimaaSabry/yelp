package shaimaa.yelp.infrastructure.repositories.sql;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.locationtech.jts.geom.Point;
import shaimaa.yelp.domain.Address;
import shaimaa.yelp.domain.Location;
import shaimaa.yelp.domain.Place;

import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "place")
public class PlaceEntity {
    @Id
    private UUID id;

    //    @OneToMany(mappedBy = "place")
//    private Set<PhotoEntity> photos;

    private String name;

    private String description;

    @JdbcTypeCode(SqlTypes.JSON)
    private Address address;

    private Point location;  // JTS Point for PostGIS

    @JdbcTypeCode(SqlTypes.ARRAY)
    private Set<String> tags;

//    PlaceEntity(String id) {
//        this.id = id;
//    }

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
    Place to() {
        return Place.of(
                this.id,
                this.name,
                this.description,
                this.address,
                Location.create(location.getY(), location.getX()),
                this.tags
        );
    }
}
