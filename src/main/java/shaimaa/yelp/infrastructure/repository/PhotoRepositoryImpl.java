//package com.shaimaa.yelp.repository;
//
//import com.shaimaa.yelp.domain.Photo;
//import com.shaimaa.yelp.domain.outport.PhotoRepository;
//import org.springframework.stereotype.Component;
//
//import java.util.UUID;
//
//@Component
//public class PhotoRepositoryImpl implements PhotoRepository {
////    private final PhotoSqlRepository photoSqlRepository;
//
////    PhotoRepositoryImpl(
////            final PhotoSqlRepository photoSqlRepository
////    ) {
////        this.photoSqlRepository = photoSqlRepository;
////    }
//
//    @Override
//    public Photo createOne(Photo photo) {
//        PhotoEntity photoEntity = PhotoEntity.from(photo);
//        UUID uuid = UUID.randomUUID();
//        photoEntity.setId(uuid.toString());
//
////        photoEntity = this.photoSqlRepository.save(photoEntity);
//
//        return photoEntity.to();
//    }
//}
