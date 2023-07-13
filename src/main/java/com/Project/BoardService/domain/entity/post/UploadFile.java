package com.Project.BoardService.domain.entity.post;

import jakarta.persistence.Embeddable;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Embeddable
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UploadFile {

    //고객이 업로드한 파일명
    private String uploadName;

    //서버에 저장되는 파일명
    private String storeName;

    public static UploadFile of(MultipartFile image){
        //업로드 이미지 이름
        String originalImageName = image.getOriginalFilename();
        //서버 저장용 식별자
        String uuid = UUID.randomUUID().toString();
        //이미지 확장자
        String ext = extractExt(originalImageName);

        return UploadFile.builder()
                .uploadName(originalImageName)
                .storeName(uuid+"."+ext)
                .build();
    }

    //확장자 추출
    private static String extractExt(String originalImageName){
        int pos = originalImageName.lastIndexOf(".");
        return originalImageName.substring(pos + 1);
    }
}
