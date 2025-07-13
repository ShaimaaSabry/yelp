package shaimaa.yelp.presentation.api.error;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

public record ErrorResponse (
    String error,
    Map<String, String> errors
) {
    public ErrorResponse(String error) {
        this(error, Collections.emptyMap());
    }
}
