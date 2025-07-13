package shaimaa.yelp.infrastructure.gateways.openai;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "gateways.openai")
public class OpenAIProperties {
    private String url;
    private String apiKey;
}
