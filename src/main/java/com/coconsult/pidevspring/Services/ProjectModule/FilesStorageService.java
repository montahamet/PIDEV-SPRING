package com.coconsult.pidevspring.Services.ProjectModule;

import com.coconsult.pidevspring.DAO.Entities.FileInfodoc;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.Path;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
public interface FilesStorageService {
    public void init();

    public void save(MultipartFile file);

    public Resource load(String filename);

    public void deleteAll();

    public Stream<Path> loadAll();
    public boolean delete(String filename);


}
