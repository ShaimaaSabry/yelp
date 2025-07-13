package shaimaa.yelp.domain;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Photo {
    private String id;

    private Place place;

    private String fileName;

    private String s3Path;

    private String url;

    public Photo(Place place, String fileName) {
        this.place = place;
        this.fileName = fileName;
    }
}
