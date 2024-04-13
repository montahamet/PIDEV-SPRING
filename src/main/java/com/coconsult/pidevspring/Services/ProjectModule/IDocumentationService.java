package com.coconsult.pidevspring.Services.ProjectModule;

import com.coconsult.pidevspring.DAO.Entities.Documentation;
import com.coconsult.pidevspring.DAO.Entities.Invoice;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IDocumentationService {
    List<Documentation> getAllDocs();
    Documentation findDocById(long id);

    Documentation addDoc(Documentation doc, MultipartFile rapportFile, MultipartFile specficationDocFile);

    Documentation updateDoc(Documentation doc);

    void removeDocbyid(long id);
    void deleteDoc(Documentation doc);

}
