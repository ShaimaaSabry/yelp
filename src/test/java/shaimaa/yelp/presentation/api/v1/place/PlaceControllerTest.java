package shaimaa.yelp.presentation.api.v1.place;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;
import shaimaa.yelp.application.commands.CreatePlace.CreatePlaceCommand;
import shaimaa.yelp.application.commands.CreatePlace.CreatePlaceHandler;
import shaimaa.yelp.application.queries.GetPlace.GetPlaceHandler;
import shaimaa.yelp.application.queries.GetPlaces.GetPlacesHandler;
import shaimaa.yelp.domain.Address;
import shaimaa.yelp.domain.Location;
import shaimaa.yelp.domain.Place;
import shaimaa.yelp.presentation.api.v1.Wrapper;
import shaimaa.yelp.presentation.api.v1.place.CreatePlace.CreatePlaceRequest;
import shaimaa.yelp.presentation.api.v1.place.dto.AddressDto;
import shaimaa.yelp.presentation.api.v1.place.dto.LocationDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import static org.hamcrest.Matchers.closeTo;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PlaceController.class)
@Import(PlaceControllerTest.MockBeans.class)
class PlaceControllerTest {
    @TestConfiguration
    static class MockBeans {

        @Bean
        public GetPlacesHandler getPlacesHandler() {
            return Mockito.mock(GetPlacesHandler.class);
        }

        @Bean
        public GetPlaceHandler getPlaceHandler() {
            return Mockito.mock(GetPlaceHandler.class);
        }

        @Bean
        public CreatePlaceHandler createPlaceHandler() {
            return Mockito.mock(CreatePlaceHandler.class);
        }
    }

    @Autowired
    private GetPlacesHandler mockGetPlacesHandler;

    @Autowired
    private GetPlaceHandler mockGetPlaceHandler;

    @Autowired
    private CreatePlaceHandler mockCreatePlaceHandler;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Nested
    class GetPlaces {

        @Test
        void givenLatitudeAndLongitude_thenReturn200AndPlaces() throws Exception {
            // GIVEN
            Place place = createValidPlace();

            Mockito.when(mockGetPlacesHandler.handle(Mockito.any())).thenReturn(List.of(place));

            // WHEN
            RequestBuilder request = MockMvcRequestBuilders
                    .get("/v1/places")
                    .param("latitude", "40.7279243469238")
                    .param("longitude", "-73.9851531982422");

            // THEN
            mockMvc.perform(request)
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.data").isArray())
                    .andExpect(jsonPath("$.data.length()").value(1))
                    .andExpect(jsonPath("$.data[0].id").value(place.getId()))
                    .andExpect(jsonPath("$.data[0].name").value(place.getName()))
                    .andExpect(jsonPath("$.data[0].description").value(place.getDescription()))
                    .andExpect(jsonPath("$.data[0].address").isNotEmpty())
                    .andExpect(jsonPath("$.data[0].address.street").value(place.getAddress().getStreet()))
                    .andExpect(jsonPath("$.data[0].address.city").value(place.getAddress().getCity()))
                    .andExpect(jsonPath("$.data[0].address.state").value(place.getAddress().getState()))
                    .andExpect(jsonPath("$.data[0].address.country").value(place.getAddress().getCountry()))
                    .andExpect(jsonPath("$.data[0].location").isNotEmpty())
                    .andExpect(jsonPath("$.data[0].location.latitude", closeTo(place.getLocation().getLatitude(), 0.0000001)))
                    .andExpect(jsonPath("$.data[0].location.longitude", closeTo(place.getLocation().getLongitude(), 0.0000001)))
                    .andExpect(jsonPath("$.data[0].tags").isArray())
                    .andExpect(jsonPath("$.data[0].tags.length()").value(place.getTags().length))
                    .andExpect(jsonPath("$.data[0].tags[0]").value(place.getTags()[0]));
        }
    }

    @Nested
    class GetPlace {

        @Test
        void givenPlaceId_thenReturn200AndPlace() throws Exception {
            // GIVEN
            Place place = createValidPlace();

            Mockito.when(mockGetPlaceHandler.handle(Mockito.any())).thenReturn(place);

            // WHEN
            RequestBuilder request = MockMvcRequestBuilders.get("/v1/places/{placeId}", place.getId());

            // THEN
            mockMvc.perform(request)
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id").value(place.getId()))
                    .andExpect(jsonPath("$.name").value(place.getName()))
                    .andExpect(jsonPath("$.description").value(place.getDescription()))
                    .andExpect(jsonPath("$.address").isNotEmpty())
                    .andExpect(jsonPath("$.address.street").value(place.getAddress().getStreet()))
                    .andExpect(jsonPath("$.address.city").value(place.getAddress().getCity()))
                    .andExpect(jsonPath("$.address.state").value(place.getAddress().getState()))
                    .andExpect(jsonPath("$.address.country").value(place.getAddress().getCountry()))
                    .andExpect(jsonPath("$.location").isNotEmpty())
                    .andExpect(jsonPath("$.location.latitude", closeTo(place.getLocation().getLatitude(), 0.0000001)))
                    .andExpect(jsonPath("$.location.longitude", closeTo(place.getLocation().getLongitude(), 0.0000001)))
                    .andExpect(jsonPath("$.tags").isNotEmpty())
                    .andExpect(jsonPath("$.tags").isArray())
                    .andExpect(jsonPath("$.tags.length()").value(place.getTags().length))
                    .andExpect(jsonPath("$.tags[0]").value(place.getTags()[0]));

        }
    }

    @Nested
    class CreatePlace {

        @Test
        void givenValidRequest_thenReturn201AndCreatePlace() throws Exception {
            // GIVEN
            Place place = createValidPlace();

            Mockito.when(mockCreatePlaceHandler.handle(Mockito.any(CreatePlaceCommand.class)))
                    .thenReturn(place);

            // WHEN
            Map<String, Object> requestBody = Map.of(
                    "name", place.getName(),
                    "description", place.getDescription(),
                    "address", Map.of(
                            "street", place.getAddress().getStreet(),
                            "city", place.getAddress().getCity(),
                            "state", place.getAddress().getState(),
                            "country", place.getAddress().getCountry()
                    ),
                    "location", Map.of(
                            "latitude", place.getLocation().getLatitude(),
                            "longitude", place.getLocation().getLongitude()
                    ),
                    "tags", place.getTags()
            );

            RequestBuilder request = MockMvcRequestBuilders.post("/v1/places")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(requestBody));

            // THEN
            mockMvc.perform(request)
                    .andDo(print())
                    .andExpect(status().isCreated())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.name").value(place.getName()))
                    .andExpect(jsonPath("$.description").value(place.getDescription()))
                    .andExpect(jsonPath("$.address").isNotEmpty())
                    .andExpect(jsonPath("$.address.street").value(place.getAddress().getStreet()))
                    .andExpect(jsonPath("$.address.city").value(place.getAddress().getCity()))
                    .andExpect(jsonPath("$.address.state").value(place.getAddress().getState()))
                    .andExpect(jsonPath("$.address.country").value(place.getAddress().getCountry()))
                    .andExpect(jsonPath("$.location").isNotEmpty())
                    .andExpect(jsonPath("$.location.latitude", closeTo(place.getLocation().getLatitude(), 0.0000001)))
                    .andExpect(jsonPath("$.location.longitude", closeTo(place.getLocation().getLongitude(), 0.0000001)))
                    .andExpect(jsonPath("$.tags").isNotEmpty())
                    .andExpect(jsonPath("$.tags").isArray())
                    .andExpect(jsonPath("$.tags.length()").value(place.getTags().length))
                    .andExpect(jsonPath("$.tags[0]").value(place.getTags()[0]));

            Mockito.verify(mockCreatePlaceHandler).handle(Mockito.any(CreatePlaceCommand.class));
        }
    }

    private Place createValidPlace() {
        String placeId = UUID.randomUUID().toString();
        return Place.builder()
                .id(placeId)
                .name("East Village Pizza")
                .description("This East Village spot offers crispy thin-crust pepperoni and buffalo chicken slices.")
                .address(
                        Address.builder()
                                .street("145 1st Ave")
                                .city("East Village")
                                .state("New York")
                                .country("USA")
                                .build()
                )
                .location(
                        Location.builder()
                                .latitude(40.7279243469238f)
                                .longitude(-73.9851531982422f)
                                .build()
                )
                .tags(new String[]{"Pizza"})
                .build();
    }
}