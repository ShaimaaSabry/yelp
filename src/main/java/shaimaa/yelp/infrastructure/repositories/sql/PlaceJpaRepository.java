package shaimaa.yelp.infrastructure.repositories.sql;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Repository
public interface PlaceJpaRepository extends CrudRepository<PlaceEntity, UUID> {
    @Query(
            value = """
        SELECT * FROM place
        WHERE (
            LOWER(name) LIKE LOWER(CONCAT('%', :query, '%')) OR
            LOWER(description) LIKE LOWER(CONCAT('%', :query, '%')) OR
            EXISTS (
                SELECT 1 FROM unnest(tags) AS tag
                WHERE LOWER(tag) LIKE LOWER(CONCAT('%', :query, '%'))
            )
        )
        AND ST_DWithin(location, ST_SetSRID(ST_MakePoint(:longitude, :latitude), 4326), :radiusInMeters)
        """,
            nativeQuery = true
    )
    List<PlaceEntity> searchByTextAndLocation(
            @Param("query") String query,
            @Param("latitude") double latitude,
            @Param("longitude") double longitude,
            @Param("radiusInMeters") double radiusInMeters
    );
}
