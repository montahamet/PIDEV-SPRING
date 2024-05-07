package com.coconsult.pidevspring.Services;


import com.coconsult.pidevspring.DAO.Entities.Leaves;
import com.coconsult.pidevspring.DAO.Entities.User;
import com.coconsult.pidevspring.DAO.Repository.LeaveRepository;
import com.coconsult.pidevspring.DAO.Repository.User.UserRepository;
import lombok.AllArgsConstructor;
import okhttp3.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class LeaveService implements ILeaveService {

    LeaveRepository attendenceRepository;
    UserRepository userRepository ;
    @Override
    public List<Leaves> retrieveAllLeave() {
        return attendenceRepository.findLeavesByApproved(false);
    }

    @Override
    public Leaves retrieveLeave(Long attendenceId) {
        return attendenceRepository.findById(attendenceId).get();
    }

    @Override
    public Leaves addLeave(Leaves a, Long id) {
        User user = userRepository.findById(id).get();
        a.setEmployee(user);
        a.setApproved(false);


        return attendenceRepository.save(a);
    }

    @Override
    public void removeLeave(Long attendenceId) {
        attendenceRepository.deleteById(attendenceId);
    }

    @Override
    public Leaves modifyLeave(Leaves attendence) {

        Leaves attendence1=attendenceRepository.save(attendence);
        return attendence1;
    }

    @Override
    public Leaves aprouveLeave(Leaves attendence, Long id) {
        User user = userRepository.findById(id).get();
        attendence.setAdmin(user);
        attendence.setApproved(true);
        return  attendenceRepository.save(attendence);

    }
    public   void sendWhatsAppMessage(String phoneNumber, String message) throws Exception {
        RequestBody body = new FormBody.Builder()
                .add("token", "qpzu69svxgduw96a")
                .add("to", phoneNumber)
                .add("body", message)
                .build();

        Request request = new Request.Builder()
                .url("https://api.ultramsg.com/instance84126/messages/chat")
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
}
