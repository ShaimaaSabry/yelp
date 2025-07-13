package shaimaa.yelp.infrastructure.gateways.openai;

import lombok.Data;

import java.util.List;

@Data
public class OpenAIEmbeddingResponse {
    List<EmbeddingData> data;

    @Data
    static class EmbeddingData {
        List<Double> embedding;
    }
}
