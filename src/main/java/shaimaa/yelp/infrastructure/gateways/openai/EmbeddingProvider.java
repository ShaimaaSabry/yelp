package shaimaa.yelp.infrastructure.gateways.openai;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;

@Component
public class EmbeddingProvider {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmbeddingProvider.class);

    private final WebClient webClient;
    private final String apiKey;

    EmbeddingProvider(OpenAIProperties openAIProperties) {
        webClient = WebClient.create(openAIProperties.getUrl());
        this.apiKey = openAIProperties.getApiKey();
    }

    public List<Double> embed(String text) {
        OpenAIEmbeddingResponse response = webClient
                .post()
                .uri("/v1/embeddings")
                .header("Authorization", "Bearer %s".formatted(apiKey))
                .bodyValue(
                        Map.of(
                                "input", text,
                                "model", "text-embedding-3-small"
                                )
                ).retrieve()
                .bodyToMono(OpenAIEmbeddingResponse.class)
                .doOnNext(r -> LOGGER.debug("OpenAI response: {}", r))
                .block();

        if (response == null || response.getData() == null || response.getData().isEmpty()) {
            throw new IllegalStateException("OpenAI returned no embedding data %s".formatted(response));
        }
        return response.getData().getFirst().embedding;
    }
}
