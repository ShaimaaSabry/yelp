package shaimaa.yelp.infrastructure.repositories.qdrant;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import shaimaa.yelp.application.contracts.PlaceRepository;
import shaimaa.yelp.domain.Location;
import shaimaa.yelp.domain.Place;
import shaimaa.yelp.infrastructure.gateways.openai.EmbeddingProvider;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

//@Primary
@Component
public class PlaceQdrantRepository implements PlaceRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(PlaceQdrantRepository.class);
    private static final String COLLECTION_NAME = "places";

    private final EmbeddingProvider embeddingProvider;
    private final WebClient webClient;

    PlaceQdrantRepository(final EmbeddingProvider embeddingProvider) {
        this.embeddingProvider = embeddingProvider;

        this.webClient = WebClient.builder()
                .baseUrl("http://localhost:6333")
                .build();
    }

    @Override
    public Optional<Place> getPlace(String placeId) {
        return Optional.empty();
    }

    @Override
    public Place createPlace(Place place) {
        String placeId = UUID.randomUUID().toString();

        QdrantPlacePayload payload = QdrantPlacePayload.from(place);

        String text = """
                Name: %s.
                Description: %s.
                Tags: %s.
                """.formatted(
                place.getName(),
                place.getDescription(),
                String.join(", ", place.getTags())
        );
        List<Double> textVector = embeddingProvider.embed(text);
        LOGGER.debug("Text vector {}", textVector);

        List<Double> locationVector = List.of(
                place.getLocation().getLatitude(),
                place.getLocation().getLongitude()
        );
        LOGGER.debug("Location vector {}", locationVector);

        Map<String, List<Double>> namedVectors = Map.of(
                "content", textVector,
                "location", locationVector
        );

        QdrantUpsertPlacePoint point = new QdrantUpsertPlacePoint(
                placeId,
                namedVectors,
                payload
        );

        QdrantUpsertPlaceRequest request = new QdrantUpsertPlaceRequest(
                new QdrantUpsertPlacePoint[]{point}
        );

        webClient
                .put()
                .uri("/collections/{collection}/points", COLLECTION_NAME)
                .bodyValue(request)
                .retrieve()
                .bodyToMono(String.class)
                .doOnNext(response -> LOGGER.debug("Qdrant upsert response: {}", response))
                .doOnSuccess(v -> LOGGER.debug("Upserted place: {}", placeId))
                .doOnError(e -> LOGGER.error("Failed to upsert place into Qdrant", e))
                .block();

        return null;
    }

    @Override
    public List<Place> search(String query, Location location, double radius) throws JsonProcessingException {
        // Embed the user's query
        List<Double> contentVector = embeddingProvider.embed(query);
        LOGGER.debug("Text vector {}", contentVector);

        // Build the location vector
        List<Double> locationVector = List.of(
                location.getLatitude(),
                location.getLongitude()
        );
        LOGGER.debug("Location vector {}", locationVector);

        // Build search body
        QdrantSearchRequest request = new QdrantSearchRequest(List.of(
                new QdrantSearch("content", contentVector, 10),
                new QdrantSearch("location", locationVector, 10)
        ));

//        LOGGER.debug("Final Qdrant search request:\n{}", request);

//        QdrantSearchResponse response = webClient
//                .post()
//                .uri("/collections/{collection}/points/multi_search", COLLECTION_NAME)
//                .bodyValue(request)
//                .retrieve()
//                .bodyToMono(QdrantSearchResponse.class)
//                .block();

        Map<String, Object> contentRequest = Map.of(
//                "vector", contentVector,
//                "vector_name", "content",
                "vector", Map.of(
            "vector", contentVector, // Your query vector
            "name", "content"    // The name of the vector to search against
        ),
                "limit", 10,
                "with_payload", true,
                "with_vector", false
        );

        ObjectMapper mapper = new ObjectMapper();
        LOGGER.debug("Content search JSON:\n{}", mapper.writerWithDefaultPrettyPrinter().writeValueAsString(contentRequest));

        QdrantSearchResponse contentResults = webClient.post()
                .uri("/collections/{collection}/points/search", COLLECTION_NAME)
                .bodyValue(contentRequest)
                .retrieve()
                .bodyToMono(QdrantSearchResponse.class)
                .block();

        Map<String, Object> locationRequest = Map.of(
//                "vector", locationVector,
//                "vector_name", "location",          // ✅ required
                "vector", Map.of(
                        "vector", locationVector, // Your query vector
                        "name", "location"    // The name of the vector to search against
                ),
                "limit", 10,
                "with_payload", true,
                "with_vector", false
        );

        LOGGER.debug("Location search JSON:\n{}", mapper.writerWithDefaultPrettyPrinter().writeValueAsString(locationRequest));

        QdrantSearchResponse locationResults = webClient.post()
                .uri("/collections/{collection}/points/search", COLLECTION_NAME)
                .bodyValue(locationRequest)
                .retrieve()
                .bodyToMono(QdrantSearchResponse.class)
                .block();

//        if (response == null || response.result() == null) {
//            throw new IllegalStateException("Qdrant empty search response");
//        }

        return Stream.concat(
                contentResults.result().stream(),
                locationResults.result().stream()
        ).distinct().map(QdrantSearchResponse.QdrantSearchResult::toPlace).toList();
    }
}
