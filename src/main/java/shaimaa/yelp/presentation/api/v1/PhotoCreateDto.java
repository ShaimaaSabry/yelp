//package shaimaa.yelp.presentation.api.v1;
//
//import jakarta.validation.Valid;
//import jakarta.validation.constraints.NotBlank;
//import jakarta.validation.constraints.NotNull;
//import lombok.Data;
//import shaimaa.yelp.domain.Photo;
//import shaimaa.yelp.domain.Place;
//
////import javax.validation.Valid;
////import javax.validation.constraints.NotBlank;
////import javax.validation.constraints.NotNull;
//
//@Data
//public class PhotoCreateDto {
//    @NotNull
//    @Valid
//    private PhotoPlaceDto place;
//
//    @NotBlank(message = "File name is required.")
//    private String fileName;
//
//    Photo to() {
//        return new Photo(
//          new Place(place.getId()),
//          this.fileName
//        );
//    }
//
//    @Data
//    class PhotoPlaceDto {
//        @NotBlank(message = "Place id is required.")
//        private String id;
//    }
//}
