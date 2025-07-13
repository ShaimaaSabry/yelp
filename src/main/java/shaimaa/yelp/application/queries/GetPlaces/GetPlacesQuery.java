package shaimaa.yelp.application.queries.GetPlaces;

public record GetPlacesQuery(
        String query,
        Location location
) {
    public record Location(double latitude, double longitude) {}
}
