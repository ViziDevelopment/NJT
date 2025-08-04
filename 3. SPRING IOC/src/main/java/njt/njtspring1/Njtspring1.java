/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package njt.njtspring1;

import njt.njtspring1.config.AppConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 * @author vanja
 */
public class Njtspring1 {

    public static void main(String[] args) {
        
        ApplicationContext ctx =
                new AnnotationConfigApplicationContext(AppConfig.class);
        
        NotificationService service = ctx.getBean(NotificationService.class);
        service.sendNotification("Dobrodošli na sistem!");
        
       /* MessageSender sender = new EmailSender();
        NotificationService service = new NotificationService(sender);
        service.sendNotification("Dobrodošli na sistem!");*/
        
    }
}
