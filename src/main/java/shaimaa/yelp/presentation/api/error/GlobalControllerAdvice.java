package shaimaa.yelp.presentation.api.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalControllerAdvice {
    private static final Logger LOG = LoggerFactory.getLogger(GlobalControllerAdvice.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    ErrorResponse handle400ValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.toMap(
                        FieldError::getField,
                        error -> error.getDefaultMessage() != null ? error.getDefaultMessage() : "invalid value"
                ));

        String errorMessage = (errors.size() == 1)
                ? String.format("%s %s",
                errors.entrySet().iterator().next().getKey(),
                errors.entrySet().iterator().next().getValue())
                : "Validation errors";

        return new ErrorResponse(errorMessage, errors);
    }

    @ExceptionHandler(WebClientResponseException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    ErrorResponse handle500WebClientResponseException(WebClientResponseException e) {
        LOG.error("Error calling external API: {} | {}", e.getStatusCode(), e.getResponseBodyAsString());

        String errorMessage = e.getResponseBodyAsString();

        return new ErrorResponse(errorMessage);
    }
}
