package shaimaa.yelp.domain;

import java.util.HashSet;
import java.util.Set;

class QuadTreeNode {
//    private static int MAX_PLACES_PER_NODE = 12;
//    float minLatitude, maxLatitude, minLongitude, maxLongitude;
//    Point mid;
//
//    QuadTreeNode northEast;
//    QuadTreeNode southEast;
//    QuadTreeNode southWest;
//    QuadTreeNode northWest;
//    Set<Place> places = new HashSet<>();
//    QuadTreeNode(
//            final float minLatitude, final float maxLatitude,
//            final float minLongitude, final float maxLongitude) {
//        this.minLatitude = minLatitude;
//        this.maxLatitude = maxLatitude;
//        this.minLongitude = minLongitude;
//        this.maxLongitude = maxLongitude;
//        float midLatitude = (minLatitude + maxLatitude) / 2;
//        float midLongitude = (minLongitude + maxLongitude) / 2;
//        this.mid = new Point(midLatitude, midLongitude);
//    }
//    void addPlace(Place place) {
//        if(this.isLeafNode()) {
//            this.places.add(place);
//            if (places.size() >= MAX_PLACES_PER_NODE) {
//                this.divide();
//            }
//            return;
//        }
//
//        QuadTreeNode childNode = findChildNode(place.location);
//        childNode.addPlace(place);
//    }
//     Set<Place> find(Point location) {
//        if (this.isLeafNode()) {
//            return this.places;
//        }
//
//        QuadTreeNode childNode = findChildNode(location);
//        return childNode.find(location);
//     }
//
//     private boolean isLeafNode() {
//        return places != null;
//     }
//
//    private void divide() {
//        this.northEast = new QuadTreeNode(
//                this.minLatitude,
//                this.mid.latitude,
//                this.minLongitude,
//                this.mid.longitude);
//
//        this.southEast = new QuadTreeNode(
//                this.minLatitude,
//                this.mid.latitude,
//                this.mid.longitude,
//                this.maxLongitude);
//
//        this.southWest = new QuadTreeNode(
//                this.mid.latitude,
//                this.maxLatitude,
//                this.mid.longitude,
//                this.maxLongitude);
//
//        this.northWest = new QuadTreeNode(
//                this.mid.latitude,
//                this.maxLatitude,
//                this.minLongitude,
//                this.mid.longitude);
//
//        for(Place place : this.places) {
//            QuadTreeNode childNode = findChildNode(place.location);
//            childNode.places.add(place);
//        }
//
//        this.places = null;
//    }
//
//    private QuadTreeNode findChildNode(Point location) {
//        if (isNorthEast(mid, location)) {
//            return this.northEast;
//        } else if(isSouthEast(mid, location)) {
//            return this.southEast;
//        } else if (isSouthWest(mid, location)) {
//            return this.southWest;
//        } else if (isNorthWest(mid, location)) {
//            return this.northWest;
//        }
//        return null;
//    }
//
//    private boolean isNorthEast(Point center, Point point) {
//        return point.latitude <= center.latitude && point.longitude <= center.longitude;
//    }
//
//    private boolean isSouthEast(Point center, Point point) {
//        return point.latitude <= center.latitude && point.longitude > center.longitude;
//    }
//
//    private boolean isSouthWest(Point center, Point point) {
//        return point.latitude > center.latitude && point.longitude > center.longitude;
//    }
//
//    private boolean isNorthWest(Point center, Point point) {
//        return point.latitude > center.latitude && point.longitude <= center.longitude;
//    }

}
