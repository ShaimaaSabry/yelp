package shaimaa.yelp.infrastructure.repository;

import shaimaa.yelp.domain.Place;
import shaimaa.yelp.domain.outport.PlaceRepository;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.jooq.Record;

//import my.package_.name.generated.jooq.tables.Place.PLACE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

//@Component
public class PlaceJooqRepository implements PlaceRepository {
    private Connection connection;

    @Autowired
    private DSLContext context;

//    PlaceJooqRepository() throws SQLException {
//        String url = "jdbc:postgresql://localhost:4444/yelp_local";
//        String userName = "postgres";
//        String password = "postgres";
//        this.connection = DriverManager.getConnection(url, userName, password);
//        this.context = DSL.using(this.connection, SQLDialect.POSTGRES);
//    }

    @Override
    public Set<Place> getAll() {
        Result<Record> placeEntities = context.select()
//                .from(my.package_.name.generated.jooq.tables.Place.PLACE)
                .from("place")
                .fetch();

        Set<Place> places = new HashSet<>();
        for(Record placeEntity : placeEntities) {
            Place place = Place.builder()
//                    .id(placeEntity.getValue(my.package_.name.generated.jooq.tables.Place.PLACE.ID))
                    .id((String) placeEntity.getValue("id"))
//                    .name(placeEntity.getValue(my.package_.name.generated.jooq.tables.Place.PLACE.NAME))
                    .name((String) placeEntity.getValue("name"))
                    .build();

            places.add(place);
        }
        return places;
    }

    @Override
    public Optional<Place> getOne(String placeId) {
        return Optional.empty();
    }

    @Override
    public Place save(Place place) {
        return null;
    }
}
