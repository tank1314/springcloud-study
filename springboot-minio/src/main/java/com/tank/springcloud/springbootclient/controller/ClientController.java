package com.tank.springcloud.springbootclient.controller;

import com.tank.springcloud.springbootclient.serivce.MinioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class ClientController {


    @Autowired
    private MinioService minioService;


    public String getAddNum(@RequestParam Integer a, @RequestParam Integer b) {
        return a + "-----" + b + "服务熔断...";
    }

    @PostMapping(value = "/upload")
    public String uploadFile(MultipartFile[] files) {
        List<String> upload = minioService.upload(files);
        return String.join(",", upload);
    }

    @PostMapping(value = "/download")
    public ResponseEntity download(String fileName) {
        ResponseEntity<byte[]> download = minioService.download(fileName);
        return download;
    }

    /**
     * ßß
     *
     * @param fileName
     * @return
     */
    @GetMapping(value = "/review")
    public String review(String fileName) {
        String download = minioService.preview(fileName);
        return download;
    }

}
