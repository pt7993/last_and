package com.testcode.yjp.last.service;

import com.testcode.yjp.last.domain.Files;
import com.testcode.yjp.last.repository.FilesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FilesService {

    private final FilesRepository filesRepository;

    public void save(Files files) {
        Files f = new Files();
        f.setFilename(files.getFilename());
        f.setFileOriName(files.getFileOriName());
        f.setFileurl(files.getFileurl());

        filesRepository.save(f);
    }

    public Files findByFno(int i) {
        return filesRepository.findByFno(i);
    }
}
