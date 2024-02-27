package com.coconsult.pidevspring.Services.ProjectModule;

import com.coconsult.pidevspring.DAO.Entities.Documentation;
import com.coconsult.pidevspring.DAO.Repository.ProjectModule.DocumentationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DocumentationService implements IDocumentationService {
    DocumentationRepository documentationRepository;

    @Override
    public List<Documentation> getAllDocs() {
        return documentationRepository.findAll();
    }

    @Override
    public Documentation findDocById(long id) {
        return documentationRepository.findById(id).get();
    }

    @Override
    public Documentation addDoc(Documentation doc) {
        return documentationRepository.save(doc);
    }

    @Override
    public Documentation updateDoc(Documentation doc) {
        return documentationRepository.save(doc);
    }



    @Override
    public void removeDocbyid(long id) {
        documentationRepository.deleteById(id);

    }

    @Override
    public void deleteDoc(Documentation doc) {
        documentationRepository.delete(doc);

    }
}
