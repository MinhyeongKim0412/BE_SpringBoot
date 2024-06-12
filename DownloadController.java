// package com.example.demo.controller;

// import org.apache.catalina.util.URLEncoder;
// import org.springframework.boot.autoconfigure.ssl.SslProperties.Bundles.Watch.File;
// import org.springframework.core.io.InputStreamResource;
// import org.springframework.http.ResponseEntity;
// import org.springframework.stereotype.Controller;
// import org.springframework.web.bind.annotation.GetMapping;

// @Controller public class DownloadController {
//     @GetMapping("/download") 
//     public ResponseEntity<Resource> download() throws Exception { 
//         File file = new File("c:/study/lake.jpg");
//         InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
//         return ResponseEntity.ok() 
//         .header("content-disposition", "filename=" + URLEncoder
//         .encode(file.getName(), "utf-8")) 
//         .contentLength(file.length()) 
//         .contentType(MediaType
//         .parseMediaType("application/octet-stream")) 
//         .body(resource);
//     }
//  }
