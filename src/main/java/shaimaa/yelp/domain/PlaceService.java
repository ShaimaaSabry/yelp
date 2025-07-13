package shaimaa.yelp.domain;

import shaimaa.yelp.domain.outport.PlaceRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

//@Service
public class PlaceService {
    private static float MIN_LATITUDE = 0, MAX_LATITUDE = 1000, MIN_LONGITUDE = 0, MAX_LONGITUDE = 100;
    private QuadTreeNode quadTree;
    private PlaceRepository placeRepository;

    PlaceService(
            final PlaceRepository placeRepository
    ) {
        this.placeRepository = placeRepository;
    }

    public Set<Place> getAll(
//            Location location
    ) {
//        Set<Place> places = quadTree.find(location);
//        return places;
        Set<Place> places = this.placeRepository.getAll();

//        places.stream().forEach(
//                x -> x.getPhotos().stream().forEach(p -> p.setUrl(this.createUrl(p.getS3Path())))
//        );

        return places;
    }

    public Place getOne(String placeId) {
        return this.placeRepository.getOne(placeId).get();
    }

    public String createUrl(String s3Key) {
        return "https://<eu-west-2.127.0.0.1:456/<yelp/" + s3Key;
    }

    public Place createOne(Place place) {
        return this.placeRepository.save(place);
    }

//    private void constructQuadTree() {
//        quadTree = new QuadTreeNode(MIN_LATITUDE, MAX_LATITUDE, MIN_LONGITUDE, MAX_LONGITUDE);
//
//        Set<Place> places = placeRepository.findAll();
//        for (Place place : places) {
//            quadTree.addPlace(place);
//        }
//    }
}
