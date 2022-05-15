package com.tank.springcloud.springbootclient.serivce;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MinioService {

    void existBucket(String name);

    Boolean makeBucket(String bucketName);

    Boolean removeBucket(String bucketName);

    List<String> upload(MultipartFile[] multipartFile);

    ResponseEntity<byte[]> download(String fileName);

    String preview(String fileName);
}
