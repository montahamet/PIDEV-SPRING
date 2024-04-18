package com.coconsult.pidevspring.Services.ProjectModule;

import com.coconsult.pidevspring.DAO.Entities.Documentation;
import com.coconsult.pidevspring.DAO.Repository.ProjectModule.DocumentationRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@AllArgsConstructor
public class DocumentationService implements IDocumentationService {
    @Autowired

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
    public Documentation addDoc(Documentation doc, MultipartFile rapportFile, MultipartFile specficationDoc) {
        try {
            if (rapportFile != null && !rapportFile.isEmpty() && specficationDoc != null && !specficationDoc.isEmpty()) {
                // Vérifier que les fichiers sont des PDF
                if (!rapportFile.getContentType().equals(MediaType.APPLICATION_PDF_VALUE) ||
                        !specficationDoc.getContentType().equals(MediaType.APPLICATION_PDF_VALUE)) {
                    throw new IllegalArgumentException("Les fichiers doivent être de type PDF.");
                }

                // Récupérer les noms des fichiers PDF
                String rapportFileName = rapportFile.getOriginalFilename();
                String specificationDocFileName = specficationDoc.getOriginalFilename();

                // Enregistrer les fichiers sur le disque
                String rapportFilePath = "C:/Users/Admin/Desktop/" + rapportFileName;
                String specificationDocFilePath = "C:/Users/Admin/Desktop/" + specificationDocFileName;

                // Enregistrer les chemins des fichiers dans l'objet Documentation
                doc.setRapport(rapportFilePath);
                doc.setSpecficationDoc(specificationDocFilePath);

                // Enregistrer l'objet Documentation dans la base de données
                return documentationRepository.save(doc);
            } else {
                throw new IllegalArgumentException("Les fichiers PDF sont obligatoires.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
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
