/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package njt.njtspring1;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 *
 * @author vanja
 */
@Component
@Primary
public class EmailSender implements MessageSender {

    @Override
    public void sendMessage(String message) {
        //
       System.out.println("Email poslat korisniku: " + message);
    }
    
}
