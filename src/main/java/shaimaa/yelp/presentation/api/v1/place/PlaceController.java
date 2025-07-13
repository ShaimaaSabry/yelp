package shaimaa.yelp.presentation.api.v1.place;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.tags.Tag;
import shaimaa.yelp.application.commands.CreatePlace.CreatePlaceHandler;
import shaimaa.yelp.application.queries.GetPlace.GetPlaceHandler;
import shaimaa.yelp.application.queries.GetPlaces.GetPlacesHandler;
import shaimaa.yelp.application.queries.GetPlaces.GetPlacesQuery;
import shaimaa.yelp.domain.Location;
import shaimaa.yelp.domain.Place;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import shaimaa.yelp.presentation.api.v1.Wrapper;
import shaimaa.yelp.presentation.api.v1.place.CreatePlace.CreatePlaceRequest;
import shaimaa.yelp.presentation.api.v1.place.CreatePlace.CreatePlaceResponse;
import shaimaa.yelp.presentation.api.v1.place.GetPlace.PlaceItemDto;

//import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("v1/places")
@Tag(name = "Places", description = "APIs for managing places.")
class PlaceController {
    private final GetPlacesHandler getPlacesHandler;
    private final GetPlaceHandler getPlaceHandler;
    private final CreatePlaceHandler createPlaceHandler;

    public PlaceController(
            final GetPlacesHandler getPlacesHandler,
            final GetPlaceHandler getPlaceHandler,
            final CreatePlaceHandler createPlaceHandler
    ) {
        this.getPlacesHandler = getPlacesHandler;
        this.getPlaceHandler = getPlaceHandler;
        this.createPlaceHandler = createPlaceHandler;
    }

    @GetMapping
    Wrapper<List<PlaceItemDto>> searchPlaces(
            @RequestParam(required = true) Float latitude,
            @RequestParam(required = true) Float longitude,
            String query
    ) throws JsonProcessingException {
        List<Place> places = this.getPlacesHandler.handle(
                new GetPlacesQuery(
                        query,
                        new GetPlacesQuery.Location(latitude, longitude
                )
        ));

        List<PlaceItemDto> responseData = places
                .stream()
                .map(PlaceItemDto::of)
                .toList();

        return new Wrapper<>(responseData);
    }

    @GetMapping("{placeId}")
    PlaceItemDto getPlace(@PathVariable UUID placeId) {
        Place place = this.getPlaceHandler.handle(placeId);

        return PlaceItemDto.of(place);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    CreatePlaceResponse createPlace(
            @RequestBody @Valid CreatePlaceRequest request
    ) {
        Place place = this.createPlaceHandler.handle(
                request.toCommand()
        );

        return CreatePlaceResponse.of(place);
    }
}
