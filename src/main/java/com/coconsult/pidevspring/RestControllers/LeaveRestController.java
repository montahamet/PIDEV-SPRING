package com.coconsult.pidevspring.RestControllers;

import com.coconsult.pidevspring.DAO.Entities.Leaves;
import com.coconsult.pidevspring.Services.ILeaveService;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

@RestController
@AllArgsConstructor
@RequestMapping("/leaves")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials="true")
public class LeaveRestController {
    ILeaveService iLeaveService;


    @GetMapping("/retrieve-leaves")
    public List<Leaves> retrieveAllLeave() {
        List<Leaves> Leaves= iLeaveService.retrieveAllLeave();
        return Leaves;
    }

    @PostMapping("/add-leave/{id}")
    public Leaves addLeave(@RequestBody Leaves Leaves,@PathVariable long id) {
        Leaves leaves1 = iLeaveService.addLeave(Leaves,id);

        return leaves1;
    }

    @PutMapping("/update-leave")
    public Leaves updateLeave(@RequestBody Leaves Leaves) {

        return iLeaveService.modifyLeave(Leaves);
    }
    @PutMapping("/aprouve-leave")
    public Leaves aprouveLeave(@RequestBody Leaves Leaves) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("https://graph.facebook.com/v18.0/321024171084396/messages"))
                    .header("Authorization", "Bearer EAAUGAtLGkHoBO4ZApXX2siWKskN0p5ZCvZAbeilTCBhdDPDS2Avhf1dmZCDnDVGLYMH1qC6mr47JsVMQEZB8XHYI8OGmV2p8ZAvDoeck0j76xfx0BKn3rnfSnulQZBQPajEoXUQRq1FWFDW7o3me3OZCZCG42Mkr3maRpAzoVwY8fXT0aGNptQLedOdyTqN5ybFYOXYZBJNeQLZCVyMLtihcaMZD")
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString("{ \"messaging_product\": \"whatsapp\", \"recipient_type\": \"individual\", \"to\": \"+21650378582\", \"type\": \"template\", \"template\": { \"name\": \"hello_world\", \"language\": { \"code\": \"en_US\" } } }"))
                    .build();
            HttpClient http = HttpClient.newHttpClient();
            HttpResponse<String> response = http.send(request,BodyHandlers.ofString());
            System.out.println(response.body());

        } catch (URISyntaxException | IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return iLeaveService.aprouveLeave(Leaves);
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


}
