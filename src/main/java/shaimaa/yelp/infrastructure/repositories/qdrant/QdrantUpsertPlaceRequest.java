package shaimaa.yelp.infrastructure.repositories.qdrant;

public record QdrantUpsertPlaceRequest(
        QdrantUpsertPlacePoint[] points
) {
}
