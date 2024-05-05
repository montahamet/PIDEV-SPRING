package com.coconsult.pidevspring.RestControllers;

import com.coconsult.pidevspring.DAO.Entities.Screenshot;
import com.coconsult.pidevspring.Services.ScreenshotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
@RequestMapping("/api/screenshots")
public class ScreenshotController {

    @Autowired
    private ScreenshotService screenshotService;

    @PostMapping("/{userId}")
    public ResponseEntity<String> captureScreenshot(@PathVariable Long userId, @RequestParam("image") MultipartFile image) {
        try {
            byte[] imageData = image.getBytes();
            screenshotService.captureScreenshot(userId, imageData);
            return ResponseEntity.ok("Screenshot captured and saved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to capture and save screenshot.");
        }
    }
//    @GetMapping("/{userId}")
//    public List<String> getScreenshotsByUserId(@PathVariable Long userId) {
//        List<Screenshot> screenshots = screenshotService.getScreenshotsByUserId(userId);
//        List<String> filePaths = new ArrayList<>();
//        for (Screenshot screenshot : screenshots) {
//            filePaths.add(screenshot.getFilePath());
//        }
//        return filePaths;
//    }
@GetMapping("/{userId}")
public List<String> getScreenshotsUrlsByUserId(@PathVariable Long userId) {
    return screenshotService.getScreenshotsUrlsByUserId(userId);
}




}
