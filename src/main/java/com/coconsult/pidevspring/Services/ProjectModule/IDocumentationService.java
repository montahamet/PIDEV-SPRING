package com.coconsult.pidevspring.Services.ProjectModule;

import com.coconsult.pidevspring.DAO.Entities.Documentation;
import com.coconsult.pidevspring.DAO.Entities.Invoice;

import java.util.List;

public interface IDocumentationService {
    List<Documentation> getAllDocs();
    Documentation findDocById(long id);

    Documentation addDoc(Documentation doc);

    Documentation updateDoc(Documentation doc);

    void removeDocbyid(long id);
    void deleteDoc(Documentation doc);
}
