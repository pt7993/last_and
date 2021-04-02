package com.testcode.yjp.last.controller;

import com.testcode.yjp.last.domain.Files;
import com.testcode.yjp.last.repository.FilesRepository;
import com.testcode.yjp.last.service.FilesService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

@Controller
@RequiredArgsConstructor
public class FilesController {

    private final FilesService filesService;
    private final FilesRepository filesRepository;

    @GetMapping("/file/look")
    public String index(Model model) {
        model.addAttribute("file", filesRepository.findByFno(2));
        return "file/look";
    }

    @GetMapping("/file/insert")
    public String Insert() {

        return "file/insert";
    }

    @PostMapping("/file/fileinsert")
    public String fileinsert(HttpServletRequest request, @RequestPart MultipartFile files) throws Exception{
        Files file = new Files();

        String sourceFileName = files.getOriginalFilename();
        String sourceFileNameExtension = FilenameUtils.getExtension(sourceFileName).toLowerCase();
        File destinationFile;
        String destinationFileName;
        String fileUrl = "C:/Users/Bon_300-13/IdeaProjects/DaGymTest/src/main/resources/static/image/";
        // mung-1은 자기 프로젝트이름으로 체인지!!

        do {
            destinationFileName = RandomStringUtils.randomAlphanumeric(32) + "." + sourceFileNameExtension;
            destinationFile = new File(fileUrl + destinationFileName);
        } while (destinationFile.exists());

        destinationFile.getParentFile().mkdirs();
        files.transferTo(destinationFile);

        file.setFilename(destinationFileName);
        file.setFileOriName(sourceFileName);
        file.setFileurl(fileUrl);
        filesService.save(file);
        return "redirect:/file/insert";
    }

}
