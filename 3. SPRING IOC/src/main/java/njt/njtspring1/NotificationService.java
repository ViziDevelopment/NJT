/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package njt.njtspring1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author vanja
 */
@Service
public class NotificationService {
    @Autowired
    private MessageSender messageSender;
    public NotificationService(MessageSender messageSender) {
        this.messageSender = messageSender;
    }
    public void sendNotification(String message) { 
        messageSender.sendMessage(message);
    }
}
