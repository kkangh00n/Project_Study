package com.Project.BoardService.service.file;

import com.Project.BoardService.domain.entity.post.UploadFile;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class FileStore {

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    private final AmazonS3Client amazonS3Client;

    public List<UploadFile> uploadImages(List<MultipartFile> images){
        return images.stream().map(this::uploadImage).collect(Collectors.toList());
    }

    public UploadFile uploadImage(MultipartFile image){

        UploadFile uploadImage = UploadFile.of(image);

        try{
            ObjectMetadata metadata= new ObjectMetadata();
            metadata.setContentType(image.getContentType());
            metadata.setContentLength(image.getSize());
            amazonS3Client.putObject(bucket, uploadImage.getStoreName(),image.getInputStream(),metadata);

            return uploadImage;
        }catch(IOException e){
            return null;
        }
    }


}
