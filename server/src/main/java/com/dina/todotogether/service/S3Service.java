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
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class S3Service {

    private final AmazonS3 s3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public String UploadFile(MultipartFile mf, String folder) throws Exception {
        String uploadDate = LocalDate.now().toString();
        String folderPath = folder + "/" + uploadDate;

        String uuid = UUID.randomUUID().toString();
        String originalFilename = mf.getOriginalFilename();
        String uploadFilename = String.format("%s_%s", uuid, originalFilename.replaceAll(" ", ""));

        String uploadFilePath = folderPath + "/" + uploadFilename;
        try {
                PutObjectResult saveFilePath = s3Client.putObject(new PutObjectRequest(bucket, uploadFilePath, mf.getInputStream(), null)
                        .withCannedAcl(CannedAccessControlList.PublicRead));

        } catch (IOException e) {
            e.printStackTrace();
            throw new Exception("파일 업로드 중 에러 발생");
        }
        return uploadFilePath;
    }
}
