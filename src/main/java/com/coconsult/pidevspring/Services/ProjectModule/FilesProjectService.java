package com.coconsult.pidevspring.Services.ProjectModule;

import org.springframework.web.multipart.MultipartFile;
import java.nio.file.Path;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
public interface FilesProjectService {
    public void init();

    public void save(MultipartFile file);

    public Resource load(String filename);

    public void deleteAll();

    public Stream<Path> loadAll();
    public boolean delete(String filename);


}
