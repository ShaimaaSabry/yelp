package shaimaa.yelp.domain;

import shaimaa.yelp.infrastructure.S3Service;
import org.springframework.stereotype.Service;

@Service
public class PhotoService {
//    private final PhotoRepository photoRepository;
    private final S3Service s3Service;

    PhotoService(
//            final PhotoRepository photoRepository,
            final S3Service s3Service
    ) {
//        this.photoRepository = photoRepository;
        this.s3Service = s3Service;
    }

    public Photo createOne(Photo photoCreateData) {
//        String s3Key = this.createS3Key(
//                photoCreateData.getPlace().getId(),
//                photoCreateData.getFileName()
//        );
//
//        photoCreateData.setS3Path(s3Key);
//        Photo photo = this.photoRepository.createOne(photoCreateData);
//        System.out.println("heree " + photo.getS3Path());
//
//        String uploadUrl = this.s3Service.generateUploadUrl(s3Key);
//        photo.setUrl(uploadUrl);
//
//        return photo;
        return null;
    }

    private String createS3Key(String placeId, String fileName) {
        return "place_" + placeId + "/" + fileName;
    }


}
