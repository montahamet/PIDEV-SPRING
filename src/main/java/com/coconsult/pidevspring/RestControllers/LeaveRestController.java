package com.coconsult.pidevspring.RestControllers;

import com.coconsult.pidevspring.DAO.Entities.Leaves;
import com.coconsult.pidevspring.Security.payload.request.LeaveRequestDTO;
import com.coconsult.pidevspring.Services.ILeaveService;
import com.coconsult.pidevspring.Services.LeaveService;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import okhttp3.FormBody;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import okhttp3.*;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@AllArgsConstructor
@RequestMapping("/leaves")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials="true")
public class LeaveRestController {
    ILeaveService iLeaveService;
    LeaveService leaveService ;


    @GetMapping("/retrieve-leaves")
    public List<Leaves> retrieveAllLeave() {
        List<Leaves> Leaves= iLeaveService.retrieveAllLeave();
        return Leaves;
    }

    @PostMapping("/add-leave/{id}")
    public Leaves addLeave(@RequestBody Leaves Leaves, @PathVariable long id) {
        Leaves leaves1 = iLeaveService.addLeave(Leaves,id);

        return leaves1;
    }

    @PutMapping("/update-leave")
    public Leaves updateLeave(@RequestBody Leaves Leaves) {

        return iLeaveService.modifyLeave(Leaves);
    }
    @PutMapping("/aprouve-leave/{id}")
    public Leaves aprouveLeave(@RequestBody Leaves Leaves,@PathVariable long id) throws Exception {
//        try {
//            HttpRequest request = HttpRequest.newBuilder()
//                    .uri(new URI("https://graph.facebook.com/v18.0/321024171084396/messages"))
//                    .header("Authorization", "Bearer EAAUGAtLGkHoBO4ZApXX2siWKskN0p5ZCvZAbeilTCBhdDPDS2Avhf1dmZCDnDVGLYMH1qC6mr47JsVMQEZB8XHYI8OGmV2p8ZAvDoeck0j76xfx0BKn3rnfSnulQZBQPajEoXUQRq1FWFDW7o3me3OZCZCG42Mkr3maRpAzoVwY8fXT0aGNptQLedOdyTqN5ybFYOXYZBJNeQLZCVyMLtihcaMZD")
//                    .header("Content-Type", "application/json")
//                    .POST(HttpRequest.BodyPublishers.ofString("{ \"messaging_product\": \"whatsapp\", \"recipient_type\": \"individual\", \"to\": \"+21650378582\", \"type\": \"template\", \"template\": { \"name\": \"hello_world\", \"language\": { \"code\": \"en_US\" } } }"))
//                    .build();
//            HttpClient http = HttpClient.newHttpClient();
//            HttpResponse<String> response = http.send(request,BodyHandlers.ofString());
//            System.out.println(response.body());
//
//        } catch (URISyntaxException | IOException | InterruptedException e) {
//            e.printStackTrace();
//        }
        this.sendWhatsAppMessage("50378582", "test");
        return iLeaveService.aprouveLeave(Leaves,id);
    }


    @GetMapping("/retrieve-leave/{LeaveId}")
    public Leaves retrieveProjectOffer(@PathVariable Long LeaveId) {
        Leaves Leaves = iLeaveService.retrieveLeave(LeaveId);
        return Leaves;
    }

    @DeleteMapping("/remove-leave/{LeaveId}")
    public void removeLeave(@PathVariable Long LeaveId) {
        iLeaveService.removeLeave(LeaveId);
    }
    public   void sendWhatsAppMessage(String phoneNumber, String message) throws Exception {
        okhttp3.RequestBody body = new FormBody.Builder()
                .add("token", "m4m4lol4paukmt1g")
                .add("to", phoneNumber)
                .add("body", message)
                .build();

        Request request = new Request.Builder()
                .url("https://api.ultramsg.com/instance85109/messages/chat")
                .post(body)
                .addHeader("content-type", "application/x-www-form-urlencoded")
                .build();

        OkHttpClient client = new OkHttpClient();
        Response response = client.newCall(request).execute();

        if (!response.isSuccessful()) {
            throw new IOException("Erreur lors de l'envoi du message WhatsApp: " + response);
        }

        response.close();
    }
    @GetMapping("leaves/{userId}/{startDate}/{endDate}")
    public LeaveRequestDTO getLeaveRequestDetails(@PathVariable Long userId,@PathVariable LocalDate startDate,@PathVariable LocalDate endDate){
        return iLeaveService.getLeaveRequestDetails(userId,startDate,endDate);
    }
}