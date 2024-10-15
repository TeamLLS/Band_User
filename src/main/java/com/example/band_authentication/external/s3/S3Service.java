package com.example.band_authentication.external.s3;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.io.IOException;

@Service
public class S3Service {


    @Value("${spring.cloud.aws.s3.bucket}")
    private String bucket;

    @Value("${spring.cloud.aws.cdn.url}")
    private String production;

    private final S3Client s3Client;

    @Autowired
    public S3Service(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    public String saveImage(String group, String subject, MultipartFile image){
        try {

            if(image.isEmpty()){
                return null;
            }

            String imageKey = buildFileName(group, subject, image.getOriginalFilename());

            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucket)
                    .contentType(image.getContentType())
                    .contentLength(image.getSize())
                    .key(imageKey)
                    .build();
            RequestBody requestBody = RequestBody.fromBytes(image.getBytes());
            s3Client.putObject(putObjectRequest, requestBody);

            return imageKey;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    public ResponseInputStream<GetObjectResponse> loadImage(String imageKey){

        if(imageKey==null){
            return null;
        }

        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(bucket)
                .key(imageKey)
                .build();

        ResponseInputStream<GetObjectResponse> imageObject = s3Client.getObject(getObjectRequest);



        return imageObject;
    }

    public void deleteImage(String imagKey){

        if(imagKey==null){
            return;
        }

        DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
                .bucket(bucket)
                .key(imagKey)
                .build();

        s3Client.deleteObject(deleteObjectRequest);
    }

    public String getProduction(){
        return this.production;
    }


    private String buildFileName(String group, String subject, String originalFileName) {
        int fileExtensionIndex = originalFileName.lastIndexOf('.'); //파일 확장자 구분선
        String fileExtension = originalFileName.substring(fileExtensionIndex); //파일 확장자
        String now = String.valueOf(System.currentTimeMillis()); //파일 업로드 시간

        return group + "/" + subject + "_" + now + fileExtension;
    }
}
