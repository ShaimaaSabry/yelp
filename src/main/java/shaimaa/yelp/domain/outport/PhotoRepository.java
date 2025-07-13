package shaimaa.yelp.domain.outport;

import shaimaa.yelp.domain.Photo;

public interface PhotoRepository {
    Photo createOne(Photo photo);
}
