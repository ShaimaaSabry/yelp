package shaimaa.yelp.presentation.api.error;

import java.util.Map;

public record ErrorResponse (
    String error,
    Map<String, String> errors
) {}
