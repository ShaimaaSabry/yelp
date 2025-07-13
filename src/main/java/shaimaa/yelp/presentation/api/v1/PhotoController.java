//package shaimaa.yelp.presentation.api.v1;
//
//import shaimaa.yelp.domain.Photo;
//import shaimaa.yelp.domain.PhotoService;
//import shaimaa.yelp.domain.Place;
//import jakarta.validation.Valid;
//import org.springframework.web.bind.annotation.*;
//
////import javax.validation.Valid;
//
//@RestController
//@RequestMapping("photos")
//public class PhotoController {
//    private final PhotoService photoService;
//
//    PhotoController(
//            final PhotoService photoService
//    ) {
//        this.photoService = photoService;
//    }
//
//    @PostMapping
//    Wrapper<PhotoResponse> createOne(@RequestBody @Valid Wrapper<PhotoCreateDto> requestWrapper) {
//        PhotoCreateDto requestData = requestWrapper.getData();
//
////        Photo photo = requestData.to();
//        Photo photo = Photo.builder()
//                .place(
//                        Place.builder()
//                                .id(requestData.getPlace().getId())
//                                .build()
//                )
//                .fileName(requestData.getFileName())
//                .build();
//        photo = this.photoService.createOne(photo);
//
//        PhotoResponse responseData = PhotoResponse.from(photo);
//        return new Wrapper(responseData);
//    }
//}
