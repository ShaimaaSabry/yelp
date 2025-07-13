package shaimaa.yelp.infrastructure.repositories.qdrant;

import java.util.List;

public record QdrantSearchRequest(
        List<QdrantSearch> search
) {

}


