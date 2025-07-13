package shaimaa.yelp.infrastructure.repositories.qdrant;

import shaimaa.yelp.domain.Place;

import java.util.List;
import java.util.UUID;

public record QdrantSearchResponse(
        List<QdrantSearchResult> result
) {

    static record QdrantSearchResult(
            String id,
       QdrantPlacePayload payload
    ) {
        Place toPlace() {
            return Place.of(
                    UUID.fromString(this.id),
                    payload.name(),
                    payload.description(),
                    payload.address().toDomain(),
                    payload.location().toDomain(),
                    payload.tags()
            );
        }
    }
}
