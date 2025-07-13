package shaimaa.yelp.presentation.api.v1;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import shaimaa.yelp.domain.Photo;
import shaimaa.yelp.presentation.api.v1.place.CreatePlace.CreatePlaceResponse;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PhotoResponse {
    private String id;

    private CreatePlaceResponse place;

    private String s3Path;

    private String url;

    static PhotoResponse from (Photo photo) {
        return new PhotoResponse(
                photo.getId(),
                null,
                null,
                photo.getUrl()
        );
    }
}
