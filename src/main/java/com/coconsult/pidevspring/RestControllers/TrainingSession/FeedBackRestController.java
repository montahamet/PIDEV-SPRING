package com.coconsult.pidevspring.RestControllers.TrainingSession;

import com.coconsult.pidevspring.DAO.Entities.FeedBack;
import com.coconsult.pidevspring.Services.TrainingSession.IFeedBackService;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/FeedBack-TrainingSession")
public class FeedBackRestController {
    IFeedBackService iFeedBackService;

    @GetMapping("/findOneFeedBack")
    public FeedBack findOneFeedBack(@PathParam("feedback_id") Long feedback_id){
        return iFeedBackService.findOneFeedBack(feedback_id);
    }
    @GetMapping("/findAllFeedBacks")
    public List<FeedBack> findAllFeedBack() {
        return  iFeedBackService.findAllFeedBack();
    }
    @PostMapping("/addFeedBack")
    public  FeedBack addFeedBack(@RequestBody FeedBack feedBack) {
        return iFeedBackService.addFeedBack(feedBack);
    }
    @PostMapping("/updateFeedBack")
    public FeedBack updateFeedBack(@RequestBody FeedBack feedBack){
        return iFeedBackService.updateFeedBack(feedBack);
    }
    @DeleteMapping("/deleteFeedBack")
    public void  deleteFeedBack(FeedBack feedBack){
        iFeedBackService.deleteFeedBack(feedBack);
    }

}
