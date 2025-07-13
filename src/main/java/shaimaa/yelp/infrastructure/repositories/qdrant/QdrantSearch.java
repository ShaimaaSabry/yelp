package shaimaa.yelp.infrastructure.repositories.qdrant;

import java.util.List;

public record QdrantSearch(
        String name,
        List<Double> vector,
        int limit
) {}
