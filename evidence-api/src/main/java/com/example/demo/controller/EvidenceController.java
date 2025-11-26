package com.example.demo.controller;

import java.util.Map;

import org.apache.catalina.connector.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.service.DatabaseFileService;

@RestController
@RequestMapping("/api/v1/evidence")
public class EvidenceController {
    
    private static Logger logger = LoggerFactory.getLogger(EvidenceController.class);
    private DatabaseFileService databaseFileService = new DatabaseFileService(); 
    
    @ResponseStatus(code = HttpStatus.OK)
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> upload(
        @RequestPart(value = "evidence") 
        MultipartFile file
    ) {
            System.out.println("cheguei aqui");

            var name = file.getOriginalFilename();
            var size = file.getSize();
            var contentType = file.getContentType();

            logger.info("Upload file {} size {} contentType {}", name, size, contentType);

            // databaseFileService.upload("nomee", "contentType", file.setBytes);

            return ResponseEntity.ok(Map.of(
                "name", name,
                "size", size,
                "contentType", contentType
            ));
    }
}
