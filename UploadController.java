package com.example.demo.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dao.UploadFileDao;

@Controller
public class UploadController {

    @Autowired
    private UploadFileDao uploadFileDao;

    @GetMapping("/upload")
    public String upload() {
        return "upload"; // 뷰 이름에서 슬래시 제거
    }

    @PostMapping("/upload")
    public String uploadPost(@RequestParam MultipartFile file,
                             @RequestParam String writer,
                             @RequestParam String desc) throws IOException {
        UUID uuid = UUID.randomUUID();
        String originalFileName = file.getOriginalFilename();
        String fileName = uuid + "_" + originalFileName; // 공백 대신 밑줄 사용
        String filePath = "C:/backend/demo/src/main/resources/file/"; // 경로에서 공백 제거
        String uploadFile = filePath + fileName;

        // 디렉토리 존재 여부 확인 및 생성
        File directory = new File(filePath);
        if (!directory.exists()) {
            directory.mkdirs(); // 디렉토리가 존재하지 않으면 생성
        }

        File saveFile = new File(uploadFile);
        file.transferTo(saveFile);

        uploadFileDao.insert(writer, desc, originalFileName, filePath, fileName);

        return "redirect:/file/list"; // 슬래시 추가
    }

    @GetMapping("/file/list")
    public String fileList(Model model) { // Model 객체를 매개변수로 받음
        List<Map<String, Object>> resultSet = uploadFileDao.select();
        model.addAttribute("resultSet", resultSet);
        return "file/list";
    }
}

    
    // @GetMapping("/upload2") public String upload2() { 
    //     return "upload2";
    // }
    // @PostMapping("/upload2") 
    // @ResponseBody 
    // public String upload2(@RequestParam("file") MultipartFile mFile) { 
    //     String result = "";
    //     String oName = mFile.getOriginalFilename();
    //     result += oName + "<br>" + mFile.getSize();
    //     return result;
    // }
    // @GetMapping("/upload3") public String upload3() { 
    //     return "upload3";
    // }
    // @PostMapping("/upload3") 
    // @ResponseBody 
    // public String upload3Post(@ModelAttribute FileInfo info) { 
    //     String result = "";
    //     String oName = info.getFile().getOriginalFilename();
    //     result += oName + "<br>" + info.getFile().getSize();
    //     return result;
    // }
    // @GetMapping("/upload4") public String upload4() { 
    //     return "upload4";
    // }
    // @PostMapping("/upload4") 
    // @ResponseBody 
    // public String upload4Post(@RequestParam("file") MultipartFile[] mFiles) { 
    //     String result = "";
    //     for(MultipartFile mFile : mFiles) { 
    //         String oName = mFile.getOriginalFilename();
    //         result += oName + " : " + mFile.getSize() + "<br>";
    //     } 
    //     return result;
    // }
    // @GetMapping("/upload5") 
    // public String upload5() { 
    //     return "upload5";
    //     }
    //     @PostMapping("/upload5") 
    //     @ResponseBody 
    //     public String upload5Post(@ModelAttribute FileInfo info) { 
    //         String result = "";
    //         for(MultipartFile mFile : info.getFiles()) { 
    //             String oName = mFile.getOriginalFilename();
    //             result += oName + " : " + mFile.getSize() + "<br>";
    //         } 
    //         return result;
    //     }
    //     @GetMapping("/upload6") public String upload6() { 
    //         return "upload6";
    //     }
    //     @PostMapping("/upload6") 
    //     @ResponseBody 
    //     public String upload6Post(MultipartHttpServletRequest mRequest) { 
    //         String result = "";
    //         Iterator<String> fileNames = mRequest.getFileNames();
    //         while(fileNames.hasNext()) { String fileName = fileNames.next();
    //             List<MultipartFile> mFiles = mRequest.getFiles(fileName);
    //             for(MultipartFile mFile : mFiles) { String oName = mFile.getOriginalFilename();
    //                 long size = mFile.getSize();
    //                 result += oName + " : " + size + "<br>";
    //             } 
    //         } 
    //         return result;
    //     }