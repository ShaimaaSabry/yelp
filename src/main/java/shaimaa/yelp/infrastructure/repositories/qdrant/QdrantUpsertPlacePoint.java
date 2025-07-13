package shaimaa.yelp.infrastructure.repositories.qdrant;

import java.util.List;
import java.util.Map;

public record QdrantUpsertPlacePoint(
        String id,
        Map<String, List<Double>> vector,
        QdrantPlacePayload payload
) {
}
