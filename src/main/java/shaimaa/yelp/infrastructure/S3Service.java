package shaimaa.yelp.infrastructure;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.HttpMethod;
import java.net.URL;

@Component
public class S3Service {
    private final String bucketName;
    private final AmazonS3 s3Client;

    public S3Service (
//            final AmazonS3 amasonS3
    ) {
        this.bucketName = "yelp";

        BasicAWSCredentials credentials = new BasicAWSCredentials(
                "localstack",
                "localstack"
        );

        AwsClientBuilder.EndpointConfiguration x =new EndpointConfiguration(
                "http://127.0.0.1:4566", "eu-west-1");

        this.s3Client = AmazonS3ClientBuilder
                .standard()
                .withEndpointConfiguration(x)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
//                .withRegion(Regions.EU_WEST_1)
                .build();
    }

    public void create() {
//        PutObjectRequest request = PutObjectRequest.builder()
//                .bucket(bucketName)
//                .key(key)
//                .build();

        byte[] content = "hi".getBytes();
        InputStream x = new ByteArrayInputStream(content);
        s3Client.putObject("yelp", "x", x, null);
    }

    // curl -v --upload-file ${s3Key} ${location}
    public String generateUploadUrl(String s3Key) {
        java.util.Date expiration = new java.util.Date();
        long expTimeMillis = expiration.getTime();
        expTimeMillis += 1000 * 60 * 10;
        expiration.setTime(expTimeMillis);

        GeneratePresignedUrlRequest generatePresignedUrlRequest =
            new GeneratePresignedUrlRequest(this.bucketName, s3Key)
                    .withMethod(HttpMethod.POST)
                    .withExpiration(expiration);

        URL url = this.s3Client.generatePresignedUrl(generatePresignedUrlRequest);
        return url.toString();
    }
}
