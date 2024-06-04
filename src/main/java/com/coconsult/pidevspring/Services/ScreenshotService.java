package com.coconsult.pidevspring.Services;

import com.coconsult.pidevspring.DAO.Entities.User;
import com.coconsult.pidevspring.DAO.Repository.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.coconsult.pidevspring.DAO.Entities.Screenshot;
import com.coconsult.pidevspring.DAO.Repository.ScreenshotRepository;
import com.coconsult.pidevspring.Services.FileStorageService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ScreenshotService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ScreenshotRepository screenshotRepository;

    @Autowired
    private FileStorageService fileStorageService;

    public void captureScreenshot(Long userId, byte[] imageData) {
        // Générer un nom de fichier unique pour l'image
        String fileName = generateUniqueFileName();
        User user= userRepository.findById(userId).get();

        try {
            // Enregistrer les données d'image dans le système de fichiers
            String filePath = fileStorageService.storeFile(fileName, imageData);

            // Créer un objet Screenshot pour enregistrer les détails de la capture d'écran
            Screenshot screenshot = new Screenshot();
            screenshot.setUserId(userId);
            screenshot.setUser(user);
            screenshot.setFileName(fileName);
            screenshot.setFilePath(filePath);

            // Enregistrer la capture d'écran dans la base de données
            screenshotRepository.save(screenshot);
        } catch (IOException e) {
            // Gérer l'exception en conséquence
            e.printStackTrace();
            // Vous pouvez lancer une exception personnalisée ou effectuer toute autre action appropriée ici
        }
    }

    private String generateUniqueFileName() {
        // Générer un nom de fichier unique selon vos besoins
        return UUID.randomUUID().toString() + ".png";
    }

//    public List<Screenshot> getScreenshotsByUserId(Long userId) {
//        return screenshotRepository.findByUserId(userId);
//    }
// ScreenshotService.java
public List<String> getScreenshotsUrlsByUserId(Long userId) {
    List<Screenshot> screenshots = screenshotRepository.findByUserId(userId);
    List<String> urls = new ArrayList<>();
    for (Screenshot screenshot : screenshots) {
        // Assuming you have a method to construct the URL for the images
        String imageUrl = constructImageUrl(screenshot.getFileName());
        urls.add(imageUrl);
    }
    return urls;
}
// Assuming your images are stored in a directory accessible via a URL, for example:
// http://localhost:8082/images/

    private String constructImageUrl(String fileName) {
        // Assuming your XAMPP server is running on localhost and using port 80
        String baseUrl = "http://localhost:80/Uploads/Attendances/";

        // Append the filename to the base URL to form the complete image URL
        return baseUrl + fileName;
    }



//comparison part  :
    

}

