package com.dina.todotogether.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class S3Service {

    private final AmazonS3 s3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public void UploadFile(MultipartFile mf) throws Exception {
        try {
            if(mf != null) {
                PutObjectResult saveFilePath = s3Client.putObject(new PutObjectRequest(bucket, mf.getOriginalFilename(), mf.getInputStream(), null)
                        .withCannedAcl(CannedAccessControlList.PublicRead));
                log.info("파일 업로드 된 URL : {} ", saveFilePath.toString());
            }
        }catch (IOException e) {
            e.printStackTrace();
            throw new Exception(" 파일 업로드 중 에러 발생");
        }
    }
}
